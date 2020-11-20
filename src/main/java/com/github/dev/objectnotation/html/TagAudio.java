package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * audio
 */
class TagAudio extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("src");
		ATTRIBUTES.add("crossorigin");
		ATTRIBUTES.add("preload");
		ATTRIBUTES.add("autoplay");
		ATTRIBUTES.add("loop");
		ATTRIBUTES.add("muted");
		ATTRIBUTES.add("controls");
	}

	public TagAudio() {
		super("audio");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
