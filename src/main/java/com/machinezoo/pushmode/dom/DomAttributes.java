// Part of PushMode: https://pushmode.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.pushmode.dom;

import java.util.*;

public interface DomAttributes {
	DomElement set(String name, String value);
	DomElement set(String name, boolean value);
	DomElement set(String name);
	DomElement set(String name, int value);
	DomElement set(String name, double value);
	DomElement set(DomAttribute attribute);
	DomElement unset(String name);
	List<DomAttribute> attributes();
	DomAttribute attribute(String name);
	String attributeAsString(String name);
	boolean attributeAsBoolean(String name);
	OptionalInt attributeAsInt(String name);
	OptionalDouble attributeAsDouble(String name);
	/**
	 * Sets {@code class} attribute to an array of values.
	 * Array items are joined with spaces and the attribute is set to the resulting string.
	 * If {@code classes} is {@code null} or empty, the attribute is removed.
	 * 
	 * @param classes
	 *            list of classes
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement clazz(String... classes) {
		if (classes != null && classes.length > 0) {
			StringBuilder builder = new StringBuilder();
			boolean first = true;
			for (String clazz : classes) {
				if (clazz != null && !clazz.isEmpty()) {
					if (first)
						first = false;
					else
						builder.append(" ");
					builder.append(clazz);
				}
			}
			if (!first)
				return set("class", builder.toString());
			else
				return unset("class");
		} else
			return unset("class");
	}
	/**
	 * Sets {@code data-*} attribute.
	 * If {@code value} is {@code null}, the attribute is removed.
	 * 
	 * @param key
	 *            unprefixed name of the attribute, e.g. key {@code hello} for attribute {@code data-hello}
	 * @param value
	 *            new value of the {@code data-*} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 * @throws NullPointerException
	 *             if the {@code key} is {@code null}
	 */
	default DomElement data(String key, String value) {
		return set("data-" + key, value);
	}
	/**
	 * Gets {@code data-*} attribute.
	 * 
	 * @param key
	 *            unprefixed name of the attribute, e.g. key {@code hello} for attribute {@code data-hello}
	 * @return value of the {@code data-*} attribute or {@code null} if the attribute is missing
	 * @throws NullPointerException
	 *             if the {@code key} is {@code null}
	 */
	default String data(String key) {
		return attributeAsString("data-" + key);
	}
	/**
	 * Sets HTML attribute {@code accept}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code accept} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement accept(String value) {
		return set("accept", value);
	}
	/**
	 * Gets HTML attribute {@code accept}.
	 *
	 * @return value of the {@code accept} attribute or {@code null} if the attribute is missing
	 */
	default String accept() {
		return attributeAsString("accept");
	}
	/**
	 * Sets HTML attribute {@code accept-charset}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code accept-charset} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement acceptCharset(String value) {
		return set("accept-charset", value);
	}
	/**
	 * Gets HTML attribute {@code accept-charset}.
	 *
	 * @return value of the {@code accept-charset} attribute or {@code null} if the attribute is missing
	 */
	default String acceptCharset() {
		return attributeAsString("accept-charset");
	}
	/**
	 * Sets HTML attribute {@code accesskey}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code accesskey} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement accesskey(String value) {
		return set("accesskey", value);
	}
	/**
	 * Gets HTML attribute {@code accesskey}.
	 *
	 * @return value of the {@code accesskey} attribute or {@code null} if the attribute is missing
	 */
	default String accesskey() {
		return attributeAsString("accesskey");
	}
	/**
	 * Sets SVG attribute {@code accumulate}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code accumulate} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement accumulate(String value) {
		return set("accumulate", value);
	}
	/**
	 * Gets SVG attribute {@code accumulate}.
	 *
	 * @return value of the {@code accumulate} attribute or {@code null} if the attribute is missing
	 */
	default String accumulate() {
		return attributeAsString("accumulate");
	}
	/**
	 * Sets HTML attribute {@code action}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code action} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement action(String value) {
		return set("action", value);
	}
	/**
	 * Gets HTML attribute {@code action}.
	 *
	 * @return value of the {@code action} attribute or {@code null} if the attribute is missing
	 */
	default String action() {
		return attributeAsString("action");
	}
	/**
	 * Sets SVG attribute {@code additive}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code additive} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement additive(String value) {
		return set("additive", value);
	}
	/**
	 * Gets SVG attribute {@code additive}.
	 *
	 * @return value of the {@code additive} attribute or {@code null} if the attribute is missing
	 */
	default String additive() {
		return attributeAsString("additive");
	}
	/**
	 * Sets SVG attribute {@code alignment-baseline}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code alignment-baseline} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement alignmentBaseline(String value) {
		return set("alignment-baseline", value);
	}
	/**
	 * Gets SVG attribute {@code alignment-baseline}.
	 *
	 * @return value of the {@code alignment-baseline} attribute or {@code null} if the attribute is missing
	 */
	default String alignmentBaseline() {
		return attributeAsString("alignment-baseline");
	}
	/**
	 * Sets HTML attribute {@code allowfullscreen}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code allowfullscreen} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement allowfullscreen(String value) {
		return set("allowfullscreen", value);
	}
	/**
	 * Gets HTML attribute {@code allowfullscreen}.
	 *
	 * @return value of the {@code allowfullscreen} attribute or {@code null} if the attribute is missing
	 */
	default String allowfullscreen() {
		return attributeAsString("allowfullscreen");
	}
	/**
	 * Sets HTML attribute {@code alt}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code alt} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement alt(String value) {
		return set("alt", value);
	}
	/**
	 * Gets HTML attribute {@code alt}.
	 *
	 * @return value of the {@code alt} attribute or {@code null} if the attribute is missing
	 */
	default String alt() {
		return attributeAsString("alt");
	}
	/**
	 * Sets SVG attribute {@code amplitude}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code amplitude} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement amplitude(String value) {
		return set("amplitude", value);
	}
	/**
	 * Gets SVG attribute {@code amplitude}.
	 *
	 * @return value of the {@code amplitude} attribute or {@code null} if the attribute is missing
	 */
	default String amplitude() {
		return attributeAsString("amplitude");
	}
	/**
	 * Sets SVG attribute {@code amplitude} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code amplitude} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement amplitude(double value) {
		return set("amplitude", value);
	}
	/**
	 * Gets SVG attribute {@code amplitude} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble amplitudeAsDouble() {
		return attributeAsDouble("amplitude");
	}
	/**
	 * Adds boolean HTML attribute {@code async}.
	 * This method has no effect if the attribute is already present.
	 *
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement async() {
		return set("async");
	}
	/**
	 * Adds or removes boolean HTML attribute {@code async}.
	 *
	 * @param present
	 *            {@code true} to add the attribute, {@code false} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement async(boolean present) {
		return set("async", present);
	}
	/**
	 * Gets boolean HTML attribute {@code async}.
	 *
	 * @return value of the {@code async} attribute or {@code null} if the attribute is missing
	 */
	default boolean asyncAsBoolean() {
		return attributeAsBoolean("async");
	}
	/**
	 * Sets SVG attribute {@code attributeName}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code attributeName} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement attributeName(String value) {
		return set("attributeName", value);
	}
	/**
	 * Gets SVG attribute {@code attributeName}.
	 *
	 * @return value of the {@code attributeName} attribute or {@code null} if the attribute is missing
	 */
	default String attributeName() {
		return attributeAsString("attributeName");
	}
	/**
	 * Sets SVG attribute {@code attributeType}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code attributeType} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement attributeType(String value) {
		return set("attributeType", value);
	}
	/**
	 * Gets SVG attribute {@code attributeType}.
	 *
	 * @return value of the {@code attributeType} attribute or {@code null} if the attribute is missing
	 */
	default String attributeType() {
		return attributeAsString("attributeType");
	}
	/**
	 * Sets HTML attribute {@code autocapitalize}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code autocapitalize} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement autocapitalize(String value) {
		return set("autocapitalize", value);
	}
	/**
	 * Gets HTML attribute {@code autocapitalize}.
	 *
	 * @return value of the {@code autocapitalize} attribute or {@code null} if the attribute is missing
	 */
	default String autocapitalize() {
		return attributeAsString("autocapitalize");
	}
	/**
	 * Sets HTML attribute {@code autocomplete}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code autocomplete} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement autocomplete(String value) {
		return set("autocomplete", value);
	}
	/**
	 * Gets HTML attribute {@code autocomplete}.
	 *
	 * @return value of the {@code autocomplete} attribute or {@code null} if the attribute is missing
	 */
	default String autocomplete() {
		return attributeAsString("autocomplete");
	}
	/**
	 * Adds boolean HTML attribute {@code autofocus}.
	 * This method has no effect if the attribute is already present.
	 *
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement autofocus() {
		return set("autofocus");
	}
	/**
	 * Adds or removes boolean HTML attribute {@code autofocus}.
	 *
	 * @param present
	 *            {@code true} to add the attribute, {@code false} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement autofocus(boolean present) {
		return set("autofocus", present);
	}
	/**
	 * Gets boolean HTML attribute {@code autofocus}.
	 *
	 * @return value of the {@code autofocus} attribute or {@code null} if the attribute is missing
	 */
	default boolean autofocusAsBoolean() {
		return attributeAsBoolean("autofocus");
	}
	/**
	 * Adds boolean HTML attribute {@code autoplay}.
	 * This method has no effect if the attribute is already present.
	 *
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement autoplay() {
		return set("autoplay");
	}
	/**
	 * Adds or removes boolean HTML attribute {@code autoplay}.
	 *
	 * @param present
	 *            {@code true} to add the attribute, {@code false} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement autoplay(boolean present) {
		return set("autoplay", present);
	}
	/**
	 * Gets boolean HTML attribute {@code autoplay}.
	 *
	 * @return value of the {@code autoplay} attribute or {@code null} if the attribute is missing
	 */
	default boolean autoplayAsBoolean() {
		return attributeAsBoolean("autoplay");
	}
	/**
	 * Sets SVG attribute {@code azimuth}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code azimuth} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement azimuth(String value) {
		return set("azimuth", value);
	}
	/**
	 * Gets SVG attribute {@code azimuth}.
	 *
	 * @return value of the {@code azimuth} attribute or {@code null} if the attribute is missing
	 */
	default String azimuth() {
		return attributeAsString("azimuth");
	}
	/**
	 * Sets SVG attribute {@code azimuth} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code azimuth} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement azimuth(double value) {
		return set("azimuth", value);
	}
	/**
	 * Gets SVG attribute {@code azimuth} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble azimuthAsDouble() {
		return attributeAsDouble("azimuth");
	}
	/**
	 * Sets SVG attribute {@code baseFrequency}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code baseFrequency} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement baseFrequency(String value) {
		return set("baseFrequency", value);
	}
	/**
	 * Gets SVG attribute {@code baseFrequency}.
	 *
	 * @return value of the {@code baseFrequency} attribute or {@code null} if the attribute is missing
	 */
	default String baseFrequency() {
		return attributeAsString("baseFrequency");
	}
	/**
	 * Sets SVG attribute {@code baseFrequency} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code baseFrequency} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement baseFrequency(double value) {
		return set("baseFrequency", value);
	}
	/**
	 * Gets SVG attribute {@code baseFrequency} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble baseFrequencyAsDouble() {
		return attributeAsDouble("baseFrequency");
	}
	/**
	 * Sets SVG attribute {@code baseline-shift}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code baseline-shift} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement baselineShift(String value) {
		return set("baseline-shift", value);
	}
	/**
	 * Gets SVG attribute {@code baseline-shift}.
	 *
	 * @return value of the {@code baseline-shift} attribute or {@code null} if the attribute is missing
	 */
	default String baselineShift() {
		return attributeAsString("baseline-shift");
	}
	/**
	 * Sets SVG attribute {@code baseline-shift} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code baseline-shift} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement baselineShift(double value) {
		return set("baseline-shift", value);
	}
	/**
	 * Gets SVG attribute {@code baseline-shift} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble baselineShiftAsDouble() {
		return attributeAsDouble("baseline-shift");
	}
	/**
	 * Sets SVG attribute {@code begin}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code begin} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement begin(String value) {
		return set("begin", value);
	}
	/**
	 * Gets SVG attribute {@code begin}.
	 *
	 * @return value of the {@code begin} attribute or {@code null} if the attribute is missing
	 */
	default String begin() {
		return attributeAsString("begin");
	}
	/**
	 * Sets SVG attribute {@code bias}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code bias} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement bias(String value) {
		return set("bias", value);
	}
	/**
	 * Gets SVG attribute {@code bias}.
	 *
	 * @return value of the {@code bias} attribute or {@code null} if the attribute is missing
	 */
	default String bias() {
		return attributeAsString("bias");
	}
	/**
	 * Sets SVG attribute {@code bias} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code bias} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement bias(double value) {
		return set("bias", value);
	}
	/**
	 * Gets SVG attribute {@code bias} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble biasAsDouble() {
		return attributeAsDouble("bias");
	}
	/**
	 * Sets SVG attribute {@code calcMode}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code calcMode} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement calcMode(String value) {
		return set("calcMode", value);
	}
	/**
	 * Gets SVG attribute {@code calcMode}.
	 *
	 * @return value of the {@code calcMode} attribute or {@code null} if the attribute is missing
	 */
	default String calcMode() {
		return attributeAsString("calcMode");
	}
	/**
	 * Sets HTML attribute {@code charset}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code charset} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement charset(String value) {
		return set("charset", value);
	}
	/**
	 * Gets HTML attribute {@code charset}.
	 *
	 * @return value of the {@code charset} attribute or {@code null} if the attribute is missing
	 */
	default String charset() {
		return attributeAsString("charset");
	}
	/**
	 * Adds boolean HTML attribute {@code checked}.
	 * This method has no effect if the attribute is already present.
	 *
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement checked() {
		return set("checked");
	}
	/**
	 * Adds or removes boolean HTML attribute {@code checked}.
	 *
	 * @param present
	 *            {@code true} to add the attribute, {@code false} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement checked(boolean present) {
		return set("checked", present);
	}
	/**
	 * Gets boolean HTML attribute {@code checked}.
	 *
	 * @return value of the {@code checked} attribute or {@code null} if the attribute is missing
	 */
	default boolean checkedAsBoolean() {
		return attributeAsBoolean("checked");
	}
	/**
	 * Sets HTML attribute {@code cite}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code cite} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement cite(String value) {
		return set("cite", value);
	}
	/**
	 * Gets HTML attribute {@code cite}.
	 *
	 * @return value of the {@code cite} attribute or {@code null} if the attribute is missing
	 */
	default String cite() {
		return attributeAsString("cite");
	}
	/**
	 * Sets HTML/SVG attribute {@code class}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code class} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement clazz(String value) {
		return set("class", value);
	}
	/**
	 * Gets HTML/SVG attribute {@code class}.
	 *
	 * @return value of the {@code class} attribute or {@code null} if the attribute is missing
	 */
	default String clazz() {
		return attributeAsString("class");
	}
	/**
	 * Sets SVG attribute {@code clip-path}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code clip-path} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement clipPath(String value) {
		return set("clip-path", value);
	}
	/**
	 * Gets SVG attribute {@code clip-path}.
	 *
	 * @return value of the {@code clip-path} attribute or {@code null} if the attribute is missing
	 */
	default String clipPath() {
		return attributeAsString("clip-path");
	}
	/**
	 * Sets SVG attribute {@code clip-rule}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code clip-rule} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement clipRule(String value) {
		return set("clip-rule", value);
	}
	/**
	 * Gets SVG attribute {@code clip-rule}.
	 *
	 * @return value of the {@code clip-rule} attribute or {@code null} if the attribute is missing
	 */
	default String clipRule() {
		return attributeAsString("clip-rule");
	}
	/**
	 * Sets SVG attribute {@code clipPathUnits}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code clipPathUnits} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement clipPathUnits(String value) {
		return set("clipPathUnits", value);
	}
	/**
	 * Gets SVG attribute {@code clipPathUnits}.
	 *
	 * @return value of the {@code clipPathUnits} attribute or {@code null} if the attribute is missing
	 */
	default String clipPathUnits() {
		return attributeAsString("clipPathUnits");
	}
	/**
	 * Sets SVG attribute {@code color}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code color} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement color(String value) {
		return set("color", value);
	}
	/**
	 * Gets SVG attribute {@code color}.
	 *
	 * @return value of the {@code color} attribute or {@code null} if the attribute is missing
	 */
	default String color() {
		return attributeAsString("color");
	}
	/**
	 * Sets SVG attribute {@code color-interpolation}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code color-interpolation} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement colorInterpolation(String value) {
		return set("color-interpolation", value);
	}
	/**
	 * Gets SVG attribute {@code color-interpolation}.
	 *
	 * @return value of the {@code color-interpolation} attribute or {@code null} if the attribute is missing
	 */
	default String colorInterpolation() {
		return attributeAsString("color-interpolation");
	}
	/**
	 * Sets SVG attribute {@code color-interpolation-filters}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code color-interpolation-filters} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement colorInterpolationFilters(String value) {
		return set("color-interpolation-filters", value);
	}
	/**
	 * Gets SVG attribute {@code color-interpolation-filters}.
	 *
	 * @return value of the {@code color-interpolation-filters} attribute or {@code null} if the attribute is missing
	 */
	default String colorInterpolationFilters() {
		return attributeAsString("color-interpolation-filters");
	}
	/**
	 * Sets SVG attribute {@code color-profile}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code color-profile} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement colorProfile(String value) {
		return set("color-profile", value);
	}
	/**
	 * Gets SVG attribute {@code color-profile}.
	 *
	 * @return value of the {@code color-profile} attribute or {@code null} if the attribute is missing
	 */
	default String colorProfile() {
		return attributeAsString("color-profile");
	}
	/**
	 * Sets SVG attribute {@code color-rendering}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code color-rendering} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement colorRendering(String value) {
		return set("color-rendering", value);
	}
	/**
	 * Gets SVG attribute {@code color-rendering}.
	 *
	 * @return value of the {@code color-rendering} attribute or {@code null} if the attribute is missing
	 */
	default String colorRendering() {
		return attributeAsString("color-rendering");
	}
	/**
	 * Sets HTML attribute {@code cols}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code cols} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement cols(String value) {
		return set("cols", value);
	}
	/**
	 * Gets HTML attribute {@code cols}.
	 *
	 * @return value of the {@code cols} attribute or {@code null} if the attribute is missing
	 */
	default String cols() {
		return attributeAsString("cols");
	}
	/**
	 * Sets HTML attribute {@code cols} to an integer value.
	 *
	 * @param value
	 *            new value of the {@code cols} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement cols(int value) {
		return set("cols", value);
	}
	/**
	 * Gets HTML attribute {@code cols} as an integer value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not an integer
	 */
	default OptionalInt colsAsInt() {
		return attributeAsInt("cols");
	}
	/**
	 * Sets HTML attribute {@code colspan}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code colspan} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement colspan(String value) {
		return set("colspan", value);
	}
	/**
	 * Gets HTML attribute {@code colspan}.
	 *
	 * @return value of the {@code colspan} attribute or {@code null} if the attribute is missing
	 */
	default String colspan() {
		return attributeAsString("colspan");
	}
	/**
	 * Sets HTML attribute {@code colspan} to an integer value.
	 *
	 * @param value
	 *            new value of the {@code colspan} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement colspan(int value) {
		return set("colspan", value);
	}
	/**
	 * Gets HTML attribute {@code colspan} as an integer value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not an integer
	 */
	default OptionalInt colspanAsInt() {
		return attributeAsInt("colspan");
	}
	/**
	 * Sets HTML attribute {@code content}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code content} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement content(String value) {
		return set("content", value);
	}
	/**
	 * Gets HTML attribute {@code content}.
	 *
	 * @return value of the {@code content} attribute or {@code null} if the attribute is missing
	 */
	default String content() {
		return attributeAsString("content");
	}
	/**
	 * Sets HTML attribute {@code contenteditable}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code contenteditable} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement contenteditable(String value) {
		return set("contenteditable", value);
	}
	/**
	 * Gets HTML attribute {@code contenteditable}.
	 *
	 * @return value of the {@code contenteditable} attribute or {@code null} if the attribute is missing
	 */
	default String contenteditable() {
		return attributeAsString("contenteditable");
	}
	/**
	 * Sets HTML attribute {@code contextmenu}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code contextmenu} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement contextmenu(String value) {
		return set("contextmenu", value);
	}
	/**
	 * Gets HTML attribute {@code contextmenu}.
	 *
	 * @return value of the {@code contextmenu} attribute or {@code null} if the attribute is missing
	 */
	default String contextmenu() {
		return attributeAsString("contextmenu");
	}
	/**
	 * Adds boolean HTML attribute {@code controls}.
	 * This method has no effect if the attribute is already present.
	 *
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement controls() {
		return set("controls");
	}
	/**
	 * Adds or removes boolean HTML attribute {@code controls}.
	 *
	 * @param present
	 *            {@code true} to add the attribute, {@code false} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement controls(boolean present) {
		return set("controls", present);
	}
	/**
	 * Gets boolean HTML attribute {@code controls}.
	 *
	 * @return value of the {@code controls} attribute or {@code null} if the attribute is missing
	 */
	default boolean controlsAsBoolean() {
		return attributeAsBoolean("controls");
	}
	/**
	 * Sets HTML attribute {@code coords}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code coords} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement coords(String value) {
		return set("coords", value);
	}
	/**
	 * Gets HTML attribute {@code coords}.
	 *
	 * @return value of the {@code coords} attribute or {@code null} if the attribute is missing
	 */
	default String coords() {
		return attributeAsString("coords");
	}
	/**
	 * Sets HTML attribute {@code crossorigin}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code crossorigin} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement crossorigin(String value) {
		return set("crossorigin", value);
	}
	/**
	 * Gets HTML attribute {@code crossorigin}.
	 *
	 * @return value of the {@code crossorigin} attribute or {@code null} if the attribute is missing
	 */
	default String crossorigin() {
		return attributeAsString("crossorigin");
	}
	/**
	 * Sets SVG attribute {@code cursor}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code cursor} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement cursor(String value) {
		return set("cursor", value);
	}
	/**
	 * Gets SVG attribute {@code cursor}.
	 *
	 * @return value of the {@code cursor} attribute or {@code null} if the attribute is missing
	 */
	default String cursor() {
		return attributeAsString("cursor");
	}
	/**
	 * Sets SVG attribute {@code cx}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code cx} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement cx(String value) {
		return set("cx", value);
	}
	/**
	 * Gets SVG attribute {@code cx}.
	 *
	 * @return value of the {@code cx} attribute or {@code null} if the attribute is missing
	 */
	default String cx() {
		return attributeAsString("cx");
	}
	/**
	 * Sets SVG attribute {@code cx} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code cx} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement cx(double value) {
		return set("cx", value);
	}
	/**
	 * Gets SVG attribute {@code cx} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble cxAsDouble() {
		return attributeAsDouble("cx");
	}
	/**
	 * Sets SVG attribute {@code cy}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code cy} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement cy(String value) {
		return set("cy", value);
	}
	/**
	 * Gets SVG attribute {@code cy}.
	 *
	 * @return value of the {@code cy} attribute or {@code null} if the attribute is missing
	 */
	default String cy() {
		return attributeAsString("cy");
	}
	/**
	 * Sets SVG attribute {@code cy} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code cy} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement cy(double value) {
		return set("cy", value);
	}
	/**
	 * Gets SVG attribute {@code cy} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble cyAsDouble() {
		return attributeAsDouble("cy");
	}
	/**
	 * Sets SVG attribute {@code d}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code d} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement d(String value) {
		return set("d", value);
	}
	/**
	 * Gets SVG attribute {@code d}.
	 *
	 * @return value of the {@code d} attribute or {@code null} if the attribute is missing
	 */
	default String d() {
		return attributeAsString("d");
	}
	/**
	 * Sets HTML attribute {@code data}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code data} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement plaindata(String value) {
		return set("data", value);
	}
	/**
	 * Gets HTML attribute {@code data}.
	 *
	 * @return value of the {@code data} attribute or {@code null} if the attribute is missing
	 */
	default String plaindata() {
		return attributeAsString("data");
	}
	/**
	 * Sets HTML attribute {@code datetime}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code datetime} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement datetime(String value) {
		return set("datetime", value);
	}
	/**
	 * Gets HTML attribute {@code datetime}.
	 *
	 * @return value of the {@code datetime} attribute or {@code null} if the attribute is missing
	 */
	default String datetime() {
		return attributeAsString("datetime");
	}
	/**
	 * Adds boolean HTML attribute {@code default}.
	 * This method has no effect if the attribute is already present.
	 *
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement defaults() {
		return set("default");
	}
	/**
	 * Adds or removes boolean HTML attribute {@code default}.
	 *
	 * @param present
	 *            {@code true} to add the attribute, {@code false} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement defaults(boolean present) {
		return set("default", present);
	}
	/**
	 * Gets boolean HTML attribute {@code default}.
	 *
	 * @return value of the {@code default} attribute or {@code null} if the attribute is missing
	 */
	default boolean defaultsAsBoolean() {
		return attributeAsBoolean("default");
	}
	/**
	 * Adds boolean HTML attribute {@code defer}.
	 * This method has no effect if the attribute is already present.
	 *
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement defer() {
		return set("defer");
	}
	/**
	 * Adds or removes boolean HTML attribute {@code defer}.
	 *
	 * @param present
	 *            {@code true} to add the attribute, {@code false} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement defer(boolean present) {
		return set("defer", present);
	}
	/**
	 * Gets boolean HTML attribute {@code defer}.
	 *
	 * @return value of the {@code defer} attribute or {@code null} if the attribute is missing
	 */
	default boolean deferAsBoolean() {
		return attributeAsBoolean("defer");
	}
	/**
	 * Sets SVG attribute {@code diffuseConstant}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code diffuseConstant} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement diffuseConstant(String value) {
		return set("diffuseConstant", value);
	}
	/**
	 * Gets SVG attribute {@code diffuseConstant}.
	 *
	 * @return value of the {@code diffuseConstant} attribute or {@code null} if the attribute is missing
	 */
	default String diffuseConstant() {
		return attributeAsString("diffuseConstant");
	}
	/**
	 * Sets SVG attribute {@code diffuseConstant} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code diffuseConstant} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement diffuseConstant(double value) {
		return set("diffuseConstant", value);
	}
	/**
	 * Gets SVG attribute {@code diffuseConstant} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble diffuseConstantAsDouble() {
		return attributeAsDouble("diffuseConstant");
	}
	/**
	 * Sets HTML attribute {@code dir}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code dir} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement dir(String value) {
		return set("dir", value);
	}
	/**
	 * Gets HTML attribute {@code dir}.
	 *
	 * @return value of the {@code dir} attribute or {@code null} if the attribute is missing
	 */
	default String dir() {
		return attributeAsString("dir");
	}
	/**
	 * Sets SVG attribute {@code direction}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code direction} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement direction(String value) {
		return set("direction", value);
	}
	/**
	 * Gets SVG attribute {@code direction}.
	 *
	 * @return value of the {@code direction} attribute or {@code null} if the attribute is missing
	 */
	default String direction() {
		return attributeAsString("direction");
	}
	/**
	 * Adds boolean HTML attribute {@code disabled}.
	 * This method has no effect if the attribute is already present.
	 *
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement disabled() {
		return set("disabled");
	}
	/**
	 * Adds or removes boolean HTML attribute {@code disabled}.
	 *
	 * @param present
	 *            {@code true} to add the attribute, {@code false} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement disabled(boolean present) {
		return set("disabled", present);
	}
	/**
	 * Gets boolean HTML attribute {@code disabled}.
	 *
	 * @return value of the {@code disabled} attribute or {@code null} if the attribute is missing
	 */
	default boolean disabledAsBoolean() {
		return attributeAsBoolean("disabled");
	}
	/**
	 * Sets SVG attribute {@code display}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code display} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement display(String value) {
		return set("display", value);
	}
	/**
	 * Gets SVG attribute {@code display}.
	 *
	 * @return value of the {@code display} attribute or {@code null} if the attribute is missing
	 */
	default String display() {
		return attributeAsString("display");
	}
	/**
	 * Sets SVG attribute {@code divisor}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code divisor} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement divisor(String value) {
		return set("divisor", value);
	}
	/**
	 * Gets SVG attribute {@code divisor}.
	 *
	 * @return value of the {@code divisor} attribute or {@code null} if the attribute is missing
	 */
	default String divisor() {
		return attributeAsString("divisor");
	}
	/**
	 * Sets SVG attribute {@code divisor} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code divisor} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement divisor(double value) {
		return set("divisor", value);
	}
	/**
	 * Gets SVG attribute {@code divisor} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble divisorAsDouble() {
		return attributeAsDouble("divisor");
	}
	/**
	 * Sets SVG attribute {@code dominant-baseline}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code dominant-baseline} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement dominantBaseline(String value) {
		return set("dominant-baseline", value);
	}
	/**
	 * Gets SVG attribute {@code dominant-baseline}.
	 *
	 * @return value of the {@code dominant-baseline} attribute or {@code null} if the attribute is missing
	 */
	default String dominantBaseline() {
		return attributeAsString("dominant-baseline");
	}
	/**
	 * Adds boolean HTML attribute {@code download}.
	 * This method has no effect if the attribute is already present.
	 *
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement download() {
		return set("download");
	}
	/**
	 * Adds or removes boolean HTML attribute {@code download}.
	 *
	 * @param present
	 *            {@code true} to add the attribute, {@code false} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement download(boolean present) {
		return set("download", present);
	}
	/**
	 * Gets boolean HTML attribute {@code download}.
	 *
	 * @return value of the {@code download} attribute or {@code null} if the attribute is missing
	 */
	default boolean downloadAsBoolean() {
		return attributeAsBoolean("download");
	}
	/**
	 * Sets HTML attribute {@code draggable}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code draggable} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement draggable(String value) {
		return set("draggable", value);
	}
	/**
	 * Gets HTML attribute {@code draggable}.
	 *
	 * @return value of the {@code draggable} attribute or {@code null} if the attribute is missing
	 */
	default String draggable() {
		return attributeAsString("draggable");
	}
	/**
	 * Sets HTML attribute {@code dropzone}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code dropzone} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement dropzone(String value) {
		return set("dropzone", value);
	}
	/**
	 * Gets HTML attribute {@code dropzone}.
	 *
	 * @return value of the {@code dropzone} attribute or {@code null} if the attribute is missing
	 */
	default String dropzone() {
		return attributeAsString("dropzone");
	}
	/**
	 * Sets SVG attribute {@code dur}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code dur} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement dur(String value) {
		return set("dur", value);
	}
	/**
	 * Gets SVG attribute {@code dur}.
	 *
	 * @return value of the {@code dur} attribute or {@code null} if the attribute is missing
	 */
	default String dur() {
		return attributeAsString("dur");
	}
	/**
	 * Sets SVG attribute {@code dx}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code dx} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement dx(String value) {
		return set("dx", value);
	}
	/**
	 * Gets SVG attribute {@code dx}.
	 *
	 * @return value of the {@code dx} attribute or {@code null} if the attribute is missing
	 */
	default String dx() {
		return attributeAsString("dx");
	}
	/**
	 * Sets SVG attribute {@code dx} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code dx} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement dx(double value) {
		return set("dx", value);
	}
	/**
	 * Gets SVG attribute {@code dx} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble dxAsDouble() {
		return attributeAsDouble("dx");
	}
	/**
	 * Sets SVG attribute {@code dy}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code dy} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement dy(String value) {
		return set("dy", value);
	}
	/**
	 * Gets SVG attribute {@code dy}.
	 *
	 * @return value of the {@code dy} attribute or {@code null} if the attribute is missing
	 */
	default String dy() {
		return attributeAsString("dy");
	}
	/**
	 * Sets SVG attribute {@code dy} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code dy} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement dy(double value) {
		return set("dy", value);
	}
	/**
	 * Gets SVG attribute {@code dy} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble dyAsDouble() {
		return attributeAsDouble("dy");
	}
	/**
	 * Sets SVG attribute {@code edgeMode}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code edgeMode} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement edgeMode(String value) {
		return set("edgeMode", value);
	}
	/**
	 * Gets SVG attribute {@code edgeMode}.
	 *
	 * @return value of the {@code edgeMode} attribute or {@code null} if the attribute is missing
	 */
	default String edgeMode() {
		return attributeAsString("edgeMode");
	}
	/**
	 * Sets SVG attribute {@code elevation}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code elevation} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement elevation(String value) {
		return set("elevation", value);
	}
	/**
	 * Gets SVG attribute {@code elevation}.
	 *
	 * @return value of the {@code elevation} attribute or {@code null} if the attribute is missing
	 */
	default String elevation() {
		return attributeAsString("elevation");
	}
	/**
	 * Sets SVG attribute {@code elevation} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code elevation} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement elevation(double value) {
		return set("elevation", value);
	}
	/**
	 * Gets SVG attribute {@code elevation} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble elevationAsDouble() {
		return attributeAsDouble("elevation");
	}
	/**
	 * Sets HTML attribute {@code enctype}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code enctype} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement enctype(String value) {
		return set("enctype", value);
	}
	/**
	 * Gets HTML attribute {@code enctype}.
	 *
	 * @return value of the {@code enctype} attribute or {@code null} if the attribute is missing
	 */
	default String enctype() {
		return attributeAsString("enctype");
	}
	/**
	 * Sets SVG attribute {@code end}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code end} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement end(String value) {
		return set("end", value);
	}
	/**
	 * Gets SVG attribute {@code end}.
	 *
	 * @return value of the {@code end} attribute or {@code null} if the attribute is missing
	 */
	default String end() {
		return attributeAsString("end");
	}
	/**
	 * Sets SVG attribute {@code externalResourcesRequired}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code externalResourcesRequired} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement externalResourcesRequired(String value) {
		return set("externalResourcesRequired", value);
	}
	/**
	 * Gets SVG attribute {@code externalResourcesRequired}.
	 *
	 * @return value of the {@code externalResourcesRequired} attribute or {@code null} if the attribute is missing
	 */
	default String externalResourcesRequired() {
		return attributeAsString("externalResourcesRequired");
	}
	/**
	 * Sets SVG attribute {@code fill}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code fill} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement fill(String value) {
		return set("fill", value);
	}
	/**
	 * Gets SVG attribute {@code fill}.
	 *
	 * @return value of the {@code fill} attribute or {@code null} if the attribute is missing
	 */
	default String fill() {
		return attributeAsString("fill");
	}
	/**
	 * Sets SVG attribute {@code fill-opacity}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code fill-opacity} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement fillOpacity(String value) {
		return set("fill-opacity", value);
	}
	/**
	 * Gets SVG attribute {@code fill-opacity}.
	 *
	 * @return value of the {@code fill-opacity} attribute or {@code null} if the attribute is missing
	 */
	default String fillOpacity() {
		return attributeAsString("fill-opacity");
	}
	/**
	 * Sets SVG attribute {@code fill-opacity} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code fill-opacity} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement fillOpacity(double value) {
		return set("fill-opacity", value);
	}
	/**
	 * Gets SVG attribute {@code fill-opacity} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble fillOpacityAsDouble() {
		return attributeAsDouble("fill-opacity");
	}
	/**
	 * Sets SVG attribute {@code fill-rule}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code fill-rule} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement fillRule(String value) {
		return set("fill-rule", value);
	}
	/**
	 * Gets SVG attribute {@code fill-rule}.
	 *
	 * @return value of the {@code fill-rule} attribute or {@code null} if the attribute is missing
	 */
	default String fillRule() {
		return attributeAsString("fill-rule");
	}
	/**
	 * Sets SVG attribute {@code filter}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code filter} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement filter(String value) {
		return set("filter", value);
	}
	/**
	 * Gets SVG attribute {@code filter}.
	 *
	 * @return value of the {@code filter} attribute or {@code null} if the attribute is missing
	 */
	default String filter() {
		return attributeAsString("filter");
	}
	/**
	 * Sets SVG attribute {@code filterUnits}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code filterUnits} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement filterUnits(String value) {
		return set("filterUnits", value);
	}
	/**
	 * Gets SVG attribute {@code filterUnits}.
	 *
	 * @return value of the {@code filterUnits} attribute or {@code null} if the attribute is missing
	 */
	default String filterUnits() {
		return attributeAsString("filterUnits");
	}
	/**
	 * Sets SVG attribute {@code flood-color}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code flood-color} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement floodColor(String value) {
		return set("flood-color", value);
	}
	/**
	 * Gets SVG attribute {@code flood-color}.
	 *
	 * @return value of the {@code flood-color} attribute or {@code null} if the attribute is missing
	 */
	default String floodColor() {
		return attributeAsString("flood-color");
	}
	/**
	 * Sets SVG attribute {@code flood-opacity}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code flood-opacity} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement floodOpacity(String value) {
		return set("flood-opacity", value);
	}
	/**
	 * Gets SVG attribute {@code flood-opacity}.
	 *
	 * @return value of the {@code flood-opacity} attribute or {@code null} if the attribute is missing
	 */
	default String floodOpacity() {
		return attributeAsString("flood-opacity");
	}
	/**
	 * Sets SVG attribute {@code flood-opacity} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code flood-opacity} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement floodOpacity(double value) {
		return set("flood-opacity", value);
	}
	/**
	 * Gets SVG attribute {@code flood-opacity} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble floodOpacityAsDouble() {
		return attributeAsDouble("flood-opacity");
	}
	/**
	 * Sets SVG attribute {@code font-family}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code font-family} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement fontFamily(String value) {
		return set("font-family", value);
	}
	/**
	 * Gets SVG attribute {@code font-family}.
	 *
	 * @return value of the {@code font-family} attribute or {@code null} if the attribute is missing
	 */
	default String fontFamily() {
		return attributeAsString("font-family");
	}
	/**
	 * Sets SVG attribute {@code font-size}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code font-size} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement fontSize(String value) {
		return set("font-size", value);
	}
	/**
	 * Gets SVG attribute {@code font-size}.
	 *
	 * @return value of the {@code font-size} attribute or {@code null} if the attribute is missing
	 */
	default String fontSize() {
		return attributeAsString("font-size");
	}
	/**
	 * Sets SVG attribute {@code font-size-adjust}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code font-size-adjust} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement fontSizeAdjust(String value) {
		return set("font-size-adjust", value);
	}
	/**
	 * Gets SVG attribute {@code font-size-adjust}.
	 *
	 * @return value of the {@code font-size-adjust} attribute or {@code null} if the attribute is missing
	 */
	default String fontSizeAdjust() {
		return attributeAsString("font-size-adjust");
	}
	/**
	 * Sets SVG attribute {@code font-size-adjust} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code font-size-adjust} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement fontSizeAdjust(double value) {
		return set("font-size-adjust", value);
	}
	/**
	 * Gets SVG attribute {@code font-size-adjust} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble fontSizeAdjustAsDouble() {
		return attributeAsDouble("font-size-adjust");
	}
	/**
	 * Sets SVG attribute {@code font-stretch}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code font-stretch} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement fontStretch(String value) {
		return set("font-stretch", value);
	}
	/**
	 * Gets SVG attribute {@code font-stretch}.
	 *
	 * @return value of the {@code font-stretch} attribute or {@code null} if the attribute is missing
	 */
	default String fontStretch() {
		return attributeAsString("font-stretch");
	}
	/**
	 * Sets SVG attribute {@code font-variant}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code font-variant} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement fontVariant(String value) {
		return set("font-variant", value);
	}
	/**
	 * Gets SVG attribute {@code font-variant}.
	 *
	 * @return value of the {@code font-variant} attribute or {@code null} if the attribute is missing
	 */
	default String fontVariant() {
		return attributeAsString("font-variant");
	}
	/**
	 * Sets SVG attribute {@code font-weight}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code font-weight} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement fontWeight(String value) {
		return set("font-weight", value);
	}
	/**
	 * Gets SVG attribute {@code font-weight}.
	 *
	 * @return value of the {@code font-weight} attribute or {@code null} if the attribute is missing
	 */
	default String fontWeight() {
		return attributeAsString("font-weight");
	}
	/**
	 * Sets SVG attribute {@code font-weight} to an integer value.
	 *
	 * @param value
	 *            new value of the {@code font-weight} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement fontWeight(int value) {
		return set("font-weight", value);
	}
	/**
	 * Gets SVG attribute {@code font-weight} as an integer value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not an integer
	 */
	default OptionalInt fontWeightAsInt() {
		return attributeAsInt("font-weight");
	}
	/**
	 * Sets HTML attribute {@code for}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code for} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement forid(String value) {
		return set("for", value);
	}
	/**
	 * Gets HTML attribute {@code for}.
	 *
	 * @return value of the {@code for} attribute or {@code null} if the attribute is missing
	 */
	default String forid() {
		return attributeAsString("for");
	}
	/**
	 * Sets HTML attribute {@code form}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code form} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement form(String value) {
		return set("form", value);
	}
	/**
	 * Gets HTML attribute {@code form}.
	 *
	 * @return value of the {@code form} attribute or {@code null} if the attribute is missing
	 */
	default String form() {
		return attributeAsString("form");
	}
	/**
	 * Sets HTML attribute {@code formaction}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code formaction} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement formaction(String value) {
		return set("formaction", value);
	}
	/**
	 * Gets HTML attribute {@code formaction}.
	 *
	 * @return value of the {@code formaction} attribute or {@code null} if the attribute is missing
	 */
	default String formaction() {
		return attributeAsString("formaction");
	}
	/**
	 * Sets HTML attribute {@code formmethod}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code formmethod} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement formmethod(String value) {
		return set("formmethod", value);
	}
	/**
	 * Gets HTML attribute {@code formmethod}.
	 *
	 * @return value of the {@code formmethod} attribute or {@code null} if the attribute is missing
	 */
	default String formmethod() {
		return attributeAsString("formmethod");
	}
	/**
	 * Adds boolean HTML attribute {@code formnovalidate}.
	 * This method has no effect if the attribute is already present.
	 *
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement formnovalidate() {
		return set("formnovalidate");
	}
	/**
	 * Adds or removes boolean HTML attribute {@code formnovalidate}.
	 *
	 * @param present
	 *            {@code true} to add the attribute, {@code false} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement formnovalidate(boolean present) {
		return set("formnovalidate", present);
	}
	/**
	 * Gets boolean HTML attribute {@code formnovalidate}.
	 *
	 * @return value of the {@code formnovalidate} attribute or {@code null} if the attribute is missing
	 */
	default boolean formnovalidateAsBoolean() {
		return attributeAsBoolean("formnovalidate");
	}
	/**
	 * Sets HTML attribute {@code formtarget}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code formtarget} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement formtarget(String value) {
		return set("formtarget", value);
	}
	/**
	 * Gets HTML attribute {@code formtarget}.
	 *
	 * @return value of the {@code formtarget} attribute or {@code null} if the attribute is missing
	 */
	default String formtarget() {
		return attributeAsString("formtarget");
	}
	/**
	 * Sets SVG attribute {@code fr}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code fr} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement fr(String value) {
		return set("fr", value);
	}
	/**
	 * Gets SVG attribute {@code fr}.
	 *
	 * @return value of the {@code fr} attribute or {@code null} if the attribute is missing
	 */
	default String fr() {
		return attributeAsString("fr");
	}
	/**
	 * Sets SVG attribute {@code fr} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code fr} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement fr(double value) {
		return set("fr", value);
	}
	/**
	 * Gets SVG attribute {@code fr} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble frAsDouble() {
		return attributeAsDouble("fr");
	}
	/**
	 * Sets SVG attribute {@code from}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code from} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement from(String value) {
		return set("from", value);
	}
	/**
	 * Gets SVG attribute {@code from}.
	 *
	 * @return value of the {@code from} attribute or {@code null} if the attribute is missing
	 */
	default String from() {
		return attributeAsString("from");
	}
	/**
	 * Sets SVG attribute {@code fx}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code fx} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement fx(String value) {
		return set("fx", value);
	}
	/**
	 * Gets SVG attribute {@code fx}.
	 *
	 * @return value of the {@code fx} attribute or {@code null} if the attribute is missing
	 */
	default String fx() {
		return attributeAsString("fx");
	}
	/**
	 * Sets SVG attribute {@code fx} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code fx} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement fx(double value) {
		return set("fx", value);
	}
	/**
	 * Gets SVG attribute {@code fx} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble fxAsDouble() {
		return attributeAsDouble("fx");
	}
	/**
	 * Sets SVG attribute {@code fy}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code fy} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement fy(String value) {
		return set("fy", value);
	}
	/**
	 * Gets SVG attribute {@code fy}.
	 *
	 * @return value of the {@code fy} attribute or {@code null} if the attribute is missing
	 */
	default String fy() {
		return attributeAsString("fy");
	}
	/**
	 * Sets SVG attribute {@code fy} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code fy} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement fy(double value) {
		return set("fy", value);
	}
	/**
	 * Gets SVG attribute {@code fy} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble fyAsDouble() {
		return attributeAsDouble("fy");
	}
	/**
	 * Sets SVG attribute {@code gradientTransform}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code gradientTransform} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement gradientTransform(String value) {
		return set("gradientTransform", value);
	}
	/**
	 * Gets SVG attribute {@code gradientTransform}.
	 *
	 * @return value of the {@code gradientTransform} attribute or {@code null} if the attribute is missing
	 */
	default String gradientTransform() {
		return attributeAsString("gradientTransform");
	}
	/**
	 * Sets SVG attribute {@code gradientUnits}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code gradientUnits} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement gradientUnits(String value) {
		return set("gradientUnits", value);
	}
	/**
	 * Gets SVG attribute {@code gradientUnits}.
	 *
	 * @return value of the {@code gradientUnits} attribute or {@code null} if the attribute is missing
	 */
	default String gradientUnits() {
		return attributeAsString("gradientUnits");
	}
	/**
	 * Sets HTML attribute {@code headers}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code headers} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement headers(String value) {
		return set("headers", value);
	}
	/**
	 * Gets HTML attribute {@code headers}.
	 *
	 * @return value of the {@code headers} attribute or {@code null} if the attribute is missing
	 */
	default String headers() {
		return attributeAsString("headers");
	}
	/**
	 * Sets HTML/SVG attribute {@code height}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code height} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement height(String value) {
		return set("height", value);
	}
	/**
	 * Gets HTML/SVG attribute {@code height}.
	 *
	 * @return value of the {@code height} attribute or {@code null} if the attribute is missing
	 */
	default String height() {
		return attributeAsString("height");
	}
	/**
	 * Sets HTML/SVG attribute {@code height} to an integer value.
	 *
	 * @param value
	 *            new value of the {@code height} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement height(int value) {
		return set("height", value);
	}
	/**
	 * Gets HTML/SVG attribute {@code height} as an integer value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not an integer
	 */
	default OptionalInt heightAsInt() {
		return attributeAsInt("height");
	}
	/**
	 * Sets HTML/SVG attribute {@code height} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code height} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement height(double value) {
		return set("height", value);
	}
	/**
	 * Gets HTML/SVG attribute {@code height} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble heightAsDouble() {
		return attributeAsDouble("height");
	}
	/**
	 * Adds boolean HTML attribute {@code hidden}.
	 * This method has no effect if the attribute is already present.
	 *
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement hidden() {
		return set("hidden");
	}
	/**
	 * Adds or removes boolean HTML attribute {@code hidden}.
	 *
	 * @param present
	 *            {@code true} to add the attribute, {@code false} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement hidden(boolean present) {
		return set("hidden", present);
	}
	/**
	 * Gets boolean HTML attribute {@code hidden}.
	 *
	 * @return value of the {@code hidden} attribute or {@code null} if the attribute is missing
	 */
	default boolean hiddenAsBoolean() {
		return attributeAsBoolean("hidden");
	}
	/**
	 * Sets HTML attribute {@code high}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code high} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement high(String value) {
		return set("high", value);
	}
	/**
	 * Gets HTML attribute {@code high}.
	 *
	 * @return value of the {@code high} attribute or {@code null} if the attribute is missing
	 */
	default String high() {
		return attributeAsString("high");
	}
	/**
	 * Sets HTML attribute {@code high} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code high} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement high(double value) {
		return set("high", value);
	}
	/**
	 * Gets HTML attribute {@code high} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble highAsDouble() {
		return attributeAsDouble("high");
	}
	/**
	 * Sets HTML/SVG attribute {@code href}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code href} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement href(String value) {
		return set("href", value);
	}
	/**
	 * Gets HTML/SVG attribute {@code href}.
	 *
	 * @return value of the {@code href} attribute or {@code null} if the attribute is missing
	 */
	default String href() {
		return attributeAsString("href");
	}
	/**
	 * Sets HTML attribute {@code hreflang}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code hreflang} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement hreflang(String value) {
		return set("hreflang", value);
	}
	/**
	 * Gets HTML attribute {@code hreflang}.
	 *
	 * @return value of the {@code hreflang} attribute or {@code null} if the attribute is missing
	 */
	default String hreflang() {
		return attributeAsString("hreflang");
	}
	/**
	 * Sets HTML attribute {@code http-equiv}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code http-equiv} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement httpEquiv(String value) {
		return set("http-equiv", value);
	}
	/**
	 * Gets HTML attribute {@code http-equiv}.
	 *
	 * @return value of the {@code http-equiv} attribute or {@code null} if the attribute is missing
	 */
	default String httpEquiv() {
		return attributeAsString("http-equiv");
	}
	/**
	 * Sets HTML attribute {@code icon}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code icon} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement icon(String value) {
		return set("icon", value);
	}
	/**
	 * Gets HTML attribute {@code icon}.
	 *
	 * @return value of the {@code icon} attribute or {@code null} if the attribute is missing
	 */
	default String icon() {
		return attributeAsString("icon");
	}
	/**
	 * Sets SVG attribute {@code image-rendering}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code image-rendering} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement imageRendering(String value) {
		return set("image-rendering", value);
	}
	/**
	 * Gets SVG attribute {@code image-rendering}.
	 *
	 * @return value of the {@code image-rendering} attribute or {@code null} if the attribute is missing
	 */
	default String imageRendering() {
		return attributeAsString("image-rendering");
	}
	/**
	 * Sets SVG attribute {@code in}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code in} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement in(String value) {
		return set("in", value);
	}
	/**
	 * Gets SVG attribute {@code in}.
	 *
	 * @return value of the {@code in} attribute or {@code null} if the attribute is missing
	 */
	default String in() {
		return attributeAsString("in");
	}
	/**
	 * Sets SVG attribute {@code in2}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code in2} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement in2(String value) {
		return set("in2", value);
	}
	/**
	 * Gets SVG attribute {@code in2}.
	 *
	 * @return value of the {@code in2} attribute or {@code null} if the attribute is missing
	 */
	default String in2() {
		return attributeAsString("in2");
	}
	/**
	 * Sets HTML attribute {@code inputmode}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code inputmode} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement inputmode(String value) {
		return set("inputmode", value);
	}
	/**
	 * Gets HTML attribute {@code inputmode}.
	 *
	 * @return value of the {@code inputmode} attribute or {@code null} if the attribute is missing
	 */
	default String inputmode() {
		return attributeAsString("inputmode");
	}
	/**
	 * Sets HTML attribute {@code integrity}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code integrity} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement integrity(String value) {
		return set("integrity", value);
	}
	/**
	 * Gets HTML attribute {@code integrity}.
	 *
	 * @return value of the {@code integrity} attribute or {@code null} if the attribute is missing
	 */
	default String integrity() {
		return attributeAsString("integrity");
	}
	/**
	 * Adds boolean HTML attribute {@code ismap}.
	 * This method has no effect if the attribute is already present.
	 *
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement ismap() {
		return set("ismap");
	}
	/**
	 * Adds or removes boolean HTML attribute {@code ismap}.
	 *
	 * @param present
	 *            {@code true} to add the attribute, {@code false} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement ismap(boolean present) {
		return set("ismap", present);
	}
	/**
	 * Gets boolean HTML attribute {@code ismap}.
	 *
	 * @return value of the {@code ismap} attribute or {@code null} if the attribute is missing
	 */
	default boolean ismapAsBoolean() {
		return attributeAsBoolean("ismap");
	}
	/**
	 * Sets SVG attribute {@code k1}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code k1} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement k1(String value) {
		return set("k1", value);
	}
	/**
	 * Gets SVG attribute {@code k1}.
	 *
	 * @return value of the {@code k1} attribute or {@code null} if the attribute is missing
	 */
	default String k1() {
		return attributeAsString("k1");
	}
	/**
	 * Sets SVG attribute {@code k1} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code k1} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement k1(double value) {
		return set("k1", value);
	}
	/**
	 * Gets SVG attribute {@code k1} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble k1AsDouble() {
		return attributeAsDouble("k1");
	}
	/**
	 * Sets SVG attribute {@code k2}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code k2} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement k2(String value) {
		return set("k2", value);
	}
	/**
	 * Gets SVG attribute {@code k2}.
	 *
	 * @return value of the {@code k2} attribute or {@code null} if the attribute is missing
	 */
	default String k2() {
		return attributeAsString("k2");
	}
	/**
	 * Sets SVG attribute {@code k2} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code k2} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement k2(double value) {
		return set("k2", value);
	}
	/**
	 * Gets SVG attribute {@code k2} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble k2AsDouble() {
		return attributeAsDouble("k2");
	}
	/**
	 * Sets SVG attribute {@code k3}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code k3} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement k3(String value) {
		return set("k3", value);
	}
	/**
	 * Gets SVG attribute {@code k3}.
	 *
	 * @return value of the {@code k3} attribute or {@code null} if the attribute is missing
	 */
	default String k3() {
		return attributeAsString("k3");
	}
	/**
	 * Sets SVG attribute {@code k3} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code k3} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement k3(double value) {
		return set("k3", value);
	}
	/**
	 * Gets SVG attribute {@code k3} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble k3AsDouble() {
		return attributeAsDouble("k3");
	}
	/**
	 * Sets SVG attribute {@code k4}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code k4} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement k4(String value) {
		return set("k4", value);
	}
	/**
	 * Gets SVG attribute {@code k4}.
	 *
	 * @return value of the {@code k4} attribute or {@code null} if the attribute is missing
	 */
	default String k4() {
		return attributeAsString("k4");
	}
	/**
	 * Sets SVG attribute {@code k4} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code k4} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement k4(double value) {
		return set("k4", value);
	}
	/**
	 * Gets SVG attribute {@code k4} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble k4AsDouble() {
		return attributeAsDouble("k4");
	}
	/**
	 * Sets SVG attribute {@code kernelMatrix}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code kernelMatrix} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement kernelMatrix(String value) {
		return set("kernelMatrix", value);
	}
	/**
	 * Gets SVG attribute {@code kernelMatrix}.
	 *
	 * @return value of the {@code kernelMatrix} attribute or {@code null} if the attribute is missing
	 */
	default String kernelMatrix() {
		return attributeAsString("kernelMatrix");
	}
	/**
	 * Sets SVG attribute {@code kernelUnitLength}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code kernelUnitLength} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement kernelUnitLength(String value) {
		return set("kernelUnitLength", value);
	}
	/**
	 * Gets SVG attribute {@code kernelUnitLength}.
	 *
	 * @return value of the {@code kernelUnitLength} attribute or {@code null} if the attribute is missing
	 */
	default String kernelUnitLength() {
		return attributeAsString("kernelUnitLength");
	}
	/**
	 * Sets SVG attribute {@code kernelUnitLength} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code kernelUnitLength} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement kernelUnitLength(double value) {
		return set("kernelUnitLength", value);
	}
	/**
	 * Gets SVG attribute {@code kernelUnitLength} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble kernelUnitLengthAsDouble() {
		return attributeAsDouble("kernelUnitLength");
	}
	/**
	 * Sets SVG attribute {@code kerning}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code kerning} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement kerning(String value) {
		return set("kerning", value);
	}
	/**
	 * Gets SVG attribute {@code kerning}.
	 *
	 * @return value of the {@code kerning} attribute or {@code null} if the attribute is missing
	 */
	default String kerning() {
		return attributeAsString("kerning");
	}
	/**
	 * Sets SVG attribute {@code kerning} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code kerning} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement kerning(double value) {
		return set("kerning", value);
	}
	/**
	 * Gets SVG attribute {@code kerning} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble kerningAsDouble() {
		return attributeAsDouble("kerning");
	}
	/**
	 * Sets SVG attribute {@code keySplines}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code keySplines} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement keySplines(String value) {
		return set("keySplines", value);
	}
	/**
	 * Gets SVG attribute {@code keySplines}.
	 *
	 * @return value of the {@code keySplines} attribute or {@code null} if the attribute is missing
	 */
	default String keySplines() {
		return attributeAsString("keySplines");
	}
	/**
	 * Sets SVG attribute {@code keyTimes}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code keyTimes} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement keyTimes(String value) {
		return set("keyTimes", value);
	}
	/**
	 * Gets SVG attribute {@code keyTimes}.
	 *
	 * @return value of the {@code keyTimes} attribute or {@code null} if the attribute is missing
	 */
	default String keyTimes() {
		return attributeAsString("keyTimes");
	}
	/**
	 * Sets HTML attribute {@code kind}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code kind} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement kind(String value) {
		return set("kind", value);
	}
	/**
	 * Gets HTML attribute {@code kind}.
	 *
	 * @return value of the {@code kind} attribute or {@code null} if the attribute is missing
	 */
	default String kind() {
		return attributeAsString("kind");
	}
	/**
	 * Sets HTML attribute {@code label}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code label} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement label(String value) {
		return set("label", value);
	}
	/**
	 * Gets HTML attribute {@code label}.
	 *
	 * @return value of the {@code label} attribute or {@code null} if the attribute is missing
	 */
	default String label() {
		return attributeAsString("label");
	}
	/**
	 * Sets HTML attribute {@code lang}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code lang} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement lang(String value) {
		return set("lang", value);
	}
	/**
	 * Gets HTML attribute {@code lang}.
	 *
	 * @return value of the {@code lang} attribute or {@code null} if the attribute is missing
	 */
	default String lang() {
		return attributeAsString("lang");
	}
	/**
	 * Sets HTML attribute {@code language}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code language} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement language(String value) {
		return set("language", value);
	}
	/**
	 * Gets HTML attribute {@code language}.
	 *
	 * @return value of the {@code language} attribute or {@code null} if the attribute is missing
	 */
	default String language() {
		return attributeAsString("language");
	}
	/**
	 * Sets SVG attribute {@code letter-spacing}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code letter-spacing} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement letterSpacing(String value) {
		return set("letter-spacing", value);
	}
	/**
	 * Gets SVG attribute {@code letter-spacing}.
	 *
	 * @return value of the {@code letter-spacing} attribute or {@code null} if the attribute is missing
	 */
	default String letterSpacing() {
		return attributeAsString("letter-spacing");
	}
	/**
	 * Sets SVG attribute {@code letter-spacing} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code letter-spacing} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement letterSpacing(double value) {
		return set("letter-spacing", value);
	}
	/**
	 * Gets SVG attribute {@code letter-spacing} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble letterSpacingAsDouble() {
		return attributeAsDouble("letter-spacing");
	}
	/**
	 * Sets SVG attribute {@code lighting-color}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code lighting-color} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement lightingColor(String value) {
		return set("lighting-color", value);
	}
	/**
	 * Gets SVG attribute {@code lighting-color}.
	 *
	 * @return value of the {@code lighting-color} attribute or {@code null} if the attribute is missing
	 */
	default String lightingColor() {
		return attributeAsString("lighting-color");
	}
	/**
	 * Sets SVG attribute {@code limitingConeAngle}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code limitingConeAngle} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement limitingConeAngle(String value) {
		return set("limitingConeAngle", value);
	}
	/**
	 * Gets SVG attribute {@code limitingConeAngle}.
	 *
	 * @return value of the {@code limitingConeAngle} attribute or {@code null} if the attribute is missing
	 */
	default String limitingConeAngle() {
		return attributeAsString("limitingConeAngle");
	}
	/**
	 * Sets SVG attribute {@code limitingConeAngle} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code limitingConeAngle} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement limitingConeAngle(double value) {
		return set("limitingConeAngle", value);
	}
	/**
	 * Gets SVG attribute {@code limitingConeAngle} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble limitingConeAngleAsDouble() {
		return attributeAsDouble("limitingConeAngle");
	}
	/**
	 * Sets HTML attribute {@code list}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code list} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement list(String value) {
		return set("list", value);
	}
	/**
	 * Gets HTML attribute {@code list}.
	 *
	 * @return value of the {@code list} attribute or {@code null} if the attribute is missing
	 */
	default String list() {
		return attributeAsString("list");
	}
	/**
	 * Sets SVG attribute {@code local}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code local} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement local(String value) {
		return set("local", value);
	}
	/**
	 * Gets SVG attribute {@code local}.
	 *
	 * @return value of the {@code local} attribute or {@code null} if the attribute is missing
	 */
	default String local() {
		return attributeAsString("local");
	}
	/**
	 * Adds boolean HTML attribute {@code loop}.
	 * This method has no effect if the attribute is already present.
	 *
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement loop() {
		return set("loop");
	}
	/**
	 * Adds or removes boolean HTML attribute {@code loop}.
	 *
	 * @param present
	 *            {@code true} to add the attribute, {@code false} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement loop(boolean present) {
		return set("loop", present);
	}
	/**
	 * Gets boolean HTML attribute {@code loop}.
	 *
	 * @return value of the {@code loop} attribute or {@code null} if the attribute is missing
	 */
	default boolean loopAsBoolean() {
		return attributeAsBoolean("loop");
	}
	/**
	 * Sets HTML attribute {@code low}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code low} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement low(String value) {
		return set("low", value);
	}
	/**
	 * Gets HTML attribute {@code low}.
	 *
	 * @return value of the {@code low} attribute or {@code null} if the attribute is missing
	 */
	default String low() {
		return attributeAsString("low");
	}
	/**
	 * Sets HTML attribute {@code low} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code low} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement low(double value) {
		return set("low", value);
	}
	/**
	 * Gets HTML attribute {@code low} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble lowAsDouble() {
		return attributeAsDouble("low");
	}
	/**
	 * Sets SVG attribute {@code marker-end}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code marker-end} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement markerEnd(String value) {
		return set("marker-end", value);
	}
	/**
	 * Gets SVG attribute {@code marker-end}.
	 *
	 * @return value of the {@code marker-end} attribute or {@code null} if the attribute is missing
	 */
	default String markerEnd() {
		return attributeAsString("marker-end");
	}
	/**
	 * Sets SVG attribute {@code marker-mid}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code marker-mid} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement markerMid(String value) {
		return set("marker-mid", value);
	}
	/**
	 * Gets SVG attribute {@code marker-mid}.
	 *
	 * @return value of the {@code marker-mid} attribute or {@code null} if the attribute is missing
	 */
	default String markerMid() {
		return attributeAsString("marker-mid");
	}
	/**
	 * Sets SVG attribute {@code marker-start}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code marker-start} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement markerStart(String value) {
		return set("marker-start", value);
	}
	/**
	 * Gets SVG attribute {@code marker-start}.
	 *
	 * @return value of the {@code marker-start} attribute or {@code null} if the attribute is missing
	 */
	default String markerStart() {
		return attributeAsString("marker-start");
	}
	/**
	 * Sets SVG attribute {@code markerHeight}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code markerHeight} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement markerHeight(String value) {
		return set("markerHeight", value);
	}
	/**
	 * Gets SVG attribute {@code markerHeight}.
	 *
	 * @return value of the {@code markerHeight} attribute or {@code null} if the attribute is missing
	 */
	default String markerHeight() {
		return attributeAsString("markerHeight");
	}
	/**
	 * Sets SVG attribute {@code markerHeight} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code markerHeight} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement markerHeight(double value) {
		return set("markerHeight", value);
	}
	/**
	 * Gets SVG attribute {@code markerHeight} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble markerHeightAsDouble() {
		return attributeAsDouble("markerHeight");
	}
	/**
	 * Sets SVG attribute {@code markerUnits}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code markerUnits} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement markerUnits(String value) {
		return set("markerUnits", value);
	}
	/**
	 * Gets SVG attribute {@code markerUnits}.
	 *
	 * @return value of the {@code markerUnits} attribute or {@code null} if the attribute is missing
	 */
	default String markerUnits() {
		return attributeAsString("markerUnits");
	}
	/**
	 * Sets SVG attribute {@code markerWidth}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code markerWidth} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement markerWidth(String value) {
		return set("markerWidth", value);
	}
	/**
	 * Gets SVG attribute {@code markerWidth}.
	 *
	 * @return value of the {@code markerWidth} attribute or {@code null} if the attribute is missing
	 */
	default String markerWidth() {
		return attributeAsString("markerWidth");
	}
	/**
	 * Sets SVG attribute {@code markerWidth} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code markerWidth} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement markerWidth(double value) {
		return set("markerWidth", value);
	}
	/**
	 * Gets SVG attribute {@code markerWidth} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble markerWidthAsDouble() {
		return attributeAsDouble("markerWidth");
	}
	/**
	 * Sets SVG attribute {@code mask}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code mask} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement mask(String value) {
		return set("mask", value);
	}
	/**
	 * Gets SVG attribute {@code mask}.
	 *
	 * @return value of the {@code mask} attribute or {@code null} if the attribute is missing
	 */
	default String mask() {
		return attributeAsString("mask");
	}
	/**
	 * Sets SVG attribute {@code maskContentUnits}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code maskContentUnits} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement maskContentUnits(String value) {
		return set("maskContentUnits", value);
	}
	/**
	 * Gets SVG attribute {@code maskContentUnits}.
	 *
	 * @return value of the {@code maskContentUnits} attribute or {@code null} if the attribute is missing
	 */
	default String maskContentUnits() {
		return attributeAsString("maskContentUnits");
	}
	/**
	 * Sets SVG attribute {@code maskUnits}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code maskUnits} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement maskUnits(String value) {
		return set("maskUnits", value);
	}
	/**
	 * Gets SVG attribute {@code maskUnits}.
	 *
	 * @return value of the {@code maskUnits} attribute or {@code null} if the attribute is missing
	 */
	default String maskUnits() {
		return attributeAsString("maskUnits");
	}
	/**
	 * Sets HTML/SVG attribute {@code max}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code max} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement max(String value) {
		return set("max", value);
	}
	/**
	 * Gets HTML/SVG attribute {@code max}.
	 *
	 * @return value of the {@code max} attribute or {@code null} if the attribute is missing
	 */
	default String max() {
		return attributeAsString("max");
	}
	/**
	 * Sets HTML/SVG attribute {@code max} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code max} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement max(double value) {
		return set("max", value);
	}
	/**
	 * Gets HTML/SVG attribute {@code max} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble maxAsDouble() {
		return attributeAsDouble("max");
	}
	/**
	 * Sets HTML attribute {@code maxlength}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code maxlength} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement maxlength(String value) {
		return set("maxlength", value);
	}
	/**
	 * Gets HTML attribute {@code maxlength}.
	 *
	 * @return value of the {@code maxlength} attribute or {@code null} if the attribute is missing
	 */
	default String maxlength() {
		return attributeAsString("maxlength");
	}
	/**
	 * Sets HTML attribute {@code maxlength} to an integer value.
	 *
	 * @param value
	 *            new value of the {@code maxlength} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement maxlength(int value) {
		return set("maxlength", value);
	}
	/**
	 * Gets HTML attribute {@code maxlength} as an integer value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not an integer
	 */
	default OptionalInt maxlengthAsInt() {
		return attributeAsInt("maxlength");
	}
	/**
	 * Sets HTML attribute {@code media}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code media} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement media(String value) {
		return set("media", value);
	}
	/**
	 * Gets HTML attribute {@code media}.
	 *
	 * @return value of the {@code media} attribute or {@code null} if the attribute is missing
	 */
	default String media() {
		return attributeAsString("media");
	}
	/**
	 * Sets HTML attribute {@code method}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code method} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement method(String value) {
		return set("method", value);
	}
	/**
	 * Gets HTML attribute {@code method}.
	 *
	 * @return value of the {@code method} attribute or {@code null} if the attribute is missing
	 */
	default String method() {
		return attributeAsString("method");
	}
	/**
	 * Sets HTML/SVG attribute {@code min}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code min} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement min(String value) {
		return set("min", value);
	}
	/**
	 * Gets HTML/SVG attribute {@code min}.
	 *
	 * @return value of the {@code min} attribute or {@code null} if the attribute is missing
	 */
	default String min() {
		return attributeAsString("min");
	}
	/**
	 * Sets HTML/SVG attribute {@code min} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code min} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement min(double value) {
		return set("min", value);
	}
	/**
	 * Gets HTML/SVG attribute {@code min} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble minAsDouble() {
		return attributeAsDouble("min");
	}
	/**
	 * Sets HTML attribute {@code minlength}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code minlength} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement minlength(String value) {
		return set("minlength", value);
	}
	/**
	 * Gets HTML attribute {@code minlength}.
	 *
	 * @return value of the {@code minlength} attribute or {@code null} if the attribute is missing
	 */
	default String minlength() {
		return attributeAsString("minlength");
	}
	/**
	 * Sets HTML attribute {@code minlength} to an integer value.
	 *
	 * @param value
	 *            new value of the {@code minlength} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement minlength(int value) {
		return set("minlength", value);
	}
	/**
	 * Gets HTML attribute {@code minlength} as an integer value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not an integer
	 */
	default OptionalInt minlengthAsInt() {
		return attributeAsInt("minlength");
	}
	/**
	 * Sets SVG attribute {@code mode}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code mode} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement mode(String value) {
		return set("mode", value);
	}
	/**
	 * Gets SVG attribute {@code mode}.
	 *
	 * @return value of the {@code mode} attribute or {@code null} if the attribute is missing
	 */
	default String mode() {
		return attributeAsString("mode");
	}
	/**
	 * Adds boolean HTML attribute {@code multiple}.
	 * This method has no effect if the attribute is already present.
	 *
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement multiple() {
		return set("multiple");
	}
	/**
	 * Adds or removes boolean HTML attribute {@code multiple}.
	 *
	 * @param present
	 *            {@code true} to add the attribute, {@code false} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement multiple(boolean present) {
		return set("multiple", present);
	}
	/**
	 * Gets boolean HTML attribute {@code multiple}.
	 *
	 * @return value of the {@code multiple} attribute or {@code null} if the attribute is missing
	 */
	default boolean multipleAsBoolean() {
		return attributeAsBoolean("multiple");
	}
	/**
	 * Adds boolean HTML attribute {@code muted}.
	 * This method has no effect if the attribute is already present.
	 *
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement muted() {
		return set("muted");
	}
	/**
	 * Adds or removes boolean HTML attribute {@code muted}.
	 *
	 * @param present
	 *            {@code true} to add the attribute, {@code false} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement muted(boolean present) {
		return set("muted", present);
	}
	/**
	 * Gets boolean HTML attribute {@code muted}.
	 *
	 * @return value of the {@code muted} attribute or {@code null} if the attribute is missing
	 */
	default boolean mutedAsBoolean() {
		return attributeAsBoolean("muted");
	}
	/**
	 * Sets HTML attribute {@code name}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code name} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement name(String value) {
		return set("name", value);
	}
	/**
	 * Gets HTML attribute {@code name}.
	 *
	 * @return value of the {@code name} attribute or {@code null} if the attribute is missing
	 */
	default String name() {
		return attributeAsString("name");
	}
	/**
	 * Adds boolean HTML attribute {@code novalidate}.
	 * This method has no effect if the attribute is already present.
	 *
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement novalidate() {
		return set("novalidate");
	}
	/**
	 * Adds or removes boolean HTML attribute {@code novalidate}.
	 *
	 * @param present
	 *            {@code true} to add the attribute, {@code false} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement novalidate(boolean present) {
		return set("novalidate", present);
	}
	/**
	 * Gets boolean HTML attribute {@code novalidate}.
	 *
	 * @return value of the {@code novalidate} attribute or {@code null} if the attribute is missing
	 */
	default boolean novalidateAsBoolean() {
		return attributeAsBoolean("novalidate");
	}
	/**
	 * Sets SVG attribute {@code numOctaves}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code numOctaves} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement numOctaves(String value) {
		return set("numOctaves", value);
	}
	/**
	 * Gets SVG attribute {@code numOctaves}.
	 *
	 * @return value of the {@code numOctaves} attribute or {@code null} if the attribute is missing
	 */
	default String numOctaves() {
		return attributeAsString("numOctaves");
	}
	/**
	 * Sets SVG attribute {@code numOctaves} to an integer value.
	 *
	 * @param value
	 *            new value of the {@code numOctaves} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement numOctaves(int value) {
		return set("numOctaves", value);
	}
	/**
	 * Gets SVG attribute {@code numOctaves} as an integer value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not an integer
	 */
	default OptionalInt numOctavesAsInt() {
		return attributeAsInt("numOctaves");
	}
	/**
	 * Sets SVG attribute {@code opacity}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code opacity} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement opacity(String value) {
		return set("opacity", value);
	}
	/**
	 * Gets SVG attribute {@code opacity}.
	 *
	 * @return value of the {@code opacity} attribute or {@code null} if the attribute is missing
	 */
	default String opacity() {
		return attributeAsString("opacity");
	}
	/**
	 * Sets SVG attribute {@code opacity} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code opacity} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement opacity(double value) {
		return set("opacity", value);
	}
	/**
	 * Gets SVG attribute {@code opacity} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble opacityAsDouble() {
		return attributeAsDouble("opacity");
	}
	/**
	 * Adds boolean HTML attribute {@code open}.
	 * This method has no effect if the attribute is already present.
	 *
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement open() {
		return set("open");
	}
	/**
	 * Adds or removes boolean HTML attribute {@code open}.
	 *
	 * @param present
	 *            {@code true} to add the attribute, {@code false} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement open(boolean present) {
		return set("open", present);
	}
	/**
	 * Gets boolean HTML attribute {@code open}.
	 *
	 * @return value of the {@code open} attribute or {@code null} if the attribute is missing
	 */
	default boolean openAsBoolean() {
		return attributeAsBoolean("open");
	}
	/**
	 * Sets SVG attribute {@code operator}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code operator} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement operator(String value) {
		return set("operator", value);
	}
	/**
	 * Gets SVG attribute {@code operator}.
	 *
	 * @return value of the {@code operator} attribute or {@code null} if the attribute is missing
	 */
	default String operator() {
		return attributeAsString("operator");
	}
	/**
	 * Sets HTML attribute {@code optimum}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code optimum} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement optimum(String value) {
		return set("optimum", value);
	}
	/**
	 * Gets HTML attribute {@code optimum}.
	 *
	 * @return value of the {@code optimum} attribute or {@code null} if the attribute is missing
	 */
	default String optimum() {
		return attributeAsString("optimum");
	}
	/**
	 * Sets HTML attribute {@code optimum} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code optimum} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement optimum(double value) {
		return set("optimum", value);
	}
	/**
	 * Gets HTML attribute {@code optimum} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble optimumAsDouble() {
		return attributeAsDouble("optimum");
	}
	/**
	 * Sets SVG attribute {@code order}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code order} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement order(String value) {
		return set("order", value);
	}
	/**
	 * Gets SVG attribute {@code order}.
	 *
	 * @return value of the {@code order} attribute or {@code null} if the attribute is missing
	 */
	default String order() {
		return attributeAsString("order");
	}
	/**
	 * Sets SVG attribute {@code order} to an integer value.
	 *
	 * @param value
	 *            new value of the {@code order} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement order(int value) {
		return set("order", value);
	}
	/**
	 * Gets SVG attribute {@code order} as an integer value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not an integer
	 */
	default OptionalInt orderAsInt() {
		return attributeAsInt("order");
	}
	/**
	 * Sets SVG attribute {@code overflow}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code overflow} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement overflow(String value) {
		return set("overflow", value);
	}
	/**
	 * Gets SVG attribute {@code overflow}.
	 *
	 * @return value of the {@code overflow} attribute or {@code null} if the attribute is missing
	 */
	default String overflow() {
		return attributeAsString("overflow");
	}
	/**
	 * Sets SVG attribute {@code overline-position}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code overline-position} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement overlinePosition(String value) {
		return set("overline-position", value);
	}
	/**
	 * Gets SVG attribute {@code overline-position}.
	 *
	 * @return value of the {@code overline-position} attribute or {@code null} if the attribute is missing
	 */
	default String overlinePosition() {
		return attributeAsString("overline-position");
	}
	/**
	 * Sets SVG attribute {@code overline-position} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code overline-position} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement overlinePosition(double value) {
		return set("overline-position", value);
	}
	/**
	 * Gets SVG attribute {@code overline-position} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble overlinePositionAsDouble() {
		return attributeAsDouble("overline-position");
	}
	/**
	 * Sets SVG attribute {@code overline-thickness}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code overline-thickness} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement overlineThickness(String value) {
		return set("overline-thickness", value);
	}
	/**
	 * Gets SVG attribute {@code overline-thickness}.
	 *
	 * @return value of the {@code overline-thickness} attribute or {@code null} if the attribute is missing
	 */
	default String overlineThickness() {
		return attributeAsString("overline-thickness");
	}
	/**
	 * Sets SVG attribute {@code overline-thickness} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code overline-thickness} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement overlineThickness(double value) {
		return set("overline-thickness", value);
	}
	/**
	 * Gets SVG attribute {@code overline-thickness} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble overlineThicknessAsDouble() {
		return attributeAsDouble("overline-thickness");
	}
	/**
	 * Sets SVG attribute {@code paint-order}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code paint-order} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement paintOrder(String value) {
		return set("paint-order", value);
	}
	/**
	 * Gets SVG attribute {@code paint-order}.
	 *
	 * @return value of the {@code paint-order} attribute or {@code null} if the attribute is missing
	 */
	default String paintOrder() {
		return attributeAsString("paint-order");
	}
	/**
	 * Sets SVG attribute {@code pathLength}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code pathLength} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement pathLength(String value) {
		return set("pathLength", value);
	}
	/**
	 * Gets SVG attribute {@code pathLength}.
	 *
	 * @return value of the {@code pathLength} attribute or {@code null} if the attribute is missing
	 */
	default String pathLength() {
		return attributeAsString("pathLength");
	}
	/**
	 * Sets SVG attribute {@code pathLength} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code pathLength} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement pathLength(double value) {
		return set("pathLength", value);
	}
	/**
	 * Gets SVG attribute {@code pathLength} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble pathLengthAsDouble() {
		return attributeAsDouble("pathLength");
	}
	/**
	 * Sets HTML attribute {@code pattern}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code pattern} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement pattern(String value) {
		return set("pattern", value);
	}
	/**
	 * Gets HTML attribute {@code pattern}.
	 *
	 * @return value of the {@code pattern} attribute or {@code null} if the attribute is missing
	 */
	default String pattern() {
		return attributeAsString("pattern");
	}
	/**
	 * Sets SVG attribute {@code patternContentUnits}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code patternContentUnits} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement patternContentUnits(String value) {
		return set("patternContentUnits", value);
	}
	/**
	 * Gets SVG attribute {@code patternContentUnits}.
	 *
	 * @return value of the {@code patternContentUnits} attribute or {@code null} if the attribute is missing
	 */
	default String patternContentUnits() {
		return attributeAsString("patternContentUnits");
	}
	/**
	 * Sets SVG attribute {@code patternTransform}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code patternTransform} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement patternTransform(String value) {
		return set("patternTransform", value);
	}
	/**
	 * Gets SVG attribute {@code patternTransform}.
	 *
	 * @return value of the {@code patternTransform} attribute or {@code null} if the attribute is missing
	 */
	default String patternTransform() {
		return attributeAsString("patternTransform");
	}
	/**
	 * Sets SVG attribute {@code patternUnits}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code patternUnits} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement patternUnits(String value) {
		return set("patternUnits", value);
	}
	/**
	 * Gets SVG attribute {@code patternUnits}.
	 *
	 * @return value of the {@code patternUnits} attribute or {@code null} if the attribute is missing
	 */
	default String patternUnits() {
		return attributeAsString("patternUnits");
	}
	/**
	 * Adds boolean HTML attribute {@code ping}.
	 * This method has no effect if the attribute is already present.
	 *
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement ping() {
		return set("ping");
	}
	/**
	 * Adds or removes boolean HTML attribute {@code ping}.
	 *
	 * @param present
	 *            {@code true} to add the attribute, {@code false} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement ping(boolean present) {
		return set("ping", present);
	}
	/**
	 * Gets boolean HTML attribute {@code ping}.
	 *
	 * @return value of the {@code ping} attribute or {@code null} if the attribute is missing
	 */
	default boolean pingAsBoolean() {
		return attributeAsBoolean("ping");
	}
	/**
	 * Sets HTML attribute {@code placeholder}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code placeholder} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement placeholder(String value) {
		return set("placeholder", value);
	}
	/**
	 * Gets HTML attribute {@code placeholder}.
	 *
	 * @return value of the {@code placeholder} attribute or {@code null} if the attribute is missing
	 */
	default String placeholder() {
		return attributeAsString("placeholder");
	}
	/**
	 * Sets SVG attribute {@code pointer-events}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code pointer-events} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement pointerEvents(String value) {
		return set("pointer-events", value);
	}
	/**
	 * Gets SVG attribute {@code pointer-events}.
	 *
	 * @return value of the {@code pointer-events} attribute or {@code null} if the attribute is missing
	 */
	default String pointerEvents() {
		return attributeAsString("pointer-events");
	}
	/**
	 * Sets SVG attribute {@code points}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code points} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement points(String value) {
		return set("points", value);
	}
	/**
	 * Gets SVG attribute {@code points}.
	 *
	 * @return value of the {@code points} attribute or {@code null} if the attribute is missing
	 */
	default String points() {
		return attributeAsString("points");
	}
	/**
	 * Sets SVG attribute {@code pointsAtX}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code pointsAtX} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement pointsAtX(String value) {
		return set("pointsAtX", value);
	}
	/**
	 * Gets SVG attribute {@code pointsAtX}.
	 *
	 * @return value of the {@code pointsAtX} attribute or {@code null} if the attribute is missing
	 */
	default String pointsAtX() {
		return attributeAsString("pointsAtX");
	}
	/**
	 * Sets SVG attribute {@code pointsAtX} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code pointsAtX} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement pointsAtX(double value) {
		return set("pointsAtX", value);
	}
	/**
	 * Gets SVG attribute {@code pointsAtX} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble pointsAtXAsDouble() {
		return attributeAsDouble("pointsAtX");
	}
	/**
	 * Sets SVG attribute {@code pointsAtY}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code pointsAtY} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement pointsAtY(String value) {
		return set("pointsAtY", value);
	}
	/**
	 * Gets SVG attribute {@code pointsAtY}.
	 *
	 * @return value of the {@code pointsAtY} attribute or {@code null} if the attribute is missing
	 */
	default String pointsAtY() {
		return attributeAsString("pointsAtY");
	}
	/**
	 * Sets SVG attribute {@code pointsAtY} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code pointsAtY} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement pointsAtY(double value) {
		return set("pointsAtY", value);
	}
	/**
	 * Gets SVG attribute {@code pointsAtY} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble pointsAtYAsDouble() {
		return attributeAsDouble("pointsAtY");
	}
	/**
	 * Sets SVG attribute {@code pointsAtZ}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code pointsAtZ} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement pointsAtZ(String value) {
		return set("pointsAtZ", value);
	}
	/**
	 * Gets SVG attribute {@code pointsAtZ}.
	 *
	 * @return value of the {@code pointsAtZ} attribute or {@code null} if the attribute is missing
	 */
	default String pointsAtZ() {
		return attributeAsString("pointsAtZ");
	}
	/**
	 * Sets SVG attribute {@code pointsAtZ} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code pointsAtZ} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement pointsAtZ(double value) {
		return set("pointsAtZ", value);
	}
	/**
	 * Gets SVG attribute {@code pointsAtZ} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble pointsAtZAsDouble() {
		return attributeAsDouble("pointsAtZ");
	}
	/**
	 * Sets HTML attribute {@code poster}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code poster} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement poster(String value) {
		return set("poster", value);
	}
	/**
	 * Gets HTML attribute {@code poster}.
	 *
	 * @return value of the {@code poster} attribute or {@code null} if the attribute is missing
	 */
	default String poster() {
		return attributeAsString("poster");
	}
	/**
	 * Sets HTML attribute {@code preload}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code preload} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement preload(String value) {
		return set("preload", value);
	}
	/**
	 * Gets HTML attribute {@code preload}.
	 *
	 * @return value of the {@code preload} attribute or {@code null} if the attribute is missing
	 */
	default String preload() {
		return attributeAsString("preload");
	}
	/**
	 * Sets SVG attribute {@code preserveAlpha}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code preserveAlpha} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement preserveAlpha(String value) {
		return set("preserveAlpha", value);
	}
	/**
	 * Gets SVG attribute {@code preserveAlpha}.
	 *
	 * @return value of the {@code preserveAlpha} attribute or {@code null} if the attribute is missing
	 */
	default String preserveAlpha() {
		return attributeAsString("preserveAlpha");
	}
	/**
	 * Sets SVG attribute {@code preserveAspectRatio}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code preserveAspectRatio} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement preserveAspectRatio(String value) {
		return set("preserveAspectRatio", value);
	}
	/**
	 * Gets SVG attribute {@code preserveAspectRatio}.
	 *
	 * @return value of the {@code preserveAspectRatio} attribute or {@code null} if the attribute is missing
	 */
	default String preserveAspectRatio() {
		return attributeAsString("preserveAspectRatio");
	}
	/**
	 * Sets SVG attribute {@code primitiveUnits}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code primitiveUnits} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement primitiveUnits(String value) {
		return set("primitiveUnits", value);
	}
	/**
	 * Gets SVG attribute {@code primitiveUnits}.
	 *
	 * @return value of the {@code primitiveUnits} attribute or {@code null} if the attribute is missing
	 */
	default String primitiveUnits() {
		return attributeAsString("primitiveUnits");
	}
	/**
	 * Sets SVG attribute {@code r}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code r} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement r(String value) {
		return set("r", value);
	}
	/**
	 * Gets SVG attribute {@code r}.
	 *
	 * @return value of the {@code r} attribute or {@code null} if the attribute is missing
	 */
	default String r() {
		return attributeAsString("r");
	}
	/**
	 * Sets SVG attribute {@code r} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code r} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement r(double value) {
		return set("r", value);
	}
	/**
	 * Gets SVG attribute {@code r} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble rAsDouble() {
		return attributeAsDouble("r");
	}
	/**
	 * Sets HTML attribute {@code radiogroup}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code radiogroup} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement radiogroup(String value) {
		return set("radiogroup", value);
	}
	/**
	 * Gets HTML attribute {@code radiogroup}.
	 *
	 * @return value of the {@code radiogroup} attribute or {@code null} if the attribute is missing
	 */
	default String radiogroup() {
		return attributeAsString("radiogroup");
	}
	/**
	 * Sets SVG attribute {@code radius}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code radius} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement radius(String value) {
		return set("radius", value);
	}
	/**
	 * Gets SVG attribute {@code radius}.
	 *
	 * @return value of the {@code radius} attribute or {@code null} if the attribute is missing
	 */
	default String radius() {
		return attributeAsString("radius");
	}
	/**
	 * Sets SVG attribute {@code radius} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code radius} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement radius(double value) {
		return set("radius", value);
	}
	/**
	 * Gets SVG attribute {@code radius} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble radiusAsDouble() {
		return attributeAsDouble("radius");
	}
	/**
	 * Adds boolean HTML attribute {@code readonly}.
	 * This method has no effect if the attribute is already present.
	 *
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement readonly() {
		return set("readonly");
	}
	/**
	 * Adds or removes boolean HTML attribute {@code readonly}.
	 *
	 * @param present
	 *            {@code true} to add the attribute, {@code false} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement readonly(boolean present) {
		return set("readonly", present);
	}
	/**
	 * Gets boolean HTML attribute {@code readonly}.
	 *
	 * @return value of the {@code readonly} attribute or {@code null} if the attribute is missing
	 */
	default boolean readonlyAsBoolean() {
		return attributeAsBoolean("readonly");
	}
	/**
	 * Sets SVG attribute {@code refX}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code refX} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement refX(String value) {
		return set("refX", value);
	}
	/**
	 * Gets SVG attribute {@code refX}.
	 *
	 * @return value of the {@code refX} attribute or {@code null} if the attribute is missing
	 */
	default String refX() {
		return attributeAsString("refX");
	}
	/**
	 * Sets SVG attribute {@code refX} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code refX} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement refX(double value) {
		return set("refX", value);
	}
	/**
	 * Gets SVG attribute {@code refX} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble refXAsDouble() {
		return attributeAsDouble("refX");
	}
	/**
	 * Sets SVG attribute {@code refy}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code refy} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement refy(String value) {
		return set("refy", value);
	}
	/**
	 * Gets SVG attribute {@code refy}.
	 *
	 * @return value of the {@code refy} attribute or {@code null} if the attribute is missing
	 */
	default String refy() {
		return attributeAsString("refy");
	}
	/**
	 * Sets SVG attribute {@code refy} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code refy} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement refy(double value) {
		return set("refy", value);
	}
	/**
	 * Gets SVG attribute {@code refy} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble refyAsDouble() {
		return attributeAsDouble("refy");
	}
	/**
	 * Sets HTML attribute {@code rel}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code rel} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement rel(String value) {
		return set("rel", value);
	}
	/**
	 * Gets HTML attribute {@code rel}.
	 *
	 * @return value of the {@code rel} attribute or {@code null} if the attribute is missing
	 */
	default String rel() {
		return attributeAsString("rel");
	}
	/**
	 * Sets SVG attribute {@code repeatCount}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code repeatCount} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement repeatCount(String value) {
		return set("repeatCount", value);
	}
	/**
	 * Gets SVG attribute {@code repeatCount}.
	 *
	 * @return value of the {@code repeatCount} attribute or {@code null} if the attribute is missing
	 */
	default String repeatCount() {
		return attributeAsString("repeatCount");
	}
	/**
	 * Sets SVG attribute {@code repeatCount} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code repeatCount} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement repeatCount(double value) {
		return set("repeatCount", value);
	}
	/**
	 * Gets SVG attribute {@code repeatCount} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble repeatCountAsDouble() {
		return attributeAsDouble("repeatCount");
	}
	/**
	 * Sets SVG attribute {@code repeatDur}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code repeatDur} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement repeatDur(String value) {
		return set("repeatDur", value);
	}
	/**
	 * Gets SVG attribute {@code repeatDur}.
	 *
	 * @return value of the {@code repeatDur} attribute or {@code null} if the attribute is missing
	 */
	default String repeatDur() {
		return attributeAsString("repeatDur");
	}
	/**
	 * Adds boolean HTML attribute {@code required}.
	 * This method has no effect if the attribute is already present.
	 *
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement required() {
		return set("required");
	}
	/**
	 * Adds or removes boolean HTML attribute {@code required}.
	 *
	 * @param present
	 *            {@code true} to add the attribute, {@code false} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement required(boolean present) {
		return set("required", present);
	}
	/**
	 * Gets boolean HTML attribute {@code required}.
	 *
	 * @return value of the {@code required} attribute or {@code null} if the attribute is missing
	 */
	default boolean requiredAsBoolean() {
		return attributeAsBoolean("required");
	}
	/**
	 * Sets SVG attribute {@code requiredFeatures}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code requiredFeatures} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement requiredFeatures(String value) {
		return set("requiredFeatures", value);
	}
	/**
	 * Gets SVG attribute {@code requiredFeatures}.
	 *
	 * @return value of the {@code requiredFeatures} attribute or {@code null} if the attribute is missing
	 */
	default String requiredFeatures() {
		return attributeAsString("requiredFeatures");
	}
	/**
	 * Sets SVG attribute {@code restart}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code restart} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement restart(String value) {
		return set("restart", value);
	}
	/**
	 * Gets SVG attribute {@code restart}.
	 *
	 * @return value of the {@code restart} attribute or {@code null} if the attribute is missing
	 */
	default String restart() {
		return attributeAsString("restart");
	}
	/**
	 * Sets SVG attribute {@code result}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code result} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement result(String value) {
		return set("result", value);
	}
	/**
	 * Gets SVG attribute {@code result}.
	 *
	 * @return value of the {@code result} attribute or {@code null} if the attribute is missing
	 */
	default String result() {
		return attributeAsString("result");
	}
	/**
	 * Adds boolean HTML attribute {@code reversed}.
	 * This method has no effect if the attribute is already present.
	 *
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement reversed() {
		return set("reversed");
	}
	/**
	 * Adds or removes boolean HTML attribute {@code reversed}.
	 *
	 * @param present
	 *            {@code true} to add the attribute, {@code false} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement reversed(boolean present) {
		return set("reversed", present);
	}
	/**
	 * Gets boolean HTML attribute {@code reversed}.
	 *
	 * @return value of the {@code reversed} attribute or {@code null} if the attribute is missing
	 */
	default boolean reversedAsBoolean() {
		return attributeAsBoolean("reversed");
	}
	/**
	 * Sets HTML attribute {@code role}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code role} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement role(String value) {
		return set("role", value);
	}
	/**
	 * Gets HTML attribute {@code role}.
	 *
	 * @return value of the {@code role} attribute or {@code null} if the attribute is missing
	 */
	default String role() {
		return attributeAsString("role");
	}
	/**
	 * Sets HTML attribute {@code rows}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code rows} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement rows(String value) {
		return set("rows", value);
	}
	/**
	 * Gets HTML attribute {@code rows}.
	 *
	 * @return value of the {@code rows} attribute or {@code null} if the attribute is missing
	 */
	default String rows() {
		return attributeAsString("rows");
	}
	/**
	 * Sets HTML attribute {@code rows} to an integer value.
	 *
	 * @param value
	 *            new value of the {@code rows} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement rows(int value) {
		return set("rows", value);
	}
	/**
	 * Gets HTML attribute {@code rows} as an integer value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not an integer
	 */
	default OptionalInt rowsAsInt() {
		return attributeAsInt("rows");
	}
	/**
	 * Sets HTML attribute {@code rowspan}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code rowspan} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement rowspan(String value) {
		return set("rowspan", value);
	}
	/**
	 * Gets HTML attribute {@code rowspan}.
	 *
	 * @return value of the {@code rowspan} attribute or {@code null} if the attribute is missing
	 */
	default String rowspan() {
		return attributeAsString("rowspan");
	}
	/**
	 * Sets HTML attribute {@code rowspan} to an integer value.
	 *
	 * @param value
	 *            new value of the {@code rowspan} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement rowspan(int value) {
		return set("rowspan", value);
	}
	/**
	 * Gets HTML attribute {@code rowspan} as an integer value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not an integer
	 */
	default OptionalInt rowspanAsInt() {
		return attributeAsInt("rowspan");
	}
	/**
	 * Sets SVG attribute {@code rx}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code rx} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement rx(String value) {
		return set("rx", value);
	}
	/**
	 * Gets SVG attribute {@code rx}.
	 *
	 * @return value of the {@code rx} attribute or {@code null} if the attribute is missing
	 */
	default String rx() {
		return attributeAsString("rx");
	}
	/**
	 * Sets SVG attribute {@code rx} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code rx} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement rx(double value) {
		return set("rx", value);
	}
	/**
	 * Gets SVG attribute {@code rx} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble rxAsDouble() {
		return attributeAsDouble("rx");
	}
	/**
	 * Sets SVG attribute {@code ry}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code ry} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement ry(String value) {
		return set("ry", value);
	}
	/**
	 * Gets SVG attribute {@code ry}.
	 *
	 * @return value of the {@code ry} attribute or {@code null} if the attribute is missing
	 */
	default String ry() {
		return attributeAsString("ry");
	}
	/**
	 * Sets SVG attribute {@code ry} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code ry} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement ry(double value) {
		return set("ry", value);
	}
	/**
	 * Gets SVG attribute {@code ry} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble ryAsDouble() {
		return attributeAsDouble("ry");
	}
	/**
	 * Sets HTML attribute {@code sandbox}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code sandbox} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement sandbox(String value) {
		return set("sandbox", value);
	}
	/**
	 * Gets HTML attribute {@code sandbox}.
	 *
	 * @return value of the {@code sandbox} attribute or {@code null} if the attribute is missing
	 */
	default String sandbox() {
		return attributeAsString("sandbox");
	}
	/**
	 * Sets SVG attribute {@code scale}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code scale} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement scale(String value) {
		return set("scale", value);
	}
	/**
	 * Gets SVG attribute {@code scale}.
	 *
	 * @return value of the {@code scale} attribute or {@code null} if the attribute is missing
	 */
	default String scale() {
		return attributeAsString("scale");
	}
	/**
	 * Sets SVG attribute {@code scale} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code scale} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement scale(double value) {
		return set("scale", value);
	}
	/**
	 * Gets SVG attribute {@code scale} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble scaleAsDouble() {
		return attributeAsDouble("scale");
	}
	/**
	 * Sets HTML attribute {@code scope}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code scope} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement scope(String value) {
		return set("scope", value);
	}
	/**
	 * Gets HTML attribute {@code scope}.
	 *
	 * @return value of the {@code scope} attribute or {@code null} if the attribute is missing
	 */
	default String scope() {
		return attributeAsString("scope");
	}
	/**
	 * Sets SVG attribute {@code seed}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code seed} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement seed(String value) {
		return set("seed", value);
	}
	/**
	 * Gets SVG attribute {@code seed}.
	 *
	 * @return value of the {@code seed} attribute or {@code null} if the attribute is missing
	 */
	default String seed() {
		return attributeAsString("seed");
	}
	/**
	 * Sets SVG attribute {@code seed} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code seed} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement seed(double value) {
		return set("seed", value);
	}
	/**
	 * Gets SVG attribute {@code seed} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble seedAsDouble() {
		return attributeAsDouble("seed");
	}
	/**
	 * Adds boolean HTML attribute {@code selected}.
	 * This method has no effect if the attribute is already present.
	 *
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement selected() {
		return set("selected");
	}
	/**
	 * Adds or removes boolean HTML attribute {@code selected}.
	 *
	 * @param present
	 *            {@code true} to add the attribute, {@code false} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement selected(boolean present) {
		return set("selected", present);
	}
	/**
	 * Gets boolean HTML attribute {@code selected}.
	 *
	 * @return value of the {@code selected} attribute or {@code null} if the attribute is missing
	 */
	default boolean selectedAsBoolean() {
		return attributeAsBoolean("selected");
	}
	/**
	 * Sets HTML attribute {@code selectionDirection}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code selectionDirection} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement selectionDirection(String value) {
		return set("selectionDirection", value);
	}
	/**
	 * Gets HTML attribute {@code selectionDirection}.
	 *
	 * @return value of the {@code selectionDirection} attribute or {@code null} if the attribute is missing
	 */
	default String selectionDirection() {
		return attributeAsString("selectionDirection");
	}
	/**
	 * Sets HTML attribute {@code selectionEnd}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code selectionEnd} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement selectionEnd(String value) {
		return set("selectionEnd", value);
	}
	/**
	 * Gets HTML attribute {@code selectionEnd}.
	 *
	 * @return value of the {@code selectionEnd} attribute or {@code null} if the attribute is missing
	 */
	default String selectionEnd() {
		return attributeAsString("selectionEnd");
	}
	/**
	 * Sets HTML attribute {@code selectionEnd} to an integer value.
	 *
	 * @param value
	 *            new value of the {@code selectionEnd} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement selectionEnd(int value) {
		return set("selectionEnd", value);
	}
	/**
	 * Gets HTML attribute {@code selectionEnd} as an integer value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not an integer
	 */
	default OptionalInt selectionEndAsInt() {
		return attributeAsInt("selectionEnd");
	}
	/**
	 * Sets HTML attribute {@code selectionStart}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code selectionStart} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement selectionStart(String value) {
		return set("selectionStart", value);
	}
	/**
	 * Gets HTML attribute {@code selectionStart}.
	 *
	 * @return value of the {@code selectionStart} attribute or {@code null} if the attribute is missing
	 */
	default String selectionStart() {
		return attributeAsString("selectionStart");
	}
	/**
	 * Sets HTML attribute {@code selectionStart} to an integer value.
	 *
	 * @param value
	 *            new value of the {@code selectionStart} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement selectionStart(int value) {
		return set("selectionStart", value);
	}
	/**
	 * Gets HTML attribute {@code selectionStart} as an integer value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not an integer
	 */
	default OptionalInt selectionStartAsInt() {
		return attributeAsInt("selectionStart");
	}
	/**
	 * Sets HTML attribute {@code shape}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code shape} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement shape(String value) {
		return set("shape", value);
	}
	/**
	 * Gets HTML attribute {@code shape}.
	 *
	 * @return value of the {@code shape} attribute or {@code null} if the attribute is missing
	 */
	default String shape() {
		return attributeAsString("shape");
	}
	/**
	 * Sets SVG attribute {@code shape-rendering}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code shape-rendering} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement shapeRendering(String value) {
		return set("shape-rendering", value);
	}
	/**
	 * Gets SVG attribute {@code shape-rendering}.
	 *
	 * @return value of the {@code shape-rendering} attribute or {@code null} if the attribute is missing
	 */
	default String shapeRendering() {
		return attributeAsString("shape-rendering");
	}
	/**
	 * Sets HTML attribute {@code size}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code size} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement size(String value) {
		return set("size", value);
	}
	/**
	 * Gets HTML attribute {@code size}.
	 *
	 * @return value of the {@code size} attribute or {@code null} if the attribute is missing
	 */
	default String size() {
		return attributeAsString("size");
	}
	/**
	 * Sets HTML attribute {@code size} to an integer value.
	 *
	 * @param value
	 *            new value of the {@code size} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement size(int value) {
		return set("size", value);
	}
	/**
	 * Gets HTML attribute {@code size} as an integer value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not an integer
	 */
	default OptionalInt sizeAsInt() {
		return attributeAsInt("size");
	}
	/**
	 * Sets HTML attribute {@code sizes}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code sizes} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement sizes(String value) {
		return set("sizes", value);
	}
	/**
	 * Gets HTML attribute {@code sizes}.
	 *
	 * @return value of the {@code sizes} attribute or {@code null} if the attribute is missing
	 */
	default String sizes() {
		return attributeAsString("sizes");
	}
	/**
	 * Sets HTML attribute {@code slot}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code slot} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement slot(String value) {
		return set("slot", value);
	}
	/**
	 * Gets HTML attribute {@code slot}.
	 *
	 * @return value of the {@code slot} attribute or {@code null} if the attribute is missing
	 */
	default String slot() {
		return attributeAsString("slot");
	}
	/**
	 * Sets HTML attribute {@code span}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code span} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement span(String value) {
		return set("span", value);
	}
	/**
	 * Gets HTML attribute {@code span}.
	 *
	 * @return value of the {@code span} attribute or {@code null} if the attribute is missing
	 */
	default String span() {
		return attributeAsString("span");
	}
	/**
	 * Sets HTML attribute {@code span} to an integer value.
	 *
	 * @param value
	 *            new value of the {@code span} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement span(int value) {
		return set("span", value);
	}
	/**
	 * Gets HTML attribute {@code span} as an integer value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not an integer
	 */
	default OptionalInt spanAsInt() {
		return attributeAsInt("span");
	}
	/**
	 * Sets SVG attribute {@code specularConstant}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code specularConstant} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement specularConstant(String value) {
		return set("specularConstant", value);
	}
	/**
	 * Gets SVG attribute {@code specularConstant}.
	 *
	 * @return value of the {@code specularConstant} attribute or {@code null} if the attribute is missing
	 */
	default String specularConstant() {
		return attributeAsString("specularConstant");
	}
	/**
	 * Sets SVG attribute {@code specularConstant} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code specularConstant} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement specularConstant(double value) {
		return set("specularConstant", value);
	}
	/**
	 * Gets SVG attribute {@code specularConstant} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble specularConstantAsDouble() {
		return attributeAsDouble("specularConstant");
	}
	/**
	 * Sets SVG attribute {@code specularExponent}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code specularExponent} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement specularExponent(String value) {
		return set("specularExponent", value);
	}
	/**
	 * Gets SVG attribute {@code specularExponent}.
	 *
	 * @return value of the {@code specularExponent} attribute or {@code null} if the attribute is missing
	 */
	default String specularExponent() {
		return attributeAsString("specularExponent");
	}
	/**
	 * Sets SVG attribute {@code specularExponent} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code specularExponent} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement specularExponent(double value) {
		return set("specularExponent", value);
	}
	/**
	 * Gets SVG attribute {@code specularExponent} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble specularExponentAsDouble() {
		return attributeAsDouble("specularExponent");
	}
	/**
	 * Sets HTML attribute {@code spellcheck}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code spellcheck} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement spellcheck(String value) {
		return set("spellcheck", value);
	}
	/**
	 * Gets HTML attribute {@code spellcheck}.
	 *
	 * @return value of the {@code spellcheck} attribute or {@code null} if the attribute is missing
	 */
	default String spellcheck() {
		return attributeAsString("spellcheck");
	}
	/**
	 * Sets HTML attribute {@code src}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code src} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement src(String value) {
		return set("src", value);
	}
	/**
	 * Gets HTML attribute {@code src}.
	 *
	 * @return value of the {@code src} attribute or {@code null} if the attribute is missing
	 */
	default String src() {
		return attributeAsString("src");
	}
	/**
	 * Sets HTML attribute {@code srcdoc}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code srcdoc} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement srcdoc(String value) {
		return set("srcdoc", value);
	}
	/**
	 * Gets HTML attribute {@code srcdoc}.
	 *
	 * @return value of the {@code srcdoc} attribute or {@code null} if the attribute is missing
	 */
	default String srcdoc() {
		return attributeAsString("srcdoc");
	}
	/**
	 * Sets HTML attribute {@code srclang}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code srclang} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement srclang(String value) {
		return set("srclang", value);
	}
	/**
	 * Gets HTML attribute {@code srclang}.
	 *
	 * @return value of the {@code srclang} attribute or {@code null} if the attribute is missing
	 */
	default String srclang() {
		return attributeAsString("srclang");
	}
	/**
	 * Sets HTML attribute {@code srcset}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code srcset} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement srcset(String value) {
		return set("srcset", value);
	}
	/**
	 * Gets HTML attribute {@code srcset}.
	 *
	 * @return value of the {@code srcset} attribute or {@code null} if the attribute is missing
	 */
	default String srcset() {
		return attributeAsString("srcset");
	}
	/**
	 * Sets HTML attribute {@code start}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code start} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement start(String value) {
		return set("start", value);
	}
	/**
	 * Gets HTML attribute {@code start}.
	 *
	 * @return value of the {@code start} attribute or {@code null} if the attribute is missing
	 */
	default String start() {
		return attributeAsString("start");
	}
	/**
	 * Sets HTML attribute {@code start} to an integer value.
	 *
	 * @param value
	 *            new value of the {@code start} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement start(int value) {
		return set("start", value);
	}
	/**
	 * Gets HTML attribute {@code start} as an integer value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not an integer
	 */
	default OptionalInt startAsInt() {
		return attributeAsInt("start");
	}
	/**
	 * Sets SVG attribute {@code stdDeviation}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code stdDeviation} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement stdDeviation(String value) {
		return set("stdDeviation", value);
	}
	/**
	 * Gets SVG attribute {@code stdDeviation}.
	 *
	 * @return value of the {@code stdDeviation} attribute or {@code null} if the attribute is missing
	 */
	default String stdDeviation() {
		return attributeAsString("stdDeviation");
	}
	/**
	 * Sets SVG attribute {@code stdDeviation} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code stdDeviation} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement stdDeviation(double value) {
		return set("stdDeviation", value);
	}
	/**
	 * Gets SVG attribute {@code stdDeviation} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble stdDeviationAsDouble() {
		return attributeAsDouble("stdDeviation");
	}
	/**
	 * Sets HTML attribute {@code step}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code step} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement step(String value) {
		return set("step", value);
	}
	/**
	 * Gets HTML attribute {@code step}.
	 *
	 * @return value of the {@code step} attribute or {@code null} if the attribute is missing
	 */
	default String step() {
		return attributeAsString("step");
	}
	/**
	 * Sets HTML attribute {@code step} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code step} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement step(double value) {
		return set("step", value);
	}
	/**
	 * Gets HTML attribute {@code step} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble stepAsDouble() {
		return attributeAsDouble("step");
	}
	/**
	 * Sets SVG attribute {@code stitchTiles}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code stitchTiles} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement stitchTiles(String value) {
		return set("stitchTiles", value);
	}
	/**
	 * Gets SVG attribute {@code stitchTiles}.
	 *
	 * @return value of the {@code stitchTiles} attribute or {@code null} if the attribute is missing
	 */
	default String stitchTiles() {
		return attributeAsString("stitchTiles");
	}
	/**
	 * Sets SVG attribute {@code stop-color}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code stop-color} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement stopColor(String value) {
		return set("stop-color", value);
	}
	/**
	 * Gets SVG attribute {@code stop-color}.
	 *
	 * @return value of the {@code stop-color} attribute or {@code null} if the attribute is missing
	 */
	default String stopColor() {
		return attributeAsString("stop-color");
	}
	/**
	 * Sets SVG attribute {@code stop-opacity}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code stop-opacity} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement stopOpacity(String value) {
		return set("stop-opacity", value);
	}
	/**
	 * Gets SVG attribute {@code stop-opacity}.
	 *
	 * @return value of the {@code stop-opacity} attribute or {@code null} if the attribute is missing
	 */
	default String stopOpacity() {
		return attributeAsString("stop-opacity");
	}
	/**
	 * Sets SVG attribute {@code stop-opacity} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code stop-opacity} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement stopOpacity(double value) {
		return set("stop-opacity", value);
	}
	/**
	 * Gets SVG attribute {@code stop-opacity} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble stopOpacityAsDouble() {
		return attributeAsDouble("stop-opacity");
	}
	/**
	 * Sets SVG attribute {@code strikethrough-position}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code strikethrough-position} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement strikethroughPosition(String value) {
		return set("strikethrough-position", value);
	}
	/**
	 * Gets SVG attribute {@code strikethrough-position}.
	 *
	 * @return value of the {@code strikethrough-position} attribute or {@code null} if the attribute is missing
	 */
	default String strikethroughPosition() {
		return attributeAsString("strikethrough-position");
	}
	/**
	 * Sets SVG attribute {@code strikethrough-position} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code strikethrough-position} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement strikethroughPosition(double value) {
		return set("strikethrough-position", value);
	}
	/**
	 * Gets SVG attribute {@code strikethrough-position} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble strikethroughPositionAsDouble() {
		return attributeAsDouble("strikethrough-position");
	}
	/**
	 * Sets SVG attribute {@code strikethrough-thickness}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code strikethrough-thickness} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement strikethroughThickness(String value) {
		return set("strikethrough-thickness", value);
	}
	/**
	 * Gets SVG attribute {@code strikethrough-thickness}.
	 *
	 * @return value of the {@code strikethrough-thickness} attribute or {@code null} if the attribute is missing
	 */
	default String strikethroughThickness() {
		return attributeAsString("strikethrough-thickness");
	}
	/**
	 * Sets SVG attribute {@code strikethrough-thickness} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code strikethrough-thickness} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement strikethroughThickness(double value) {
		return set("strikethrough-thickness", value);
	}
	/**
	 * Gets SVG attribute {@code strikethrough-thickness} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble strikethroughThicknessAsDouble() {
		return attributeAsDouble("strikethrough-thickness");
	}
	/**
	 * Sets SVG attribute {@code stroke}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code stroke} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement stroke(String value) {
		return set("stroke", value);
	}
	/**
	 * Gets SVG attribute {@code stroke}.
	 *
	 * @return value of the {@code stroke} attribute or {@code null} if the attribute is missing
	 */
	default String stroke() {
		return attributeAsString("stroke");
	}
	/**
	 * Sets SVG attribute {@code stroke-dasharray}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code stroke-dasharray} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement strokeDasharray(String value) {
		return set("stroke-dasharray", value);
	}
	/**
	 * Gets SVG attribute {@code stroke-dasharray}.
	 *
	 * @return value of the {@code stroke-dasharray} attribute or {@code null} if the attribute is missing
	 */
	default String strokeDasharray() {
		return attributeAsString("stroke-dasharray");
	}
	/**
	 * Sets SVG attribute {@code stroke-dashoffset}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code stroke-dashoffset} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement strokeDashoffset(String value) {
		return set("stroke-dashoffset", value);
	}
	/**
	 * Gets SVG attribute {@code stroke-dashoffset}.
	 *
	 * @return value of the {@code stroke-dashoffset} attribute or {@code null} if the attribute is missing
	 */
	default String strokeDashoffset() {
		return attributeAsString("stroke-dashoffset");
	}
	/**
	 * Sets SVG attribute {@code stroke-dashoffset} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code stroke-dashoffset} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement strokeDashoffset(double value) {
		return set("stroke-dashoffset", value);
	}
	/**
	 * Gets SVG attribute {@code stroke-dashoffset} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble strokeDashoffsetAsDouble() {
		return attributeAsDouble("stroke-dashoffset");
	}
	/**
	 * Sets SVG attribute {@code stroke-linecap}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code stroke-linecap} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement strokeLinecap(String value) {
		return set("stroke-linecap", value);
	}
	/**
	 * Gets SVG attribute {@code stroke-linecap}.
	 *
	 * @return value of the {@code stroke-linecap} attribute or {@code null} if the attribute is missing
	 */
	default String strokeLinecap() {
		return attributeAsString("stroke-linecap");
	}
	/**
	 * Sets SVG attribute {@code stroke-linejoin}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code stroke-linejoin} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement strokeLinejoin(String value) {
		return set("stroke-linejoin", value);
	}
	/**
	 * Gets SVG attribute {@code stroke-linejoin}.
	 *
	 * @return value of the {@code stroke-linejoin} attribute or {@code null} if the attribute is missing
	 */
	default String strokeLinejoin() {
		return attributeAsString("stroke-linejoin");
	}
	/**
	 * Sets SVG attribute {@code stroke-miterlimit}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code stroke-miterlimit} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement strokeMiterlimit(String value) {
		return set("stroke-miterlimit", value);
	}
	/**
	 * Gets SVG attribute {@code stroke-miterlimit}.
	 *
	 * @return value of the {@code stroke-miterlimit} attribute or {@code null} if the attribute is missing
	 */
	default String strokeMiterlimit() {
		return attributeAsString("stroke-miterlimit");
	}
	/**
	 * Sets SVG attribute {@code stroke-miterlimit} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code stroke-miterlimit} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement strokeMiterlimit(double value) {
		return set("stroke-miterlimit", value);
	}
	/**
	 * Gets SVG attribute {@code stroke-miterlimit} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble strokeMiterlimitAsDouble() {
		return attributeAsDouble("stroke-miterlimit");
	}
	/**
	 * Sets SVG attribute {@code stroke-opacity}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code stroke-opacity} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement strokeOpacity(String value) {
		return set("stroke-opacity", value);
	}
	/**
	 * Gets SVG attribute {@code stroke-opacity}.
	 *
	 * @return value of the {@code stroke-opacity} attribute or {@code null} if the attribute is missing
	 */
	default String strokeOpacity() {
		return attributeAsString("stroke-opacity");
	}
	/**
	 * Sets SVG attribute {@code stroke-opacity} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code stroke-opacity} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement strokeOpacity(double value) {
		return set("stroke-opacity", value);
	}
	/**
	 * Gets SVG attribute {@code stroke-opacity} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble strokeOpacityAsDouble() {
		return attributeAsDouble("stroke-opacity");
	}
	/**
	 * Sets SVG attribute {@code stroke-width}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code stroke-width} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement strokeWidth(String value) {
		return set("stroke-width", value);
	}
	/**
	 * Gets SVG attribute {@code stroke-width}.
	 *
	 * @return value of the {@code stroke-width} attribute or {@code null} if the attribute is missing
	 */
	default String strokeWidth() {
		return attributeAsString("stroke-width");
	}
	/**
	 * Sets SVG attribute {@code stroke-width} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code stroke-width} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement strokeWidth(double value) {
		return set("stroke-width", value);
	}
	/**
	 * Gets SVG attribute {@code stroke-width} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble strokeWidthAsDouble() {
		return attributeAsDouble("stroke-width");
	}
	/**
	 * Sets HTML/SVG attribute {@code style}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code style} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement style(String value) {
		return set("style", value);
	}
	/**
	 * Gets HTML/SVG attribute {@code style}.
	 *
	 * @return value of the {@code style} attribute or {@code null} if the attribute is missing
	 */
	default String style() {
		return attributeAsString("style");
	}
	/**
	 * Sets SVG attribute {@code surfaceScale}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code surfaceScale} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement surfaceScale(String value) {
		return set("surfaceScale", value);
	}
	/**
	 * Gets SVG attribute {@code surfaceScale}.
	 *
	 * @return value of the {@code surfaceScale} attribute or {@code null} if the attribute is missing
	 */
	default String surfaceScale() {
		return attributeAsString("surfaceScale");
	}
	/**
	 * Sets SVG attribute {@code surfaceScale} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code surfaceScale} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement surfaceScale(double value) {
		return set("surfaceScale", value);
	}
	/**
	 * Gets SVG attribute {@code surfaceScale} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble surfaceScaleAsDouble() {
		return attributeAsDouble("surfaceScale");
	}
	/**
	 * Sets SVG attribute {@code systemLanguage}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code systemLanguage} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement systemLanguage(String value) {
		return set("systemLanguage", value);
	}
	/**
	 * Gets SVG attribute {@code systemLanguage}.
	 *
	 * @return value of the {@code systemLanguage} attribute or {@code null} if the attribute is missing
	 */
	default String systemLanguage() {
		return attributeAsString("systemLanguage");
	}
	/**
	 * Sets HTML/SVG attribute {@code tabindex}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code tabindex} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement tabindex(String value) {
		return set("tabindex", value);
	}
	/**
	 * Gets HTML/SVG attribute {@code tabindex}.
	 *
	 * @return value of the {@code tabindex} attribute or {@code null} if the attribute is missing
	 */
	default String tabindex() {
		return attributeAsString("tabindex");
	}
	/**
	 * Sets HTML/SVG attribute {@code tabindex} to an integer value.
	 *
	 * @param value
	 *            new value of the {@code tabindex} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement tabindex(int value) {
		return set("tabindex", value);
	}
	/**
	 * Gets HTML/SVG attribute {@code tabindex} as an integer value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not an integer
	 */
	default OptionalInt tabindexAsInt() {
		return attributeAsInt("tabindex");
	}
	/**
	 * Sets HTML attribute {@code target}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code target} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement target(String value) {
		return set("target", value);
	}
	/**
	 * Gets HTML attribute {@code target}.
	 *
	 * @return value of the {@code target} attribute or {@code null} if the attribute is missing
	 */
	default String target() {
		return attributeAsString("target");
	}
	/**
	 * Sets SVG attribute {@code targetX}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code targetX} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement targetX(String value) {
		return set("targetX", value);
	}
	/**
	 * Gets SVG attribute {@code targetX}.
	 *
	 * @return value of the {@code targetX} attribute or {@code null} if the attribute is missing
	 */
	default String targetX() {
		return attributeAsString("targetX");
	}
	/**
	 * Sets SVG attribute {@code targetX} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code targetX} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement targetX(double value) {
		return set("targetX", value);
	}
	/**
	 * Gets SVG attribute {@code targetX} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble targetXAsDouble() {
		return attributeAsDouble("targetX");
	}
	/**
	 * Sets SVG attribute {@code targetY}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code targetY} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement targetY(String value) {
		return set("targetY", value);
	}
	/**
	 * Gets SVG attribute {@code targetY}.
	 *
	 * @return value of the {@code targetY} attribute or {@code null} if the attribute is missing
	 */
	default String targetY() {
		return attributeAsString("targetY");
	}
	/**
	 * Sets SVG attribute {@code targetY} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code targetY} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement targetY(double value) {
		return set("targetY", value);
	}
	/**
	 * Gets SVG attribute {@code targetY} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble targetYAsDouble() {
		return attributeAsDouble("targetY");
	}
	/**
	 * Sets SVG attribute {@code text-anchor}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code text-anchor} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement textAnchor(String value) {
		return set("text-anchor", value);
	}
	/**
	 * Gets SVG attribute {@code text-anchor}.
	 *
	 * @return value of the {@code text-anchor} attribute or {@code null} if the attribute is missing
	 */
	default String textAnchor() {
		return attributeAsString("text-anchor");
	}
	/**
	 * Sets SVG attribute {@code text-decoration}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code text-decoration} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement textDecoration(String value) {
		return set("text-decoration", value);
	}
	/**
	 * Gets SVG attribute {@code text-decoration}.
	 *
	 * @return value of the {@code text-decoration} attribute or {@code null} if the attribute is missing
	 */
	default String textDecoration() {
		return attributeAsString("text-decoration");
	}
	/**
	 * Sets SVG attribute {@code text-rendering}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code text-rendering} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement textRendering(String value) {
		return set("text-rendering", value);
	}
	/**
	 * Gets SVG attribute {@code text-rendering}.
	 *
	 * @return value of the {@code text-rendering} attribute or {@code null} if the attribute is missing
	 */
	default String textRendering() {
		return attributeAsString("text-rendering");
	}
	/**
	 * Sets SVG attribute {@code textLength}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code textLength} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement textLength(String value) {
		return set("textLength", value);
	}
	/**
	 * Gets SVG attribute {@code textLength}.
	 *
	 * @return value of the {@code textLength} attribute or {@code null} if the attribute is missing
	 */
	default String textLength() {
		return attributeAsString("textLength");
	}
	/**
	 * Sets SVG attribute {@code textLength} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code textLength} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement textLength(double value) {
		return set("textLength", value);
	}
	/**
	 * Gets SVG attribute {@code textLength} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble textLengthAsDouble() {
		return attributeAsDouble("textLength");
	}
	/**
	 * Sets HTML attribute {@code title}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code title} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement title(String value) {
		return set("title", value);
	}
	/**
	 * Gets HTML attribute {@code title}.
	 *
	 * @return value of the {@code title} attribute or {@code null} if the attribute is missing
	 */
	default String title() {
		return attributeAsString("title");
	}
	/**
	 * Sets SVG attribute {@code to}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code to} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement to(String value) {
		return set("to", value);
	}
	/**
	 * Gets SVG attribute {@code to}.
	 *
	 * @return value of the {@code to} attribute or {@code null} if the attribute is missing
	 */
	default String to() {
		return attributeAsString("to");
	}
	/**
	 * Sets SVG attribute {@code transform}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code transform} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement transform(String value) {
		return set("transform", value);
	}
	/**
	 * Gets SVG attribute {@code transform}.
	 *
	 * @return value of the {@code transform} attribute or {@code null} if the attribute is missing
	 */
	default String transform() {
		return attributeAsString("transform");
	}
	/**
	 * Sets HTML/SVG attribute {@code type}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code type} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement type(String value) {
		return set("type", value);
	}
	/**
	 * Gets HTML/SVG attribute {@code type}.
	 *
	 * @return value of the {@code type} attribute or {@code null} if the attribute is missing
	 */
	default String type() {
		return attributeAsString("type");
	}
	/**
	 * Adds boolean HTML attribute {@code typemustmatch}.
	 * This method has no effect if the attribute is already present.
	 *
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement typemustmatch() {
		return set("typemustmatch");
	}
	/**
	 * Adds or removes boolean HTML attribute {@code typemustmatch}.
	 *
	 * @param present
	 *            {@code true} to add the attribute, {@code false} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement typemustmatch(boolean present) {
		return set("typemustmatch", present);
	}
	/**
	 * Gets boolean HTML attribute {@code typemustmatch}.
	 *
	 * @return value of the {@code typemustmatch} attribute or {@code null} if the attribute is missing
	 */
	default boolean typemustmatchAsBoolean() {
		return attributeAsBoolean("typemustmatch");
	}
	/**
	 * Sets SVG attribute {@code underline-position}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code underline-position} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement underlinePosition(String value) {
		return set("underline-position", value);
	}
	/**
	 * Gets SVG attribute {@code underline-position}.
	 *
	 * @return value of the {@code underline-position} attribute or {@code null} if the attribute is missing
	 */
	default String underlinePosition() {
		return attributeAsString("underline-position");
	}
	/**
	 * Sets SVG attribute {@code underline-position} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code underline-position} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement underlinePosition(double value) {
		return set("underline-position", value);
	}
	/**
	 * Gets SVG attribute {@code underline-position} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble underlinePositionAsDouble() {
		return attributeAsDouble("underline-position");
	}
	/**
	 * Sets SVG attribute {@code underline-thickness}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code underline-thickness} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement underlineThickness(String value) {
		return set("underline-thickness", value);
	}
	/**
	 * Gets SVG attribute {@code underline-thickness}.
	 *
	 * @return value of the {@code underline-thickness} attribute or {@code null} if the attribute is missing
	 */
	default String underlineThickness() {
		return attributeAsString("underline-thickness");
	}
	/**
	 * Sets SVG attribute {@code underline-thickness} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code underline-thickness} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement underlineThickness(double value) {
		return set("underline-thickness", value);
	}
	/**
	 * Gets SVG attribute {@code underline-thickness} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble underlineThicknessAsDouble() {
		return attributeAsDouble("underline-thickness");
	}
	/**
	 * Sets HTML attribute {@code usemap}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code usemap} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement usemap(String value) {
		return set("usemap", value);
	}
	/**
	 * Gets HTML attribute {@code usemap}.
	 *
	 * @return value of the {@code usemap} attribute or {@code null} if the attribute is missing
	 */
	default String usemap() {
		return attributeAsString("usemap");
	}
	/**
	 * Sets HTML attribute {@code value}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code value} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement value(String value) {
		return set("value", value);
	}
	/**
	 * Gets HTML attribute {@code value}.
	 *
	 * @return value of the {@code value} attribute or {@code null} if the attribute is missing
	 */
	default String value() {
		return attributeAsString("value");
	}
	/**
	 * Sets HTML attribute {@code value} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code value} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement value(double value) {
		return set("value", value);
	}
	/**
	 * Gets HTML attribute {@code value} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble valueAsDouble() {
		return attributeAsDouble("value");
	}
	/**
	 * Sets SVG attribute {@code values}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code values} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement values(String value) {
		return set("values", value);
	}
	/**
	 * Gets SVG attribute {@code values}.
	 *
	 * @return value of the {@code values} attribute or {@code null} if the attribute is missing
	 */
	default String values() {
		return attributeAsString("values");
	}
	/**
	 * Sets SVG attribute {@code vector-effect}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code vector-effect} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement vectorEffect(String value) {
		return set("vector-effect", value);
	}
	/**
	 * Gets SVG attribute {@code vector-effect}.
	 *
	 * @return value of the {@code vector-effect} attribute or {@code null} if the attribute is missing
	 */
	default String vectorEffect() {
		return attributeAsString("vector-effect");
	}
	/**
	 * Sets SVG attribute {@code version}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code version} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement version(String value) {
		return set("version", value);
	}
	/**
	 * Gets SVG attribute {@code version}.
	 *
	 * @return value of the {@code version} attribute or {@code null} if the attribute is missing
	 */
	default String version() {
		return attributeAsString("version");
	}
	/**
	 * Sets SVG attribute {@code viewBox}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code viewBox} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement viewBox(String value) {
		return set("viewBox", value);
	}
	/**
	 * Gets SVG attribute {@code viewBox}.
	 *
	 * @return value of the {@code viewBox} attribute or {@code null} if the attribute is missing
	 */
	default String viewBox() {
		return attributeAsString("viewBox");
	}
	/**
	 * Sets SVG attribute {@code visibility}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code visibility} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement visibility(String value) {
		return set("visibility", value);
	}
	/**
	 * Gets SVG attribute {@code visibility}.
	 *
	 * @return value of the {@code visibility} attribute or {@code null} if the attribute is missing
	 */
	default String visibility() {
		return attributeAsString("visibility");
	}
	/**
	 * Sets HTML attribute {@code volume}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code volume} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement volume(String value) {
		return set("volume", value);
	}
	/**
	 * Gets HTML attribute {@code volume}.
	 *
	 * @return value of the {@code volume} attribute or {@code null} if the attribute is missing
	 */
	default String volume() {
		return attributeAsString("volume");
	}
	/**
	 * Sets HTML attribute {@code volume} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code volume} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement volume(double value) {
		return set("volume", value);
	}
	/**
	 * Gets HTML attribute {@code volume} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble volumeAsDouble() {
		return attributeAsDouble("volume");
	}
	/**
	 * Sets HTML/SVG attribute {@code width}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code width} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement width(String value) {
		return set("width", value);
	}
	/**
	 * Gets HTML/SVG attribute {@code width}.
	 *
	 * @return value of the {@code width} attribute or {@code null} if the attribute is missing
	 */
	default String width() {
		return attributeAsString("width");
	}
	/**
	 * Sets HTML/SVG attribute {@code width} to an integer value.
	 *
	 * @param value
	 *            new value of the {@code width} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement width(int value) {
		return set("width", value);
	}
	/**
	 * Gets HTML/SVG attribute {@code width} as an integer value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not an integer
	 */
	default OptionalInt widthAsInt() {
		return attributeAsInt("width");
	}
	/**
	 * Sets HTML/SVG attribute {@code width} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code width} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement width(double value) {
		return set("width", value);
	}
	/**
	 * Gets HTML/SVG attribute {@code width} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble widthAsDouble() {
		return attributeAsDouble("width");
	}
	/**
	 * Sets SVG attribute {@code word-spacing}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code word-spacing} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement wordSpacing(String value) {
		return set("word-spacing", value);
	}
	/**
	 * Gets SVG attribute {@code word-spacing}.
	 *
	 * @return value of the {@code word-spacing} attribute or {@code null} if the attribute is missing
	 */
	default String wordSpacing() {
		return attributeAsString("word-spacing");
	}
	/**
	 * Sets SVG attribute {@code word-spacing} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code word-spacing} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement wordSpacing(double value) {
		return set("word-spacing", value);
	}
	/**
	 * Gets SVG attribute {@code word-spacing} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble wordSpacingAsDouble() {
		return attributeAsDouble("word-spacing");
	}
	/**
	 * Sets HTML attribute {@code wrap}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code wrap} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement wrap(String value) {
		return set("wrap", value);
	}
	/**
	 * Gets HTML attribute {@code wrap}.
	 *
	 * @return value of the {@code wrap} attribute or {@code null} if the attribute is missing
	 */
	default String wrap() {
		return attributeAsString("wrap");
	}
	/**
	 * Sets SVG attribute {@code writing-mode}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code writing-mode} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement writingMode(String value) {
		return set("writing-mode", value);
	}
	/**
	 * Gets SVG attribute {@code writing-mode}.
	 *
	 * @return value of the {@code writing-mode} attribute or {@code null} if the attribute is missing
	 */
	default String writingMode() {
		return attributeAsString("writing-mode");
	}
	/**
	 * Sets SVG attribute {@code x}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code x} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement x(String value) {
		return set("x", value);
	}
	/**
	 * Gets SVG attribute {@code x}.
	 *
	 * @return value of the {@code x} attribute or {@code null} if the attribute is missing
	 */
	default String x() {
		return attributeAsString("x");
	}
	/**
	 * Sets SVG attribute {@code x} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code x} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement x(double value) {
		return set("x", value);
	}
	/**
	 * Gets SVG attribute {@code x} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble xAsDouble() {
		return attributeAsDouble("x");
	}
	/**
	 * Sets SVG attribute {@code x1}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code x1} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement x1(String value) {
		return set("x1", value);
	}
	/**
	 * Gets SVG attribute {@code x1}.
	 *
	 * @return value of the {@code x1} attribute or {@code null} if the attribute is missing
	 */
	default String x1() {
		return attributeAsString("x1");
	}
	/**
	 * Sets SVG attribute {@code x1} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code x1} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement x1(double value) {
		return set("x1", value);
	}
	/**
	 * Gets SVG attribute {@code x1} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble x1AsDouble() {
		return attributeAsDouble("x1");
	}
	/**
	 * Sets SVG attribute {@code x2}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code x2} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement x2(String value) {
		return set("x2", value);
	}
	/**
	 * Gets SVG attribute {@code x2}.
	 *
	 * @return value of the {@code x2} attribute or {@code null} if the attribute is missing
	 */
	default String x2() {
		return attributeAsString("x2");
	}
	/**
	 * Sets SVG attribute {@code x2} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code x2} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement x2(double value) {
		return set("x2", value);
	}
	/**
	 * Gets SVG attribute {@code x2} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble x2AsDouble() {
		return attributeAsDouble("x2");
	}
	/**
	 * Sets SVG attribute {@code xChannelSelector}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code xChannelSelector} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement xChannelSelector(String value) {
		return set("xChannelSelector", value);
	}
	/**
	 * Gets SVG attribute {@code xChannelSelector}.
	 *
	 * @return value of the {@code xChannelSelector} attribute or {@code null} if the attribute is missing
	 */
	default String xChannelSelector() {
		return attributeAsString("xChannelSelector");
	}
	/**
	 * Sets SVG attribute {@code y}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code y} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement y(String value) {
		return set("y", value);
	}
	/**
	 * Gets SVG attribute {@code y}.
	 *
	 * @return value of the {@code y} attribute or {@code null} if the attribute is missing
	 */
	default String y() {
		return attributeAsString("y");
	}
	/**
	 * Sets SVG attribute {@code y} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code y} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement y(double value) {
		return set("y", value);
	}
	/**
	 * Gets SVG attribute {@code y} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble yAsDouble() {
		return attributeAsDouble("y");
	}
	/**
	 * Sets SVG attribute {@code y1}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code y1} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement y1(String value) {
		return set("y1", value);
	}
	/**
	 * Gets SVG attribute {@code y1}.
	 *
	 * @return value of the {@code y1} attribute or {@code null} if the attribute is missing
	 */
	default String y1() {
		return attributeAsString("y1");
	}
	/**
	 * Sets SVG attribute {@code y1} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code y1} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement y1(double value) {
		return set("y1", value);
	}
	/**
	 * Gets SVG attribute {@code y1} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble y1AsDouble() {
		return attributeAsDouble("y1");
	}
	/**
	 * Sets SVG attribute {@code y2}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code y2} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement y2(String value) {
		return set("y2", value);
	}
	/**
	 * Gets SVG attribute {@code y2}.
	 *
	 * @return value of the {@code y2} attribute or {@code null} if the attribute is missing
	 */
	default String y2() {
		return attributeAsString("y2");
	}
	/**
	 * Sets SVG attribute {@code y2} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code y2} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement y2(double value) {
		return set("y2", value);
	}
	/**
	 * Gets SVG attribute {@code y2} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble y2AsDouble() {
		return attributeAsDouble("y2");
	}
	/**
	 * Sets SVG attribute {@code yChannelSelector}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code yChannelSelector} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement yChannelSelector(String value) {
		return set("yChannelSelector", value);
	}
	/**
	 * Gets SVG attribute {@code yChannelSelector}.
	 *
	 * @return value of the {@code yChannelSelector} attribute or {@code null} if the attribute is missing
	 */
	default String yChannelSelector() {
		return attributeAsString("yChannelSelector");
	}
	/**
	 * Sets SVG attribute {@code z}.
	 * If {@code value} is {@code null}, the attribute is removed.
	 *
	 * @param value
	 *            new value of the {@code z} attribute or {@code null} to remove the attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement z(String value) {
		return set("z", value);
	}
	/**
	 * Gets SVG attribute {@code z}.
	 *
	 * @return value of the {@code z} attribute or {@code null} if the attribute is missing
	 */
	default String z() {
		return attributeAsString("z");
	}
	/**
	 * Sets SVG attribute {@code z} to a floating-point value.
	 *
	 * @param value
	 *            new value of the {@code z} attribute
	 * @return {@code this}
	 * @throws IllegalStateException
	 *             if the element is frozen
	 */
	default DomElement z(double value) {
		return set("z", value);
	}
	/**
	 * Gets SVG attribute {@code z} as a floating-point value.
	 *
	 * @return {@code this}
	 * @throws NumberFormatException
	 *             if the attribute is not a floating-point number
	 */
	default OptionalDouble zAsDouble() {
		return attributeAsDouble("z");
	}
}
