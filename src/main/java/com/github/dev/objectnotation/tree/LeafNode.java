package com.github.dev.objectnotation.tree;

import java.util.Optional;

import com.github.dev.objectnotation.TextTypeAdapter;

/**
 * Leaf node.
 */
public interface LeafNode extends Node {

	/**
	 * Set text.
	 * 
	 * @param text the text.
	 */
	LeafNode setText(CharSequence cs);

	/**
	 * Returns text.
	 */
	Optional<String> getText();

	/**
	 * Returns text's type adapter.
	 */
	TextTypeAdapter getTypeAdapter();

}
