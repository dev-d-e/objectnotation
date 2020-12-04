package com.github.dev.objectnotation.tree;

import java.util.List;

/**
 * Branch node.
 */
public interface BranchNode extends Node {

	/**
	 * Returns all child nodes.
	 */
	List<Node> nodes();

	/**
	 * Returns the number of child nodes.
	 */
	int size();

	/**
	 * Returns node at the specified index position.
	 * 
	 * @param index the index of the node.
	 */
	Node node(int index);

	/**
	 * Add a child node.
	 * 
	 * @param node a child node.
	 */
	BranchNode add(Node node);

	/**
	 * Returns child nodes by key.
	 * 
	 * @param key the key of the node.
	 */
	List<Node> node(String key);

}
