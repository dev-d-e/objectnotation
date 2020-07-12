package com.github.dev.objectnotation.html;

import java.util.function.IntConsumer;

import com.github.dev.objectnotation.DirectTextInvoker;
import com.github.dev.objectnotation.IntStringConsumer;
import com.github.dev.objectnotation.tree.Document;
import com.github.dev.objectnotation.tree.DocumentFactory;
import com.github.dev.objectnotation.value.EntityFactory;

/**
 * Document resolver.
 */
class DocumentResolver {

	private final CharSequence charSequence;
	private final DocumentFactory documentFactory;

	private int curOffset = -1;
	private String curKey;

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

		private StringBuilder builder = new StringBuilder();

		@Override
		public void accept(int i) {
			if (i == -1) {
				if (builder.length() > 0) {
					documentFactory.addLeaf(curOffset, curKey, EntityFactory.createPrimitiveTypeEntity(builder.toString()));
				} else {
					documentFactory.addBranch(curOffset, curKey);
				}
				builder = new StringBuilder();
			} else {
				builder.append((char) i);
			}
		}

	}

}
