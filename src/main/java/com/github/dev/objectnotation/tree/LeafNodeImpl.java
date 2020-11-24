package com.github.dev.objectnotation.tree;

import com.github.dev.objectnotation.value.Entity;

/**
 * Leaf node.
 */
class LeafNodeImpl extends AbstractNode implements LeafNode {

	private Entity entity;

	LeafNodeImpl(int offset, String key, Entity entity) {
		super(offset, key);
		setEntity(entity);
	}

	@Override
	public Entity getEntity() {
		return entity;
	}

	@Override
	public LeafNode setEntity(Entity entity) {
		this.entity = entity;
		return this;
	}

}
