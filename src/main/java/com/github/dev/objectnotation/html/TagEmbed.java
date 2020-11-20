package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * embed
 */
class TagEmbed extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("src");
		ATTRIBUTES.add("type");
		ATTRIBUTES.add("width");
		ATTRIBUTES.add("height");
	}

	public TagEmbed() {
		super("embed");
	}

	@Override
	public boolean isAttribute(String str) {
		return true;
	}

}
