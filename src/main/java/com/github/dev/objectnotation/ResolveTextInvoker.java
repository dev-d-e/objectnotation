package com.github.dev.objectnotation;

import java.io.IOException;
import java.io.Reader;
import java.util.function.IntConsumer;

import com.github.dev.objectnotation.tree.Document;
import com.github.dev.objectnotation.tree.DocumentFactory;

/**
 * Invoker provides static methods.
 */
public final class ResolveTextInvoker {

	/**
	 * Suppress default constructor, ensuring non-instantiability.
	 */
	private ResolveTextInvoker() {
	}

	/**
	 * Resolve text, output text.
	 *
	 * @param array        the text.
	 * @param keyConsumer  the consumer of the key.
	 * @param textConsumer the consumer of the text.
	 */
	public static void accept(char[] array, IntStringConsumer keyConsumer, IntConsumer textConsumer) {
		if (array == null || array.length == 0) {
			return;
		}
		StandardResolver emptyResolver = new StandardResolver(keyConsumer, textConsumer);
		for (int n = 0; n < array.length; n++) {
			emptyResolver.apply(array[n]);
		}
		emptyResolver.apply(-1);
	}

	/**
	 * Resolve text, output text.
	 *
	 * @param charSequence the text.
	 * @param keyConsumer  the consumer of the key.
	 * @param textConsumer the consumer of the text.
	 */
	public static void accept(CharSequence charSequence, IntStringConsumer keyConsumer, IntConsumer textConsumer) {
		if (charSequence == null || charSequence.length() == 0) {
			return;
		}
		StandardResolver emptyResolver = new StandardResolver(keyConsumer, textConsumer);
		charSequence.chars().forEach(i -> emptyResolver.apply(i));
		emptyResolver.apply(-1);
	}

	/**
	 * Resolve text, output text. the reader is not closed.
	 *
	 * @param reader       the text.
	 * @param keyConsumer  the consumer of the key.
	 * @param textConsumer the consumer of the text.
	 */
	public static void accept(Reader reader, IntStringConsumer keyConsumer, IntConsumer textConsumer) throws IOException {
		if (reader == null) {
			return;
		}
		StandardResolver emptyResolver = new StandardResolver(keyConsumer, textConsumer);
		while (true) {
			int i = reader.read();
			emptyResolver.apply(i);
			if (i == -1) {
				break;
			}
		}
	}

	/**
	 * Resolve text, output {@code Document}.
	 *
	 * @param array the text.
	 * @return Document
	 */
	public static Document accept(char[] array) {
		DocumentFactory factory = new DocumentFactory();
		if (array != null && array.length > 0) {
			StandardResolver resolver = new StandardResolver(factory);
			for (int n = 0; n < array.length; n++) {
				resolver.apply(array[n]);
			}
			resolver.apply(-1);
		}
		return factory.getDocument();
	}

	/**
	 * Resolve text, output {@code Document}.
	 *
	 * @param charSequence the text.
	 * @return Document
	 */
	public static Document accept(CharSequence charSequence) {
		DocumentFactory factory = new DocumentFactory();
		if (charSequence != null && charSequence.length() > 0) {
			StandardResolver resolver = new StandardResolver(factory);
			charSequence.chars().forEach(i -> resolver.apply(i));
			resolver.apply(-1);
		}
		return factory.getDocument();
	}

	/**
	 * Resolve text, output {@code Document}. the reader is not closed.
	 *
	 * @param reader the text.
	 * @return Document
	 */
	public static Document accept(Reader reader) throws IOException {
		DocumentFactory factory = new DocumentFactory();
		if (reader != null) {
			StandardResolver resolver = new StandardResolver(factory);
			while (true) {
				int i = reader.read();
				resolver.apply(i);
				if (i == -1) {
					break;
				}
			}
		}
		return factory.getDocument();
	}

	/**
	 * Resolve text.
	 *
	 * @param array the text.
	 * @param obj   the output.
	 */
	public static <T> void accept(char[] array, T obj) {
		ObjectBuilder.build(accept(array), obj);
	}

	/**
	 * Resolve text.
	 *
	 * @param array the text.
	 * @param type  the class.
	 * @return T
	 */
	public static <T> T accept(char[] array, Class<T> type) {
		return ObjectBuilder.build(accept(array), type);
	}

	/**
	 * Resolve text.
	 *
	 * @param charSequence the text.
	 * @param obj          the output.
	 */
	public static <T> void accept(CharSequence charSequence, T obj) {
		ObjectBuilder.build(accept(charSequence), obj);
	}

	/**
	 * Resolve text.
	 *
	 * @param charSequence the text.
	 * @param type         the class.
	 * @return T
	 */
	public static <T> T accept(CharSequence charSequence, Class<T> type) {
		return ObjectBuilder.build(accept(charSequence), type);
	}

	/**
	 * Resolve text. the reader is not closed.
	 *
	 * @param reader the text.
	 * @param obj    the output.
	 */
	public static <T> void accept(Reader reader, T obj) throws IOException {
		ObjectBuilder.build(accept(reader), obj);
	}

	/**
	 * Resolve text. the reader is not closed.
	 *
	 * @param reader the text.
	 * @param type   the class.
	 * @return T
	 */
	public static <T> T accept(Reader reader, Class<T> type) throws IOException {
		return ObjectBuilder.build(accept(reader), type);
	}

}
