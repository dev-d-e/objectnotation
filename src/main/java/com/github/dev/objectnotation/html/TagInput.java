package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

import com.github.dev.objectnotation.tree.Node;

/**
 * input
 */
class TagInput extends TagAbstractImpl {

	private static final Set<String> ATTRIBUTES = new HashSet<>();

	static {
		ATTRIBUTES.add("accept");
		ATTRIBUTES.add("alt");
		ATTRIBUTES.add("autocomplete");
		ATTRIBUTES.add("checked");
		ATTRIBUTES.add("dirname");
		ATTRIBUTES.add("disabled");
		ATTRIBUTES.add("form");
		ATTRIBUTES.add("formaction");
		ATTRIBUTES.add("formenctype");
		ATTRIBUTES.add("formmethod ");
		ATTRIBUTES.add("formnovalidate");
		ATTRIBUTES.add("formtarget");
		ATTRIBUTES.add("height");
		ATTRIBUTES.add("list");
		ATTRIBUTES.add("max");
		ATTRIBUTES.add("maxlength ");
		ATTRIBUTES.add("min");
		ATTRIBUTES.add("minlength");
		ATTRIBUTES.add("multiple");
		ATTRIBUTES.add("name");
		ATTRIBUTES.add("pattern");
		ATTRIBUTES.add("placeholder");
		ATTRIBUTES.add("readonly");
		ATTRIBUTES.add("required");
		ATTRIBUTES.add("size");
		ATTRIBUTES.add("src");
		ATTRIBUTES.add("step");
		ATTRIBUTES.add("type");
		ATTRIBUTES.add("value");
		ATTRIBUTES.add("width");
	}

	protected TagInput(Node node) {
		super(node);
	}

	@Override
	protected boolean isAttribute(String str) {
		return ATTRIBUTES.contains(str);
	}

}
