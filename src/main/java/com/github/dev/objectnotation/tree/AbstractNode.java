package com.github.dev.objectnotation.tree;

import com.github.dev.objectnotation.value.Entity;

/**
 * Abstract class for {@code Node}.
 */
abstract class AbstractNode implements Node {

	private Node parent;

	private int offset;

	private String key;

	private Entity entity;

	/**
	 * Constructor.
	 *
	 * @param offset the offset number.
	 * @param key    the key.
	 */
	AbstractNode(int offset, String key) {
		this.offset = offset;
		this.key = key;
	}

	@Override
	public Node getParent() {
		return parent;
	}

	@Override
	public Node setParent(Node node) {
		this.parent = node;
		return this;
	}

	@Override
	public int getOffset() {
		return offset;
	}

	@Override
	public String getKey() {
		return key;
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

}
