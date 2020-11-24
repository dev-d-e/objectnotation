package com.github.dev.objectnotation;

/**
 * Parse value.
 */
final class ParseValue implements IntToFunction {

	private final ValueParser parser;

	ParseValue(ValueParser parser) {
		this.parser = parser;
	}

	@Override
	public IntToFunction apply(int i) {
		if (i == -1) {
			parser.valueConsumer.accept(-1);
			return this;
		}
		char c = (char) i;
		if (c == '\\') {
			return parser.backslash;
		} else if (c == '[') {
			return parser.array.apply(c);
		}
		parser.valueConsumer.accept(i);
		return this;
	}

}
