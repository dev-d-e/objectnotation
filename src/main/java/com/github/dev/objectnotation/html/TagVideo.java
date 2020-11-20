package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * video
 */
class TagVideo extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("src");
		ATTRIBUTES.add("crossorigin");
		ATTRIBUTES.add("poster");
		ATTRIBUTES.add("preload");
		ATTRIBUTES.add("autoplay");
		ATTRIBUTES.add("playsinline");
		ATTRIBUTES.add("loop");
		ATTRIBUTES.add("muted");
		ATTRIBUTES.add("controls");
		ATTRIBUTES.add("width");
		ATTRIBUTES.add("height");
	}

	public TagVideo() {
		super("video");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
