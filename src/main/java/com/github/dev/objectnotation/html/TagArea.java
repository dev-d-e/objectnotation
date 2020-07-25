package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

import com.github.dev.objectnotation.tree.Node;

/**
 * area
 */
class TagArea extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("alt");
		ATTRIBUTES.add("coords");
		ATTRIBUTES.add("shape");
		ATTRIBUTES.add("href");
		ATTRIBUTES.add("target");
		ATTRIBUTES.add("download");
		ATTRIBUTES.add("ping");
		ATTRIBUTES.add("rel");
		ATTRIBUTES.add("referrerpolicy");
	}

	protected TagArea(Node node) {
		super(node);
	}

	@Override
	protected boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
