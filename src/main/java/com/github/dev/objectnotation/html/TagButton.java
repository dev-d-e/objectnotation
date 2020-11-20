package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * button
 */
class TagButton extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("disabled");
		ATTRIBUTES.add("form");
		ATTRIBUTES.add("formaction");
		ATTRIBUTES.add("formenctype");
		ATTRIBUTES.add("formmethod");
		ATTRIBUTES.add("formnovalidate");
		ATTRIBUTES.add("formtarget");
		ATTRIBUTES.add("name");
		ATTRIBUTES.add("type");
		ATTRIBUTES.add("value");
	}

	public TagButton() {
		super("button");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
