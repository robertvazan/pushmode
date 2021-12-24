// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode;

import java.nio.charset.*;
import com.fasterxml.jackson.databind.node.*;
import com.machinezoo.pushmode.diffing.*;
import com.machinezoo.pushmode.dom.*;
import com.machinezoo.stagean.*;

/**
 * Represents one version of the page.
 */
@StubDocs
@DraftApi
public class PageFrame {
	private final PushPage page;
	public PushPage page() {
		return page;
	}
	public PageFrame(PushPage page) {
		this.page = page;
	}
	private long outputSeq = -1;
	public long outputSeq() {
		return outputSeq;
	}
	public PageFrame outputSeq(long outputSeq) {
		this.outputSeq = outputSeq;
		return this;
	}
	private long inputSeq = -1;
	public long inputSeq() {
		return inputSeq;
	}
	public PageFrame inputSeq(long inputSeq) {
		this.inputSeq = inputSeq;
		return this;
	}
	private DomElement document;
	public DomElement document() {
		return document;
	}
	public PageFrame document(DomElement document) {
		this.document = document;
		return this;
	}
	private DocumentPatch patch;
	DocumentPatch patch() {
		return patch;
	}
	PageFrame patch(DocumentPatch patch) {
		this.patch = patch;
		return this;
	}
	public byte[] serialize() {
		return DomFormatter.html()
			.pageId(page.pageId())
			.format(document)
			.toString()
			.getBytes(StandardCharsets.UTF_8);
	}
	ObjectNode jsonPatch() {
		ObjectNode json = patch.toJson();
		json.put("i", inputSeq);
		json.put("o", outputSeq);
		return json;
	}
}
