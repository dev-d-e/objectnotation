package com.github.dev.objectnotation;

import com.github.dev.objectnotation.tree.Document;
import com.github.dev.objectnotation.tree.DocumentFactory;
import com.github.dev.objectnotation.value.Entity;
import com.github.dev.objectnotation.value.EntityFactory;

/**
 * Tree resolver.
 */
class TreeResolver {

	private final IntStringConsumer keyConsumer = new KeyConsumer();
	private final TypeConsumer valueConsumer = new ValueConsumer();
	private final StandardResolver staResolver;

	private final DocumentFactory documentFactory;

	private int curOffset = -1;
	private String curKey;
	private Entity curEntity = EntityFactory.createPrimitiveTypeEntity();
	private boolean negative;

	public TreeResolver() {
		staResolver = new StandardResolver(keyConsumer, valueConsumer);
		documentFactory = new DocumentFactory();
	}

	public void apply(int i) {
		staResolver.apply(i);
	}

	public Document getDocument() {
		return documentFactory.getDocument();
	}

	private class KeyConsumer implements IntStringConsumer {

		@Override
		public void accept(int i, String s) {
			curOffset = i;
			curKey = s;
			negative = false;
		}

	}

	private class ValueConsumer implements TypeConsumer {

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

	}

}
