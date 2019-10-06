// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.internal.diffing;

import com.fasterxml.jackson.databind.*;

class ChildPatchEnd extends DomPatch {
	@Override char code() {
		return 'f';
	}
	@Override JsonNode toJson() {
		return null;
	}
}
