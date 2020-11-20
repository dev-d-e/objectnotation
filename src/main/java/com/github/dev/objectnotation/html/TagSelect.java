package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * select
 */
class TagSelect extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("autocomplete");
		ATTRIBUTES.add("disabled");
		ATTRIBUTES.add("form");
		ATTRIBUTES.add("multiple");
		ATTRIBUTES.add("name");
		ATTRIBUTES.add("required");
		ATTRIBUTES.add("size");
	}

	public TagSelect() {
		super("select");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
