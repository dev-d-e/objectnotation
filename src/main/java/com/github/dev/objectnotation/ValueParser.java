package com.github.dev.objectnotation;

import java.util.Objects;

/**
 * ValueParser.
 */
final class ValueParser {

	final TypeConsumer valueConsumer;

	final ParseValue value = new ParseValue(this);
	final ParseBackslash backslash = new ParseBackslash(this);
	final ParseQuote quote = new ParseQuote(this);
	final ParseArray array = new ParseArray(this);
	private IntToFunction currentFunction = value;

	/**
	 * Constructs a {@code ValueParser} with a consumer.
	 * 
	 * @param valueConsumer the consumer of the value.
	 */
	ValueParser(TypeConsumer valueConsumer) {
		Objects.requireNonNull(valueConsumer);
		this.valueConsumer = valueConsumer;
	}

	public void apply(int i) {
		currentFunction = currentFunction.apply(i);
	}

}
