// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.dom;

import com.machinezoo.stagean.*;

/*
 * Text nodes are necessary in order to have one base class for all content in elements.
 * The alternative is to allow mixed content in elements.
 * That would however result in a lot of complexity with questionable performance gains.
 */
/**
 * Text node in PushMode DOM tree.
 * Text nodes are immutable. In order to change the text, new {@link DomText} node must be created.
 * <p>
 * {@link DomText}, being immutable, is always safe to access by multiple threads.
 */
@DraftTests
public class DomText extends DomContent {
	private final String text;
	/**
	 * Gets content of this text node.
	 * Text is always non-null even if the constructor was called with {@code null} argument.
	 * If this is a descendant of {@link DomElement} or {@link DomFragment},
	 * the text is guaranteed to be non-empty.
	 * 
	 * @return content of this text node
	 */
	@Override
	public String text() {
		return text;
	}
	/**
	 * Creates DOM text node.
	 * If the parameter is {@code null}, the created text node will contain an empty string.
	 * 
	 * @param text
	 *            content of the newly created text node
	 */
	public DomText(String text) {
		this.text = text != null ? text : "";
	}
	/**
	 * Clones the text node (no-op).
	 * Implementation of this method is required by parent {@link DomContent}.
	 * Since {@link DomText} is immutable, this method just returns {@code this}.
	 * 
	 * @return {@code this}
	 */
	@Override
	public DomText clone() {
		return this;
	}
	/**
	 * Compares texts of two {@link DomText} nodes.
	 * If the other object is not a {@link DomText} node, this method returns false.
	 * 
	 * @param object
	 *            object to compare this text node with
	 * @return {@code true} if the two text nodes are equal, {@code false} otherwise
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (!(object instanceof DomText))
			return false;
		return text.equals(((DomText)object).text);
	}
	/**
	 * Computes hash code of the text node.
	 * The returned hash code is equal to the hash code of {@link #text()}.
	 * 
	 * @return hash code of the text node
	 */
	@Override
	public int hashCode() {
		return text.hashCode();
	}
	/**
	 * Freezes this DOM tree node (no-op). Since {@link DomText} is immutable by nature, this method has no effect.
	 * It is implemented, because it is required by parent {@link DomContent}.
	 * 
	 * @return {@code this}
	 */
	@Override
	public DomText freeze() {
		return this;
	}
	/*
	 * We want to make it possible to easily embed HTML in java code.
	 * As part of this effort, we expose HTML character entity references as public fields of DomText.
	 * These fields however just reference strings with Unicode-encoded expanded values
	 * of the corresponding HTML character entity references.
	 * We never actually send character entity references to the browser in generated HTML
	 * except for special characters that are required to be escaped.
	 * 
	 * HTML defines hundreds of character entity references. It would be silly to list them all here.
	 * We will instead include only two kinds of character entity references:
	 * those used for required special character escaping in XML (quot, apos, amp, lt, gt)
	 * and those that represent whitespace that might be hard to enter literally in the code as java string.
	 */
	/**
	 * Unicode expansion of &amp;quot;.
	 */
	public static final String quot = "\"";
	/**
	 * Unicode expansion of &amp;amp;.
	 */
	public static final String amp = "&";
	/**
	 * Unicode expansion of &amp;apos;.
	 */
	public static final String apos = "'";
	/**
	 * Unicode expansion of &amp;lt;.
	 */
	public static final String lt = "<";
	/**
	 * Unicode expansion of &amp;gt;.
	 */
	public static final String gt = ">";
	/**
	 * Unicode expansion of &amp;nbsp;.
	 */
	public static final String nbsp = "\u00A0";
	/**
	 * Unicode expansion of &amp;shy;.
	 */
	public static final String shy = "\u00AD";
	/**
	 * Unicode expansion of &amp;ensp;.
	 */
	public static final String ensp = "\u2002";
	/**
	 * Unicode expansion of &amp;emsp;.
	 */
	public static final String emsp = "\u2003";
	/**
	 * Unicode expansion of &amp;thinsp;.
	 */
	public static final String thinsp = "\u2009";
	/**
	 * Unicode expansion of &amp;zwnj;.
	 */
	public static final String zwnj = "\u200C";
	/**
	 * Unicode expansion of &amp;zwj;.
	 */
	public static final String zwj = "\u200D";
	/**
	 * Unicode expansion of &amp;lrm;.
	 */
	public static final String lrm = "\u200E";
	/**
	 * Unicode expansion of &amp;rlm;.
	 */
	public static final String rlm = "\u200F";
}
