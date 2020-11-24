package com.github.dev.objectnotation;

import com.github.dev.objectnotation.tree.Document;
import com.github.dev.objectnotation.tree.DocumentFactory;

/**
 * Resolve key and value pair.
 */
class StandardResolver {

	private final Parser parser;
	private final ValueParser valueParser;

	private final DocumentFactory documentFactory = new DocumentFactory();

	private int curOffset = -1;
	private String curKey;
	private StringBuilder builder;

	/**
	 * Constructs a {@code Parser} and a {@code ValueParser}.
	 */
	StandardResolver() {
		valueParser = new ValueParser(i -> {
			if (builder != null) {
				if (i == -1) {
					documentFactory.addNode(curOffset, curKey, builder);
					builder = null;
				} else if (i == -2) {
					documentFactory.addNode(curOffset, curKey, builder);
					builder = new StringBuilder();
				} else {
					builder.append((char) i);
				}
			}
		});
		parser = new Parser((i, s) -> {
			curOffset = i;
			curKey = s;
			builder = new StringBuilder();
		}, i -> {
			valueParser.apply(i);
		});
	}

	public void apply(int i) {
		parser.apply(i);
	}

	public Document getDocument() {
		return documentFactory.getDocument();
	}

}
