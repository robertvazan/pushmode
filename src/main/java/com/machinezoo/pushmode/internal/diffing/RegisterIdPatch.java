// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.internal.diffing;

import com.machinezoo.pushmode.dom.*;

class RegisterIdPatch extends IdPatch {
	private final DomElement element;
	RegisterIdPatch(DomElement element) {
		this.element = element;
	}
	@Override
	void updateIdMap(ElementIdIndex index) {
		index.add(element);
	}
}
