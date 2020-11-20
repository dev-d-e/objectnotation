package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * label
 */
class TagLabel extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("for");
	}

	public TagLabel() {
		super("label");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
