package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * dialog
 */
class TagDialog extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("open");
	}

	public TagDialog() {
		super("dialog");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
