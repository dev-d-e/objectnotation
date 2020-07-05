package com.github.dev.objectnotation.tree;

import java.util.Map;

/**
 * Document.
 */
public interface Document {

	/**
	 * Returns configuration collection.
	 */
	Map<String, String> configuration();

	/**
	 * Returns declared external resource array.
	 */
	String[] externalResources();

	/**
	 * Returns all root nodes.
	 */
	Node[] nodes();

	/**
	 * Returns node at the specified index position.
	 * 
	 * @param index the index of the node.
	 */
	Node node(int index);

	/**
	 * Add a node.
	 * 
	 * @param node a node.
	 */
	Document add(Node node);

}
