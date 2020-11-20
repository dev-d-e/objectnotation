package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * slot
 */
class TagSlot extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("name");
	}

	public TagSlot() {
		super("slot");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
