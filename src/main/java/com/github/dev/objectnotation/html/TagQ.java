package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * q
 */
class TagQ extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("cite");
	}

	public TagQ() {
		super("q");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
