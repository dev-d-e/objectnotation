package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * html
 */
class TagHtml extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("manifest");
	}

	public TagHtml() {
		super("html");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
