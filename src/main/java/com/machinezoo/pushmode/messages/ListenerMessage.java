// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.messages;

import com.fasterxml.jackson.databind.*;
import com.machinezoo.pushmode.dom.*;

public abstract class ListenerMessage {
	private final String elementId;
	public String elementId() {
		return elementId;
	}
	public abstract Object key();
	public abstract void match(DomListener listener);
	abstract ListenerMessage combine(ListenerMessage next);
	protected ListenerMessage(JsonNode json) {
		elementId = json.get("e").asText();
	}
	public static ListenerMessage parse(JsonNode json) {
		String type = json.get("t").asText();
		if (type.equals("b"))
			return new BindingMessage(json);
		if (type.equals("e"))
			return new EventMessage(json);
		throw new IllegalArgumentException();
	}
}
