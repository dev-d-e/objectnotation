package com.github.dev.objectnotation.html;

import com.github.dev.objectnotation.tree.Node;

/**
 * 
 */
class TagCommon extends TagAbstractImpl {

	protected TagCommon(Node node) {
		super(node);
	}

	@Override
	protected boolean isAttribute(String str) {
		return false;
	}

}
