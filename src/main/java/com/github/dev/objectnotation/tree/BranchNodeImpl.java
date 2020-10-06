package com.github.dev.objectnotation.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Branch node.
 */
class BranchNodeImpl extends AbstractNode {

	private List<Node> nodes = new ArrayList<>();

	BranchNodeImpl(int offset, String key) {
		super(offset, key);
	}

	@Override
	public boolean isBranch() {
		return true;
	}

	@Override
	public Iterator<Node> iterator() {
		return nodes.iterator();
	}

	@Override
	public Node[] toArray() {
		return nodes.toArray(new Node[nodes.size()]);
	}

	@Override
	public int size() {
		return nodes.size();
	}

	@Override
	public Node node(int index) {
		Objects.checkIndex(index, nodes.size());
		return nodes.get(index);
	}

	@Override
	public Node add(Node node) {
		nodes.add(node);
		return this;
	}

}
