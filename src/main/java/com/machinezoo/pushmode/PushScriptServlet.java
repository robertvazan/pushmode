// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode;

import java.io.*;
import java.nio.*;
import java.nio.charset.*;
import java.security.*;
import java.util.*;
import java.util.regex.*;
import javax.servlet.http.*;
import org.apache.commons.io.*;
import com.machinezoo.hookless.servlets.*;
import com.machinezoo.noexception.*;
import com.machinezoo.stagean.*;

/**
 * Servlet for pushmode.js.
 */
@StubDocs
@DraftApi("might be better done on app or framework level (CDN, busters, ...)")
@DraftCode("minify and compress, proper ETag parsing")
public class PushScriptServlet extends ReactiveServlet {
	private static final long serialVersionUID = 1L;
	private static final String buster;
	public static String buster() {
		return buster;
	}
	private static final ByteBuffer data;
	static {
		byte[] raw = Exceptions.sneak().get(() -> {
			try (InputStream input = PushScriptServlet.class.getResourceAsStream("/com/machinezoo/pushmode/pushmode.js")) {
				return IOUtils.toByteArray(input);
			}
		});
		byte[] hash = Exceptions.sneak().get(() -> MessageDigest.getInstance("SHA-256")).digest(raw);
		buster = Base64.getUrlEncoder().encodeToString(hash).replace("_", "").replace("-", "").replace("=", "");
		data = ByteBuffer.wrap(new String(raw, StandardCharsets.UTF_8).replace("VERSION", buster).getBytes(StandardCharsets.UTF_8));
	}
	public static String url() {
		return "/pushmode/script?v=" + buster;
	}
	/*
	 * We currently don't bother supporting multiple values in If-None-Match.
	 * The only downside is reduced performance in rare cases when If-None-Match actually contains multiple values.
	 */
	private static final Pattern etagRe = Pattern.compile("\\s*\"([^\"]+)\"\\s*");
	@Override
	public ReactiveServletResponse doGet(ReactiveServletRequest request) {
		ReactiveServletResponse response = new ReactiveServletResponse();
		response.headers().put("Cache-Control", "max-age=31536000");
		/*
		 * We do bother tinkering with ETag here, because pushmode-triggered page reloads
		 * (commonly occurring when server restarts) trigger a train of resource reloads.
		 * We want to kill those requests with 304 and pushmode.js is one of them.
		 * 
		 * We cannot rely on buster presence in query parameters, because it could be easily inconsistent with If-None-Match,
		 * because this servlet returns current script regardless of which one the buster was generated from.
		 */
		response.headers().put("ETag", "\"" + buster + "\"");
		String inm = request.headers().get("If-None-Match");
		if (inm != null) {
			Matcher matcher = etagRe.matcher(inm);
			if (matcher.matches() && buster.equals(matcher.group(1))) {
				response.status(HttpServletResponse.SC_NOT_MODIFIED);
				return response;
			}
		}
		response.headers().put("Content-Type", "application/javascript; charset=utf-8");
		response.headers().put("Content-Length", Integer.toString(data.limit()));
		response.data(data);
		return response;
	}
}
