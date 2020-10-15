package com.github.dev.objectnotation;

import com.github.dev.objectnotation.value.EntityFactory;

/**
 * Parse value.
 */
final class ParseValue implements IntToFunction {

	private final ValueParser parser;

	ParseValue(ValueParser parser) {
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
			return parser.backslash;
		} else if (c == '[') {
			parser.valueConsumer.setEntity(EntityFactory.createArrayEntity());
			return parser.array;
		} else if (c == '&') {
			parser.valueConsumer.setEntity(EntityFactory.createQuoteEntity());
			return parser.quote;
		}
		parser.valueConsumer.accept(i);
		return this;
	}

}
