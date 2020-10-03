package com.github.dev.objectnotation;

/**
 * Parse text.
 */
final class ParseText implements IntToFunction {

	private final Parser parser;

	private boolean hasCR = false;

	private boolean hasLF = false;

	ParseText(Parser parser) {
		this.parser = parser;
	}

	@Override
	public IntToFunction apply(int i) {
		if (i == -1) {
			parser.valueConsumer.accept(-1);
			return parser.offset;
		}
		char c = (char) i;
		if (hasCR && hasLF) {
			if (c == '\r') {
				parser.valueConsumer.accept(13);
				parser.valueConsumer.accept(10);
				parser.offset.setBrotherAvailable();
				hasLF = false;
				parser.addRow();
				return this;
			} else if (c == '\n') {
				parser.valueConsumer.accept(13);
				parser.valueConsumer.accept(10);
				parser.offset.setBrotherAvailable();
				hasCR = false;
				parser.addRow();
				return this;
			}
			hasCR = false;
			hasLF = false;
			parser.n = 0;
			return parser.next.apply(i);
		} else if (hasCR) {
			if (c == '\n') {
				hasLF = true;
				return this;
			} else if (c == '\r') {
				parser.valueConsumer.accept(i);
				parser.offset.setBrotherAvailable();
				parser.addRow();
				return this;
			}
			hasCR = false;
			parser.n = 0;
			return parser.next.apply(i);
		} else if (hasLF) {
			if (c == '\n') {
				parser.valueConsumer.accept(i);
				parser.offset.setBrotherAvailable();
				return this;
			} else if (c == '\r') {
				parser.valueConsumer.accept(10);
				parser.offset.setBrotherAvailable();
				hasCR = true;
				hasLF = false;
				return this;
			}
			hasLF = false;
			parser.n = 0;
			return parser.next.apply(i);
		} else {
			if (c == '\r') {
				hasCR = true;
				return this;
			} else if (c == '\n') {
				hasLF = true;
				return this;
			}
			parser.valueConsumer.accept(i);
			parser.offset.setBrotherAvailable();
			return this;
		}
	}

}
