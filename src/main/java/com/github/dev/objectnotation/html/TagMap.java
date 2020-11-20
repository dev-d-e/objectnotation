package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * map
 */
class TagMap extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("name");
	}

	public TagMap() {
		super("map");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
