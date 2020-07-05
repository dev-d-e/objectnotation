package com.github.dev.objectnotation;

/**
 * Read object or map.
 */
public class ObjectReader implements IntToFunction {

	private final ValueParser parser;

	ObjectReader(ValueParser parser) {
		this.parser = parser;
	}

	@Override
	public IntToFunction apply(int i) {
		return parser.valueReader;
	}

}
