// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.bindings;

import java.util.function.*;
import com.machinezoo.pushmode.dom.*;

public final class ValueBinding extends DomBinding {
	public ValueBinding(String value, Consumer<String> handler) {
		super(value, handler);
	}
	@Override public String attribute() {
		return "value";
	}
	@Override public Class<?> datatype() {
		return String.class;
	}
}
