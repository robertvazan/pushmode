// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.internal.diffing;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

class DeleteNodePatch extends DomPatch {
	private final int at;
	DeleteNodePatch(int at) {
		this.at = at;
	}
	@Override char code() {
		return 'd';
	}
	@Override JsonNode toJson() {
		return new IntNode(at);
	}
}
