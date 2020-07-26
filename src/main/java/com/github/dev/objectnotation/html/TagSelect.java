package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

import com.github.dev.objectnotation.tree.Node;

/**
 * select
 */
class TagSelect extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("autocomplete");
		ATTRIBUTES.add("disabled");
		ATTRIBUTES.add("form");
		ATTRIBUTES.add("multiple");
		ATTRIBUTES.add("name");
		ATTRIBUTES.add("required");
		ATTRIBUTES.add("size");
	}

	protected TagSelect(Node node) {
		super(node);
	}

	@Override
	protected boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
