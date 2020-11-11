package com.github.dev.objectnotation.tree;

/**
 * Node in a tree.
 */
public interface Node extends Cloneable {

	/**
	 * Returns parent node or null if it is the root node.
	 */
	BranchNode getParent();

	/**
	 * Set parent node.
	 * 
	 * @param node the parent.
	 */
	Node setParent(BranchNode node);

	/**
	 * Returns offset number.
	 */
	int getOffset();

	/**
	 * Returns node at the specified offset in ancestors.
	 * 
	 * @param node a node.
	 */
	Node getNodeByOffset(int seekoffset);

	/**
	 * Returns key.
	 */
	String getKey();

}
