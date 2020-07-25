package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

import com.github.dev.objectnotation.tree.Node;

/**
 * html
 */
class TagHtml extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("manifest");
	}

	protected TagHtml(Node node) {
		super(node);
	}

	@Override
	protected boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

	@Override
	protected Tag createTag(String str, Node node) {
		switch (str) {
		case "head":
			return new TagHead(node);
		case "body":
			return new TagBody(node);
		default:
			return new TagCommon(node);
		}
	}

}
