package com.github.dev.objectnotation;

import static com.github.dev.objectnotation.Constants.CR;
import static com.github.dev.objectnotation.Constants.LF;

import java.util.ArrayList;
import java.util.List;

/**
 * Parse CR and LF.
 */
final class ParseNewLine {

	private boolean hasCR = false;

	private boolean hasLF = false;

	private List<Character> t = new ArrayList<>();

	public List<Character> accept(char i) {
		t.clear();
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
		t.add(CR);
		t.add(LF);
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
			t.add(CR);
		} else if (i == LF) {
			hasLF = true;
		} else {
			hasCR = false;
			t.add(CR);
		}
	}

	private void hasLF(int i) {
		if (i == CR) {
			hasCR = true;
			hasLF = false;
			t.add(LF);
		} else if (i == LF) {
			t.add(LF);
		} else {
			hasLF = false;
			t.add(LF);
		}
	}

}
