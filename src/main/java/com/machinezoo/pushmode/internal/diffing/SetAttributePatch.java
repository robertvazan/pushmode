// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.internal.diffing;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

class SetAttributePatch extends DomPatch {
	private final String name;
	private final String value;
	SetAttributePatch(String name, String value) {
		this.name = name;
		this.value = value;
	}
	@Override
	char code() {
		return 'a';
	}
	@Override
	JsonNode toJson() {
		ObjectNode node = DocumentPatch.mapper.createObjectNode();
		node.put("n", name);
		node.put("v", value);
		return node;
	}
}
