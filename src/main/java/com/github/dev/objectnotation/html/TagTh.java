package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

import com.github.dev.objectnotation.tree.Node;

/**
 * th
 */
class TagTh extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("colspan");
		ATTRIBUTES.add("rowspan");
		ATTRIBUTES.add("headers");
		ATTRIBUTES.add("scope");
		ATTRIBUTES.add("abbr");
	}

	protected TagTh(Node node) {
		super(node);
	}

	@Override
	protected boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
