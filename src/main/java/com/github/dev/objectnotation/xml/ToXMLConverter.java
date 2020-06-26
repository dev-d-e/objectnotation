package com.github.dev.objectnotation.xml;

import java.util.LinkedList;

import com.github.dev.objectnotation.DirectTextInvoker;

/**
 * Provides static methods.
 */
public final class ToXMLConverter {

	/**
	 * Suppress default constructor, ensuring non-instantiability.
	 */
	private ToXMLConverter() {
	}

	public static String convert(String value) {
		LinkedList<String> keys = new LinkedList<>();
		StringBuilder builder = new StringBuilder();
		DirectTextInvoker.accept(value, (i, s) -> {
			while (i <= keys.size() - 1) {
				builder.append('<');
				builder.append(keys.pop());
				builder.append("/>");
			}
			keys.push(s);
			builder.append('<');
			builder.append(s);
			builder.append('>');
		}, i -> {
			if (i != -1) {
				builder.append((char) i);
			}
		});
		while (keys.size() > 0) {
			builder.append('<');
			builder.append(keys.pop());
			builder.append("/>");
		}
		return builder.toString();
	}

}
