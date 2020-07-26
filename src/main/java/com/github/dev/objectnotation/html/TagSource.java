package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

import com.github.dev.objectnotation.tree.Node;

/**
 * source
 */
class TagSource extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("src");
		ATTRIBUTES.add("type");
		ATTRIBUTES.add("srcset");
		ATTRIBUTES.add("sizes");
		ATTRIBUTES.add("media");
	}

	protected TagSource(Node node) {
		super(node);
	}

	@Override
	protected boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
