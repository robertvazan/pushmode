// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.internal.diffing;

import com.machinezoo.pushmode.*;
import com.machinezoo.pushmode.dom.*;

class ReleaseIdPatch extends IdPatch {
	private final DomElement element;
	ReleaseIdPatch(DomElement element) {
		this.element = element;
	}
	@Override void updateIdMap(ElementIdIndex index) {
		index.remove(element);
	}
}
