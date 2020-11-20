package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * meta
 */
class TagMeta extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("name");
		ATTRIBUTES.add("http-equiv");
		ATTRIBUTES.add("content");
		ATTRIBUTES.add("charset");
	}

	public TagMeta() {
		super("meta");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
