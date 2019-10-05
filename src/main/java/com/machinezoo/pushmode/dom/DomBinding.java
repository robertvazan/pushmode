package com.machinezoo.pushmode.dom;

import java.util.*;
import java.util.function.*;
import com.fasterxml.jackson.databind.node.*;

public abstract class DomBinding extends DomListener {
	private final String value;
	public String value() {
		return value;
	}
	private final Consumer<String> handler;
	public Consumer<String> handler() {
		return handler;
	}
	public DomBinding(String value, Consumer<String> handler) {
		Objects.requireNonNull(value);
		Objects.requireNonNull(handler);
		this.value = value;
		this.handler = handler;
	}
	public abstract String attribute();
	public abstract Class<?> datatype();
	@Override public DomBinding combine(DomListener other) {
		if (other.getClass() == getClass())
			return (DomBinding)other;
		return null;
	}
	@Override public boolean isSameSubscription(DomListener other) {
		return this == other || getClass() == other.getClass();
	}
	@Override public String listenerTypeCode() {
		return "b";
	}
	@Override public String listenerMapKey() {
		return attribute();
	}
	@Override public void subscribeJson(ObjectNode json) {
		super.subscribeJson(json);
		if (datatype() == Boolean.class)
			json.put("d", "b");
		else
			json.put("d", "s");
		json.put("v", value);
	}
	@Override protected void subscribeJsonCommon(ObjectNode json) {
		super.subscribeJsonCommon(json);
		json.put("a", attribute());
	}
	@Override public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null || getClass() != object.getClass())
			return false;
		DomBinding other = (DomBinding)object;
		return handler == other.handler && value.equals(other.value);
	}
	@Override public int hashCode() {
		return 31 * 31 * getClass().hashCode() + 31 * Objects.hashCode(handler) + value.hashCode();
	}
}
