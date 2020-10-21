package com.github.dev.objectnotation.tree;

import com.github.dev.objectnotation.value.Entity;

/**
 * Node in a tree.
 */
public interface Node extends Cloneable, Iterable<Node> {

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
	 * Returns true if this node is branch node.
	 */
	boolean isBranch();

	/**
	 * Returns all child nodes.
	 */
	Node[] toArray();

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
	Node add(Node node);

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

	/**
	 * Returns entity.
	 * 
	 */
	Entity getEntity();

	/**
	 * Set entity.
	 * 
	 * @param entity the entity.
	 */
	Node setEntity(Entity entity);

	/**
	 * Add entity.
	 * 
	 * @param entity the entity.
	 */
	Node addEntity(Entity entity);

}
