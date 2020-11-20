package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * output
 */
class TagOutput extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("for");
		ATTRIBUTES.add("form");
		ATTRIBUTES.add("name");
	}

	public TagOutput() {
		super("output");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
