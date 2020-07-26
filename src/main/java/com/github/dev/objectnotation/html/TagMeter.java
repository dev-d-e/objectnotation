package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

import com.github.dev.objectnotation.tree.Node;

/**
 * meter
 */
class TagMeter extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("value");
		ATTRIBUTES.add("min");
		ATTRIBUTES.add("max");
		ATTRIBUTES.add("low");
		ATTRIBUTES.add("high");
		ATTRIBUTES.add("optimum");
	}

	protected TagMeter(Node node) {
		super(node);
	}

	@Override
	protected boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
