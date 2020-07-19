package com.github.dev.objectnotation.html;

import com.github.dev.objectnotation.tree.Node;

class TagHead extends TagAbstractImpl {

	protected TagHead(Node node) {
		super(node);
	}

	@Override
	protected boolean isAttribute(String str) {
		return false;
	}

	@Override
	protected Tag createTag(String str, Node node) {
		switch (str) {
		case "meta":
			return new TagMeta(node);
		case "title":
			return new TagTitle(node);
		default:
			return null;
		}
	}

}
