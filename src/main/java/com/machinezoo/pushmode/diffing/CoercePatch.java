// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.diffing;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import com.machinezoo.pushmode.dom.*;

class CoercePatch {
	private final String elementId;
	private final DomBinding binding;
	CoercePatch(String elementId, DomBinding binding) {
		this.elementId = elementId;
		this.binding = binding;
	}
	JsonNode toJson() {
		ObjectNode json = DocumentPatch.mapper.createObjectNode();
		json.put("e", elementId);
		json.put("a", binding.attribute());
		json.put("v", binding.value());
		return json;
	}
}
