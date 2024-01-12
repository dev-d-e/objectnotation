package com.github.dev.objectnotation;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * XML Converter.
 */
public final class XMLConverter {

	private XMLConverter() {
	}

	/**
	 * Convert text to XML.
	 */
	public static String convert(CharSequence charSequence) {
		return convert(ResolveTextInvoker.accept(charSequence));
	}

	/**
	 * Convert text to XML.
	 */
	public static String convert(char[] array) {
		return convert(ResolveTextInvoker.accept(array));
	}

	/**
	 * Convert text to XML.
	 */
	public static String convert(Reader reader) throws IOException {
		return convert(ResolveTextInvoker.accept(reader));
	}

	/**
	 * Convert List Target to XML.
	 */
	public static String convert(List<Target> targets) {
		StringBuilder builder = new StringBuilder();
		targets.forEach(t -> toXML(builder, t));
		return builder.toString();
	}

	/**
	 * Convert Target to XML.
	 */
	public static String convert(Target target) {
		StringBuilder builder = new StringBuilder();
		toXML(builder, target);
		return builder.toString();
	}

	private static void toXML(StringBuilder builder, Target target) {
		String n = target.getName();
		target.getText().forEach(t -> {
			addTag(builder, n, t);
		});
		String s = convert(target.getValue());
		if (s != null && s.length() > 0) {
			addTag(builder, n, s);
		}
	}

	/**
	 * append tag.
	 */
	private static void addTag(StringBuilder builder, String tagName, CharSequence text) {
		builder.append('<');
		builder.append(tagName);
		builder.append('>');
		if (text != null && text.length() > 0) {
			builder.append(text);
		}
		builder.append('<');
		builder.append('/');
		builder.append(tagName);
		builder.append('>');
	}
}
