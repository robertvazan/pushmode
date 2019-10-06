// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.internal.diffing;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import com.machinezoo.pushmode.dom.*;

class InsertElementPatch extends DomPatch {
	private final int at;
	private final DomElement element;
	InsertElementPatch(int at, DomElement element) {
		this.at = at;
		this.element = element;
	}
	@Override char code() {
		return 'e';
	}
	@Override JsonNode toJson() {
		ObjectNode node = DocumentPatch.mapper.createObjectNode();
		node.put("i", at);
		node.put("h", DomFormatter.fragment()
			.format(element)
			.toString());
		return node;
	}
}
