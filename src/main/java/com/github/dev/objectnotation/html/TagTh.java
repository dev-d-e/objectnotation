package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * th
 */
class TagTh extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("colspan");
		ATTRIBUTES.add("rowspan");
		ATTRIBUTES.add("headers");
		ATTRIBUTES.add("scope");
		ATTRIBUTES.add("abbr");
	}

	public TagTh() {
		super("th");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
