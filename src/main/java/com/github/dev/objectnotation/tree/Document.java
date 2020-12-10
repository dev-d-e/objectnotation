package com.github.dev.objectnotation.tree;

import java.util.List;
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
	 * Returns declared external resource collection.
	 */
	List<String> externalResources();

	/**
	 * Returns all root nodes.
	 */
	List<Node> nodes();

	/**
	 * Add a node.
	 * 
	 * @param node a node.
	 */
	Document add(Node node);

	/**
	 * Returns node at a series of keys.
	 * 
	 * @param str some keys.
	 */
	List<Node> getNode(String str);

}
