package com.machinezoo.pushmode.dom;

import java.util.*;

/*
 * Even though elements have custom representation for attributes,
 * we still need an attribute object in order to provide dynamic attribute API.
 * Like in element's internal representation, null value means boolean attribute.
 */
public class DomAttribute {
	private final String name;
	public String name() {
		return name;
	}
	private final String value;
	public String value() {
		return value;
	}
	public DomAttribute(String name, String value) {
		Objects.requireNonNull(name);
		this.name = name;
		this.value = value;
	}
	@Override public boolean equals(Object object) {
		if (this == object)
			return true;
		if (!(object instanceof DomAttribute))
			return false;
		DomAttribute other = (DomAttribute)object;
		return name.equals(other.name) && Objects.equals(value, other.value);
	}
	@Override public int hashCode() {
		return 31 * name.hashCode() + Objects.hashCode(value);
	}
}
