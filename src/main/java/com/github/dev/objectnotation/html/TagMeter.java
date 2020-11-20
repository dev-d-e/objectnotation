package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

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

	public TagMeter() {
		super("meter");
	}

	@Override
	public boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
