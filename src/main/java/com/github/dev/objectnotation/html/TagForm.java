package com.github.dev.objectnotation.html;

import com.github.dev.objectnotation.tree.Node;

class TagForm extends TagAbstractImpl {

	protected TagForm(Node node) {
		super(node);
	}

	@Override
	protected boolean isAttribute(String str) {
		return false;
	}

	@Override
	protected Tag createTag(String str, Node node) {
		switch (str) {
		case "div":
			return new TagDiv(node);
		default:
			return null;
		}
	}
}
