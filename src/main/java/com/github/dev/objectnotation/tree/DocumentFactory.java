package com.github.dev.objectnotation.tree;

import java.util.Objects;

import com.github.dev.objectnotation.value.Entity;

/**
 * Document factory.
 */
public class DocumentFactory {

	private final DocumentImpl document = new DocumentImpl();
	private Node last;

	public Document getDocument() {
		return document;
	}

	public void addBranch(int offset, String key) {
		Objects.requireNonNull(key);
		if (offset < 0) {
			return;
		}
		if (offset == 0) {
			Node node = new BranchNodeImpl(offset, key);
			document.add(node);
			last = node;
			return;
		}
		Node node = new BranchNodeImpl(offset, key);
		if (offset == last.getOffset()) {
			node.setParent(last.getParent());
			last.getParent().add(node);
		} else {
			node.setParent(last);
			last.add(node);
		}
		last = node;
	}

	public void addLeaf(int offset, String key, Entity entity) {
		Objects.requireNonNull(key);
		Objects.requireNonNull(entity);
		if (offset < 0) {
			return;
		}
		if (offset == 0) {
			Node node = new LeafNodeImpl(offset, key, entity);
			document.add(node);
			last = node;
			return;
		}
		Node node = new LeafNodeImpl(offset, key, entity);
		if (offset == last.getOffset()) {
			node.setParent(last.getParent());
			last.getParent().add(node);
		} else {
			node.setParent(last);
			last.add(node);
		}
		document.addMapping(node);
		last = node;
	}

}
