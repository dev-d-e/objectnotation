package com.github.dev.objectnotation.html;

/**
 * 
 */
class TagCommon extends TagAbstractImpl {

	public TagCommon(String name) {
		super(name);
	}

	@Override
	public boolean isAttribute(String str) {
		return false;
	}

}
