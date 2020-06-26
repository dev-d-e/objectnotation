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
		return parser.valueReader;
	}

}
