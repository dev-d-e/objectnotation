package com.github.dev.objectnotation;

import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

import com.github.dev.objectnotation.tree.Document;

/**
 * Invoker provides static methods.
 */
public class ResolveTextInvoker {

	/**
	 * Suppress default constructor, ensuring non-instantiability.
	 */
	private ResolveTextInvoker() {
	}

	/**
	 * Resolve text, output Document.
	 *
	 * @param array the text.
	 * @return Document
	 */
	public static Document accept(char[] array) {
		Objects.requireNonNull(array);
		if (array.length == 0) {
			throw new NullPointerException();
		}
		StandardResolver resolver = new StandardResolver();
		for (int n = 0; n < array.length; n++) {
			resolver.apply(array[n]);
		}
		resolver.apply(-1);
		return resolver.getDocument();
	}

	/**
	 * Resolve text, output Document.
	 *
	 * @param charSequence the text.
	 * @return Document
	 */
	public static Document accept(CharSequence charSequence) {
		Objects.requireNonNull(charSequence);
		if (charSequence.length() == 0) {
			throw new NullPointerException();
		}
		StandardResolver resolver = new StandardResolver();
		charSequence.chars().forEach(i -> resolver.apply(i));
		resolver.apply(-1);
		return resolver.getDocument();
	}

	/**
	 * Resolve text, output Document. the reader is not closed.
	 *
	 * @param reader the text.
	 * @return Document
	 */
	public static Document accept(Reader reader) throws IOException {
		Objects.requireNonNull(reader);
		StandardResolver resolver = new StandardResolver();
		while (true) {
			int i = reader.read();
			resolver.apply(i);
			if (i == -1) {
				break;
			}
		}
		return resolver.getDocument();
	}

}
