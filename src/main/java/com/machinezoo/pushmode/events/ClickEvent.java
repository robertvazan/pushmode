// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.events;

import com.machinezoo.pushmode.dom.*;

public final class ClickEvent extends DomEvent {
	public ClickEvent(Runnable handler) {
		super(handler);
	}
	@Override
	public String name() {
		return "click";
	}
	@Override
	public ClickEvent withHandler(Runnable handler) {
		return new ClickEvent(handler);
	}
}
