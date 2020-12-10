package com.github.dev.objectnotation.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	public List<String> externalResources() {
		return externalResources;
	}

	public Document externalResource(String str) {
		externalResources.add(str);
		return this;
	}

	@Override
	public List<Node> nodes() {
		return nodes;
	}

	@Override
	public Document add(Node node) {
		nodes.add(node);
		return this;
	}

	@Override
	public List<Node> getNode(String str) {
		if (str == null) {
			return Collections.emptyList();
		}
		String[] keys = str.split("\\.");
		int n = keys.length - 1;
		Stream<Node> st = nodes.stream();
		for (int i = 0; i < keys.length; i++) {
			String k = keys[i];
			st = st.filter(o -> o.getKey().equals(k));
			if (i < n) {
				st = st.filter(o -> o instanceof BranchNode).flatMap(o -> ((BranchNode) o).getAll().stream());
			}
		}
		return st.collect(Collectors.toList());
	}

}
