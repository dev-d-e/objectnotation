package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * del
 */
class TagDel extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("cite");
		ATTRIBUTES.add("datetime");
	}

	public TagDel() {
		super("del");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
