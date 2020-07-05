package com.github.dev.objectnotation.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
	private Map<String, String> configuration = new HashMap<>();

	/**
	 * Some declared external resource keys.
	 */
	private List<String> externalResources = new ArrayList<>();

	/**
	 * Trees.
	 */
	private List<Node> nodes = new ArrayList<>();

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

}
