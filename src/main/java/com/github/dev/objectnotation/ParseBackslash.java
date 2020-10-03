package com.github.dev.objectnotation;

/**
 * Escape character.
 */
final class ParseBackslash implements IntToFunction {

	private final ValueParser parser;

	ParseBackslash(ValueParser parser) {
		this.parser = parser;
	}

	@Override
	public IntToFunction apply(int i) {
		if (i == -1) {
			parser.valueConsumer.accept(-1);
			return parser.value;
		}
		parser.valueConsumer.accept(i);
		return parser.value;
	}

}
