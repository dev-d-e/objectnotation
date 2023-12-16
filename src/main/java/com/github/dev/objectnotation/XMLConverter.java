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
		Converter c = new Converter();
		ResolveTextInvoker.accept(charSequence, c);
		return c.toXML();
	}

}

class Converter implements Contents {
	LinkedList<String> keys = new LinkedList<>();
	int i;
	StringBuilder key = new StringBuilder();
	StringBuilder builder = new StringBuilder();

	@Override
	public void preKey(int offset) {
		this.i = offset;
	}

	@Override
	public void key(int i) {
		key.append((char) i);
	}

	@Override
	public void postKey() {
		while (i <= keys.size() - 1) {
			builder.append('<');
			builder.append(keys.pop());
			builder.append("/>");
		}
		keys.push(key.toString());
		builder.append('<');
		builder.append(key);
		builder.append('>');
		key = new StringBuilder();
	}

	@Override
	public void preText() {

	}

	@Override
	public void text(int i) {
		builder.append((char) i);
	}

	@Override
	public void textArray() {

	}

	@Override
	public void postText() {

	}

	@Override
	public void error(int row, int n) {

	}

	public String toXML() {
		while (keys.size() > 0) {
			builder.append('<');
			builder.append(keys.pop());
			builder.append("/>");
		}
		return builder.toString();
	}

}
