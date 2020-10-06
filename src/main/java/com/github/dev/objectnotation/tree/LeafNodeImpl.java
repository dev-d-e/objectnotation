package com.github.dev.objectnotation.tree;

import java.util.Iterator;

import com.github.dev.objectnotation.value.Entity;

/**
 * Leaf node.
 */
class LeafNodeImpl extends AbstractNode {

	LeafNodeImpl(int offset, String key, Entity entity) {
		super(offset, key);
		setEntity(entity);
	}

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

}
