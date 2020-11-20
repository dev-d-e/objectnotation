package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * progress
 */
class TagProgress extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("value");
		ATTRIBUTES.add("max");
	}

	public TagProgress() {
		super("progress");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
