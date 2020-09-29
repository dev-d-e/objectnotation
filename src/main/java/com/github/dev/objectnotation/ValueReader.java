package com.github.dev.objectnotation;

/**
 * Read Value.
 */
final class ValueReader implements IntToFunction {

	private final ValueParser parser;

	ValueReader(ValueParser parser) {
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
			return parser.backslashConverter;
		} else if (c == '&') {
			return parser.quoteValue;
		} else if (c == '[') {
			parser.valueConsumer.opt(1);
			return parser.arrayReader;
		}
		parser.valueConsumer.accept(i);
		return this;
	}

}
