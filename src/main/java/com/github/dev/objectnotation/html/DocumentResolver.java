package com.github.dev.objectnotation.html;

import java.util.function.IntConsumer;

import com.github.dev.objectnotation.DirectTextInvoker;
import com.github.dev.objectnotation.IntStringConsumer;
import com.github.dev.objectnotation.tree.Document;
import com.github.dev.objectnotation.tree.DocumentFactory;
import com.github.dev.objectnotation.value.Entity;
import com.github.dev.objectnotation.value.EntityFactory;

/**
 * Document resolver.
 */
class DocumentResolver {

	private final CharSequence charSequence;
	private final DocumentFactory documentFactory;

	private int curOffset = -1;
	private String curKey;
	private Entity curEntity = EntityFactory.createPrimitiveTypeEntity();

	private Document document;

	DocumentResolver(CharSequence charSequence) {
		this.charSequence = charSequence;
		documentFactory = new DocumentFactory();
	}

	public Document getDocument() {
		if (document == null) {
			DirectTextInvoker.accept(charSequence, new KeyConsumer(), new ValueConsumer());
		}
		return documentFactory.getDocument();
	}

	private class KeyConsumer implements IntStringConsumer {

		@Override
		public void accept(int i, String s) {
			curOffset = i;
			curKey = s;
		}

	}

	private class ValueConsumer implements IntConsumer {

		@Override
		public void accept(int i) {
			if (i == -1) {
				curEntity.finish();
				documentFactory.addNode(curOffset, curKey, curEntity);
				curEntity = EntityFactory.createPrimitiveTypeEntity();
			} else {
				curEntity.accept((char) i);
			}
		}

	}

}
