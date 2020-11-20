package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * base
 */
class TagBase extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("href");
		ATTRIBUTES.add("target");
	}

	public TagBase() {
		super("base");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
