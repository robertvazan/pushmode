// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.dom;

import java.util.stream.*;

/**
 * Fragment of PushMode DOM tree.
 * Fragment can have children just like {@link DomElement}, but it has no attributes nor other element properties.
 * It's a thin extension of {@link DomContainer}.
 * <p>
 * Fragment can be added to any {@link DomContainer}, including another {@link DomFragment}.
 * When added, the fragment is automatically inlined.
 * Fragment thus cannot appear as a child of {@link DomElement} or another {@link DomFragment}.
 * <p>
 * It is recommended that application methods take and return {@link DomContent} instead of {@link DomElement},
 * which enables methods to pass around any combination of elements and plain text
 * as {@code DomFragment}, including an empty fragment, instead of just a single {@link DomElement}.
 */
public final class DomFragment extends DomContainer {
	/**
	 * Creates an empty DOM fragment.
	 */
	public DomFragment() {
	}
	/*
	 * New methods added on top of DomContainer are mostly about object copying.
	 */
	/**
	 * Creates deep mutable copy of the DOM fragment.
	 * Calling this constructor is equivalent to calling {@link #clone()}.
	 * 
	 * @param other
	 *            fragment to copy
	 * @see #clone()
	 */
	public DomFragment(DomFragment other) {
		super(other);
	}
	/**
	 * Creates mutable deep clone of this {@link DomFragment}.
	 * All child nodes are cloned recursively.
	 * The clone is completely independent of this fragment.
	 * 
	 * @return deep mutable clone
	 * @see #DomFragment(DomFragment)
	 */
	@Override public DomFragment clone() {
		return new DomFragment(this);
	}
	/**
	 * Replaces contents of this fragment with contents of another fragment.
	 * Since fragment is merely a list of child nodes,
	 * these child nodes are copied from the source fragment into this fragment.
	 * Previous content of this fragment is discarded.
	 * <p>
	 * Most of the DOM API consists of builder-like API to construct DOM trees.
	 * When DOM tree must be modified, it is recommended to completely rebuild it,
	 * perhaps using bits of the old tree in the process, and return the new tree.
	 * However, it is sometimes not possible to change the returned pointer
	 * and in those cases the fragment must be actually modified.
	 * Application should build new fragment as usual and then
	 * use this method to replace contents of the original fragment.
	 * 
	 * @param other
	 *            source fragment to be assigned to this fragment
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if this fragment is frozen
	 * @see #clone()
	 * @see #DomFragment(DomFragment)
	 */
	public DomFragment assign(DomFragment other) {
		touch();
		assignChildren(other);
		return this;
	}
	/*
	 * Equality and hash code are identical to DomContainer, but we want to specialize the javadoc.
	 */
	/**
	 * Compares content of two fragments.
	 * If the supplied object is not {@link DomFragment}, this method returns false.
	 * Equality is unaffected by whether the fragments are frozen or not.
	 * Children of both fragments are compared recursively by calling their {@link DomContent#equals(Object)} methods.
	 * 
	 * @param object
	 *            object to compare this fragment with
	 * @return {@code true} if the two fragments are equal, {@code false} otherwise
	 */
	@Override public boolean equals(Object object) {
		return super.equals(object);
	}
	/**
	 * Computes hash code of this fragment. It is computed by combining hash codes of all children.
	 * Hash code is unaffected by whether the fragment is frozen or not.
	 * 
	 * @return fragment's hash code
	 */
	@Override public int hashCode() {
		return super.hashCode();
	}
	/*
	 * Fluent methods must be overridden to return correct type.
	 * While we are at this, we also specialize javadocs for the fragment.
	 */
	/**
	 * Adds new child node to this fragment.
	 * Fragments will be inlined, {@code null}s ignored, and text concatenated.
	 * 
	 * @param child
	 *            {@inheritDoc}
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             {@inheritDoc}
	 * @see #add(String)
	 * @see #add(Iterable)
	 * @see #add(Stream)
	 */
	@Override public DomFragment add(DomContent child) {
		super.add(child);
		return this;
	}
	/**
	 * Adds all nodes in an {@link Iterable} to this fragment.
	 * Fragments will be inlined, {@code null}s ignored, and text concatenated.
	 * 
	 * @param children
	 *            {@inheritDoc}
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             {@inheritDoc}
	 * @see #add(DomContent)
	 */
	@Override public <C extends DomContent> DomFragment add(Iterable<C> children) {
		super.add(children);
		return this;
	}
	/**
	 * Adds all nodes in a {@link Stream} to this fragment.
	 * Fragments will be inlined, {@code null}s ignored, and text concatenated.
	 * 
	 * @param children
	 *            {@inheritDoc}
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             {@inheritDoc}
	 * @see #add(DomContent)
	 */
	@Override public <C extends DomContent> DomFragment add(Stream<C> children) {
		super.add(children);
		return this;
	}
	/**
	 * Adds literal text to this fragment
	 * Consecutive text nodes will be concatenated.
	 * 
	 * @param text
	 *            {@inheritDoc}
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             {@inheritDoc}
	 * @see #add(DomContent)
	 */
	@Override public DomFragment add(String text) {
		super.add(text);
		return this;
	}
	/**
	 * Protects this fragment from further modification.
	 * For more information, use cases, and thread safety, see {@link DomContent#freeze()}.
	 * 
	 * @return {@code this}
	 * @see DomContent#freeze()
	 */
	@Override public DomFragment freeze() {
		super.freeze();
		return this;
	}
	/*
	 * This is particularly useful for quickly building lists with separators.
	 * We aren't providing overloads with ellipsis (...) items,
	 * because this function is most useful for unbounded lists. Ellipsis overload would be used rarely.
	 * Streams are more likely to be used, but they are easy to convert to iterable via collect(toList()).
	 * We could define the same function as a non-static method on DomContent,
	 * which would be used implicitly as a separator, but then we wouldn't have the overload with plain string separator.
	 */
	public static DomFragment join(DomContent separator, Iterable<? extends DomContent> items) {
		DomFragment result = new DomFragment();
		boolean first = true;
		for (DomContent item : items) {
			if (first)
				first = false;
			else
				result.add(separator);
			result.add(item);
		}
		return result;
	}
	public static DomFragment join(String separator, Iterable<? extends DomContent> items) {
		return join(new DomText(separator), items);
	}
}
