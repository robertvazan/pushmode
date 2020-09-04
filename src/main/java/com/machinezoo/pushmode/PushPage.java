// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode;

import java.security.*;
import java.util.*;
import java.util.concurrent.*;
import org.slf4j.*;
import com.machinezoo.hookless.*;
import com.machinezoo.hookless.servlets.*;
import com.machinezoo.hookless.util.*;
import com.machinezoo.pushmode.dom.*;
import com.machinezoo.pushmode.internal.diffing.*;
import com.machinezoo.pushmode.internal.messages.*;
import com.machinezoo.stagean.*;
import io.micrometer.core.instrument.*;
import io.micrometer.core.instrument.Timer;

/**
 * Root of server-side object graph corresponding to some currently viewed page.
 * PushMode is a stateful web framework. All currently open pages in browsers of all users have corresponding server-side state.
 * This state is stored in multiple server-side objects and {@link PushPage} is the root of page's server-side object graph.
 * <p>
 * The recommended practice is to define one derived class for every distinct page on the site.
 * It is often desirable to create one or more "master" pages and then derive specialized pages from them.
 * Every derived class must at the very minimum define {@link #document()} method.
 * <p>
 * Whenever the user visits some page, corresponding {@link PushPage} instance is created.
 * Instances of {@link PushPage} live for as long as the user keeps the page open (i.e. while keepalive messages keep coming)
 * and some time after that to account for temporary connection loss.
 * <p>
 * Every {@link PushPage} has an associated {@link PageExecutor} that can be obtained from {@link #executor()} method.
 * All page renders (executions of {@link PushPage#document()}), DOM events, and DOM bindings are scheduled on the {@link PageExecutor}.
 */
public abstract class PushPage {
	private static final Logger logger = LoggerFactory.getLogger(PushPage.class);
	private ReactiveServletRequest request;
	/**
	 * Initial request that opened this page.
	 * <p>
	 * This method only reads immutable data.
	 * 
	 * @return Page's initial HTTP request.
	 */
	public ReactiveServletRequest request() {
		return request;
	}
	private final String pageId;
	/**
	 * Globally unique page ID. Page IDs are produced by secure cryptographic random number generator. They never repeat.
	 * <p>
	 * This method only reads immutable data.
	 * 
	 * @return Page ID as a string matching regex [a-zA-Z0-9]+.
	 */
	public String pageId() {
		return pageId;
	}
	private final ElementIdIndex elementIds = new ElementIdIndex();
	private final ExecutorService executor = new PageExecutor(this);
	/**
	 * Page's event loop. All page renders and all DOM events and bindings run in this event loop.
	 * <p>
	 * This method only reads immutable data.
	 * 
	 * @return Page's event loop.
	 */
	@DraftApi("some way to specify the underlying thread pool")
	public ExecutorService executor() {
		return executor;
	}
	/**
	 * Renders the page, i.e. generates DOM tree of the page.
	 * Override this method to define HTML content of the page.
	 * <p>
	 * This method is called as part of page's reactive computation, i.e. its dependencies are tracked and it is re-run every time its dependencies change.
	 * <p>
	 * This method is run by page's {@link #executor()}.
	 * 
	 * @return Page's DOM tree with {@code <html>} root tag.
	 */
	public abstract DomElement document();
	/**
	 * Creates new {@link PushPage}.
	 * {@link PushPage} is an abstract class. You have to derive from it in order to instantiate it.
	 */
	protected PushPage() {
		pageId = randomId();
		OwnerTrace.of(this)
			.alias("page")
			.tag("id", pageId);
	}
	private static final SecureRandom random = new SecureRandom();
	private static String randomId() {
		byte[] bytes = new byte[30];
		random.nextBytes(bytes);
		return Base64.getUrlEncoder().encodeToString(bytes).replace("_", "").replace("-", "").replace("=", "");
	}
	@NoDocs
	public void serve(ReactiveServletRequest request) {
		this.request = request;
		OwnerTrace.of(this).tag("http.url", request.url());
	}
	@NoDocs
	public void serve(ReactiveServletResponse response) {
	}
	private final ReactiveVariable<Boolean> poster = OwnerTrace.of(new ReactiveVariable<Boolean>(true))
		.parent(this)
		.tag("role", "poster")
		.target();
	/**
	 * Checks whether currently rendered frame is a poster frame.
	 * <p>
	 * Poster frame is sent as vanilla HTML to the browser in response to initial HTTP request.
	 * Usually only the first frame of the page is a poster frame, but draft frames may cause the poster frame to be rendered several times.
	 * <p>
	 * Application code can call this method from within {@link PushPage#document()} method.
	 * It is useful for rendering slightly different content in the poster frame, perhaps to deal with bots or to enable events in a more controlled fashion.
	 * <p>
	 * This method accesses reactive data. It will cause reactive computations to re-evaluate when the reactive data changes.
	 * <p>
	 * This method is safe to call from any thread.
	 * 
	 * @return True if rendering poster frame.
	 */
	public boolean poster() {
		return poster.get();
	}
	@NoDocs
	@Override
	public String toString() {
		return request.toString();
	}
	private static final Timer diffTime = Metrics.timer("pushmode.diffing");
	private final ReactiveVariable<Long> inputSeq = OwnerTrace.of(new ReactiveVariable<>(0L))
		.parent(this)
		.tag("role", "inputSeq")
		.target();
	private final ReactiveVariable<Long> pollSeq = OwnerTrace.of(new ReactiveVariable<>(-1L))
		.parent(this)
		.tag("role", "pollSeq")
		.target();
	private final ReactiveThread loop = OwnerTrace
		.of(new ReactiveThread(this::run)
			.executor(executor)
			.daemon(true))
		.parent(this)
		.target();
	private final ReactiveVariable<PageFrame> output = OwnerTrace.of(new ReactiveVariable<>(new PageFrame(this)))
		.parent(this)
		.tag("role", "output")
		.target();
	@NoDocs
	public void start() {
		loop.start();
	}
	void handle(long inputSeq, List<ListenerMessage> messages) {
		executor.execute(() -> {
			if (inputSeq < this.inputSeq.get())
				return;
			if (inputSeq > this.inputSeq.get())
				throw new IllegalArgumentException("Input events submitted out of order.");
			/*
			 * We don't care that we receive events before first frame is generated (which is always an error),
			 * because elementIds below is empty in that case anyway, so no event handlers will actually run.
			 */
			for (ListenerMessage message : messages)
				dispatch(message);
			this.inputSeq.set(this.inputSeq.get() + messages.size());
		});
	}
	private void dispatch(ListenerMessage message) {
		List<DomElement> elements = elementIds.get(message.elementId());
		if (elements.isEmpty())
			return;
		if (elements.size() > 1) {
			logger.error("Duplicate element ID {}", message.elementId());
			return;
		}
		for (DomListener listener : elements.get(0).listeners())
			message.match(listener);
	}
	@NoDocs
	@DraftApi("mind testability")
	public PageFrame frame(long requestSeq) {
		if (requestSeq < 0)
			throw new IllegalArgumentException();
		/*
		 * This will propagate exception that terminated the page if there was one.
		 */
		PageFrame latest = output.get();
		if (latest.outputSeq() == requestSeq)
			return latest;
		else if (latest.outputSeq() + 1 == requestSeq) {
			synchronized (this) {
				if (requestSeq > pollSeq.get())
					pollSeq.set(requestSeq);
			}
			CurrentReactiveScope.block();
			return latest;
		} else {
			if (requestSeq == 0) {
				/*
				 * Some kind of page reload or illegal caching. Happens all the time. Don't bother to log it.
				 */
				return null;
			} else {
				logger.debug("Polled for sequence {} while at sequence {}, likely page cloning, reloading affected pages", requestSeq, latest.outputSeq());
				return null;
			}
		}
	}
	private final ReactiveStateMachine<PageFrame> generator = OwnerTrace
		.of(ReactiveStateMachine.supply(this::refresh))
		.parent(this)
		.target();
	private final ReactiveVariable<Long> generatorSeq = OwnerTrace.of(new ReactiveVariable<>(-1L))
		.parent(this)
		.tag("role", "generatorSeq")
		.target();
	private void run() {
		try {
			/*
			 * If there was an exception before, then the loop was stopped and we couldn't have arrived here again.
			 * That means reading output below cannot ever throw an exception.
			 */
			PageFrame previous = output.get();
			/*
			 * Don't do anything unless we have pending frame request from the client.
			 */
			if (previous.outputSeq() + 1 == pollSeq.get()) {
				/*
				 * When client asks for new frame, it's a confirmation that it has consumed the previous frame.
				 * We can thus let the generator advance, but only if it has been already invalidated.
				 */
				if (generatorSeq.get() < pollSeq.get() && !generator.valid()) {
					generator.advance();
					ReactiveValue<PageFrame> generated = generator.output();
					/*
					 * The generator has now produced new frame, but we will accept it only if it doesn't block.
					 * Otherwise we will just wait for the generator to be invalidated and we will advance it again.
					 */
					if (!generated.blocking())
						generatorSeq.set(generatorSeq.get() + 1);
				}
				/*
				 * If the generator has a frame ready, send it. Ready means it has the right sequence and it is not blocking.
				 * We don't care whether it was already invalidated or not. We will take stale frame if needed.
				 */
				if ((long)generatorSeq.get() == (long)pollSeq.get() && !generator.output().blocking()) {
					/*
					 * If page rendering ended with an exception, the exception will be propagated from here.
					 */
					PageFrame next = generator.output().get();
					if (previous.outputSeq() < 0) {
						elementIds.seed(next.document());
						next.outputSeq(0);
					} else {
						Timer.Sample sample = Timer.start(Clock.SYSTEM);
						DocumentPatch patch = new DocumentPatch();
						patch.compareDocumentRoots(previous.document(), next.document());
						sample.stop(diffTime);
						boolean substantial = previous.inputSeq() != next.inputSeq() || patch.isSubstantial();
						patch.updateIds(elementIds);
						if (substantial) {
							next
								.outputSeq(previous.outputSeq() + 1)
								.patch(patch);
						} else {
							next
								.outputSeq(previous.outputSeq())
								.patch(previous.patch());
							/*
							 * If the frame is not being sent to the client, force production of new frame.
							 * We aren't advancing any client sequences, because nothing happened from client's point of view.
							 */
							generatorSeq.set(generatorSeq.get() - 1);
						}
					}
					output.set(next);
					poster.set(false);
				}
			}
		} catch (Throwable ex) {
			/*
			 * We aren't going to attempt any sort of recovery, because exception caught here means serious flaw in the app.
			 * Apps (or app frameworks) should have their own exception handler that displays nice in-page message.
			 * 
			 * The code below will disable further rendering and thus all HTML streaming.
			 * Event handling will continue as well as everything else using page executor.
			 * Halting page executor might get the program into extreme error conditions that are unlikely to be handled well.
			 * Merely disabling streaming on our side is safe. Apps should never require continuous rendering for internal consistency.
			 */
			loop.stop();
			/*
			 * This will cause servlets to fail with 5xx error codes. This is especially important for the initial request.
			 */
			output.value(new ReactiveValue<>(ex));
			logger.error("Page was killed by exception.", ex);
		}
	}
	long inputSeq() {
		return inputSeq.get();
	}
	private static final Timer renderTime = Metrics.timer("pushmode.rendering");
	private PageFrame refresh() {
		Timer.Sample sample = Timer.start(Clock.SYSTEM);
		/*
		 * We are freezing the DOM tree here in order to simplify diffing.
		 * Diffing can then assume all arrays have been compacted.
		 * This is probably less efficient than checking for null terminators in diff code.
		 * We might later switch to diffing unfrozen trees and suffer the cost of null checking instead.
		 */
		DomElement document = document().freeze();
		document = poster() ? document.toPoster() : document;
		sample.stop(renderTime);
		return new PageFrame(this)
			.document(document)
			.inputSeq(inputSeq());
	}
	@NoDocs
	public static PushPage current() {
		PageExecutor executor = PageExecutor.current();
		return executor != null ? executor.page : null;
	}
}
