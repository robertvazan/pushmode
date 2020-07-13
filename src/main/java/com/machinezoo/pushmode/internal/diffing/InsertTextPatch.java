// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.internal.diffing;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

class InsertTextPatch extends DomPatch {
	private final int at;
	private final String text;
	InsertTextPatch(int at, String text) {
		this.at = at;
		this.text = text;
	}
	@Override
	char code() {
		return 't';
	}
	@Override
	JsonNode toJson() {
		ObjectNode node = DocumentPatch.mapper.createObjectNode();
		node.put("i", at);
		node.put("t", text);
		return node;
	}
}
