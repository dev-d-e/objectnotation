package com.github.dev.objectnotation;

import java.util.Objects;
import java.util.function.IntConsumer;

/**
 * 
 */
final class Parser {

	int n = -1;

	final IntStringConsumer keyConsumer;
	final IntConsumer valueConsumer;

	private IntToFunction currentFunction;

	/**
	 * Constructs a {@code Parser} with two consumers.
	 * 
	 * @param keyConsumer   the consumer of the key.
	 * @param valueConsumer the consumer of the value.
	 */
	Parser(IntStringConsumer keyConsumer, IntConsumer valueConsumer) {
		Objects.requireNonNull(keyConsumer);
		Objects.requireNonNull(valueConsumer);
		this.keyConsumer = keyConsumer;
		this.valueConsumer = valueConsumer;
	}

	public void apply(int i) {
		n++;
		currentFunction = currentFunction.apply(i);
	}

}
