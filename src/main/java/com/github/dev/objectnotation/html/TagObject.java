package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

import com.github.dev.objectnotation.tree.Node;

/**
 * object
 */
class TagObject extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("data");
		ATTRIBUTES.add("type");
		ATTRIBUTES.add("name");
		ATTRIBUTES.add("usemap");
		ATTRIBUTES.add("form");
		ATTRIBUTES.add("width");
		ATTRIBUTES.add("height");
	}

	protected TagObject(Node node) {
		super(node);
	}

	@Override
	protected boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
