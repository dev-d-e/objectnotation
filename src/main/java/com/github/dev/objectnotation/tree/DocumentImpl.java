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
	private final LinkedHashMap<String, List<Node>> nodes = new LinkedHashMap<>();

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
		List<Node> rst=new ArrayList<>();
		nodes.values().forEach(e->rst.addAll(e));
		return rst;
	}

	@Override
	public LinkedHashMap<String, List<Node>> getNodes() {
		return nodes;
	}

	@Override
	public Document add(Node node) {
		String k = node.getKey();
		List<Node> n = nodes.getOrDefault(k, new ArrayList<>());
		n.add(node);
		nodes.put(k, n);
		return this;
	}

	@Override
	public List<Node> getNode(String str) {
		if (str == null) {
			return Collections.emptyList();
		}
		String[] keys = str.split("\\.");
		int n = keys.length - 1;
		Stream<Node> st = nodes.get(keys[0]).stream();
		for (int i = 0; i < keys.length; i++) {
			String k = keys[i];
			st = st.filter(o -> o.getKey().equals(k));
			if (i < n) {
				st = st.flatMap(o -> o.getAll().stream());
			}
		}
		return st.collect(Collectors.toList());
	}

}
