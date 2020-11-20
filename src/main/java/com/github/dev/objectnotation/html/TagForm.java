package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * form
 */
class TagForm extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("accept-charset");
		ATTRIBUTES.add("action");
		ATTRIBUTES.add("autocomplete");
		ATTRIBUTES.add("enctype");
		ATTRIBUTES.add("method");
		ATTRIBUTES.add("name");
		ATTRIBUTES.add("novalidate");
		ATTRIBUTES.add("target");
	}

	public TagForm() {
		super("form");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
