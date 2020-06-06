// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.dom;

import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import org.w3c.dom.*;
import org.w3c.dom.Node;
import com.machinezoo.noexception.*;
import com.machinezoo.pushmode.*;
import com.machinezoo.pushmode.bindings.*;
import com.machinezoo.pushmode.events.*;

/**
 * Element in PushMode DOM tree.
 * Every element inherits from {@link DomContainer}, which means it has a list of {@link DomContent} children.
 * In addition, element has attributes, attached listeners, and some special properties like {@link #tagname()}, {@link #id()}, and {@link #key()}.
 * <p>
 * {@link DomElement} cannot be directly instantiated by calling constructor {@link #DomElement(String)}.
 * Application code can instantiate elements more intuitively using {@link Html} class.
 * Attributes can be set by calling specialized fluent setters inherited from {@link DomAttributes}.
 * <p>
 * Every element can have an {@link #id()}. Element ID is serialized as an {@code id} attribute in HTML. Element ID has a special meaning in PushMode.
 * Listeners require the element to have an ID. Listeners on ID-less elements are ignored.
 * ID is also the default value of {@link #key()}.
 * <p>
 * Every element can have a {@link #key()}. It is the same as {@link #id()} by default, but it can be set separately via {@link #key(String)}.
 * While {@link #id()} is sent to the browser and it should be unique in the document,
 * {@link #key()} is used only server-side and it only needs to be unique among sibling elements.
 * It is used in hierarchical DOM diff to pair corresponding elements in cases where some DOM nodes have been inserted or removed from parent's child list.
 * If neither {@link #key()} nor {@link #id()} is specified, DOM diff uses element position to pair elements in compared DOM trees,
 * which might result in unnecessarily big diffs if an element is inserted at the beginning of a long list.
 * Absence of {@link #key()} thus doesn't impact functionality, only performance. And even then it matters only if siblings are inserted or removed.
 * <p>
 * In order to provide interactivity for elements, application can call {@code onX()} methods to add event handlers
 * or call binding overrides for two-way attributes like e.g. {@code value} attribute on &lt;input&gt; element.
 * <p>
 * DomElement is not thread-safe. Concurrent writes will corrupt internal state.
 * It is however safe for multiple threads to read the same DomElement as far as there are no concurrent writers.
 * Application can {@link #freeze()} DOM subtree to prevent its accidental modification.
 * This is particularly useful for DOM trees in caches that are likely to be read by multiple threads.
 */
public class DomElement extends DomContainer implements DomAttributes {
	/*
	 * Element is primarily defined by its tagname.
	 * This is the only property of the element that must be always set.
	 * That's why we provide constructor with tagname parameter but no default constructor.
	 */
	private String tagname;
	public String tagname() {
		return tagname;
	}
	public DomElement(String tagname) {
		Objects.requireNonNull(tagname);
		this.tagname = tagname;
	}
	/*
	 * ID attribute is treated specially for performance reasons.
	 * We don't want to bury it in the list of attributes.
	 */
	private String id;
	/**
	 * Gets element ID. This method returns element ID set via {@link #id(String)}. ID is {@code null} by default.
	 * 
	 * @return element ID
	 */
	public String id() {
		return id;
	}
	/**
	 * Sets element's ID.
	 * Once set, the ID is available from {@link #id()} method.
	 * <p>
	 * Element ID is serialized as the {@code id} attribute in HTML.
	 * It must therefore fulfill criteria for proper element ID in HTML, which most importantly means the ID should be unique in the document.
	 * PushMode however doesn't check whether the ID is really unique and instead takes some steps to tolerate accidental duplicates.
	 * This method only checks for an empty string and treats it as {@code null}.
	 * <p>
	 * Element ID has a special meaning in PushMode.
	 * Listeners require the element to have an ID. Listeners on ID-less elements are ignored.
	 * DOM diffing algorithm also uses ID when {@link #key()} is not set.
	 * <p>
	 * Internally, ID gets its own field in the class unlike other attributes.
	 * This makes access to ID much faster than access to other attributes.
	 * 
	 * @param id
	 *            new element ID
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if element is frozen
	 */
	public DomElement id(String id) {
		touch();
		if (id == null)
			this.id = null;
		else if (id.isEmpty())
			this.id = null;
		else
			this.id = id;
		return this;
	}
	/*
	 * Since our whole diffing algorithm is stolen from react.js,
	 * we need corresponding concept of element 'key' to make the shallow diff algorithm work well.
	 * 
	 * Key is restricted to be a String in order to enable fancy processing of DOM trees.
	 * String-only key is better for serialization, for example.
	 * 
	 * Diffing algorithm defaults to ID when key field is null.
	 * This way app code doesn't have to set both the ID and the key.
	 * We will also get some accidental performance improvement due to better child list pairing.
	 * The downside is that this can rarely cause some surprising diff behavior.
	 */
	private String key;
	/**
	 * Gets element's pairing key. This method returns element key set via {@link #key(String)}.
	 * 
	 * @return element's key
	 */
	public String key() {
		return key;
	}
	/**
	 * Sets element's pairing key.
	 * Key, if set, must be unique among siblings in the DOM tree.
	 * Once set, the key is available from {@link #key()}.
	 * <p>
	 * While {@link #id()} is sent to the browser and it should be unique in the document,
	 * {@link #key()} is used only server-side and it only needs to be unique among sibling elements.
	 * <p>
	 * Key is used in hierarchical DOM diff to pair corresponding elements in cases where some DOM nodes have been inserted or removed from parent's child list.
	 * If {@link #key()} is {@code null}, DOM diff falls back to element's {@link #id()}.
	 * If that is {@code null} too, DOM diff uses element position to pair elements in compared DOM trees,
	 * which might result in unnecessarily big diffs if an element is inserted at the beginning of a long list.
	 * Absence of key thus doesn't impact functionality, only performance. And even then it matters only if siblings are inserted or removed.
	 * 
	 * @param key
	 *            new element key or {@code null}
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if this element is frozen
	 */
	public DomElement key(String key) {
		touch();
		this.key = key;
		return this;
	}
	/*
	 * Attributes are stored as strings in a single array.
	 * Every attribute takes up two items in the array, one for its name and one for value.
	 * Boolean attributes are stored in the same format except that their value is null.
	 * 
	 * Attribute array is not sorted and read/writes take linear time.
	 * Since most elements have only a few attributes, oftentimes just one,
	 * this is actually faster than a hash or even a sorted list.
	 * 
	 * Since attribute processing is linear anyway, we don't waste memory on storing attribute count.
	 * We instead make sure that unused items at the end of the array are nulled.
	 * Attribute methods can then simply iterate the array until the find an entry with null name
	 * or they reach the end of the array.
	 * 
	 * Since many elements have no attributes at all, we avoid allocating attribute array if possible.
	 * Null attribute array means the element has no attributes.
	 */
	private String[] attributes;
	/*
	 * Allow direct access to the attribute array where performance matters.
	 */
	public String[] rawAttributes() {
		return attributes;
	}
	public void rawAttributes(String[] attributes) {
		this.attributes = attributes;
	}
	/*
	 * Element API is optimized for building,
	 * i.e. application code usually just appends attributes and children.
	 * It feels natural to just let applications append attributes at the end of the attribute array
	 * and provide no removal/update methods for attributes.
	 * This doesn't work though, because attributes must have unique names.
	 * We have to check for duplicates before adding any new attribute.
	 * Given that we suffer the performance cost anyway and attribute API resembles map API,
	 * we offer generalized mutable attribute API instead of pure append-only API.
	 * Attributes can be changed or erased even though the typical use case is building/appending.
	 *
	 * Modification of attribute array is particularly error-prone.
	 * We want to concentrate all complexity in one place.
	 * The following two methods implement attribute write/erase algorithms.
	 * All other attribute-modifying methods rely on them.
	 */
	private void setNullable(String name, String value) {
		touch();
		Objects.requireNonNull(name);
		int at;
		if (attributes != null) {
			at = attributes.length;
			for (int i = 0; i < attributes.length; i += 2) {
				if (attributes[i] == null || name.equals(attributes[i])) {
					at = i;
					break;
				}
			}
		} else
			at = 0;
		/*
		 * We are using standard doubling of array capacity.
		 * We start with null array and continue with 2-element array to save memory.
		 * This works well, because most elements have no attributes or only one attribute.
		 */
		if (attributes == null || at >= attributes.length)
			attributes = attributes == null ? new String[2] : Arrays.copyOf(attributes, 2 * attributes.length);
		attributes[at] = name;
		attributes[at + 1] = value;
	}
	@Override public DomElement unset(String name) {
		touch();
		Objects.requireNonNull(name);
		if (attributes != null) {
			int at = -1;
			for (int i = 0; i < attributes.length; i += 2) {
				if (attributes[i] == null)
					break;
				if (name.equals(attributes[i])) {
					at = i;
					break;
				}
			}
			if (at >= 0) {
				for (int i = at; i < attributes.length; i += 2) {
					if (attributes[i] == null)
						break;
					if (i + 2 < attributes.length) {
						attributes[i] = attributes[i + 2];
						attributes[i + 1] = attributes[i + 3];
					} else {
						attributes[i] = null;
						attributes[i + 1] = null;
					}
				}
			}
		}
		return this;
	}
	/*
	 * We can now build all attribute setters upon this foundation.
	 * While all known HTML/SVG attributes have their own specialized methods,
	 * it is important to provide good dynamic attribute API,
	 * partly because it will be used in the specialized attribute setters
	 * and party because applications often have to process DOM trees dynamically.
	 * 
	 * We allow unsetting attributes by setting them to null.
	 * While we have unset() method too, handling null parameters correctly is also important.
	 * This null trick only works for non-primitive attributes.
	 * We could provide overloads that take primitive wrappers,
	 * but that's a rare use case that would cost us dozens of rarely used specialized setters.
	 */
	@Override public DomElement set(String name, String value) {
		if (value != null)
			setNullable(name, value);
		else
			unset(name);
		return this;
	}
	@Override public DomElement set(String name, boolean value) {
		if (value)
			setNullable(name, null);
		else
			unset(name);
		return this;
	}
	@Override public DomElement set(String name) {
		setNullable(name, null);
		return this;
	}
	@Override public DomElement set(String name, int value) {
		setNullable(name, Integer.toString(value));
		return this;
	}
	@Override public DomElement set(String name, double value) {
		setNullable(name, Double.toString(value));
		return this;
	}
	@Override public DomElement set(DomAttribute attribute) {
		if (attribute != null)
			setNullable(attribute.name(), attribute.value());
		return this;
	}
	public DomElement set(Iterable<DomAttribute> attributes) {
		if (attributes != null)
			for (DomAttribute attribute : attributes)
				set(attribute);
		return this;
	}
	public DomElement set(Stream<DomAttribute> attributes) {
		if (attributes != null)
			attributes.forEach(this::set);
		return this;
	}
	/*
	 * While most DOM operations are append-only building of new DOM trees,
	 * applications often have to do DOM tree rewriting, e.g. to process templates.
	 * An easy to use attribute query API is very valuable in those cases.
	 * Plus some parts of this API are used by specialized attribute getters.
	 * 
	 * List of attributes can be returned either as a copy or a live view.
	 * The current implementation returns a copy, which is mostly justified by simplicity/laziness.
	 * Live view could be implemented and this option is not excluded from future development.
	 */
	@Override public List<DomAttribute> attributes() {
		List<DomAttribute> result = new ArrayList<>();
		if (attributes != null) {
			for (int i = 0; i < attributes.length; i += 2) {
				if (attributes[i] == null)
					break;
				result.add(new DomAttribute(attributes[i], attributes[i + 1]));
			}
		}
		return result;
	}
	@Override public DomAttribute attribute(String name) {
		if (attributes != null) {
			for (int i = 0; i < attributes.length; i += 2) {
				if (attributes[i] == null)
					break;
				if (name.equals(attributes[i]))
					return new DomAttribute(name, attributes[i + 1]);
			}
		}
		return null;
	}
	@Override public String attributeAsString(String name) {
		DomAttribute attribute = attribute(name);
		if (attribute == null)
			return null;
		return attribute.value();
	}
	@Override public boolean attributeAsBoolean(String name) {
		return attribute(name) != null;
	}
	@Override public OptionalInt attributeAsInt(String name) {
		DomAttribute attribute = attribute(name);
		if (attribute == null)
			return OptionalInt.empty();
		if (attribute.value() == null)
			return OptionalInt.empty();
		return OptionalInt.of(Integer.parseInt(attribute.value()));
	}
	@Override public OptionalDouble attributeAsDouble(String name) {
		DomAttribute attribute = attribute(name);
		if (attribute == null)
			return OptionalDouble.empty();
		if (attribute.value() == null)
			return OptionalDouble.empty();
		return OptionalDouble.of(Double.parseDouble(attribute.value()));
	}
	/*
	 * Specialized attribute setters and getters are defined in DomAttributes interface
	 * in order to avoid cluttering this class in javadoc and to make them easier to generate.
	 *
	 * Listeners are an open problem. This is likely just a temporary implementation.
	 * Listeners are stored as a simple array that is kept deduplicated.
	 * The array is null-terminated, so that we don't have to keep around separate listener count.
	 */
	private DomListener[] listeners;
	/*
	 * Allow direct access to the listener array where performance matters.
	 */
	public DomListener[] rawListeners() {
		return listeners;
	}
	public void rawListeners(DomListener[] listeners) {
		this.listeners = listeners;
	}
	public DomElement subscribe(DomListener listener) {
		touch();
		if (listener != null) {
			if (listeners != null) {
				for (int i = 0; i < listeners.length; ++i) {
					DomListener old = listeners[i];
					if (old == null) {
						listeners[i] = listener;
						return this;
					}
					DomListener updated = old.combine(listener);
					if (updated != null) {
						listeners[i] = updated;
						return this;
					}
				}
				int end = listeners.length;
				listeners = Arrays.copyOf(listeners, 2 * listeners.length);
				listeners[end] = listener;
				return this;
			}
			listeners = new DomListener[1];
			listeners[0] = listener;
		}
		return this;
	}
	public List<DomListener> listeners() {
		List<DomListener> result = new ArrayList<>();
		if (listeners != null) {
			for (int i = 0; i < listeners.length; ++i) {
				if (listeners[i] == null)
					break;
				result.add(listeners[i]);
			}
		}
		return result;
	}
	/*
	 * Child manipulation API is defined on DomContainer.
	 * We only have to override setters here to fix return type of fluent methods.
	 */
	/**
	 * Adds new child node to this element.
	 * 
	 * @param child
	 *            {@inheritDoc}
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if this element is frozen
	 * @see #add(String)
	 */
	@Override public DomElement add(DomContent child) {
		super.add(child);
		return this;
	}
	/**
	 * Adds all nodes in a {@link Collection} to this element.
	 * 
	 * @param children
	 *            {@inheritDoc}
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if this element is frozen
	 * @see #add(DomContent)
	 */
	@Override public <C extends DomContent> DomElement add(Collection<C> children) {
		super.add(children);
		return this;
	}
	/**
	 * Adds all nodes in a {@link Stream} to this element.
	 * 
	 * @param children
	 *            {@inheritDoc}
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if this element is frozen
	 * @see #add(DomContent)
	 */
	@Override public <C extends DomContent> DomElement add(Stream<C> children) {
		super.add(children);
		return this;
	}
	/**
	 * Adds literal text to this element
	 * The text is first wrapped in {@link DomText}.
	 * If the text is null or empty, this method has no effect.
	 * If there is already some text at the end of this element,
	 * this method replaces it with concatenation with the supplied text.
	 * 
	 * @param text
	 *            {@inheritDoc}
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if this element is frozen
	 * @see #add(DomContent)
	 */
	@Override public DomElement add(String text) {
		super.add(text);
		return this;
	}
	/*
	 * There is however one child query API that cannot be defined on DomContainer.
	 * It returns an element stream with this element prepended to its descendants.
	 */
	public Stream<DomElement> descendantsAndSelf() {
		return Stream.concat(Stream.of(this), descendants());
	}
	/*
	 * Elements are treated as values. They can be copied, compared for equality, hashed, and assigned.
	 * We provide both copy constructor and clone() method for this.
	 * Copies are always unfrozen, because the most common use for copying is to modify the copy.
	 * Comparison and hashing ignores freezing, because freezing is merely a marker for shared data.
	 */
	/**
	 * Creates deep unfrozen copy of another element.
	 * 
	 * @param other
	 *            element to clone
	 * @throws NullPointerException
	 *             if the parameter is {@code null}
	 */
	public DomElement(DomElement other) {
		super(other);
		tagname = other.tagname;
		key = other.key;
		id = other.id;
		attributes = shrink(other.attributes, 2);
		listeners = shrink(other.listeners, 1);
	}
	/**
	 * Creates deep unfrozen clone of this element.
	 * All child nodes are cloned recursively.
	 * The clone is completely independent of this element.
	 * 
	 * @return {@inheritDoc}
	 */
	@Override public DomElement clone() {
		return new DomElement(this);
	}
	/**
	 * Compares two elements.
	 * This method compares {@link #tagname()}, link #id()}, {@link #key()}, attributes, listeners, and element's children.
	 * Children are compared recursively by calling their {@link DomContent#equals(Object)} methods.
	 * If the only difference is that one of the elements is frozen and the other is not, this method returns true.
	 * Order of attributes and listeners is significant. Listeners must have identical callback pointers to compare equal.
	 * 
	 * @param object
	 *            object to compare this element with
	 * @return {@code true} if the two elements are equal, {@code false} otherwise
	 */
	@Override public boolean equals(Object object) {
		if (this == object)
			return true;
		if (!super.equals(object))
			return false;
		DomElement other = (DomElement)object;
		if (!Objects.equals(key, other.key) || !Objects.equals(id, other.id))
			return false;
		return arraysEqual(attributes, other.attributes, 2) && arraysEqual(listeners, other.listeners, 1);
	}
	private <T> boolean arraysEqual(T[] left, T[] right, int step) {
		if (left == right || left == null && right == null)
			return true;
		if (left == null)
			return right[0] == null;
		if (right == null)
			return left[0] == null;
		int max = Math.max(left.length, right.length);
		for (int i = 0; i < max; i += step) {
			if (i >= left.length)
				return right[i] == null;
			if (i >= right.length)
				return left[i] == null;
			if (left[i] == null && right[i] == null)
				break;
			for (int j = 0; j < step; ++j)
				if (!Objects.equals(left[i + j], right[i + j]))
					return false;
		}
		return true;
	}
	/**
	 * Computes deep hash code of the element.
	 * Hash code changes with {@link #tagname()}, link #id()}, {@link #key()}, attributes, listeners, and element's children
	 * Freezing the element doesn't change its hash code.
	 * Order of attributes and listeners is significant. Listeners must have identical callback pointers to have the same hash code.
	 * 
	 * @return hash code of the element
	 */
	@Override public int hashCode() {
		int hash = super.hashCode();
		hash = 31 * hash + Objects.hashCode(key);
		hash = 31 * hash + Objects.hashCode(id);
		hash = hashArray(hash, attributes, 2);
		hash = hashArray(hash, listeners, 1);
		return hash;
	}
	private <T> int hashArray(int initial, T[] array, int step) {
		int hash = initial;
		if (array != null) {
			for (int i = 0; i < array.length; i += step) {
				if (array[i] == null)
					break;
				for (int j = 0; j < step; ++j)
					hash = 31 * hash + Objects.hashCode(array[i + j]);
			}
		}
		return hash;
	}
	public DomElement assign(DomElement other) {
		touch();
		tagname = other.tagname;
		id = other.id;
		key = other.key;
		attributes = shrink(other.attributes, 2);
		listeners = shrink(other.listeners, 1);
		assignChildren(other);
		return this;
	}
	/**
	 * Protects this element from further modification.
	 * For more information, use cases, and thread safety, see {@link DomContent#freeze()}.
	 * 
	 * @return {@code this}
	 */
	@Override public DomElement freeze() {
		if (!frozen) {
			super.freeze();
			attributes = shrink(attributes, 2);
			listeners = shrink(listeners, 1);
		}
		return this;
	}
	private static <T> T[] shrink(T[] array, int increment) {
		if (array == null)
			return null;
		int count = 0;
		while (count < array.length && array[count] != null)
			count += increment;
		if (count == 0)
			return null;
		return Arrays.copyOf(array, count);
	}
	/*
	 * Create poster frame that is devoid of listeners.
	 */
	public DomElement toPoster() {
		if (!frozen)
			throw new IllegalStateException("Poster can be computed only from frozen DOM tree");
		DomContent[] newChildren = null;
		for (int i = 0; i < size; ++i) {
			DomContent child = children[i];
			if (child instanceof DomElement) {
				DomElement childPoster = ((DomElement)child).toPoster();
				if (childPoster != child) {
					if (newChildren == null) {
						newChildren = new DomContent[size];
						System.arraycopy(children, 0, newChildren, 0, i);
					}
					newChildren[i] = childPoster;
					continue;
				}
			}
			if (newChildren != null)
				newChildren[i] = child;
		}
		if (newChildren == null && listeners == null)
			return this;
		DomElement copy = new DomElement(tagname);
		copy.id = id;
		copy.key = key;
		copy.attributes = attributes;
		copy.size = size;
		copy.children = newChildren != null ? newChildren : children;
		copy.frozen = true;
		return copy;
	}
	/*
	 * These are specialized event handlers.
	 * The API is very immature. We will have to get back to it later.
	 */
	/**
	 * Adds event handler for {@code click} event.
	 * Single event can have multiple event handlers.
	 * This method adds new event handler to the end of the list.
	 * Event handlers are executed in order.
	 *
	 * @param handler
	 *            Event handler to add. If {@code null}, an empty event handler is added.
	 * @return {@code this}
	 */
	public DomElement onclick(Runnable handler) {
		return subscribe(new ClickEvent(handler));
	}
	/**
	 * Adds event handler for {@code change} event.
	 * Single event can have multiple event handlers.
	 * This method adds new event handler to the end of the list.
	 * Event handlers are executed in order.
	 *
	 * @param handler
	 *            Event handler to add. If {@code null}, an empty event handler is added.
	 * @return {@code this}
	 */
	public DomElement onchange(Runnable handler) {
		return subscribe(new ChangeEvent(handler));
	}
	/**
	 * Adds event handler for {@code input} event.
	 * Single event can have multiple event handlers.
	 * This method adds new event handler to the end of the list.
	 * Event handlers are executed in order.
	 *
	 * @param handler
	 *            Event handler to add. If {@code null}, an empty event handler is added.
	 * @return {@code this}
	 */
	public DomElement oninput(Runnable handler) {
		return subscribe(new InputEvent(handler));
	}
	/*
	 * These are binding attribute setters.
	 * This API is very unstable. We will have to get back to it later.
	 */
	/**
	 * Sets up two-way binding on attribute {@code checked}.
	 * Changes in the attribute are synchronized both ways between the client and the server.
	 * When parameter {@code value} changes between two invocations of {@link PushPage#document()}, the change is sent to the client.
	 * When user's actions cause the browser to change client-side value of the attribute,
	 * PushMode javascript sends the change to the server, which then invokes callback specified in {@code setter} parameter.
	 * <p>
	 * When {@code setter} completes, the next invocation of {@link PushPage#document()}
	 * should set {@code value} parameter to the same value that was passed to the {@code setter}.
	 * If the value is different, PushMode assumes that {@code setter} has performed coercion of the value.
	 * Coerced value is then sent to the client where it replaces user's input.
	 * <p>
	 * Bindings require {@link DomElement#id()} to be set. Bindings on elements with {@code null} ID are ignored.
	 *
	 * @param value
	 *            Server-supplied value of the attribute.
	 * @param setter
	 *            Callback accepting the most recent value available on the client side.
	 * @return {@code this}
	 * @throws NullPointerException
	 *             The {@code setter} parameter is {@code null}.
	 */
	public DomElement checked(boolean value, Consumer<Boolean> setter) {
		Objects.requireNonNull(setter);
		checked(value);
		subscribe(new CheckedBinding(value, setter));
		onclick((Runnable)null);
		return this;
	}
	/**
	 * Sets up two-way binding on attribute {@code checked}.
	 * Changes in the attribute are synchronized both ways between the client and the server.
	 * When parameter {@code value} changes between two invocations of {@link PushPage#document()}, the change is sent to the client.
	 * When user's actions cause the browser to change client-side value of the attribute,
	 * PushMode javascript sends the change to the server, which then invokes callback specified in {@code setter} parameter.
	 * <p>
	 * When {@code setter} completes, the next invocation of {@link PushPage#document()}
	 * should set {@code value} parameter to the same value that was passed to the {@code setter}.
	 * If the value is different, PushMode assumes that {@code setter} has performed coercion of the value.
	 * Coerced value is then sent to the client where it replaces user's input.
	 * <p>
	 * Bindings require {@link DomElement#id()} to be set. Bindings on elements with {@code null} ID are ignored.
	 *
	 * @param value
	 *            Server-supplied value of the attribute.
	 *            If {@code null}, {@code false} is substituted by this method.
	 * @param setter
	 *            Callback accepting the most recent value available on the client side.
	 * @return {@code this}
	 * @throws NullPointerException
	 *             The {@code setter} parameter is {@code null}.
	 */
	public DomElement checked(Boolean value, Consumer<Boolean> setter) {
		return checked(value != null && value, setter);
	}
	/**
	 * Sets up two-way binding on attribute {@code value}.
	 * Changes in the attribute are synchronized both ways between the client and the server.
	 * When parameter {@code value} changes between two invocations of {@link PushPage#document()}, the change is sent to the client.
	 * When user's actions cause the browser to change client-side value of the attribute,
	 * PushMode javascript sends the change to the server, which then invokes callback specified in {@code setter} parameter.
	 * <p>
	 * When {@code setter} completes, the next invocation of {@link PushPage#document()}
	 * should set {@code value} parameter to the same value that was passed to the {@code setter}.
	 * If the value is different, PushMode assumes that {@code setter} has performed coercion of the value.
	 * Coerced value is then sent to the client where it replaces user's input.
	 * <p>
	 * Bindings require {@link DomElement#id()} to be set. Bindings on elements with {@code null} ID are ignored.
	 *
	 * @param value
	 *            Server-supplied value of the attribute.
	 *            If {@code null}, empty string is substituted by this method.
	 * @param setter
	 *            Callback accepting the most recent value available on the client side.
	 * @return {@code this}
	 * @throws NullPointerException
	 *             The {@code setter} parameter is {@code null}.
	 */
	public DomElement value(String value, Consumer<String> setter) {
		Objects.requireNonNull(setter);
		if (value == null)
			value = "";
		value(value);
		subscribe(new ValueBinding(value, setter));
		oninput((Runnable)null);
		return this;
	}
	public static DomElement fromXml(org.w3c.dom.Element xml) {
		DomElement element = new DomElement(xml.getTagName());
		NamedNodeMap attributes = xml.getAttributes();
		for (int i = 0; i < attributes.getLength(); ++i) {
			Attr attribute = (Attr)attributes.item(i);
			if (attribute.getName().equals("id"))
				element.id(attribute.getValue());
			else
				element.set(attribute.getName(), attribute.getValue());
		}
		NodeList children = xml.getChildNodes();
		for (int i = 0; i < children.getLength(); ++i) {
			Node child = children.item(i);
			switch (child.getNodeType()) {
			case Node.ELEMENT_NODE:
				element.add(fromXml((Element)child));
				break;
			case Node.TEXT_NODE:
				element.add(((Text)child).getNodeValue());
				break;
			case Node.COMMENT_NODE:
				break;
			case Node.ENTITY_REFERENCE_NODE:
				throw new IllegalArgumentException("Entity references should be resolved prior to import");
			case Node.CDATA_SECTION_NODE:
				throw new IllegalArgumentException("CDATA not supported");
			default:
				throw new IllegalArgumentException("Unsupported node type");
			}
		}
		return element;
	}
	public static DomElement fromXml(String serialized) {
		return Exceptions.sneak().get(() -> {
			javax.xml.parsers.DocumentBuilder builder = javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder();
			return fromXml(builder.parse(new org.xml.sax.InputSource(new StringReader(serialized))).getDocumentElement());
		});
	}
}
