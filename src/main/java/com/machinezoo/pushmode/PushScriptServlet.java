// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode;

import java.io.*;
import java.nio.*;
import java.nio.charset.*;
import java.security.*;
import java.util.*;
import org.apache.commons.io.*;
import com.machinezoo.hookless.servlets.*;
import com.machinezoo.noexception.*;

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
	@Override public ReactiveServletResponse doGet(ReactiveServletRequest request) {
		ReactiveServletResponse response = new ReactiveServletResponse();
		response.headers().put("Content-Type", "application/javascript; charset=utf-8");
		response.headers().put("Cache-Control", "max-age=31536000");
		response.headers().put("Content-Length", Integer.toString(data.limit()));
		response.data(data);
		return response;
	}
}
