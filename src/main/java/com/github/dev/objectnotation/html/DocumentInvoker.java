package com.github.dev.objectnotation.html;

import com.github.dev.objectnotation.tree.Document;

/**
 * Invoker provides static methods.
 */
public final class DocumentInvoker {

	/**
	 * Suppress default constructor, ensuring non-instantiability.
	 */
	private DocumentInvoker() {
	}

	public static Document accept(CharSequence charSequence) {
		DocumentResolver treeResolver = new DocumentResolver(charSequence);
		return treeResolver.getDocument();
	}

}
