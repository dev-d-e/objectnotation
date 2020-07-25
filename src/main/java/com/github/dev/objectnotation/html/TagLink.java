package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

import com.github.dev.objectnotation.tree.Node;

/**
 * link
 */
class TagLink extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("href");
		ATTRIBUTES.add("crossorigin");
		ATTRIBUTES.add("rel");
		ATTRIBUTES.add("as");
		ATTRIBUTES.add("media");
		ATTRIBUTES.add("hreflang");
		ATTRIBUTES.add("type");
		ATTRIBUTES.add("sizes");
		ATTRIBUTES.add("imagesrcset");
		ATTRIBUTES.add("imagesizes");
		ATTRIBUTES.add("referrerpolicy");
		ATTRIBUTES.add("integrity");
		ATTRIBUTES.add("color");
		ATTRIBUTES.add("disabled");
	}

	protected TagLink(Node node) {
		super(node);
	}

	@Override
	protected boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
