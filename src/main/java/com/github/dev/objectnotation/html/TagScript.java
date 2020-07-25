package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

import com.github.dev.objectnotation.tree.Node;

/**
 * script
 */
class TagScript extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("src");
		ATTRIBUTES.add("type");
		ATTRIBUTES.add("async");
		ATTRIBUTES.add("defer");
		ATTRIBUTES.add("crossorigin");
		ATTRIBUTES.add("integrity");
		ATTRIBUTES.add("referrerpolicy");
	}

	protected TagScript(Node node) {
		super(node);
	}

	@Override
	protected boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
