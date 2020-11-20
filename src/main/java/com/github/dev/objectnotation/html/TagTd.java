package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * td
 */
class TagTd extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("colspan");
		ATTRIBUTES.add("rowspan");
		ATTRIBUTES.add("headers");
	}

	public TagTd() {
		super("td");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
