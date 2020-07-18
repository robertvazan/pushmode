// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.internal.diffing;

import java.util.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import com.machinezoo.pushmode.dom.*;

public class DocumentPatch {
	private final List<DomPatch> main = new ArrayList<>();
	private final List<UnsubscribePatch> unsubscribe = new ArrayList<>();
	private final List<SubscribePatch> subscribe = new ArrayList<>();
	private final List<CoercePatch> coerce = new ArrayList<>();
	private final List<IdPatch> ids = new ArrayList<>();
	private static final String[] noAttributes = new String[0];
	private static final DomListener[] noListeners = new DomListener[0];
	private static final DomContent[] noChildren = new DomContent[0];
	public static final ObjectMapper mapper = new ObjectMapper();
	public ObjectNode toJson() {
		ObjectNode json = mapper.createObjectNode();
		StringBuilder types = new StringBuilder();
		ArrayNode params = json.putArray("p");
		for (DomPatch patch : main) {
			types.append(patch.code());
			JsonNode node = patch.toJson();
			if (node != null)
				params.add(node);
		}
		json.put("t", types.toString());
		ArrayNode subscribeJson = json.putArray("s");
		for (SubscribePatch patch : subscribe)
			subscribeJson.add(patch.toJson());
		ArrayNode unsubscribeJson = json.putArray("u");
		for (UnsubscribePatch patch : unsubscribe)
			unsubscribeJson.add(patch.toJson());
		ArrayNode coerceJson = json.putArray("c");
		for (CoercePatch patch : coerce)
			coerceJson.add(patch.toJson());
		return json;
	}
	public boolean isSubstantial() {
		return !main.isEmpty() || !unsubscribe.isEmpty() || !subscribe.isEmpty() || !coerce.isEmpty();
	}
	public void updateIds(ElementIdIndex index) {
		for (IdPatch patch : ids)
			patch.updateIdMap(index);
	}
	public void compareDocumentRoots(DomElement e1, DomElement e2) {
		if (e1 != e2)
			compareElements(e1, e2, 0);
	}
	private void compareElements(DomElement e1, DomElement e2, int at) {
		if (e1 != e2) {
			if (e1.tagname().equals(e2.tagname()) && Objects.equals(e1.id(), e2.id())) {
				int bookmark = main.size();
				main.add(null);
				updateId(e1, e2);
				clearAttributes(e1, e2);
				setAttributes(e1, e2);
				unsubscribe(e1, e2);
				subscribe(e1, e2);
				coerce(e1, e2);
				if (!e2.tagname().equals("textarea"))
					compareElementContent(e1.rawChildren(), e2.rawChildren());
				if (main.size() - bookmark > 1) {
					main.set(bookmark, new ChildPatchStart(at));
					main.add(new ChildPatchEnd());
				} else
					main.remove(bookmark);
			} else {
				deleteNode(at, e1);
				insertNode(at, e2);
			}
		}
	}
	public void compareElementContent(DomContent[] b1, DomContent[] b2) {
		Map<String, Integer> map = null;
		int last1 = -1;
		int last2 = -1;
		if (b1 == null && b2 == null)
			return;
		if (b1 == null)
			b1 = noChildren;
		if (b2 == null)
			b2 = noChildren;
		for (int i2 = 0; i2 < b2.length; ++i2) {
			DomContent n2 = b2[i2];
			if (n2 instanceof DomElement) {
				DomElement e2 = (DomElement)n2;
				String k2 = key(e2);
				if (k2 != null) {
					int i1 = last1 + (i2 - last2);
					if (i1 < b1.length) {
						DomContent n1 = b1[i1];
						if (n1 != e2) {
							if (n1 instanceof DomElement) {
								DomElement e1 = (DomElement)n1;
								if (!k2.equals(key(e1)))
									i1 = -1;
							} else
								i1 = -1;
						}
					} else
						i1 = -1;
					if (i1 < 0) {
						if (map == null)
							map = hashKeysToPositions(b1);
						Integer at1 = map.get(k2);
						if (at1 != null && at1 > last1)
							i1 = at1;
					}
					if (i1 >= 0) {
						mergePositional(b1, last1 + 1, i1, b2, last2 + 1, i2);
						mergeAt(b1, i1, b2, i2);
						last1 = i1;
						last2 = i2;
					}
				}
			}
		}
		mergePositional(b1, last1 + 1, b1.length, b2, last2 + 1, b2.length);
	}
	private void mergePositional(DomContent[] b1, int s1, int e1, DomContent[] b2, int s2, int e2) {
		int len = Math.min(e1 - s1, e2 - s2);
		for (int i = 0; i < len; ++i)
			mergeAt(b1, s1 + i, b2, s2 + i);
		int removed = e1 - (s1 + len);
		for (int i = removed - 1; i >= 0; --i)
			deleteNode(s2 + len + i, b1[s1 + len + i]);
		for (int i2 = s2 + len; i2 < e2; ++i2)
			insertNode(i2, b2[i2]);
	}
	private void mergeAt(DomContent[] b1, int i1, DomContent[] b2, int i2) {
		DomContent n1 = b1[i1];
		DomContent n2 = b2[i2];
		if (n1 != n2) {
			if (n1 instanceof DomElement && n2 instanceof DomElement)
				compareElements((DomElement)n1, (DomElement)n2, i2);
			else if (!n1.equals(n2)) {
				deleteNode(i2, n1);
				insertNode(i2, n2);
			}
		}
	}
	private static Map<String, Integer> hashKeysToPositions(DomContent[] list) {
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < list.length; ++i) {
			DomContent node = list[i];
			if (node instanceof DomElement) {
				DomElement element = (DomElement)node;
				if (key(element) != null)
					map.put(key(element), i);
			}
		}
		return map;
	}
	private static String key(DomElement element) {
		return element.key() != null ? element.key() : element.id();
	}
	private void deleteNode(int at, DomContent node) {
		if (node instanceof DomElement)
			unsubscribeTree((DomElement)node);
		main.add(new DeleteNodePatch(at));
	}
	private void insertNode(int at, DomContent node) {
		if (node instanceof DomElement) {
			DomElement element = (DomElement)node;
			main.add(new InsertElementPatch(at, element));
			subscribeTree(element);
		} else if (node instanceof DomText)
			main.add(new InsertTextPatch(at, ((DomText)node).text()));
		else
			throw new IllegalArgumentException();
	}
	private void clearAttributes(DomElement e1, DomElement e2) {
		String[] b1 = e1.rawAttributes();
		if (b1 != null) {
			String[] b2 = e2.rawAttributes();
			if (b2 == null)
				b2 = noAttributes;
			outer: for (int i1 = 0; i1 < b1.length; i1 += 2) {
				String a1 = b1[i1];
				if (!posterOnly(a1)) {
					String v1 = b1[i1 + 1];
					if (i1 < b2.length && Objects.equals(v1, b2[i1 + 1]))
						continue;
					for (int i2 = 0; i2 < b2.length; i2 += 2)
						if (i1 != i2 && Objects.equals(v1, b2[i2 + 1]))
							continue outer;
					main.add(new ClearAttributePatch(a1));
				}
			}
		}
	}
	private void setAttributes(DomElement e1, DomElement e2) {
		String[] b2 = e2.rawAttributes();
		if (b2 != null) {
			String[] b1 = e1.rawAttributes();
			if (b1 == null)
				b1 = noAttributes;
			outer: for (int i2 = 0; i2 < b2.length; i2 += 2) {
				String a2 = b2[i2];
				String v2 = b2[i2 + 1];
				if (!posterOnly(a2)) {
					if (i2 < b1.length && updateAttribute(b1[i2], b1[i2 + 1], a2, v2))
						continue;
					for (int i1 = 0; i1 < b1.length; i1 += 2)
						if (i2 != i1 && updateAttribute(b1[i1], b1[i1 + 1], a2, v2))
							continue outer;
					main.add(new SetAttributePatch(a2, v2));
				}
			}
		}
	}
	private static boolean posterOnly(String attribute) {
		switch (attribute) {
		case "checked":
		case "value":
			return true;
		default:
			return false;
		}
	}
	private boolean updateAttribute(String a1, String v1, String a2, String v2) {
		if (a1.equals(a2)) {
			if (!Objects.equals(v1, v2))
				main.add(new SetAttributePatch(a2, v2));
			return true;
		}
		return false;
	}
	private void unsubscribe(DomElement e1, DomElement e2) {
		if (e1.id() != null) {
			DomListener[] b1 = e1.rawListeners();
			if (b1 != null) {
				DomListener[] b2 = e2.rawListeners();
				if (b2 == null)
					b2 = noListeners;
				outer: for (int i1 = 0; i1 < b1.length; ++i1) {
					DomListener listener = b1[i1];
					if (i1 < b2.length && listener.isSameSubscription(b2[i1]))
						continue;
					for (int i2 = 0; i2 < b2.length; ++i2)
						if (i1 != i2 && listener.isSameSubscription(b2[i2]))
							continue outer;
					unsubscribe.add(new UnsubscribePatch(e1.id(), listener));
				}
			}
		}
	}
	private void unsubscribeTree(DomElement element) {
		unsubscribeAll(element);
		DomContent[] children = element.rawChildren();
		if (children != null)
			for (DomContent content : children)
				if (content instanceof DomElement)
					unsubscribeTree((DomElement)content);
	}
	private void unsubscribeAll(DomElement element) {
		if (element.id() != null) {
			ids.add(new ReleaseIdPatch(element));
			DomListener[] listeners = element.rawListeners();
			if (listeners != null)
				for (DomListener listener : listeners)
					unsubscribe.add(new UnsubscribePatch(element.id(), listener));
		}
	}
	private void subscribe(DomElement e1, DomElement e2) {
		if (e2.id() != null) {
			DomListener[] b2 = e2.rawListeners();
			if (b2 != null) {
				DomListener[] b1 = e1.rawListeners();
				if (b1 == null)
					b1 = noListeners;
				outer: for (int i2 = 0; i2 < b2.length; ++i2) {
					DomListener listener = b2[i2];
					if (i2 < b1.length && listener.isSameSubscription(b1[i2]))
						continue;
					for (int i1 = 0; i1 < b1.length; ++i1)
						if (i2 != i1 && listener.isSameSubscription(b1[i1]))
							continue outer;
					subscribe.add(new SubscribePatch(e2.id(), listener));
				}
			}
		}
	}
	private void subscribeTree(DomElement element) {
		subscribeAll(element);
		DomContent[] children = element.rawChildren();
		if (children != null)
			for (DomContent content : children)
				if (content instanceof DomElement)
					subscribeTree((DomElement)content);
	}
	private void subscribeAll(DomElement element) {
		if (element.id() != null) {
			ids.add(new RegisterIdPatch(element));
			DomListener[] listeners = element.rawListeners();
			if (listeners != null)
				for (DomListener listener : listeners)
					subscribe.add(new SubscribePatch(element.id(), listener));
		}
	}
	private void coerce(DomElement e1, DomElement e2) {
		if (e2.id() != null) {
			DomListener[] b2 = e2.rawListeners();
			if (b2 != null) {
				DomListener[] b1 = e1.rawListeners();
				if (b1 == null)
					b1 = noListeners;
				outer: for (int i2 = 0; i2 < b2.length; ++i2) {
					DomListener listener = b2[i2];
					if (listener instanceof DomBinding) {
						DomBinding binding = (DomBinding)listener;
						if (i2 < b1.length && binding.isSameSubscription(b1[i2])) {
							coerce(e2.id(), (DomBinding)b1[i2], binding);
							continue;
						}
						for (int i1 = 0; i1 < b1.length; ++i1)
							if (i2 != i1 && binding.isSameSubscription(b1[i1])) {
								coerce(e2.id(), (DomBinding)b1[i1], binding);
								continue outer;
							}
					}
				}
			}
		}
	}
	private void coerce(String elementId, DomBinding b1, DomBinding b2) {
		if (!b1.value().equals(b2.value()))
			coerce.add(new CoercePatch(elementId, b2));
	}
	private void updateId(DomElement e1, DomElement e2) {
		if (e1 != e2) {
			if (e1.id() != null)
				ids.add(new ReleaseIdPatch(e1));
			if (e2.id() != null)
				ids.add(new RegisterIdPatch(e2));
		}
	}
}
