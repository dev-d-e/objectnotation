package com.github.dev.objectnotation;

import java.io.IOException;
import java.io.Reader;
import java.util.Objects;
import java.util.function.IntConsumer;

import com.github.dev.objectnotation.tree.Document;

/**
 * Invoker provides static methods.
 */
public final class DirectTextInvoker {

	/**
	 * Suppress default constructor, ensuring non-instantiability.
	 */
	private DirectTextInvoker() {
	}

	/**
	 * Resolve text, output origin value.
	 *
	 * @param array         the text.
	 * @param keyConsumer   the consumer of the key.
	 * @param valueConsumer the consumer of the value.
	 */
	public static void accept(char[] array, IntStringConsumer keyConsumer, IntConsumer valueConsumer) {
		Objects.requireNonNull(array);
		if (array.length == 0) {
			throw new NullPointerException();
		}
		EmptyResolver emptyResolver = new EmptyResolver(keyConsumer, valueConsumer);
		for (int n = 0; n < array.length; n++) {
			emptyResolver.apply(array[n]);
		}
		emptyResolver.apply(-1);
	}

	/**
	 * Resolve text, output origin value.
	 *
	 * @param charSequence  the text.
	 * @param keyConsumer   the consumer of the key.
	 * @param valueConsumer the consumer of the value.
	 */
	public static void accept(CharSequence charSequence, IntStringConsumer keyConsumer, IntConsumer valueConsumer) {
		Objects.requireNonNull(charSequence);
		if (charSequence.length() == 0) {
			throw new NullPointerException();
		}
		EmptyResolver emptyResolver = new EmptyResolver(keyConsumer, valueConsumer);
		charSequence.chars().forEach(i -> emptyResolver.apply(i));
		emptyResolver.apply(-1);
	}

	/**
	 * Resolve text, output origin value. the reader is not closed.
	 *
	 * @param reader        the text.
	 * @param keyConsumer   the consumer of the key.
	 * @param valueConsumer the consumer of the value.
	 */
	public static void accept(Reader reader, IntStringConsumer keyConsumer, IntConsumer valueConsumer) throws IOException {
		Objects.requireNonNull(reader);
		EmptyResolver emptyResolver = new EmptyResolver(keyConsumer, valueConsumer);
		while (true) {
			int i = reader.read();
			emptyResolver.apply(i);
			if (i == -1) {
				break;
			}
		}
	}

	/**
	 * Resolve text, output origin value by Document.
	 *
	 * @param array the text.
	 * @return Document
	 */
	public static Document accept(char[] array) {
		Objects.requireNonNull(array);
		if (array.length == 0) {
			throw new NullPointerException();
		}
		EmptyResolver emptyResolver = new EmptyResolver();
		for (int n = 0; n < array.length; n++) {
			emptyResolver.apply(array[n]);
		}
		emptyResolver.apply(-1);
		return emptyResolver.getDocument();
	}

	/**
	 * Resolve text, output origin value by Document.
	 *
	 * @param charSequence the text.
	 * @return Document
	 */
	public static Document accept(CharSequence charSequence) {
		Objects.requireNonNull(charSequence);
		if (charSequence.length() == 0) {
			throw new NullPointerException();
		}
		EmptyResolver emptyResolver = new EmptyResolver();
		charSequence.chars().forEach(i -> emptyResolver.apply(i));
		emptyResolver.apply(-1);
		return emptyResolver.getDocument();
	}

	/**
	 * Resolve text, output origin value by Document. the reader is not closed.
	 *
	 * @param reader the text.
	 * @return Document
	 */
	public static Document accept(Reader reader) throws IOException {
		Objects.requireNonNull(reader);
		EmptyResolver emptyResolver = new EmptyResolver();
		while (true) {
			int i = reader.read();
			emptyResolver.apply(i);
			if (i == -1) {
				break;
			}
		}
		return emptyResolver.getDocument();
	}

}
