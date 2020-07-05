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
		return parser.valueReader;
	}

}
