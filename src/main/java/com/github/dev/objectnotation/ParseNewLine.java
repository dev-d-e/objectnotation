package com.github.dev.objectnotation;

import static com.github.dev.objectnotation.Constants.CR;
import static com.github.dev.objectnotation.Constants.LF;

import java.util.Objects;
import java.util.function.IntConsumer;

/**
 * Parse CR and LF.
 */
final class ParseNewLine implements IntConsumer {

	private boolean hasCR = false;

	private boolean hasLF = false;

	private int[] t = { -1, -1 };

	private final IntConsumer a;

	private final Runnable b;

	private final IntConsumer c;

	public ParseNewLine(IntConsumer a, Runnable b, IntConsumer c) {
		Objects.requireNonNull(b);
		Objects.requireNonNull(c);
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public ParseNewLine(Runnable b, IntConsumer c) {
		this(null, b, c);
	}

	@Override
	public void accept(int i) {
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
		} else {
			c.accept(i);
		}
	}

	private void hasCRLF(int i) {
		if (i == CR) {
			hasLF = false;
			if (a != null) {
				a.accept(CR);
				a.accept(LF);
			}
			b.run();
		} else if (i == LF) {
			hasCR = false;
			if (a != null) {
				a.accept(CR);
				a.accept(LF);
			}
			b.run();
		} else {
			hasCR = false;
			hasLF = false;
			t[0] = CR;
			t[1] = LF;
			b.run();
			c.accept(i);
			t[0] = -1;
			t[1] = -1;
		}
	}

	private void hasCR(int i) {
		if (i == CR) {
			if (a != null) {
				a.accept(CR);
			}
			b.run();
		} else if (i == LF) {
			hasLF = true;
		} else {
			hasCR = false;
			t[0] = CR;
			b.run();
			c.accept(i);
			t[0] = -1;
		}
	}

	private void hasLF(int i) {
		if (i == CR) {
			hasCR = true;
			hasLF = false;
			if (a != null) {
				a.accept(LF);
			}
			b.run();
		} else if (i == LF) {
			if (a != null) {
				a.accept(LF);
			}
			b.run();
		} else {
			hasLF = false;
			t[0] = LF;
			b.run();
			c.accept(i);
			t[0] = -1;
		}
	}

	public void output() {
		if (a != null) {
			if (t[0] > -1) {
				a.accept(t[0]);
			}
			if (t[1] > -1) {
				a.accept(t[1]);
			}
		}
	}

}
