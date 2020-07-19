package com.github.dev.objectnotation.html;

import com.github.dev.objectnotation.tree.Document;

/**
 * Converter.
 */
public class HtmlConverter {

	public static String convert(CharSequence charSequence) {
		Document document = DocumentInvoker.accept(charSequence);
		TagHtml t = new TagHtml(document.node(0));
		return t.asHtml();
	}

}
