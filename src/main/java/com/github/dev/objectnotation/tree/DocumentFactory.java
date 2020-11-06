package com.github.dev.objectnotation.tree;

import java.util.Objects;

import com.github.dev.objectnotation.value.Entity;
import com.github.dev.objectnotation.value.QuoteEntity;

/**
 * Document factory.
 */
public class DocumentFactory {

	private final DocumentImpl document = new DocumentImpl();
	private Node last;

	public Document getDocument() {
		return document;
	}

	private void add(int offset, Node node) {
		if (offset == 0) {
			document.add(node);
			last = node;
			return;
		}
		if (offset == (last.getOffset() + 1)) {
			last.add(node);
		} else {
			Node p = last.getNodeByOffset(offset);
			if (p == null) {
				return;
			}
			p.getParent().add(node);
		}
		last = node;
	}

	private void addBranch(int offset, String key) {
		Objects.requireNonNull(key);
		if (offset < 0) {
			return;
		}
		add(offset, new BranchNodeImpl(offset, key));
	}

	private void addLeaf(int offset, String key, Entity entity) {
		Objects.requireNonNull(key);
		if (offset < 0) {
			return;
		}
		add(offset, new LeafNodeImpl(offset, key, entity));
	}

	private void addNode(int offset, String key, Node node) {
		Objects.requireNonNull(key);
		if (offset < 0 || node == null) {
			return;
		}
		if (node.isBranch()) {
			addBranch(offset, key);
			node.iterator().forEachRemaining(o -> last.add(o));
			while (node != null && node.size() > 0) {
				node = node.node(node.size() - 1);
			}
			last = node;
		} else {
			addLeaf(offset, key, node.getEntity());
		}
	}

	public void addNode(int offset, String key, Entity entity) {
		if (entity.isEmpty()) {
			addBranch(offset, key);
		} else if (entity instanceof QuoteEntity) {
			addNode(offset, key, document.getNode(((QuoteEntity) entity).getValue()));
		} else {
			addLeaf(offset, key, entity);
		}
	}

	public boolean notEqualsLast(int offset, String key, Entity entity) {
		if (last.getOffset() == offset && last.getKey().equals(key)) {
			last.addEntity(entity);
			return false;
		}
		return true;
	}

}
