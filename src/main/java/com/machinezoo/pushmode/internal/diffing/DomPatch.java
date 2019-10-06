// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.internal.diffing;

import com.fasterxml.jackson.databind.*;

abstract class DomPatch {
	abstract char code();
	abstract JsonNode toJson();
}
