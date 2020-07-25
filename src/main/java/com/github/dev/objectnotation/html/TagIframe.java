package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

import com.github.dev.objectnotation.tree.Node;

/**
 * iframe
 */
class TagIframe extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("src");
		ATTRIBUTES.add("srcdoc");
		ATTRIBUTES.add("name");
		ATTRIBUTES.add("sandbox");
		ATTRIBUTES.add("allow");
		ATTRIBUTES.add("allowfullscreen");
		ATTRIBUTES.add("allowpaymentrequest");
		ATTRIBUTES.add("width");
		ATTRIBUTES.add("height");
		ATTRIBUTES.add("referrerpolicy");
		ATTRIBUTES.add("loading");
	}

	protected TagIframe(Node node) {
		super(node);
	}

	@Override
	protected boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
