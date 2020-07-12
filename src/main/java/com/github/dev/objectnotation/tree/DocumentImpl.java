package com.github.dev.objectnotation.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.github.dev.objectnotation.value.Entity;

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

	/**
	 * Mapped entities.
	 */
	private final Map<String, Entity> mappedEntities = new LinkedHashMap<>();

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
		if (!node.isBranch()) {
			mappedEntities.put(node.getKey(), node.getEntity());
		}
		return this;
	}

	@Override
	public Map<String, Entity> mappedEntities() {
		return Collections.unmodifiableMap(mappedEntities);
	}

	public Document addMapping(Node node) {
		if (node.isBranch()) {
			return this;
		}
		StringBuilder builder = new StringBuilder(node.getKey());
		Node p = node.getParent();
		while (p != null) {
			builder.insert(0, '.');
			builder.insert(0, p.getKey());
			p = p.getParent();
		}
		mappedEntities.put(builder.toString(), node.getEntity());
		return this;
	}

}
