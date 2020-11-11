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
		parser.valueConsumer.accept(i);
		return this;
	}

}
