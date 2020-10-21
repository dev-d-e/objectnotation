package com.github.dev.objectnotation.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * DocumentImpl.
 */
class DocumentImpl implements Document {

	/**
	 * Some configurations.
	 */
	private final Map<String, String> configuration = new LinkedHashMap<>();

	/**
	 * Some declared external resource keys.
	 */
	private final List<String> externalResources = new ArrayList<>();

	/**
	 * Trees.
	 */
	private final List<Node> nodes = new ArrayList<>();

	@Override
	public Map<String, String> configuration() {
		return Collections.unmodifiableMap(configuration);
	}

	public DocumentImpl configure(String k, String v) {
		configuration.put(k, v);
		return this;
	}

	@Override
	public String[] externalResources() {
		return externalResources.toArray(new String[externalResources.size()]);
	}

	public Document externalResource(String str) {
		externalResources.add(str);
		return this;
	}

	@Override
	public Node[] nodes() {
		return nodes.toArray(new Node[nodes.size()]);
	}

	@Override
	public Node node(int index) {
		Objects.checkIndex(index, nodes.size());
		return nodes.get(index);
	}

	@Override
	public Document add(Node node) {
		nodes.add(node);
		return this;
	}

	@Override
	public Node getNode(String str) {
		if (str == null) {
			return null;
		}
		return getNode(nodes.iterator(), str.split("\\."), 0);
	}

	private Node getNode(Iterator<Node> nodes, String[] keys, int index) {
		while (nodes.hasNext()) {
			Node node = nodes.next();
			if (node.getKey().equals(keys[index])) {
				index++;
				if (keys.length > index) {
					return getNode(node.iterator(), keys, index);
				} else {
					return node;
				}
			}
		}
		return null;
	}

}
