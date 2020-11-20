package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * ins
 */
class TagIns extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("cite");
		ATTRIBUTES.add("datetime");
	}

	public TagIns() {
		super("ins");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
