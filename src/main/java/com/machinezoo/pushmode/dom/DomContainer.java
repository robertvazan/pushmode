// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.dom;

import static java.util.stream.Collectors.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.*;

/**
 * Base class for {@link DomElement} and {@link DomFragment}.
 * This class provides methods common to both,
 * which mostly includes nearly all methods for modifying and querying
 * the list of child nodes (child elements and text).
 * <p>
 * Since {@link DomFragment} is nothing more than a list of child nodes,
 * {@link DomFragment} is implemented as a thin wrapper around {@code DomContainer}.
 * <p>
 * Children are of type {@link DomContent}. They are either {@link DomElement} or {@link DomText}.
 * When {@link DomFragment} is added to another fragment or to an element, it is inlined.
 * Fragments cannot be children of elements or other fragments.
 * There are never two consecutive {@link DomText} nodes in the child list.
 * If text is appended to a child list that already ends with text node,
 * a new {@link DomText} node is created in its place that contains both strings concatenated together.
 * <p>
 * Due to this non-trivial manipulation of appended content,
 * it doesn't make much sense to provide list-like functionality (set, insert, or remove children)
 * and the whole child manipulation API is append-only.
 * It fits the typical functional programming use case of building DOM trees from scratch.
 * If DOM tree modification is needed, for example to expand templates,
 * apply DOM tree filters, or to import external content,
 * the best approach is to build new DOM tree with desired changes applied and discard the original DOM tree.
 * The only mutating method we support is {@code children().clear()},
 * so that contents of this {@code DomContainer} can be replaced without replacing the whole object.
 * <p>
 * Since using the high-level builder and query API might be too much overhead in some cases,
 * a low-level raw access API is provided too. It is application's responsibility to use the raw API correctly.
 */
public abstract class DomContainer extends DomContent {
	DomContainer() {
	}
	/*
	 * We are storing children in an array with child count in separate field.
	 * Child count field is necessary for performant appending to elements with many children.
	 * The array can be null if there are no children,
	 * which reduces memory consumption, because many elements are indeed empty.
	 * Unused items at the end of the array are nulled to avoid memory leaks.
	 * 
	 * We allow direct access to the child buffer via raw API where performance matters.
	 */
	int size;
	DomContent[] children;
	/**
	 * Gets number of child nodes.
	 * This is a raw API equivalent to calling {@code children().size()}.
	 * 
	 * @return number of child nodes
	 * @see #rawChildren()
	 * @see #rawChildren(int, DomContent[])
	 */
	public int rawChildCount() {
		return size;
	}
	/**
	 * Gets child node buffer.
	 * This is a raw API returning essentially the same information as {@link #children()}.
	 * <p>
	 * The buffer may be larger than the number of child nodes actually present.
	 * Extra entries are filled with {@code null}s.
	 * If there are no children, the buffer may be {@code null}.
	 * <p>
	 * The returned array may be modified, but application should
	 * satisfy the same requirements as with {@link #rawChildren(int, DomContent[])}.
	 * 
	 * @return {@code null}-padded array of child nodes or {@code null}
	 * @see #rawChildCount()
	 * @see #rawChildren(int, DomContent[])
	 */
	public DomContent[] rawChildren() {
		return children;
	}
	/**
	 * Sets child node buffer.
	 * This is a raw API to be used under extreme circumstances.
	 * The proper high-level way to fill child node buffer is to use the many {@code add()} methods.
	 * If child list has to be modified, the high-level way to do it
	 * is to rebuild the whole element/fragment.
	 * <p>
	 * The new child buffer must satisfy several requirements.
	 * It can only contain {@link DomElement} and {@link DomText} instances
	 * and there must be no consecutive text nodes and no {@code null} nodes.
	 * If {@code count} is smaller than the size of the buffer,
	 * unused entries at the end of the buffer must be {@code null}.
	 * The buffer itself may be {@code null} if {@code count} is zero.
	 * These requirements are not checked. If application fails to satisfy them,
	 * it may result in surprising behavior or exceptions.
	 * <p>
	 * This method does no check whether the container is {@link #frozen}.
	 * This is application's responsibility. Modifying frozen containers is unsafe.
	 * 
	 * @param count
	 *            new child count
	 * @param children
	 *            new child node buffer or {@code null}
	 * @see #rawChildCount()
	 * @see #rawChildren()
	 * @see #add(DomContent)
	 */
	public void rawChildren(int count, DomContent[] children) {
		this.children = children;
		this.size = count;
	}
	/*
	 * Append logic is quite complicated. We have one method that fully implements it.
	 * All higher level appenders rely on this one method.
	 */
	private void reserveOne() {
		/*
		 * Child buffer growth is standard doubling pattern.
		 * We start with buffer of size 1, because it is common for elements to have just one child
		 * and we want to save RAM at the cost of some extra allocations for longer elements.
		 */
		if (children == null)
			children = new DomContent[1];
		else if (size >= children.length)
			children = Arrays.copyOf(children, 2 * size);
	}
	/**
	 * Adds new child node to the list of children.
	 * Fragments will be inlined, {@code null}s ignored, and text concatenated.
	 * 
	 * @param child
	 *            node to add (ignored if {@code null})
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if this element or fragment is frozen
	 * @see #add(String)
	 * @see #add(Collection)
	 * @see #add(Stream)
	 */
	public DomContainer add(DomContent child) {
		touch();
		/*
		 * Null tolerance is very important. It avoids lots of null checks on application side.
		 * It also makes it easy to create application logic with optional result.
		 */
		if (child != null) {
			if (child instanceof DomElement) {
				/*
				 * Elements are the only kind of content node that can be always simply appended.
				 */
				reserveOne();
				children[size] = child;
				++size;
			} else if (child instanceof DomText) {
				/*
				 * Text nodes require merging if there is another text node before them.
				 * We also have to ignore text nodes with empty or null string.
				 * This ensures that all constructed elements and fragments can be serialized into valid HTML
				 * and node indexes in the HTML will correspond to node indexes in the element or fragment.
				 */
				DomText node = (DomText)child;
				if (!node.text().isEmpty()) {
					if (size > 0 && children[size - 1] instanceof DomText) {
						children[size - 1] = new DomText(((DomText)children[size - 1]).text() + node.text());
					} else {
						reserveOne();
						children[size] = child;
						++size;
					}
				}
			} else if (child instanceof DomFragment) {
				/*
				 * Fragments are inlined immediately. Allowing them in elements or other fragments
				 * would require later compacting/normalization step, which would be expensive and inconvenient.
				 */
				DomFragment fragment = (DomFragment)child;
				for (int i = 0; i < fragment.size; ++i)
					add(fragment.children[i]);
			} else {
				/*
				 * Nothing stops users from deriving their own class from DomContent.
				 * We don't want to deal with such cases, so let's just throw when someone tries to do it.
				 */
				throw new IllegalArgumentException();
			}
		}
		return this;
	}
	/*
	 * We will define two higher level methods for collections and streams.
	 * Array overload could be implemented too, but that's a rare use case that is usually a mistake anyway.
	 * Collections should be used by applications as a superior alternative to arrays whenever possible.
	 * If array really needs to be added, it is easy to wrap it with Arrays.asList().
	 * 
	 * We don't support Iterable, because it is hard to find useful Iterable that is not a Collection.
	 * Since people implement Iterable on things that are not really iterable (for example streams, hello StreamEx),
	 * we would be just asking for ambiguous method call errors here.
	 */
	/**
	 * Adds all nodes in a {@link Collection} to the list of children.
	 * Fragments will be inlined, {@code null}s ignored, and text concatenated.
	 * 
	 * @param <C>
	 *            item type
	 * @param children
	 *            collection of child nodes to add (ignored if {@code null})
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if this element or fragment is frozen
	 * @see #add(DomContent)
	 */
	public <C extends DomContent> DomContainer add(Collection<C> children) {
		if (children != null)
			for (C child : children)
				add(child);
		return this;
	}
	/**
	 * Adds all nodes in a {@link Stream} to the list of children.
	 * Fragments will be inlined, {@code null}s ignored, and text concatenated.
	 * 
	 * @param <C>
	 *            item type
	 * @param children
	 *            {@link Stream} of child nodes to add (ignored if {@code null})
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if this element or fragment is frozen
	 * @see #add(DomContent)
	 */
	public <C extends DomContent> DomContainer add(Stream<C> children) {
		if (children != null)
			children.forEach(c -> add(c));
		return this;
	}
	/*
	 * Since adding plain text is a frequent use case,
	 * we provide a method for it instead of forcing applications to construct DomText.
	 * 
	 * Name add() is overloaded instead of creating text(String) setter,
	 * because we want it to be clear that this is appending method.
	 * Method text(String) could be added in the future and
	 * it would replace all children with provided text.
	 * 
	 * It is tempting to add more overloads or even
	 * accepting Object parameter and intelligently converting it to string.
	 * This would however make the API untyped and thus very dangerous to use.
	 * It would also open Pandora's box of complexity in the implementation.
	 */
	/**
	 * Adds literal text to the list of children.
	 * Consecutive text nodes will be concatenated.
	 * 
	 * @param text
	 *            text to add (ignored if {@code null} or empty)
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if this element or fragment is frozen
	 * @see #add(DomContent)
	 */
	public DomContainer add(String text) {
		if (text != null && !text.isEmpty())
			add(new DomText(text));
		return this;
	}
	/*
	 * Assignment is the only way to modify already appended children.
	 * Assignment operation is exposed only on element and fragment,
	 * so we will just expose package method here to share code between the two.
	 * 
	 * Assignment doesn't clone the children.
	 * This is not always desirable in all use cases.
	 */
	void assignChildren(DomContainer other) {
		size = other.size;
		children = size > 0 ? Arrays.copyOf(other.children, size) : null;
	}
	/*
	 * While it is very hard to think of a list-like API for modifying the child list,
	 * it is perfectly reasonable to read the child list as a List<DomContent>.
	 * 
	 * Child list is exposed as a live view.
	 * While returning a copy would be easier to implement, exposing a live view has several advantages.
	 * It lets us expose child count in O(1) as .children().size() without having to add another method to DomContainer.
	 * It supports O(1) indexed access to the child list when the application needs only one child, for example the last one.
	 * It also lets us support clear() as the only mutating operation on the child list without extra method on this class.
	 * And it's likely more performant than constructing new ArrayList.
	 * 
	 * The live view object is constructed anew upon every call.
	 * We could cache it, but this would actually worsen average performance (RAM and CPU),
	 * because most application code doesn't query the child list at all, let alone multiple times.
	 */
	/**
	 * Lists all children in this element or fragment.
	 * The list is a live view of the internal representation of the child list.
	 * It will reflect changes made through other methods of this class.
	 * The returned list itself is unmodifiable except for {@link List#clear()}.
	 * <p>
	 * All methods of the returned {@link List} have time complexity like methods of {@link ArrayList}
	 * except {@link List#subList(int, int)}, which is currently implemented as returning copy of the sublist rather than a view.
	 * 
	 * @return list of all children
	 * @see #elements()
	 * @see #descendants()
	 */
	public List<DomContent> children() {
		return new ChildList();
	}
	private class ChildIterator implements ListIterator<DomContent> {
		int cursor;
		ChildIterator() {
		}
		ChildIterator(int cursor) {
			this.cursor = cursor;
		}
		@Override
		public void add(DomContent child) {
			throw new UnsupportedOperationException();
		}
		@Override
		public boolean hasNext() {
			return cursor < size;
		}
		@Override
		public boolean hasPrevious() {
			return cursor > 0;
		}
		@Override
		public DomContent next() {
			if (cursor < 0 || cursor >= size)
				throw new IndexOutOfBoundsException();
			++cursor;
			return children[cursor - 1];
		}
		@Override
		public int nextIndex() {
			return cursor;
		}
		@Override
		public DomContent previous() {
			if (cursor <= 0 || cursor > size)
				throw new IndexOutOfBoundsException();
			--cursor;
			return children[cursor];
		}
		@Override
		public int previousIndex() {
			return cursor - 1;
		}
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		@Override
		public void set(DomContent child) {
			throw new UnsupportedOperationException();
		}
		@Override
		public String toString() {
			return "@" + cursor + " in " + DomContainer.this.toString();
		}
	}
	private class ChildList implements List<DomContent> {
		@Override
		public boolean add(DomContent child) {
			throw new UnsupportedOperationException();
		}
		@Override
		public void add(int index, DomContent child) {
			throw new UnsupportedOperationException();
		}
		@Override
		public boolean addAll(Collection<? extends DomContent> collection) {
			throw new UnsupportedOperationException();
		}
		@Override
		public boolean addAll(int index, Collection<? extends DomContent> collection) {
			throw new UnsupportedOperationException();
		}
		@Override
		public void clear() {
			size = 0;
			children = null;
		}
		@Override
		public boolean contains(Object object) {
			return new ArrayList<>(this).contains(object);
		}
		@Override
		public boolean containsAll(Collection<?> collection) {
			return new ArrayList<>(this).containsAll(collection);
		}
		@Override
		public DomContent get(int index) {
			if (index < 0 || index >= size)
				throw new IndexOutOfBoundsException();
			return children[index];
		}
		@Override
		public int indexOf(Object object) {
			return new ArrayList<>(this).indexOf(object);
		}
		@Override
		public boolean isEmpty() {
			return size <= 0;
		}
		@Override
		public Iterator<DomContent> iterator() {
			return new ChildIterator();
		}
		@Override
		public int lastIndexOf(Object object) {
			return new ArrayList<>(this).lastIndexOf(object);
		}
		@Override
		public ListIterator<DomContent> listIterator() {
			return new ChildIterator();
		}
		@Override
		public ListIterator<DomContent> listIterator(int index) {
			return new ChildIterator(index);
		}
		@Override
		public DomContent remove(int index) {
			throw new UnsupportedOperationException();
		}
		@Override
		public boolean remove(Object object) {
			throw new UnsupportedOperationException();
		}
		@Override
		public boolean removeAll(Collection<?> collection) {
			throw new UnsupportedOperationException();
		}
		@Override
		public boolean retainAll(Collection<?> collection) {
			throw new UnsupportedOperationException();
		}
		@Override
		public DomContent set(int index, DomContent child) {
			Objects.requireNonNull(child);
			throw new UnsupportedOperationException();
		}
		@Override
		public int size() {
			return size;
		}
		@Override
		public List<DomContent> subList(int fromIndex, int toIndex) {
			return Collections.unmodifiableList(new ArrayList<>(this).subList(fromIndex, toIndex));
		}
		@Override
		public Object[] toArray() {
			return toArray(new Object[0]);
		}
		@SuppressWarnings("unchecked")
		@Override
		public <T> T[] toArray(T[] array) {
			if (size > array.length)
				array = (T[])Array.newInstance(array.getClass().getComponentType(), size);
			System.arraycopy(children, 0, array, 0, size);
			if (size < array.length)
				array[size] = null;
			return array;
		}
		@Override
		public String toString() {
			DomFormatter formatter = DomFormatter.fragment();
			for (int i = 0; i < size; ++i)
				formatter.format(children[i]);
			return formatter.toString();
		}
	}
	/*
	 * While children() method is as good as it gets for accessing mixed elements and text,
	 * it is possible to construct an easier to use query API for element children.
	 * 
	 * The two main methods are elements() and descendants().
	 * DomElement additionally defines descendantsAndSelf().
	 * Application can then filter down the element stream as it sees fit.
	 * We provide prefiltered query by element name for convenience.
	 */
	/**
	 * Enumerates element children.
	 * This is a convenience filter applied to {@link #children()}.
	 * 
	 * @return stream of children of type {@link DomElement}
	 * @see #children()
	 * @see #elements(String)
	 * @see #descendants()
	 */
	public Stream<DomElement> elements() {
		return children().stream()
			.filter(c -> c instanceof DomElement)
			.map(c -> (DomElement)c);
	}
	/**
	 * Enumerates descendant elements.
	 * Elements are traversed in pre-order depth-first manner.
	 * The returned stream is constructed incrementally as it is consumed.
	 * {@link DomElement} provides method {@link DomElement#descendantsAndSelf()}.
	 * 
	 * @return stream of descendant elements
	 * @see #elements()
	 * @see DomElement#descendantsAndSelf()
	 */
	public Stream<DomElement> descendants() {
		return elements().flatMap(DomElement::descendantsAndSelf);
	}
	/**
	 * Enumerates elements with given tagname.
	 * This is a convenience filter applied to {@link #children()}.
	 * 
	 * @param name
	 *            tagname to look for
	 * @return stream of elements with the tagname
	 * @see #children()
	 * @see #elements()
	 */
	public Stream<DomElement> elements(String name) {
		return elements().filter(e -> e.tagname().equals(name));
	}
	/**
	 * Finds element with given tagname.
	 * This is a convenience filter applied to {@link #children()}.
	 * 
	 * @param name
	 *            tagname to look for
	 * @return element with the tagname or an empty {@link Optional}
	 * @see #elements(String)
	 * @see #children()
	 * @see #elements()
	 */
	public Optional<DomElement> element(String name) {
		return elements(name).findFirst();
	}
	/*
	 * Many elements are text-only and we need a convenient way to get the text out of them.
	 * Additionally, it is sometimes useful to extract plaintext from richtext DOM tree.
	 */
	@Override
	public String text() {
		return children().stream().map(DomContent::text).collect(joining());
	}
	/*
	 * Freezing is implemented with a simple boolean flag.
	 * This flag is also used by DomElement to freeze attributes and other element features.
	 */
	boolean frozen;
	/*
	 * Allow direct freezing without recursion or compacting where performance matters.
	 */
	/**
	 * Marks the element or fragment as frozen.
	 * This is a raw API that merely marks the container as frozen
	 * without performing any other tasks like {@link #freeze()}.
	 */
	public void rawFreeze() {
		frozen = true;
	}
	/*
	 * All mutating operations must call touch() at the beginning
	 * to prevent writes to frozen DOM tree.
	 */
	void touch() {
		if (frozen)
			throw new IllegalStateException("DOM tree is frozen");
	}
	/**
	 * Protects this element or fragment from further modification.
	 * For more information, use cases, and thread safety, see {@link DomContent#freeze()}.
	 * 
	 * @return {@code this}
	 */
	@Override
	public DomContainer freeze() {
		if (!frozen) {
			frozen = true;
			if (size <= 0)
				children = null;
			else if (size < children.length)
				children = Arrays.copyOf(children, size);
			for (int i = 0; i < size; ++i)
				children[i].freeze();
		}
		return this;
	}
	/*
	 * We have to provide base code for copying, equality comparison, and hashing.
	 */
	/**
	 * Creates mutable deep clone of this element or fragment.
	 * All child nodes are cloned recursively.
	 * The clone is completely independent of this node.
	 * 
	 * @return deep mutable clone
	 */
	@Override
	public abstract DomContainer clone();
	DomContainer(DomContainer other) {
		size = other.size;
		if (size > 0) {
			children = new DomContent[size];
			for (int i = 0; i < size; ++i)
				children[i] = other.children[i].clone();
		}
	}
	/**
	 * Compares content of two {@link DomContainer} nodes.
	 * If the two nodes are of different type (i.e. one is {@link DomElement} and the other one {@link DomFragment}), this method returns false.
	 * Equality is unaffected by whether the element/fragment is frozen or not.
	 * Children of both containers are compared recursively by calling their {@link DomContent#equals(Object)} methods.
	 * 
	 * @param object
	 *            object to compare this container with
	 * @return {@code true} if the two nodes are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (!(object instanceof DomContainer))
			return false;
		DomContainer other = (DomContainer)object;
		if (size != other.size)
			return false;
		for (int i = 0; i < size; ++i)
			if (!children[i].equals(other.children[i]))
				return false;
		return true;
	}
	/**
	 * Computes hash code of this container. It is computed by combining hash codes of all children.
	 * Hash code is unaffected by whether the element/fragment is frozen or not.
	 * 
	 * @return container's hash code
	 */
	@Override
	public int hashCode() {
		int hash = getClass().hashCode();
		hash = 31 * hash + size;
		for (int i = 0; i < size; ++i)
			hash = 31 * hash + children[i].hashCode();
		return hash;
	}
}
