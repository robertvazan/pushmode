// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.dom;

import com.machinezoo.stagean.*;

/**
 * Base class for all PushMode DOM nodes that can be added to an element,
 * specifically {@link DomElement}, {@link DomText}, and {@link DomFragment}.
 * <p>
 * DOM nodes can be mutable ({@link DomElement} and {@link DomFragment}) or immutable ({@link DomText}).
 * Mutable nodes can be frozen, which causes all mutating methods to throw and improves thread-safety.
 * DOM nodes can be thus in three states: immutable, mutable, and frozen.
 * Immutable state cannot be changed. Transition from mutable to frozen state is performed by {@link #freeze()}.
 * Transition from frozen to mutable state is performed by {@link #clone()} or appropriate copy constructor.
 * <p>
 * DOM content has value semantics. It can be copied, compared for equality, and hashed.
 * Elements and fragments can be assigned.
 * <p>
 * HTML representation of the node can be obtained by using {@link DomFormatter}.
 * For debugging purposes, it is also available from {@link #toString()}.
 * All text stored recursively in the DOM node can be obtained by calling {@link #text()}.
 * <p>
 * DOM content is safe to read concurrently by multiple threads.
 * Write operations cannot be concurrent with reads or other writes.
 */
@NoTests
public abstract class DomContent implements Cloneable {
	DomContent() {
	}
	/**
	 * Creates deep clone of this node.
	 * If this node is immutable, this method just returns {@code this}.
	 * For frozen and mutable nodes, this method returns mutable deep clone that is completely independent of this node.
	 * 
	 * @return deep mutable clone or {@code this} for immutable nodes
	 */
	@Override
	public abstract DomContent clone();
	/*
	 * Equality and hashing merely forwards to base class by default.
	 * Actual comparison and hashing must be implemented by derived classes.
	 * This code is here only so that we can attach javadoc to it.
	 */
	/**
	 * Compares this node with another DOM node for equality.
	 * Equality considers the type of the node, all its properties, and recursively its children.
	 * Equality is unaffected by whether the node is frozen or not.
	 *
	 * @param obj
	 *            the other DOM node to compare this node to
	 * @return {@code true} if the two nodes are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	/**
	 * Computes deep hash code of the node.
	 * Hash code is unaffected by whether the node is frozen or not.
	 * 
	 * @return hash code of the node
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	/**
	 * Protects this node from further modification. Mutating methods throw {@link IllegalStateException} when executed on frozen node.
	 * Freezing is recursive. Frozen nodes therefore cannot contain mutable nodes.
	 * If this node is immutable by nature (i.e. {@link DomText}) or already frozen, this method has no effect.
	 * <p>
	 * Freezing is particularly useful in caches that provide the same DOM tree to multiple threads.
	 * While unfrozen DOM trees can be safely shared, this relies on threads voluntarily abstaining from modifications.
	 * Caches can protect consistency of data they return by freezing the returned DOM tree,
	 * which will effectively enable a runtime check causing any mutating methods to throw.
	 * If some thread runs buggy code that tries to modify the DOM tree obtained from shared cache,
	 * the buggy code will throw an exception while the cached DOM tree is left intact.
	 * <p>
	 * Besides marking the node as frozen, freezing modifies internal data structures to save memory.
	 * All internal arrays are compacted to their minimum required size.
	 * <p>
	 * Freezing is carefully implemented in such a way that multiple threads can safely attempt to freeze the same node at the same time.
	 * Freezing is also safe to run concurrently with reads. This is an exception to the general prohibition of concurrent modifications.
	 * This exception was added to ensure that downstream cache can freeze its output
	 * even though it incorporates unfrozen subtree obtained from upstream cache.
	 * The nested DOM tree from upstream cache is frozen as a byproduct of freezing parent DOM tree in the downstream cache.
	 * Since freezing doesn't change anything observable about the DOM tree except for blocking writes,
	 * freezing is also safe for the upstream cache.
	 * 
	 * @return {@code this}
	 */
	public abstract DomContent freeze();
	/**
	 * Gets concatenated text content of this node.
	 * Text is concatenated recursively in the same order in which it would appear in HTML.
	 * Attribute values are not included.
	 * No whitespace normalization is performed.
	 * 
	 * @return all text content in this node
	 */
	@DraftApi("perhaps add space around block elements and some other elements (br), perhaps normalize whitespace")
	public abstract String text();
	/**
	 * Gets serialized HTML code for this node for debugging purposes.
	 * HTML returned by this method is not guaranteed to be stable across releases or even usable in browsers.
	 * Use {@link DomFormatter} to reliably produce HTML.
	 * 
	 * @return HTML representation of this node
	 */
	@Override
	public String toString() {
		return DomFormatter.fragment()
			.format(this)
			.toString();
	}
}
