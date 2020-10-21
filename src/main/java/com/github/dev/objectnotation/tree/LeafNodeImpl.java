package com.github.dev.objectnotation.tree;

import java.util.Iterator;

import com.github.dev.objectnotation.value.ArrayEntity;
import com.github.dev.objectnotation.value.Entity;
import com.github.dev.objectnotation.value.EntityFactory;

/**
 * Leaf node.
 */
class LeafNodeImpl extends AbstractNode {

	LeafNodeImpl(int offset, String key, Entity entity) {
		super(offset, key);
		setEntity(entity);
	}

	/**
	 * Returns false.
	 */
	@Override
	public boolean isBranch() {
		return false;
	}

	/**
	 * Returns null.
	 */
	@Override
	public Iterator<Node> iterator() {
		return null;
	}

	/**
	 * Returns null.
	 */
	@Override
	public Node[] toArray() {
		return null;
	}

	/**
	 * Returns 0.
	 */
	@Override
	public int size() {
		return 0;
	}

	/**
	 * Returns null.
	 */
	@Override
	public Node node(int index) {
		return null;
	}

	/**
	 * Do not add.
	 */
	@Override
	public Node add(Node node) {
		return null;
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
