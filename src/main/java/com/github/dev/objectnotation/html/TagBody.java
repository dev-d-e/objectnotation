package com.github.dev.objectnotation.html;

import com.github.dev.objectnotation.tree.Node;

class TagBody extends TagAbstractImpl {

	protected TagBody(Node node) {
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
		case "form":
			return new TagForm(node);
		default:
			return null;
		}
	}

}
