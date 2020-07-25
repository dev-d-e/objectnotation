package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

import com.github.dev.objectnotation.tree.Node;

/**
 * body
 */
class TagBody extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("onafterprint");
		ATTRIBUTES.add("onbeforeprint");
		ATTRIBUTES.add("onbeforeunload");
		ATTRIBUTES.add("onhashchange");
		ATTRIBUTES.add("onlanguagechange");
		ATTRIBUTES.add("onmessage");
		ATTRIBUTES.add("onmessageerror");
		ATTRIBUTES.add("onoffline");
		ATTRIBUTES.add("ononline");
		ATTRIBUTES.add("onpagehide");
		ATTRIBUTES.add("onpageshow");
		ATTRIBUTES.add("onpopstate");
		ATTRIBUTES.add("onrejectionhandled");
		ATTRIBUTES.add("onstorage");
		ATTRIBUTES.add("onunhandledrejection");
		ATTRIBUTES.add("onunload");
	}

	protected TagBody(Node node) {
		super(node);
	}

	@Override
	protected boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
