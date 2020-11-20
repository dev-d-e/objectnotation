package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * img
 */
class TagImg extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("alt");
		ATTRIBUTES.add("src");
		ATTRIBUTES.add("srcset");
		ATTRIBUTES.add("sizes");
		ATTRIBUTES.add("crossorigin");
		ATTRIBUTES.add("usemap");
		ATTRIBUTES.add("ismap");
		ATTRIBUTES.add("width");
		ATTRIBUTES.add("height");
		ATTRIBUTES.add("referrerpolicy");
		ATTRIBUTES.add("decoding");
		ATTRIBUTES.add("loading");
	}

	public TagImg() {
		super("img");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
