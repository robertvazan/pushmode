// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.diffing;

import java.util.*;
import com.machinezoo.pushmode.dom.*;

public class ElementIdIndex {
	private final Map<String, List<DomElement>> ids = new HashMap<>();
	public void add(DomElement element) {
		if (element.id() == null)
			throw new NullPointerException();
		List<DomElement> list = ids.get(element.id());
		if (list == null)
			ids.put(element.id(), list = new ArrayList<>());
		list.add(element);
	}
	public void remove(DomElement element) {
		List<DomElement> list = ids.get(element.id());
		list.remove(element);
		if (list.isEmpty())
			ids.remove(element.id());
	}
	public List<DomElement> get(String id) {
		List<DomElement> found = ids.get(id);
		return found != null ? found : Collections.emptyList();
	}
	public void seed(DomElement element) {
		if (element.id() != null)
			add(element);
		DomContent[] children = element.rawChildren();
		if (children != null)
			for (DomContent content : children)
				if (content instanceof DomElement)
					seed((DomElement)content);
	}
}
