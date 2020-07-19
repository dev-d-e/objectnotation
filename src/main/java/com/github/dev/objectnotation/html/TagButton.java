package com.github.dev.objectnotation.html;

import com.github.dev.objectnotation.tree.Node;

class TagButton extends TagAbstractImpl {

	protected TagButton(Node node) {
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