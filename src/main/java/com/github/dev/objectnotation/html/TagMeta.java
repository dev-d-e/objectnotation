package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

import com.github.dev.objectnotation.tree.Node;

/**
 * meta
 */
class TagMeta extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("name");
		ATTRIBUTES.add("http-equiv");
		ATTRIBUTES.add("content");
		ATTRIBUTES.add("charset");
	}

	protected TagMeta(Node node) {
		super(node);
	}

	@Override
	protected boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
