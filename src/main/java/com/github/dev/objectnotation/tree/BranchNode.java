package com.github.dev.objectnotation.tree;

import java.util.List;

/**
 * Branch node.
 */
public interface BranchNode extends Node {

	/**
	 * Add a child node.
	 * 
	 * @param node a child node.
	 */
	BranchNode add(Node node);

	/**
	 * Returns all child nodes.
	 */
	List<Node> getAll();

	/**
	 * Returns child nodes by key.
	 * 
	 * @param key the key of the node.
	 */
	List<Node> get(String key);

	/**
	 * Returns child branch nodes.
	 */
	List<BranchNode> getBranch();

	/**
	 * Returns child leaf nodes.
	 */
	List<LeafNode> getLeaf();

}
