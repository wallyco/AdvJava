package lab_start;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestRedact {
	
	String s = "this is the string \n";

	
	@Test
	public void testRedactor() {
		Redact e = new Redact("is");
		assertEquals(e.getRedactedWord(), "**");
	}
	
	@Test
	public void testTraverse() {
		Redact e = new Redact("is");
		Redact.setFr(false);
		assertEquals("this ** the string", e.traverseRedact(s));
	}	
}
