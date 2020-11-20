package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * a
 */
class TagA extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("href");
		ATTRIBUTES.add("target");
		ATTRIBUTES.add("download");
		ATTRIBUTES.add("ping");
		ATTRIBUTES.add("rel");
		ATTRIBUTES.add("hreflang");
		ATTRIBUTES.add("type");
		ATTRIBUTES.add("referrerpolicy");
	}

	public TagA() {
		super("a");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
