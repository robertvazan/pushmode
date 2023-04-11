// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode;

import static java.util.stream.Collectors.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import org.slf4j.*;
import com.machinezoo.hookless.*;
import com.machinezoo.noexception.*;
import com.machinezoo.noexception.slf4j.*;
import com.machinezoo.stagean.*;

/**
 * Event loop where {@link PushPage} executes.
 * Every {@link PushPage} instance has one associated event loop that can be obtained from {@link PushPage#executor()} method.
 * All page renders (executions of {@link PushPage#document()}), DOM events, and DOM bindings are scheduled on {@link PageExecutor}.
 * Event loops of different page instances are unrelated. Pages execute concurrently with each other.
 * <p>
 * {@link PageExecutor} processes one event at a time.
 * Every event might run on a different thread, because events are scheduled on a thread pool,
 * but {@link PageExecutor} makes sure no two events execute at the same time.
 * From application's point of view, all page-related code appears to run in a single thread.
 * It is therefore unnecessary to implement any synchronization on page level.
 * Thread-local variables still work, but only within one event.
 * Thread-local variables cannot maintain state across several events.
 * <p>
 * {@link PageExecutor} implements {@link ExecutorService} interface.
 * {@link ExecutorService} defines convenient methods that allow scheduling custom application events on the event loop.
 * Any 3rd party code can be forced to run in the event loop as far as it supports configurable {@link ExecutorService}.
 * 
 * The thread pool is compute-optimized and applications should refrain from running blocking operations in it.
 * All I/O operations should instead go through appropriate non-blocking reactive APIs.
 */
@DraftCode("remove the old javadoc")
class PageExecutor implements ExecutorService {
	final PushPage page;
	private boolean executing;
	private final Queue<Runnable> queue = new ArrayDeque<>();
	private static final ThreadLocal<PageExecutor> current = new ThreadLocal<>();
	private static final Logger logger = LoggerFactory.getLogger(PageExecutor.class);
	PageExecutor(PushPage page) {
		this.page = page;
	}
	/**
	 * Finds currently running {@link PageExecutor}.
	 * If the calling method runs within context of an event scheduled on {@link PageExecutor}, this method will return the {@link PageExecutor}.
	 * Reference to {@link PageExecutor} can then be used to obtain reference to the associated {@link PushPage} via {@link #page()} method.
	 * 
	 * @return Currently running {@link PageExecutor} or {@code null}.
	 * @see #page()
	 */
	static PageExecutor current() {
		return current.get();
	}
	private void schedule() {
		if (!executing && !queue.isEmpty()) {
			executing = true;
			Runnable task = queue.remove();
			ReactiveExecutor.common().execute(() -> {
				current.set(this);
				ExceptionLogging.log(logger).run(task);
				current.remove();
				synchronized (this) {
					executing = false;
					schedule();
				}
			});
		}
	}
	/**
	 * Schedules {@link Runnable} on this {@link PageExecutor}.
	 * The supplied {@link Runnable} will run in page's event loop.
	 * If the {@link Runnable} throws an exception, page's event loop will stop and browser connection will be terminated.
	 * 
	 * @param runnable
	 *            {@link Runnable} to schedule.
	 * @x.threads This method is safe to call from any thread.
	 *            The supplied {@link Runnable} will run in the event loop, which means it doesn't need to synchronize access with other events in the same event loop.
	 * @see PageExecutor#submit(Runnable)
	 */
	@Override
	public synchronized void execute(Runnable runnable) {
		Objects.requireNonNull(runnable);
		queue.add(runnable);
		schedule();
	}
	/**
	 * Schedules {@link Callable} on this {@link PageExecutor}.
	 * The supplied {@link Callable} will run in page's event loop.
	 * 
	 * @param task
	 *            {@link Callable} to schedule.
	 * @return {@link CompletableFuture} that will complete normally with result of the supplied {@link Callable} or exceptionally with exception thrown by the {@link Callable}.
	 * @x.threads This method is safe to call from any thread.
	 *            The supplied {@link Callable} will run in the event loop, which means it doesn't need to synchronize access with other events in the same event loop.
	 * @see PageExecutor#submit(Runnable)
	 */
	@Override
	public <T> CompletableFuture<T> submit(Callable<T> task) {
		Objects.requireNonNull(task);
		CompletableFuture<T> future = new CompletableFuture<>();
		execute(() -> eval(task, future));
		return future;
	}
	/**
	 * Schedules {@link Runnable} on this {@link PageExecutor} and returns specified result.
	 * The supplied {@link Runnable} will run in page's event loop.
	 * 
	 * @param task
	 *            {@link Runnable} to schedule.
	 * @param result
	 *            Value to return from {@link CompletableFuture} when the {@link Runnable} finishes running.
	 * @return {@link CompletableFuture} that will complete normally with the supplied result when the supplied {@link Runnable} finishes normally.
	 *         If the {@link Runnable} throws an exception, the {@link CompletableFuture} completes exceptionally with this exception.
	 * @x.threads This method is safe to call from any thread.
	 *            The supplied {@link Runnable} will run in the event loop, which means it doesn't need to synchronize access with other events in the same event loop.
	 * @see PageExecutor#submit(Runnable)
	 */
	@Override
	public <T> CompletableFuture<T> submit(Runnable task, T result) {
		Objects.requireNonNull(task);
		return submit(Executors.callable(task, result));
	}
	/**
	 * Schedules {@link Runnable} on this {@link PageExecutor}.
	 * The supplied {@link Runnable} will run in page's event loop.
	 * 
	 * @param task
	 *            {@link Runnable} to schedule.
	 * @return {@link CompletableFuture} that will complete normally when {@link Runnable} finishes or exceptionally with exception thrown by the {@link Runnable}.
	 * @x.threads This method is safe to call from any thread.
	 *            The supplied {@link Runnable} will run in the event loop, which means it doesn't need to synchronize access with other events in the same event loop.
	 * @see PageExecutor#submit(Callable)
	 */
	@Override
	public CompletableFuture<?> submit(Runnable task) {
		Objects.requireNonNull(task);
		return submit(Executors.callable(task));
	}
	/**
	 * Blocks until all tasks complete running on this {@link PageExecutor}.
	 * All supplied tasks are executed as one big event in the event loop. They are not scheduled separately.
	 * 
	 * @param tasks
	 *            Tasks to execute on this {@link PageExecutor}.
	 * @return List of completed futures holding result or exception for every supplied task.
	 * @x.threads This method is safe to call from any thread except the thread used by this {@link PageExecutor} where it would result in a deadlock.
	 *            This method blocks, which means it shouldn't be called from within event on any {@link PageExecutor}.
	 *            The supplied tasks will run in the event loop, which means they don't need to synchronize access with other events in the same event loop.
	 */
	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) {
		if (tasks.stream().anyMatch(Objects::isNull))
			throw new NullPointerException();
		return new ArrayList<>(Exceptions.sneak().get(() -> submit(() -> {
			return tasks.stream()
				.map(PageExecutor::eval)
				.collect(toList());
		}).get()));
	}
	/**
	 * Blocks until all tasks complete running on this {@link PageExecutor} or timeout expires.
	 * All supplied tasks are executed as one big event in the event loop. They are not scheduled separately.
	 * If timeout is reached, this method completes without exception,
	 * but some or all of the tasks might be in cancelled state as if {@link CompletableFuture#cancel(boolean)} was called.
	 * 
	 * @param tasks
	 *            Tasks to execute on this {@link PageExecutor}.
	 * @param timeout
	 *            Timeout to wait for completion of the tasks in units specified.
	 * @param unit
	 *            Unit of the timeout.
	 * @return List of completed futures holding result or exception for every supplied task. If timeout is reached some or all of the tasks might be cancelled instead.
	 * @x.threads This method is safe to call from any thread except the thread used by this {@link PageExecutor} where it would result in a deadlock.
	 *            This method blocks, which means it shouldn't be called from within event on any {@link PageExecutor}.
	 *            The supplied tasks will run in the event loop, which means they don't need to synchronize access with other events in the same event loop.
	 */
	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) {
		List<Callable<T>> taskList = new ArrayList<>(tasks);
		List<CompletableFuture<T>> futures = Stream.generate(CompletableFuture<T>::new)
			.limit(tasks.size())
			.collect(toList());
		Exceptions.sneak().run(() -> {
			try {
				submit(() -> {
					for (int i = 0; i < futures.size(); ++i)
						eval(taskList.get(i), futures.get(i));
				}).get(timeout, unit);
			} catch (TimeoutException e) {
				for (CompletableFuture<T> future : futures)
					future.cancel(false);
			}
		});
		return new ArrayList<>(futures);
	}
	/**
	 * Blocks until the first supplied task completes running in the event loop.
	 * This method is provided for compatibility with {@link ExecutorService}.
	 * Call to this method is equivalent to calling {@link #submit(Callable)} and subsequently invoking {@link Future#get()}.
	 * 
	 * @param tasks
	 *            Tasks to execute. {@link PageExecutor} ignores all but the first task.
	 * @return Result of the first task.
	 * @x.threads This method is safe to call from any thread except the thread used by this {@link PageExecutor} where it would result in a deadlock.
	 *            This method blocks, which means it shouldn't be called from within event on any {@link PageExecutor}.
	 *            The first supplied task will run in the event loop, which means it doesn't need to synchronize access with other events in the same event loop.
	 * @see #submit(Callable)
	 */
	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
		if (tasks.isEmpty())
			throw new IllegalArgumentException();
		return submit(tasks.stream().findFirst().get()).get();
	}
	/**
	 * Blocks until the first supplied task completes running in the event loop or timeout expires.
	 * This method is provided for compatibility with {@link ExecutorService}.
	 * Call to this method is equivalent to calling {@link #submit(Callable)} and subsequently invoking {@link Future#get(long, TimeUnit)}.
	 * 
	 * @param tasks
	 *            Tasks to execute. {@link PageExecutor} ignores all but the first task.
	 * @param timeout
	 *            Timeout to wait for completion of the first task in units specified.
	 * @param unit
	 *            Unit of the timeout.
	 * @return Result of the first task.
	 * @x.threads This method is safe to call from any thread except the thread used by this {@link PageExecutor} where it would result in a deadlock.
	 *            This method blocks, which means it shouldn't be called from within event on any {@link PageExecutor}.
	 *            The first supplied task will run in the event loop, which means it doesn't need to synchronize access with other events in the same event loop.
	 * @see #submit(Callable)
	 */
	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		if (tasks.isEmpty())
			throw new IllegalArgumentException();
		return submit(tasks.stream().findFirst().get()).get(timeout, unit);
	}
	@Override
	public synchronized void shutdown() {
		throw new UnsupportedOperationException();
	}
	@Override
	public synchronized List<Runnable> shutdownNow() {
		throw new UnsupportedOperationException();
	}
	@Override
	public synchronized boolean isShutdown() {
		return false;
	}
	@Override
	public synchronized boolean isTerminated() {
		return false;
	}
	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
		throw new UnsupportedOperationException();
	}
	@Override
	public String toString() {
		return PageExecutor.class.getSimpleName() + ": " + page.toString();
	}
	private static <T> void eval(Callable<T> task, CompletableFuture<T> future) {
		try {
			if (!future.isCancelled())
				future.complete(task.call());
		} catch (Throwable e) {
			future.completeExceptionally(e);
		}
	}
	private static <T> CompletableFuture<T> eval(Callable<T> task) {
		CompletableFuture<T> future = new CompletableFuture<>();
		eval(task, future);
		return future;
	}
}
