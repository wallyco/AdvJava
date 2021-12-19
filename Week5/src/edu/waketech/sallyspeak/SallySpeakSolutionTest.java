package edu.waketech.sallyspeak;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SallySpeakSolutionTest {
	
	public static final String[] GOOD_STRINGS = {
			"earperk bark whimper earperk bark whimper earperk",
			"earperk tailwag bark growl earperk bark whimper earperk",
			"earperk tailwag tailwag bark growl earperk tailwag tailwag",
			"earperk bark whimper earperk bark whimper earperk tailwag tailwag bark growl earperk",
	};
	
	public static final String[] BAD_STRINGS = {
			"",
			"earperk",
			"bark",
			"whimper",
			"earperk bark whimper",
			"earperk whimper",
			"earperk bark whimper growl",
			"earperk bark bark whimper growl",
	};

	@Test
	void testIsLegalGood() {
		for (String s: GOOD_STRINGS) {
			SallySpeakSolution ss = new SallySpeakSolution(s);
			assertTrue(ss.isLegal(), " failed on " + s);
		}
	}

	@Test
	void testIsLegalBad() {
		for (String s: BAD_STRINGS) {
			SallySpeakSolution ss = new SallySpeakSolution(s);
			assertFalse(ss.isLegal(), " failed on " + s);
		}
	}

	
}
