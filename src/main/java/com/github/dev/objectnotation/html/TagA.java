package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

import com.github.dev.objectnotation.tree.Node;

/**
 * a
 */
class TagA extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("href");
		ATTRIBUTES.add("target");
		ATTRIBUTES.add("download");
		ATTRIBUTES.add("ping");
		ATTRIBUTES.add("rel");
		ATTRIBUTES.add("hreflang");
		ATTRIBUTES.add("type");
		ATTRIBUTES.add("referrerpolicy");
	}

	protected TagA(Node node) {
		super(node);
	}

	@Override
	protected boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
