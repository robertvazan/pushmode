// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.diffing;

import com.fasterxml.jackson.databind.node.*;
import com.machinezoo.pushmode.dom.*;

class SubscribePatch extends ListenerPatch {
	private final String elementId;
	@Override
	String elementId() {
		return elementId;
	}
	private final DomListener listener;
	SubscribePatch(String elementId, DomListener listener) {
		this.elementId = elementId;
		this.listener = listener;
	}
	@Override
	void listenerJson(ObjectNode json) {
		listener.subscribeJson(json);
	}
}
