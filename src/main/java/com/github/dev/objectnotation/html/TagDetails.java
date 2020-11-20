package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * details
 */
class TagDetails extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("open");
	}

	public TagDetails() {
		super("details");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
