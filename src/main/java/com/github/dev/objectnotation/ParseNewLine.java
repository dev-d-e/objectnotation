package com.github.dev.objectnotation;

import static com.github.dev.objectnotation.Constants.CR;
import static com.github.dev.objectnotation.Constants.LF;

/**
 * Parse CR and LF.
 */
final class ParseNewLine {

	private boolean hasCR = false;

	private boolean hasLF = false;

	private char[] t = { 0, 0 };

	public char[] accept(char i) {
		t[0] = 0;
		t[1] = 0;
		if (hasCR && hasLF) {
			hasCRLF(i);
		} else if (hasCR) {
			hasCR(i);
		} else if (hasLF) {
			hasLF(i);
		} else if (i == CR) {
			hasCR = true;
		} else if (i == LF) {
			hasLF = true;
		}
		return t;
	}

	private void hasCRLF(int i) {
		t[0] = CR;
		t[1] = LF;
		if (i == CR) {
			hasLF = false;
		} else if (i == LF) {
			hasCR = false;
		} else {
			hasCR = false;
			hasLF = false;
		}
	}

	private void hasCR(int i) {
		if (i == CR) {
			t[0] = CR;
		} else if (i == LF) {
			hasLF = true;
		} else {
			hasCR = false;
			t[0] = CR;
		}
	}

	private void hasLF(int i) {
		if (i == CR) {
			hasCR = true;
			hasLF = false;
			t[0] = LF;
		} else if (i == LF) {
			t[0] = LF;
		} else {
			hasLF = false;
			t[0] = LF;
		}
	}

}
