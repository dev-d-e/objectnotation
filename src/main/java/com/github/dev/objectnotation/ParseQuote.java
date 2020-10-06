package com.github.dev.objectnotation;

/**
 * Quote value.
 */
final class ParseQuote implements IntToFunction {

	private final ValueParser parser;

	ParseQuote(ValueParser parser) {
		this.parser = parser;
	}

	@Override
	public IntToFunction apply(int i) {
		char c = (char) i;
		if (c == '#' || c == '~' || c == '&' || c == '\n' || c == '\r' || c == '[' || c == ']') {
			throw new IllegalCharException("illegal char in quote.");
		} else if (c == ' ') {
			parser.valueConsumer.accept(-1);
			parser.valueConsumer.opt(0);
			return parser.value;
		}
		parser.valueConsumer.accept(i);
		return this;
	}

}
