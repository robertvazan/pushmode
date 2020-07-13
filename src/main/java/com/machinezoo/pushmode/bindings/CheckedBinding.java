// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.bindings;

import java.util.function.*;
import com.machinezoo.pushmode.dom.*;

public final class CheckedBinding extends DomBinding {
	public CheckedBinding(boolean value, Consumer<Boolean> handler) {
		super(Boolean.toString(value), v -> {
			if (handler != null)
				handler.accept(Boolean.parseBoolean(v));
		});
	}
	@Override
	public String attribute() {
		return "checked";
	}
	@Override
	public Class<?> datatype() {
		return Boolean.class;
	}
}
