package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * data
 */
class TagData extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("value");
	}

	public TagData() {
		super("data");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
