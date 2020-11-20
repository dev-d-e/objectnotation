package com.github.dev.objectnotation.html;

/**
 * head
 */
class TagHead extends TagAbstractImpl {

	public TagHead() {
		super("head");
	}

	@Override
	public boolean isAttribute(String str) {
		return false;
	}

}
