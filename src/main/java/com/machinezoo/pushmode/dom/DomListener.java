package com.machinezoo.pushmode.dom;

import com.fasterxml.jackson.databind.node.*;

public abstract class DomListener {
	public abstract DomListener combine(DomListener other);
	public abstract boolean isSameSubscription(DomListener other);
	public abstract String listenerTypeCode();
	public abstract String listenerMapKey();
	public void subscribeJson(ObjectNode json) {
		subscribeJsonCommon(json);
	}
	public void unsubscribeJson(ObjectNode json) {
		subscribeJsonCommon(json);
	}
	protected void subscribeJsonCommon(ObjectNode json) {
		json.put("t", listenerTypeCode());
	}
}
