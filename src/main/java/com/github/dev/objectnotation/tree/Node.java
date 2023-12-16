package com.github.dev.objectnotation.tree;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import com.github.dev.objectnotation.TextTypeAdapter;

/**
 * Node in a tree.
 */
public interface Node extends Cloneable {

	/**
	 * Returns parent node or null if it is the root node.
	 */
	Node getParent();

	/**
	 * Set parent node.
	 * 
	 * @param node the parent.
	 */
	Node setParent(Node node);

	/**
	 * Returns offset number.
	 */
	int getOffset();

	/**
	 * Returns node at the specified offset in ancestors.
	 * 
	 * @param node a node.
	 */
	Node getParentByOffset(int seekoffset);

	/**
	 * Returns key.
	 */
	String getKey();

	/**
	 * Add a child node.
	 * 
	 * @param node a child node.
	 */
	Node add(Node node);

	/**
	 * Returns all child nodes.
	 */
	List<Node> getAll();

	/**
	 * Returns all child nodes.
	 */
	LinkedHashMap<String, List<Node>> getNodes();

	/**
	 * Returns child nodes by key.
	 * 
	 * @param key the key of the node.
	 */
	List<Node> get(String key);

	/**
	 * Set text.
	 * 
	 * @param text the text.
	 */
	Node setText(CharSequence cs);

	/**
	 * Returns text.
	 */
	Optional<String> getText();

	/**
	 * Returns text's type adapter.
	 */
	TextTypeAdapter getTypeAdapter();
}
