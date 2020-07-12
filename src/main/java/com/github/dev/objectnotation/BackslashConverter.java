package com.github.dev.objectnotation;

/**
 * Escape character.
 */
final class BackslashConverter implements IntToFunction {

	private final ValueParser parser;

	BackslashConverter(ValueParser parser) {
		this.parser = parser;
	}

	@Override
	public IntToFunction apply(int i) {
		if (i == -1) {
			parser.valueConsumer.accept(-1);
			return parser.valueReader;
		}
		parser.valueConsumer.accept(i);
		return parser.valueReader;
	}

}
