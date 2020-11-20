package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * param
 */
class TagParam extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("name");
		ATTRIBUTES.add("value");
	}

	public TagParam() {
		super("param");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
