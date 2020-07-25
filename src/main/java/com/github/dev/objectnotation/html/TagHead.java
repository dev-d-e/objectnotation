package com.github.dev.objectnotation.html;

import com.github.dev.objectnotation.tree.Node;

/**
 * head
 */
class TagHead extends TagAbstractImpl {

	protected TagHead(Node node) {
		super(node);
	}

	@Override
	protected boolean isAttribute(String str) {
		return false;
	}

}
