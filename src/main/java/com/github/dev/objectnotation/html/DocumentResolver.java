package com.github.dev.objectnotation.html;

import com.github.dev.objectnotation.tree.Document;
import com.github.dev.objectnotation.tree.DocumentFactory;

/**
 * Document resolver.
 */
class DocumentResolver {

	private final CharSequence charSequence;
	private final DocumentFactory documentFactory;

	DocumentResolver(CharSequence charSequence) {
		this.charSequence = charSequence;
		documentFactory = new DocumentFactory();
	}

	public Document getDocument() {
		return documentFactory.getDocument();
	}

}
