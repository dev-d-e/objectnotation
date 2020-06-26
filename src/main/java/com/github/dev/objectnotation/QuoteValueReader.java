package com.github.dev.objectnotation;

/**
 * Quote value.
 */
final class QuoteValueReader implements IntToFunction {

	private final ValueParser parser;

	QuoteValueReader(ValueParser parser) {
		this.parser = parser;
	}

	@Override
	public IntToFunction apply(int i) {
		return parser.valueReader;
	}

}
