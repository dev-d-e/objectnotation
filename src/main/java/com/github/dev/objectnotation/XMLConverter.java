package com.github.dev.objectnotation;

import java.util.LinkedList;

/**
 * XML Converter.
 */
public final class XMLConverter {

	private XMLConverter() {
	}

	/**
	 * convert text.
	 *
	 * @param charSequence the text.
	 */
	public static String convert(CharSequence charSequence) {
		LinkedList<String> keys = new LinkedList<>();
		StringBuilder builder = new StringBuilder();
		ResolveTextInvoker.accept(charSequence, (i, s) -> {
			while (i <= keys.size() - 1) {
				builder.append('<');
				builder.append(keys.pop());
				builder.append("/>");
			}
			keys.push(s);
			builder.append('<');
			builder.append(s);
			builder.append('>');
		}, i -> builder.append((char) i));
		while (keys.size() > 0) {
			builder.append('<');
			builder.append(keys.pop());
			builder.append("/>");
		}
		return builder.toString();
	}

}
