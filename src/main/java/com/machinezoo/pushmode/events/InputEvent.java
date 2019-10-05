package com.machinezoo.pushmode.events;

import com.machinezoo.pushmode.dom.*;

public final class InputEvent extends DomEvent {
	public InputEvent(Runnable handler) {
		super(handler);
	}
	@Override public String name() {
		return "input";
	}
	@Override public InputEvent withHandler(Runnable handler) {
		return new InputEvent(handler);
	}
}
