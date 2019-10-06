// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.internal.diffing;

import com.machinezoo.pushmode.*;

abstract class IdPatch {
	abstract void updateIdMap(ElementIdIndex index);
}
