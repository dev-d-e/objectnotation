package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * li
 */
class TagLi extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("value");
	}

	public TagLi() {
		super("li");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
