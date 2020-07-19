package com.github.dev.objectnotation.html;

import com.github.dev.objectnotation.tree.Node;

class TagHtml extends TagAbstractImpl {

	protected TagHtml(Node node) {
		super(node);
	}

	@Override
	protected boolean isAttribute(String str) {
		return false;
	}

	@Override
	protected Tag createTag(String str, Node node) {
		switch (str) {
		case "head":
			return new TagHead(node);
		case "body":
			return new TagBody(node);
		default:
			return null;
		}
	}

}
