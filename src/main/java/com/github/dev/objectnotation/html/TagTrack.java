package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * track
 */
class TagTrack extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("default");
		ATTRIBUTES.add("kind");
		ATTRIBUTES.add("label");
		ATTRIBUTES.add("src");
		ATTRIBUTES.add("srclang");
	}

	public TagTrack() {
		super("track");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
