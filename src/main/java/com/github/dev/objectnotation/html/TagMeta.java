package com.github.dev.objectnotation.html;

import com.github.dev.objectnotation.tree.Node;

class TagMeta extends TagAbstractImpl {

	protected TagMeta(Node node) {
		super(node);
	}

	@Override
	protected boolean isAttribute(String str) {
		return true;
	}

	@Override
	protected Tag createTag(String str, Node node) {
		return null;
	}

}
