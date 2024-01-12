package com.github.dev.objectnotation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Root.
 */
final class Root {

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

	private Node lastNode;

	List<Target> build() {
		return build(nodes);
	}

	List<Target> build(LinkedHashMap<String, List<Node>> nodes) {
		List<Target> rst = new ArrayList<>();
		nodes.forEach((k, v) -> {
			Target o = new Target(k);
			v.forEach(node -> {
				node.getText().ifPresent(s -> o.getText().add(s));
				build(node.getNodes()).forEach(e -> o.getValue().add(e));
			});
			rst.add(o);
		});
		return rst;
	}

	/**
	 * Returns configuration collection.
	 */
	Map<String, String> configuration() {
		return Collections.unmodifiableMap(configuration);
	}

	Root configure(String k, String v) {
		configuration.put(k, v);
		return this;
	}

	/**
	 * Returns declared external resource collection.
	 */
	List<String> externalResources() {
		return externalResources;
	}

	Root externalResource(String str) {
		externalResources.add(str);
		return this;
	}

	/**
	 * Returns all root nodes.
	 */
	LinkedHashMap<String, List<Node>> getNodes() {
		return nodes;
	}

	/**
	 * Add a node.
	 * 
	 * @param node a node.
	 */
	Root add(Node node) {
		String k = node.getKey();
		List<Node> n = nodes.getOrDefault(k, new ArrayList<>());
		n.add(node);
		nodes.put(k, n);
		return this;
	}

	void addNode(int offset, String key, CharSequence cs) {
		Objects.requireNonNull(key);
		if (offset < 0) {
			return;
		}
		Node node = new Node(offset, key, cs);
		if (offset == 0) {
			add(node);
			lastNode = node;
			return;
		}
		if (offset == (lastNode.getOffset() + 1)) {
			lastNode.add(node);
		} else {
			Node p = lastNode.getParentByOffset(offset);
			if (p == null) {
				return;
			}
			p.add(node);
		}
		lastNode = node;
	}

	void add(Header header) {
		header.getConfiguration().forEach(s -> configure(s, s));
		header.getExternalResources().forEach(s -> externalResource(s));
	}

}
