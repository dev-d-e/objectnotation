package com.github.dev.objectnotation.tree;

import com.github.dev.objectnotation.value.Entity;

/**
 * Leaf node.
 */
public interface LeafNode extends Node {

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
