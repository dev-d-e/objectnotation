package com.github.dev.objectnotation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

/**
 * Node in a tree.
 */
final class Node {

	private Node parent;

	private int offset;

	private String key;

	private String text;

	private LinkedHashMap<String, List<Node>> nodes = new LinkedHashMap<>();

	/**
	 * Constructor.
	 *
	 * @param offset the offset number.
	 * @param key    the key.
	 * @param cs     the text.
	 */
	Node(int offset, String key, CharSequence cs) {
		this.offset = offset;
		this.key = key;
		if (cs != null && cs.length() > 0) {
			this.text = cs.toString();
		}
	}

	/**
	 * Returns parent node or null if it is the root node.
	 */
	Node getParent() {
		return parent;
	}

	/**
	 * Set parent node.
	 * 
	 * @param node the parent.
	 */
	Node setParent(Node node) {
		this.parent = node;
		return this;
	}

	/**
	 * Returns offset number.
	 */
	int getOffset() {
		return offset;
	}

	/**
	 * Returns node at the specified offset in ancestors.
	 * 
	 * @param node a node.
	 */
	Node getParentByOffset(int seekoffset) {
		if (this.offset == seekoffset) {
			return parent;
		}
		if (seekoffset > 0 && seekoffset < this.offset) {
			return parent.getParentByOffset(seekoffset);
		}
		return null;
	}

	/**
	 * Returns key.
	 */
	String getKey() {
		return key;
	}

	/**
	 * Add a child node.
	 * 
	 * @param node a child node.
	 */
	Node add(Node node) {
		String k = node.getKey();
		List<Node> n = nodes.getOrDefault(k, new ArrayList<>());
		n.add(node);
		nodes.put(k, n);
		node.setParent(this);
		return this;
	}

	/**
	 * Returns all child nodes.
	 */
	LinkedHashMap<String, List<Node>> getNodes() {
		return nodes;
	}

	/**
	 * Returns text.
	 */
	Optional<String> getText() {
		if (text == null || text.isEmpty()) {
			return Optional.ofNullable(null);
		}
		return Optional.ofNullable(text);
	}

}
