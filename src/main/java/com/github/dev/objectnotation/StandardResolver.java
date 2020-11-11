package com.github.dev.objectnotation;

import com.github.dev.objectnotation.tree.Document;
import com.github.dev.objectnotation.tree.DocumentFactory;
import com.github.dev.objectnotation.value.Entity;
import com.github.dev.objectnotation.value.EntityFactory;

/**
 * Resolve key and value pair.
 */
class StandardResolver {

	private final Parser parser;
	private final ValueParser valueParser;

	private final DocumentFactory documentFactory = new DocumentFactory();

	private int curOffset = -1;
	private String curKey;
	private Entity curEntity = EntityFactory.createPrimitiveTypeEntity();
	private boolean negative;

	/**
	 * Constructs a {@code Parser} and a {@code ValueParser}.
	 */
	StandardResolver() {
		valueParser = new ValueParser(entity -> {
			curEntity = entity;
		}, i -> {
			if (i == -1) {
				if (negative) {
					return;
				}
				curEntity.finish();
				documentFactory.addNode(curOffset, curKey, curEntity);
				curEntity = EntityFactory.createPrimitiveTypeEntity();
				negative = true;
			} else {
				curEntity.accept((char) i);
			}
		});
		parser = new Parser((i, s) -> {
			curOffset = i;
			curKey = s;
			negative = false;
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
