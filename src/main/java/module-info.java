// Part of PushMode: https://pushmode.machinezoo.com
import com.machinezoo.stagean.*;

/**
 * PushMode is a server-side Java library that streams web app's HTML output down to the browser while user's actions are streamed back to the server.
 * See the <a href="https://pushmode.machinezoo.com/">website</a> for more information.
 * <p>
 * The main package {@link com.machinezoo.pushmode} contains core classes, especially {@link com.machinezoo.pushmode.PushPage}.
 * Package {@link com.machinezoo.pushmode.dom} contains classes that represent or construct DOM tree nodes.
 * 
 * @see <a href="https://pushmode.machinezoo.com/">PushMode website</a>
 */
@NoTests
module com.machinezoo.pushmode {
	exports com.machinezoo.pushmode;
	exports com.machinezoo.pushmode.dom;
	exports com.machinezoo.pushmode.events;
	exports com.machinezoo.pushmode.bindings;
	/*
	 * XML tree conversions should be in a separate library. Perhaps have several libraries for different XML parsers.
	 * It would be of course preferable to have one standard XML tree supported, but java.xml is rather unpopular.
	 * PushMode should have only non-transitive dependency for conversions to/from serialized XML.
	 */
	requires transitive java.xml;
	requires com.machinezoo.stagean;
	requires com.machinezoo.noexception.slf4j;
	requires com.machinezoo.hookless;
	requires com.machinezoo.hookless.time;
	/*
	 * There should be a separate pushmode-servlets library. Core library should not depend on Jakarta servlet API.
	 * Transitive, because we are providing a set of default servlets.
	 */
	requires transitive com.machinezoo.hookless.servlets;
	/*
	 * SLF4J is pulled in transitively via noexception and then via hookless,
	 * but the transitive dependency will be removed in future versions of noexception.
	 */
	requires org.slf4j;
	/*
	 * Temporary transitive dependency needed by DomListener and subclasses, which hardcode JSON serialization via Jackson.
	 * A better solution needs to be found in the future. We shouldn't hardcode JSON protocol and certainly not Jackson library.
	 */
	requires transitive com.fasterxml.jackson.databind;
	requires org.apache.commons.io;
	requires org.apache.httpcomponents.httpclient;
	requires org.apache.httpcomponents.httpcore;
	requires micrometer.core;
}
