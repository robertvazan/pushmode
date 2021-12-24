// Part of PushMode: https://pushmode.machinezoo.com
/**
 * PushMode is a server-side Java library that streams web app's HTML output down to the browser while user's actions are streamed back to the server.
 * See the <a href="https://pushmode.machinezoo.com/">website</a> for more information.
 * <p>
 * The main package {@link com.machinezoo.pushmode} contains core classes, especially {@link com.machinezoo.pushmode.PushPage}.
 * Package {@link com.machinezoo.pushmode.dom} contains classes that represent or construct DOM tree nodes.
 * 
 * @see <a href="https://pushmode.machinezoo.com/">PushMode website</a>
 */
module com.machinezoo.pushmode {
	exports com.machinezoo.pushmode;
	exports com.machinezoo.pushmode.dom;
	exports com.machinezoo.pushmode.events;
	exports com.machinezoo.pushmode.bindings;
	requires transitive java.xml;
	requires jakarta.servlet;
	requires com.machinezoo.stagean;
	requires com.machinezoo.noexception;
	requires com.machinezoo.hookless;
	requires transitive com.machinezoo.hookless.servlets;
	requires com.fasterxml.jackson.core;
	/*
	 * Temporary transitive dependency needed by DomListener and subclasses, which hardcode JSON serialization via Jackson.
	 * A better solution needs to be found in the future. We shouldn't hardcode JSON protocol and certainly not Jackson library.
	 */
	requires transitive com.fasterxml.jackson.databind;
	requires org.apache.commons.io;
	requires org.apache.httpcomponents.httpclient;
	requires org.apache.httpcomponents.httpcore;
	requires micrometer.core;
	requires org.slf4j;
}
