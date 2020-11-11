package com.github.dev.objectnotation.tree;

import com.github.dev.objectnotation.value.ArrayEntity;
import com.github.dev.objectnotation.value.Entity;
import com.github.dev.objectnotation.value.EntityFactory;

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
	public Node setEntity(Entity entity) {
		this.entity = entity;
		return this;
	}

	@Override
	public Node addEntity(Entity entity) {
		Entity e = getEntity();
		if (e == null) {
			setEntity(entity);
		} else {
			if (e instanceof ArrayEntity) {
				((ArrayEntity) e).add(entity);
			} else {
				ArrayEntity arrayEntity = EntityFactory.createArrayEntity();
				arrayEntity.add(e);
				arrayEntity.add(entity);
				setEntity(arrayEntity);
			}
		}
		return this;
	}

}
