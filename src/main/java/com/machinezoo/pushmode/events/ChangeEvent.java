// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.events;

import com.machinezoo.pushmode.dom.*;

public final class ChangeEvent extends DomEvent {
	public ChangeEvent(Runnable handler) {
		super(handler);
	}
	@Override public String name() {
		return "change";
	}
	@Override public ChangeEvent withHandler(Runnable handler) {
		return new ChangeEvent(handler);
	}
}
