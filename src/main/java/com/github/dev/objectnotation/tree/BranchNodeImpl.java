package com.github.dev.objectnotation.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Branch node.
 */
class BranchNodeImpl extends AbstractNode implements BranchNode {

	private List<Node> nodes = new ArrayList<>();

	BranchNodeImpl(int offset, String key) {
		super(offset, key);
	}

	@Override
	public List<Node> nodes() {
		return nodes;
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
	public BranchNode add(Node node) {
		nodes.add(node);
		node.setParent(this);
		return this;
	}

	@Override
	public List<Node> node(String key) {
		return nodes.stream().filter(o -> o.getKey().equals(key)).collect(Collectors.toList());
	}

}
