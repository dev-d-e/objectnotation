package com.github.dev.objectnotation;

import java.util.Objects;

/**
 * ValueParser.
 */
final class ValueParser {

	final TypeConsumer valueConsumer;

	final ValueReader valueReader = new ValueReader(this);
	final BackslashConverter backslashConverter = new BackslashConverter(this);
	final QuoteValueReader quoteValue = new QuoteValueReader(this);
	final ArrayReader arrayReader = new ArrayReader(this);
	private IntToFunction currentFunction = valueReader;

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
