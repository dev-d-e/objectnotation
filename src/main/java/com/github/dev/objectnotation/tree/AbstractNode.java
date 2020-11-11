package com.github.dev.objectnotation.tree;

/**
 * Abstract class for {@code Node}.
 */
abstract class AbstractNode implements Node {

	private BranchNode parent;

	private int offset;

	private String key;

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
	public BranchNode getParent() {
		return parent;
	}

	@Override
	public Node setParent(BranchNode node) {
		this.parent = node;
		return this;
	}

	@Override
	public int getOffset() {
		return offset;
	}

	@Override
	public Node getNodeByOffset(int seekoffset) {
		if (this.offset == seekoffset) {
			return this;
		}
		if (seekoffset > 0 && seekoffset < this.offset) {
			return getParent().getNodeByOffset(seekoffset);
		}
		return null;
	}

	@Override
	public String getKey() {
		return key;
	}

}
