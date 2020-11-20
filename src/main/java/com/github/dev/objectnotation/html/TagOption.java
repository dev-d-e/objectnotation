package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * option
 */
class TagOption extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("disabled");
		ATTRIBUTES.add("label");
		ATTRIBUTES.add("selected");
		ATTRIBUTES.add("value");
	}

	public TagOption() {
		super("option");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
