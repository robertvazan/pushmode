// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.internal.diffing;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

abstract class ListenerPatch {
	abstract String elementId();
	abstract void listenerJson(ObjectNode json);
	JsonNode toJson() {
		ObjectNode json = DocumentPatch.mapper.createObjectNode();
		json.put("e", elementId());
		listenerJson(json);
		return json;
	}
}
