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
		if (offset == (last.getOffset() + 1)) {
			node.setParent(last);
			last.add(node);
		} else {
			Node p = last;
			while (p != null && offset != p.getOffset()) {
				p = p.getParent();
			}
			if (p == null) {
				return;
			}
			node.setParent(p.getParent());
			p.getParent().add(node);
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
		if (offset == (last.getOffset() + 1)) {
			node.setParent(last);
			last.add(node);
		} else {
			Node p = last;
			while (p != null && offset != p.getOffset()) {
				p = p.getParent();
			}
			if (p == null) {
				return;
			}
			node.setParent(p.getParent());
			p.getParent().add(node);
		}
		document.addMapping(node);
		last = node;
	}

}
