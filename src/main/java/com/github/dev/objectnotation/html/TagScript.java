package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * script
 */
class TagScript extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("src");
		ATTRIBUTES.add("type");
		ATTRIBUTES.add("async");
		ATTRIBUTES.add("defer");
		ATTRIBUTES.add("crossorigin");
		ATTRIBUTES.add("integrity");
		ATTRIBUTES.add("referrerpolicy");
	}

	public TagScript() {
		super("script");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
