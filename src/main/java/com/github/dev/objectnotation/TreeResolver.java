package com.github.dev.objectnotation;

import com.github.dev.objectnotation.tree.Document;
import com.github.dev.objectnotation.tree.DocumentFactory;
import com.github.dev.objectnotation.value.Entity;
import com.github.dev.objectnotation.value.EntityFactory;

/**
 * Tree resolver.
 */
class TreeResolver {

	private final StandardResolver staResolver;

	private final DocumentFactory documentFactory;

	private int curOffset = -1;
	private String curKey;
	private Entity curEntity = EntityFactory.createPrimitiveTypeEntity();
	private boolean negative;

	public TreeResolver() {
		staResolver = new StandardResolver((i, s) -> {
			curOffset = i;
			curKey = s;
			negative = false;
		}, new TypeConsumer() {

			@Override
			public void setEntity(Entity entity) {
				curEntity = entity;
			}

			@Override
			public void accept(int i) {
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
			}

		});
		documentFactory = new DocumentFactory();
	}

	public void apply(int i) {
		staResolver.apply(i);
	}

	public Document getDocument() {
		return documentFactory.getDocument();
	}

}
