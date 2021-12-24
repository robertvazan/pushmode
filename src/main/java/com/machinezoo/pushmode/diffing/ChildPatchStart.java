// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.diffing;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

class ChildPatchStart extends DomPatch {
	private final int at;
	ChildPatchStart(int at) {
		this.at = at;
	}
	@Override
	char code() {
		return 'p';
	}
	@Override
	JsonNode toJson() {
		return new IntNode(at);
	}
}
