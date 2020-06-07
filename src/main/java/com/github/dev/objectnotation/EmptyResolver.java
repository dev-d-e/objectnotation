package com.github.dev.objectnotation;

import java.util.function.IntConsumer;

/**
 * Resolve key and value pair, output origin value.
 */
final class EmptyResolver {

	private final Parser parser;

	/**
	 * Constructs a {@code Parser} with two consumers.
	 * 
	 * @param keyConsumer   the consumer of the key.
	 * @param valueConsumer the consumer of the value.
	 */
	EmptyResolver(IntStringConsumer keyConsumer, IntConsumer valueConsumer) {
		parser = new Parser(keyConsumer, valueConsumer);
	}

	public void apply(int i) {
		parser.apply(i);
	}

}
