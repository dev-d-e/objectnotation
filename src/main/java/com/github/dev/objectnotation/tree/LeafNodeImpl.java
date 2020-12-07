package com.github.dev.objectnotation.tree;

import java.util.Optional;

import com.github.dev.objectnotation.TextTypeAdapter;

/**
 * Leaf node.
 */
class LeafNodeImpl extends AbstractNode implements LeafNode {

	private String text;

	LeafNodeImpl(int offset, String key, CharSequence cs) {
		super(offset, key);
		setText(cs);
	}

	@Override
	public LeafNode setText(CharSequence cs) {
		this.text = (cs == null ? null : cs.toString());
		return this;
	}

	@Override
	public Optional<String> getText() {
		return Optional.ofNullable(text);
	}

	@Override
	public TextTypeAdapter getTypeAdapter() {
		return TextTypeAdapter.of(text);
	}

}
