// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.dom;

import java.util.*;
import com.fasterxml.jackson.databind.node.*;
import com.machinezoo.stagean.*;

/**
 * Base class for event subscriptions.
 */
@StubDocs
@DraftApi
@NoTests
public abstract class DomEvent extends DomListener {
	protected final Runnable handler;
	public Runnable handler() {
		return handler;
	}
	public DomEvent(Runnable handler) {
		this.handler = handler;
	}
	public abstract String name();
	protected abstract DomEvent withHandler(Runnable handler);
	@Override
	public DomEvent combine(DomListener other) {
		if (other.getClass() == getClass()) {
			DomEvent otherEvent = (DomEvent)other;
			if (otherEvent.handler != null) {
				if (handler == null)
					return otherEvent;
				else {
					Runnable first = handler;
					Runnable second = otherEvent.handler;
					return withHandler(() -> {
						first.run();
						second.run();
					});
				}
			}
			return this;
		}
		return null;
	}
	@Override
	public boolean isSameSubscription(DomListener other) {
		return this == other || getClass() == other.getClass();
	}
	@Override
	public String listenerTypeCode() {
		return "e";
	}
	@Override
	public String listenerMapKey() {
		return name();
	}
	@Override
	public void subscribeJson(ObjectNode json) {
		super.subscribeJson(json);
		json.put("v", handler == null);
	}
	@Override
	protected void subscribeJsonCommon(ObjectNode json) {
		super.subscribeJsonCommon(json);
		json.put("n", name());
	}
	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null || getClass() != object.getClass())
			return false;
		return handler == ((DomEvent)object).handler;
	}
	@Override
	public int hashCode() {
		return 31 * getClass().hashCode() + Objects.hashCode(handler);
	}
}
