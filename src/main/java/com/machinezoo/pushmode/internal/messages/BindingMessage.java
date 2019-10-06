// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.internal.messages;

import java.util.*;
import com.fasterxml.jackson.databind.*;
import com.machinezoo.noexception.*;
import com.machinezoo.pushmode.dom.*;

class BindingMessage extends ListenerMessage {
	private final String attribute;
	private final String value;
	public BindingMessage(JsonNode json) {
		super(json);
		attribute = json.get("a").asText();
		value = json.get("v").asText();
	}
	@Override public Object key() {
		return new BindingKey(elementId(), attribute);
	}
	@Override public void match(DomListener listener) {
		if (listener instanceof DomBinding) {
			DomBinding binding = (DomBinding)listener;
			if (attribute.equals(binding.attribute()) && binding.handler() != null)
				Exceptions.log().consumer(binding.handler()).accept(value);
		}
	}
	@Override ListenerMessage combine(ListenerMessage next) {
		if (!attribute.equals(((BindingMessage)next).attribute))
			throw new IllegalArgumentException();
		return next;
	}
	private static class BindingKey {
		final String elementId;
		final String attribute;
		BindingKey(String elementId, String attribute) {
			this.elementId = elementId;
			this.attribute = attribute;
		}
		@Override public boolean equals(Object obj) {
			if (!(obj instanceof BindingKey))
				return false;
			BindingKey other = (BindingKey)obj;
			return Objects.equals(elementId, other.elementId) && Objects.equals(attribute, other.attribute);
		}
		@Override public int hashCode() {
			return Objects.hash(elementId, attribute);
		}
		@Override public String toString() {
			return attribute + ":" + elementId;
		}
	}
}
