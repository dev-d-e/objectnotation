package com.github.dev.objectnotation;

import java.util.function.IntConsumer;

/**
 * Resolve key and value pair.
 */
class StandardResolver {

	private final Parser parser;
	private final ValueParser valueParser;

	/**
	 * Constructs a {@code Parser} and a {@code ValueParser} with two consumers.
	 * 
	 * @param keyConsumer   the consumer of the key.
	 * @param valueConsumer the consumer of the value.
	 */
	StandardResolver(IntStringConsumer keyConsumer, IntConsumer valueConsumer) {
		valueParser = new ValueParser(valueConsumer);
		parser = new Parser(keyConsumer, i -> {
			valueParser.apply(i);
		});
	}

	public void apply(int i) {
		parser.apply(i);
	}

}
