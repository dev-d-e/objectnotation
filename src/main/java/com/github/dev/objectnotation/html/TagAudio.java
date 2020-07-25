package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

import com.github.dev.objectnotation.tree.Node;

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

	protected TagAudio(Node node) {
		super(node);
	}

	@Override
	protected boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
