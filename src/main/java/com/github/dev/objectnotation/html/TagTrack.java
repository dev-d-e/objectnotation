package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

import com.github.dev.objectnotation.tree.Node;

/**
 * track
 */
class TagTrack extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("default");
		ATTRIBUTES.add("kind");
		ATTRIBUTES.add("label");
		ATTRIBUTES.add("src");
		ATTRIBUTES.add("srclang");
	}

	protected TagTrack(Node node) {
		super(node);
	}

	@Override
	protected boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
