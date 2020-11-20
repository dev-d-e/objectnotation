package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * textarea
 */
class TagTextarea extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("cols");
		ATTRIBUTES.add("dirname");
		ATTRIBUTES.add("disabled");
		ATTRIBUTES.add("form");
		ATTRIBUTES.add("maxlength");
		ATTRIBUTES.add("minlength");
		ATTRIBUTES.add("name");
		ATTRIBUTES.add("placeholder");
		ATTRIBUTES.add("readonly");
		ATTRIBUTES.add("required");
		ATTRIBUTES.add("rows");
		ATTRIBUTES.add("wrap");
	}

	public TagTextarea() {
		super("textarea");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
