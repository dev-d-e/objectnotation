package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * ol
 */
class TagOl extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("reversed");
		ATTRIBUTES.add("start");
		ATTRIBUTES.add("type");
	}

	public TagOl() {
		super("ol");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
