package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * style
 */
class TagStyle extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("media");
	}

	public TagStyle() {
		super("style");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
