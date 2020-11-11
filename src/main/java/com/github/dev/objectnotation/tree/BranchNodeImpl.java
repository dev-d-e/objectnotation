package com.github.dev.objectnotation.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Branch node.
 */
class BranchNodeImpl extends AbstractNode implements BranchNode {

	private List<Node> nodes = new ArrayList<>();

	BranchNodeImpl(int offset, String key) {
		super(offset, key);
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
	public Stream<Node> stream() {
		return nodes.stream();
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
	public Node[] node(String key) {
		return stream().filter(o -> o.getKey().equals(key)).toArray(i -> new Node[i]);
	}

}
