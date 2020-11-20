package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * time
 */
class TagTime extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("datetime");
	}

	public TagTime() {
		super("time");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
