package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * optgroup
 */
class TagOptgroup extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("disabled");
		ATTRIBUTES.add("label");
	}

	public TagOptgroup() {
		super("optgroup");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
