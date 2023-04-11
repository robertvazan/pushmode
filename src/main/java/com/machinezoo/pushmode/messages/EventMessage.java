// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.messages;

import java.util.*;
import org.slf4j.*;
import com.fasterxml.jackson.databind.*;
import com.machinezoo.noexception.slf4j.*;
import com.machinezoo.pushmode.dom.*;

class EventMessage extends ListenerMessage {
	private final String name;
	private static final Logger logger = LoggerFactory.getLogger(EventMessage.class);
	public EventMessage(JsonNode json) {
		super(json);
		name = json.get("n").asText();
	}
	@Override
	public Object key() {
		return new EventKey(elementId(), name);
	}
	@Override
	public void match(DomListener listener) {
		if (listener instanceof DomEvent) {
			DomEvent event = (DomEvent)listener;
			if (name.equals(event.name()) && event.handler() != null)
				ExceptionLogging.log(logger).run(event.handler());
		}
	}
	@Override
	ListenerMessage combine(ListenerMessage next) {
		throw new UnsupportedOperationException();
	}
	private static class EventKey {
		final String elementId;
		final String name;
		EventKey(String elementId, String name) {
			this.elementId = elementId;
			this.name = name;
		}
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof EventKey))
				return false;
			EventKey other = (EventKey)obj;
			return Objects.equals(elementId, other.elementId) && Objects.equals(name, other.name);
		}
		@Override
		public int hashCode() {
			return Objects.hash(elementId, name);
		}
		@Override
		public String toString() {
			return name + ":" + elementId;
		}
	}
}
