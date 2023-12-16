package com.github.dev.objectnotation.tree;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.github.dev.objectnotation.TextTypeAdapter;

/**
 * Abstract class for {@code Node}.
 */
class AbstractNode implements Node {

	private Node parent;

	private int offset;

	private String key;

	private List<String> text = new ArrayList<>();

	private LinkedHashMap<String, List<Node>> nodes = new LinkedHashMap<>();

	/**
	 * Constructor.
	 *
	 * @param offset the offset number.
	 * @param key    the key.
	 */
	AbstractNode(int offset, String key) {
		this.offset = offset;
		this.key = key;
	}

	AbstractNode(int offset, String key, CharSequence cs) {
		this(offset, key);
		setText(cs);
	}

	@Override
	public Node getParent() {
		return parent;
	}

	@Override
	public Node setParent(Node node) {
		this.parent = node;
		return this;
	}

	@Override
	public int getOffset() {
		return offset;
	}

	@Override
	public Node getParentByOffset(int seekoffset) {
		if (this.offset == seekoffset) {
			return getParent();
		}
		if (seekoffset > 0 && seekoffset < this.offset) {
			return getParent().getParentByOffset(seekoffset);
		}
		return null;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public Node add(Node node) {
		String k = node.getKey();
		List<Node> n = nodes.getOrDefault(k, new ArrayList<>());
		n.add(node);
		nodes.put(k, n);
		node.setParent(this);
		return this;
	}

	@Override
	public List<Node> getAll() {
		List<Node> rst=new ArrayList<>();
		nodes.values().forEach(e->rst.addAll(e));
		return rst;
	}

	@Override
	public LinkedHashMap<String, List<Node>> getNodes() {
		return nodes;
	}

	@Override
	public List<Node> get(String key) {
		return nodes.get(key).stream().filter(o -> o.getKey().equals(key)).collect(Collectors.toList());
	}

	@Override
	public Node setText(CharSequence cs) {
		if (cs != null) {
			this.text.add(cs.toString());
		}
		return this;
	}

	@Override
	public Optional<String> getText() {
		if (text.isEmpty()) {
			return Optional.ofNullable(null);
		}
		return Optional.ofNullable(text.get(0));
	}

	@Override
	public TextTypeAdapter getTypeAdapter() {
		return TextTypeAdapter.of(getText().get());
	}

}
