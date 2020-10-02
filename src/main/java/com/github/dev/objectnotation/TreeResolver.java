package com.github.dev.objectnotation;

import java.util.function.IntConsumer;

import com.github.dev.objectnotation.tree.Document;
import com.github.dev.objectnotation.tree.DocumentFactory;
import com.github.dev.objectnotation.value.ArrayEntity;
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
		}

	}

	private class ValueConsumer implements TypeConsumer {

		private int option = 0;
		private StringProcessor stringProcessor = new StringProcessor();
		private ArrayProcessor arrayProcessor = new ArrayProcessor();
		private IntConsumer intConsumer = stringProcessor;

		@Override
		public void opt(int i) {
			if (curOffset == -1) {
				throw new IllegalCharException("");
			}
			option = i;
			if (option == 0) {
				intConsumer = stringProcessor;
			} else if (option == 1) {
				intConsumer = arrayProcessor;
			}
		}

		@Override
		public void accept(int i) {
			intConsumer.accept(i);
		}

	}

	private class StringProcessor implements IntConsumer {

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

	private class ArrayProcessor implements IntConsumer {

		private ArrayEntity arrayEntity = EntityFactory.createArrayEntity();
		private StringBuilder builder = new StringBuilder();

		@Override
		public void accept(int i) {
			if (i == -1) {
				if (builder.length() > 0) {
					arrayEntity.add(EntityFactory.createPrimitiveTypeEntity(builder.toString()));
					builder = new StringBuilder();
				}
				documentFactory.addLeaf(curOffset, curKey, arrayEntity);
				arrayEntity = EntityFactory.createArrayEntity();
				valueConsumer.opt(0);
			} else {
				char c = (char) i;
				if (c == ',') {
					arrayEntity.add(EntityFactory.createPrimitiveTypeEntity(builder.toString()));
					builder = new StringBuilder();
				} else {
					builder.append(c);
				}
			}
		}

	}

}
