// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.dom;

import java.util.*;

/*
 * DOM tree serializer that outputs HTML5, SVG, or HTML/SVG fragments.
 * It is implemented as a separate object rather than a method on Dom* classes
 * in order to keep Dom* classes limited to data structure role.
 */
public class DomFormatter {
	/*
	 * We want String output nearly always.
	 * Alternate outputs could be supported in the future,
	 * but for now we are happy with StringBuilder.toString().
	 */
	private StringBuilder buffer = new StringBuilder();
	@Override public String toString() {
		return buffer.toString();
	}
	/*
	 * Page ID is attached to the output by serializer.
	 * Application doesn't have to worry about attaching it to the page.
	 * Page ID is not present in diffs. It's only in the initial HTTP response.
	 * By attaching it here, we can make most code unaware of it.
	 */
	private List<DomAttribute> extra = new ArrayList<>();
	public DomFormatter pageId(String pageId) {
		extra.add(new DomAttribute("data-pushmode-stream", pageId));
		return this;
	}
	/*
	 * Instead of numerous configuration options,
	 * we only offer a few named constructors that cover the common cases.
	 */
	private boolean isXml;
	private DomFormatter() {
	}
	public static DomFormatter html() {
		DomFormatter formatter = new DomFormatter();
		formatter.buffer.append("<!DOCTYPE html>\n");
		return formatter;
	}
	public static DomFormatter svg() {
		DomFormatter formatter = new DomFormatter();
		formatter.extra.add(new DomAttribute("xmlns", "http://www.w3.org/2000/svg"));
		formatter.isXml = true;
		return formatter;
	}
	public static DomFormatter fragment() {
		return new DomFormatter();
	}
	/*
	 * Formatting method is fluent instead of returning the output string.
	 * This is a preparation for future extensions that could produce other kinds of output.
	 * 
	 * It could be called multiple times to concatenate fragments,
	 * but that's an unlikely use case.
	 */
	public DomFormatter format(DomContent content) {
		if (!extra.isEmpty()) {
			if (!(content instanceof DomElement))
				throw new IllegalArgumentException("Must be an element.");
			/*
			 * When attaching page ID or xmlns, there's a question of where to do it.
			 * It's simpler and more performant to rewrite the root element here
			 * than to implement serialization differently for the root element just to include extra attributes there.
			 */
			DomElement root = (DomElement)content;
			DomElement tagged = new DomElement(root.tagname())
				.key(root.key())
				.id(root.id())
				.set(root.attributes())
				.add(root.children())
				.set(extra);
			for (DomListener listener : root.listeners())
				tagged.subscribe(listener);
			content = tagged;
		}
		if (content != null) {
			/*
			 * DomFragment can appear only as a top-level node in the DOM tree.
			 * We are excluding it from content() method below for performance reasons.
			 */
			if (content instanceof DomFragment)
				children((DomFragment)content);
			else
				content(content);
		}
		return this;
	}
	private void content(DomContent content) {
		if (content instanceof DomElement)
			element((DomElement)content);
		else if (content instanceof DomText)
			text((DomText)content);
		else
			throw new IllegalArgumentException();
	}
	private void element(DomElement element) {
		buffer.append('<');
		buffer.append(element.tagname());
		if (element.id() != null)
			attribute("id", element.id());
		String[] attributes = element.rawAttributes();
		if (attributes != null) {
			for (int i = 0; i < attributes.length; i += 2) {
				if (attributes[i] == null)
					break;
				attribute(attributes[i], attributes[i + 1]);
			}
		}
		if (isXml) {
			if (hasChildren(element)) {
				buffer.append(">");
				children(element);
				buffer.append("</");
				buffer.append(element.tagname());
				buffer.append('>');
			} else
				buffer.append("/>");
		} else {
			buffer.append('>');
			if (!isVoid(element.tagname())) {
				children(element);
				buffer.append("</");
				buffer.append(element.tagname());
				buffer.append('>');
			}
		}
	}
	private boolean isVoid(String tagname) {
		switch (tagname) {
		case "area":
		case "base":
		case "br":
		case "col":
		case "embed":
		case "hr":
		case "img":
		case "input":
		case "link":
		case "meta":
		case "param":
		case "source":
		case "track":
		case "wbr":
			return true;
		default:
			return false;
		}
	}
	private void attribute(String name, String value) {
		buffer.append(' ');
		buffer.append(name);
		if (value != null) {
			/*
			 * By default, we quote attributes with double quotes.
			 * We can however switch to single quotes
			 * if it reduces amount of escaping in the attribute value.
			 */
			boolean single = value.indexOf('\"') >= 0 && value.indexOf('\'') == 0;
			buffer.append('=');
			buffer.append(single ? '\'' : '\"');
			for (int i = 0; i < value.length(); ++i) {
				char ch = value.charAt(i);
				switch (ch) {
				case '&':
					buffer.append("&amp;");
					break;
				case '<':
					buffer.append("&lt;");
					break;
				case '>':
					buffer.append("&gt;");
					break;
				case '\"':
					if (single)
						buffer.append(ch);
					else
						buffer.append("&quot;");
					break;
				case '\'':
					if (single) {
						/*
						 * XML and XHTML has &apos;, but HTML doesn't have it
						 * and browsers are not required to support it.
						 * It's therefore safe to use its numeric equivalent.
						 */
						buffer.append("&#x27;");
					} else
						buffer.append(ch);
					break;
				default:
					buffer.append(ch);
					break;
				}
			}
			buffer.append(single ? '\'' : '\"');
		}
	}
	private boolean hasChildren(DomContainer container) {
		DomContent[] children = container.rawChildren();
		return children != null && children.length > 0 && children[0] != null;
	}
	private void children(DomContainer container) {
		DomContent[] children = container.rawChildren();
		if (children != null) {
			for (DomContent child : children) {
				if (child == null)
					break;
				content(child);
			}
		}
	}
	private void text(DomText node) {
		String text = node.text();
		for (int i = 0; i < text.length(); ++i) {
			char ch = text.charAt(i);
			switch (ch) {
			case '&':
				buffer.append("&amp;");
				break;
			case '<':
				buffer.append("&lt;");
				break;
			case '>':
				buffer.append("&gt;");
				break;
			default:
				buffer.append(ch);
				break;
			}
		}
	}
}
