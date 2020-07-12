package com.github.dev.objectnotation;

/**
 * Read array value.
 */
public class ArrayReader implements IntToFunction {

	private final ValueParser parser;

	ArrayReader(ValueParser parser) {
		this.parser = parser;
	}

	@Override
	public IntToFunction apply(int i) {
		char c = (char) i;
		if (c == '\r' || c == '\n') {
			throw new IllegalCharException("illegal char in array.");
		} else if (c == ']') {
			parser.valueConsumer.accept(-1);
			parser.valueConsumer.opt(0);
			return parser.valueReader;
		}
		parser.valueConsumer.accept(i);
		return this;
	}

}
