package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * canvas
 */
class TagCanvas extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("width");
		ATTRIBUTES.add("height");
	}

	public TagCanvas() {
		super("canvas");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
