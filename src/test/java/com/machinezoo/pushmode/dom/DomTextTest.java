// Part of PushMode: https://pushmode.machinezoo.com
package com.machinezoo.pushmode.dom;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.stream.*;
import org.junit.jupiter.api.*;

public class DomTextTest {
	@Test
	public void toleratesNull() {
		assertEquals("", new DomText(null).text());
	}
	@Test
	public void equatable() {
		assertEquals(new DomText("same"), new DomText("same"));
		assertEquals(new DomText("same").hashCode(), new DomText("same").hashCode());
		assertEquals(new DomText(new String("same")), new DomText(new String("same")));
		assertEquals(new DomText(new String("same")).hashCode(), new DomText(new String("same")).hashCode());
		assertNotEquals(new DomText("one"), new DomText("two"));
		assertNotEquals(new DomText("one").hashCode(), new DomText("two").hashCode());
		assertThat(IntStream.range(0, 10).map(n -> new DomText(Integer.toString(n)).hashCode()).distinct().toArray().length, greaterThan(1));
	}
}
