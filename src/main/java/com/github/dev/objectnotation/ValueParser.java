package com.github.dev.objectnotation;

import java.util.Objects;
import java.util.function.IntConsumer;

/**
 * Parse value.
 */
final class ValueParser {

	final IntConsumer valueConsumer;
	private IntToFunction currentFunction;

	/**
	 * Constructs a {@code ValueParser} with a consumer.
	 * 
	 * @param valueConsumer the consumer of the value.
	 */
	ValueParser(IntConsumer valueConsumer) {
		Objects.requireNonNull(valueConsumer);
		this.valueConsumer = valueConsumer;
	}

	public void apply(int i) {
		currentFunction = currentFunction.apply(i);
	}

}
