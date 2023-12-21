package com.github.dev.objectnotation;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

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
	 * @param array    the text.
	 * @param contents the consumer of the key and text.
	 */
	public static void accept(char[] array, Contents contents) {
		if (array == null || array.length == 0) {
			return;
		}
		StandardResolver emptyResolver = new StandardResolver(contents);
		for (int n = 0; n < array.length; n++) {
			emptyResolver.apply(array[n]);
		}
		emptyResolver.apply(-1);
	}

	/**
	 * Resolve text, output text.
	 *
	 * @param charSequence the text.
	 * @param contents     the consumer of the key and text.
	 */
	public static void accept(CharSequence charSequence, Contents contents) {
		if (charSequence == null || charSequence.length() == 0) {
			return;
		}
		StandardResolver emptyResolver = new StandardResolver(contents);
		charSequence.chars().forEach(i -> emptyResolver.apply(i));
		emptyResolver.apply(-1);
	}

	/**
	 * Resolve text, output text. the reader is not closed.
	 *
	 * @param reader   the text.
	 * @param contents the consumer of the key and text.
	 */
	public static void accept(Reader reader, Contents contents) throws IOException {
		if (reader == null) {
			return;
		}
		StandardResolver emptyResolver = new StandardResolver(contents);
		while (true) {
			int i = reader.read();
			emptyResolver.apply(i);
			if (i == -1) {
				break;
			}
		}
	}

	/**
	 * Resolve text, output {@code List<Target>}.
	 *
	 * @param array the text.
	 * @return List<Target>
	 */
	public static List<Target> accept(char[] array) {
		TreeBuilder builder = new TreeBuilder();
		accept(array, builder);
		return builder.build();
	}

	/**
	 * Resolve text, output {@code List<Target>}.
	 *
	 * @param charSequence the text.
	 * @return List<Target>
	 */
	public static List<Target> accept(CharSequence charSequence) {
		TreeBuilder builder = new TreeBuilder();
		accept(charSequence, builder);
		return builder.build();
	}

	/**
	 * Resolve text, output {@code List<Target>}. the reader is not closed.
	 *
	 * @param reader the text.
	 * @return List<Target>
	 */
	public static List<Target> accept(Reader reader) throws IOException {
		TreeBuilder builder = new TreeBuilder();
		accept(reader, builder);
		return builder.build();
	}

	/**
	 * Resolve text.
	 *
	 * @param array the text.
	 * @param obj   the output.
	 */
	public static <T> void accept(char[] array, T obj) {
		TreeBuilder builder = new TreeBuilder();
		accept(array, builder);
		ObjectBuilder.build(builder.getRoot(), obj);
	}

	/**
	 * Resolve text.
	 *
	 * @param array the text.
	 * @param type  the class.
	 * @return T
	 */
	public static <T> T accept(char[] array, Class<T> type) {
		TreeBuilder builder = new TreeBuilder();
		accept(array, builder);
		return ObjectBuilder.build(builder.getRoot(), type);
	}

	/**
	 * Resolve text.
	 *
	 * @param charSequence the text.
	 * @param obj          the output.
	 */
	public static <T> void accept(CharSequence charSequence, T obj) {
		TreeBuilder builder = new TreeBuilder();
		accept(charSequence, builder);
		ObjectBuilder.build(builder.getRoot(), obj);
	}

	/**
	 * Resolve text.
	 *
	 * @param charSequence the text.
	 * @param type         the class.
	 * @return T
	 */
	public static <T> T accept(CharSequence charSequence, Class<T> type) {
		TreeBuilder builder = new TreeBuilder();
		accept(charSequence, type);
		return ObjectBuilder.build(builder.getRoot(), type);
	}

	/**
	 * Resolve text. the reader is not closed.
	 *
	 * @param reader the text.
	 * @param obj    the output.
	 */
	public static <T> void accept(Reader reader, T obj) throws IOException {
		TreeBuilder builder = new TreeBuilder();
		accept(reader, builder);
		ObjectBuilder.build(builder.getRoot(), obj);
	}

	/**
	 * Resolve text. the reader is not closed.
	 *
	 * @param reader the text.
	 * @param type   the class.
	 * @return T
	 */
	public static <T> T accept(Reader reader, Class<T> type) throws IOException {
		TreeBuilder builder = new TreeBuilder();
		accept(reader, builder);
		return ObjectBuilder.build(builder.getRoot(), type);
	}

}
