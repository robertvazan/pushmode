// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode;

import java.nio.*;
import java.nio.charset.*;
import java.time.*;
import javax.servlet.http.*;
import org.apache.http.*;
import org.apache.http.client.utils.*;
import com.fasterxml.jackson.databind.*;
import com.machinezoo.hookless.*;
import com.machinezoo.hookless.servlets.*;
import com.machinezoo.hookless.time.*;
import com.machinezoo.noexception.*;
import com.machinezoo.stagean.*;
import io.micrometer.core.instrument.*;

/**
 * Long-poll servlet polling for changes in the page.
 */
@StubDocs
@DraftApi("switch to websocket")
@SuppressWarnings("serial")
public class PollServlet extends ReactiveServlet {
	private static final Counter reloadCounter = Metrics.counter("pushmode.poll.reloads");
	private static final Counter exceptionCounter = Metrics.counter("pushmode.poll.exceptions");
	private static final Counter requestCounter = Metrics.counter("pushmode.poll.requests");
	private static final Counter patchCounter = Metrics.counter("pushmode.poll.patches");
	private static final Counter trafficCounter = Metrics.counter("pushmode.poll.traffic");
	private static final LongTaskTimer activeTimer = LongTaskTimer.builder("pushmode.poll.active").register(Metrics.globalRegistry);
	private static final ObjectMapper mapper = new ObjectMapper();
	@Override
	public ReactiveServletResponse doPost(ReactiveServletRequest request) {
		Instant start = CurrentReactiveScope.pin("start", Instant::now);
		LongTaskTimer.Sample sample = CurrentReactiveScope.pin("sample", activeTimer::start);
		try {
			if (ReactiveInstant.now().isAfter(start.plusSeconds(25)))
				return new ReactiveServletResponse().status(HttpServletResponse.SC_NO_CONTENT);
			String buster = Exceptions.sneak().get(() -> new URIBuilder(request.url())).getQueryParams().stream()
				.filter(p -> p.getName().equals("v"))
				.findFirst()
				.map(NameValuePair::getValue)
				.orElse(null);
			if (!PushScriptServlet.buster().equals(buster)) {
				reloadCounter.increment();
				return new ReactiveServletResponse().status(HttpServletResponse.SC_RESET_CONTENT);
			}
			JsonNode json = Exceptions.sneak().get(() -> mapper.readTree(request.data()));
			/*
			 * Various bots will try to query this endpoint without supplying proper request body.
			 * We will detect missing or bogus body and return appropriate HTTP status in order to avoid
			 * unnecessary exceptions in logs later when we try to use values from the JSON body.
			 */
			if (json == null)
				return new ReactiveServletResponse().status(HttpServletResponse.SC_BAD_REQUEST);
			String id = json.get("s").asText();
			long sequence = json.get("o").asLong();
			PushPage page = CurrentReactiveScope.pin("page", () -> {
				requestCounter.increment();
				return PagePool.instance().lookup(id);
			});
			if (page == null) {
				reloadCounter.increment();
				return new ReactiveServletResponse().status(HttpServletResponse.SC_RESET_CONTENT);
			}
			PageFrame frame;
			try {
				frame = page.frame(sequence);
			} catch (Throwable ex) {
				exceptionCounter.increment();
				return new ReactiveServletResponse().status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			if (CurrentReactiveScope.blocked())
				return new ReactiveServletResponse();
			if (frame == null) {
				reloadCounter.increment();
				return new ReactiveServletResponse().status(HttpServletResponse.SC_RESET_CONTENT);
			}
			patchCounter.increment();
			byte[] serialized = frame.jsonPatch().toString().getBytes(StandardCharsets.UTF_8);
			trafficCounter.increment(serialized.length);
			ReactiveServletResponse response = new ReactiveServletResponse();
			response.headers().put("Content-Type", "application/json; charset=utf-8");
			response.data(ByteBuffer.wrap(serialized));
			return response;
		} finally {
			if (!CurrentReactiveScope.blocked())
				sample.stop();
		}
	}
}
