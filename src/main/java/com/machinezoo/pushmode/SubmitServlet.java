// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode;

import java.nio.*;
import java.nio.charset.*;
import java.util.*;
import org.apache.http.*;
import org.apache.http.client.utils.*;
import org.slf4j.*;
import com.fasterxml.jackson.databind.*;
import com.machinezoo.hookless.servlets.*;
import com.machinezoo.noexception.*;
import com.machinezoo.pushmode.messages.*;
import com.machinezoo.stagean.*;
import io.micrometer.core.instrument.*;
import io.micrometer.core.instrument.Timer;
import jakarta.servlet.http.*;

/**
 * Servlet accepting user input sent from pushmode.js.
 */
@StubDocs
@DraftApi("switch to websocket")
public class SubmitServlet extends ReactiveServlet {
	private static final long serialVersionUID = 1L;
	private static final Timer timer = Metrics.timer("pushmode.submit");
	private static final Counter reloadCounter = Metrics.counter("pushmode.submit.reloads");
	private static final ObjectMapper mapper = new ObjectMapper();
	private static final Logger logger = LoggerFactory.getLogger(SubmitServlet.class);
	@Override
	public ReactiveServletResponse doPost(ReactiveServletRequest request) {
		Timer.Sample sample = Timer.start(Clock.SYSTEM);
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
		String id = json.get("s").asText();
		PushPage page = PagePool.instance().lookup(id);
		if (page == null) {
			reloadCounter.increment();
			return new ReactiveServletResponse().status(HttpServletResponse.SC_RESET_CONTENT);
		}
		Exceptions.log(logger).passing().run(() -> {
			long inputSeq = json.get("i").asLong();
			JsonNode events = json.get("e");
			List<ListenerMessage> messages = new ArrayList<>();
			for (int i = 0; i < events.size(); ++i)
				messages.add(ListenerMessage.parse(events.get(i)));
			page.handle(inputSeq, messages);
		});
		ReactiveServletResponse response = new ReactiveServletResponse();
		response.headers().put("Content-Type", "application/json; charset=utf-8");
		response.data(ByteBuffer.wrap("{}".getBytes(StandardCharsets.UTF_8)));
		sample.stop(timer);
		return response;
	}
}
