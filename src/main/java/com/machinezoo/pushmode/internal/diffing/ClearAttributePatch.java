// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.internal.diffing;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

class ClearAttributePatch extends DomPatch {
	private final String name;
	ClearAttributePatch(String name) {
		this.name = name;
	}
	@Override char code() {
		return 'c';
	}
	@Override JsonNode toJson() {
		return new TextNode(name);
	}
}
