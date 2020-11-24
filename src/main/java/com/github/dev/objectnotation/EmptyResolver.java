package com.github.dev.objectnotation;

import java.util.function.IntConsumer;

import com.github.dev.objectnotation.tree.Document;
import com.github.dev.objectnotation.tree.DocumentFactory;

/**
 * Resolve key and value pair, output origin value.
 */
final class EmptyResolver {

	private final Parser parser;

	private final DocumentFactory documentFactory = new DocumentFactory();

	private int curOffset = -1;
	private String curKey;
	private StringBuilder builder;

	/**
	 * Constructs a {@code Parser} with two consumers.
	 * 
	 * @param keyConsumer   the consumer of the key.
	 * @param valueConsumer the consumer of the value.
	 */
	EmptyResolver(IntStringConsumer keyConsumer, IntConsumer valueConsumer) {
		parser = new Parser(keyConsumer, valueConsumer);
	}

	/**
	 * Constructs a {@code Parser}.
	 * 
	 */
	EmptyResolver() {
		parser = new Parser((i, s) -> {
			curOffset = i;
			curKey = s;
			builder = new StringBuilder();
		}, i -> {
			if (builder != null) {
				if (i == -1) {
					documentFactory.addNode(curOffset, curKey, builder);
					builder = null;
				} else {
					builder.append((char) i);
				}
			}
		});
	}

	public void apply(int i) {
		parser.apply(i);
	}

	public Document getDocument() {
		return documentFactory.getDocument();
	}

}
