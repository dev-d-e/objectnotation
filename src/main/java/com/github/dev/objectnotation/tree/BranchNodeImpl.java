package com.github.dev.objectnotation.tree;

import java.util.ArrayList;
import java.util.List;
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
	public BranchNode add(Node node) {
		nodes.add(node);
		node.setParent(this);
		return this;
	}

	@Override
	public List<Node> getAll() {
		return nodes;
	}

	@Override
	public List<Node> get(String key) {
		return nodes.stream().filter(o -> o.getKey().equals(key)).collect(Collectors.toList());
	}

	@Override
	public List<BranchNode> getBranch() {
		return nodes.stream().filter(o -> o instanceof BranchNode).map(o -> ((BranchNode) o)).collect(Collectors.toList());
	}

	@Override
	public List<LeafNode> getLeaf() {
		return nodes.stream().filter(o -> o instanceof LeafNode).map(o -> ((LeafNode) o)).collect(Collectors.toList());
	}

}
