package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * blockquote
 */
class TagBlockquote extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("cite");
	}

	public TagBlockquote() {
		super("blockquote");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
