package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

import com.github.dev.objectnotation.tree.Node;

/**
 * col
 */
class TagCol extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("span");
	}

	protected TagCol(Node node) {
		super(node);
	}

	@Override
	protected boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
