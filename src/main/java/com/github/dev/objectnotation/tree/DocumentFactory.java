package com.github.dev.objectnotation.tree;

import java.util.Objects;

import com.github.dev.objectnotation.Header;

/**
 * Document factory.
 */
public final class DocumentFactory {

	private final DocumentImpl document = new DocumentImpl();

	private boolean isBranch;
	private BranchNode lastBranch;
	private LeafNode lastLeaf;

	public Document getDocument() {
		return document;
	}

	private void addBranch(int offset, String key) {
		Objects.requireNonNull(key);
		if (offset < 0) {
			return;
		}
		BranchNode node = new BranchNodeImpl(offset, key);
		if (offset == 0) {
			document.add(node);
			lastBranch = node;
			isBranch = true;
			return;
		}
		if (isBranch) {
			if (offset == (lastBranch.getOffset() + 1)) {
				lastBranch.add(node);
			} else {
				Node p = lastBranch.getNodeByOffset(offset);
				if (p == null) {
					return;
				}
				p.getParent().add(node);
			}
		} else {
			Node p = lastLeaf.getNodeByOffset(offset);
			if (p == null) {
				return;
			}
			p.getParent().add(node);
		}
		lastBranch = node;
		isBranch = true;
	}

	private void addLeaf(int offset, String key, CharSequence cs) {
		Objects.requireNonNull(key);
		if (offset < 0) {
			return;
		}
		LeafNode node = new LeafNodeImpl(offset, key, cs);
		if (offset == 0) {
			document.add(node);
			lastLeaf = node;
			isBranch = false;
			return;
		}
		if (isBranch) {
			if (offset == (lastBranch.getOffset() + 1)) {
				lastBranch.add(node);
			} else {
				Node p = lastBranch.getNodeByOffset(offset);
				if (p == null) {
					return;
				}
				p.getParent().add(node);
			}
		} else {
			Node p = lastLeaf.getNodeByOffset(offset);
			if (p == null) {
				return;
			}
			p.getParent().add(node);
		}
		lastLeaf = node;
		isBranch = false;
	}

	public void addNode(int offset, String key, CharSequence cs) {
		if (cs == null || cs.length() == 0) {
			addBranch(offset, key);
		} else {
			addLeaf(offset, key, cs);
		}
	}

	public void add(Header header) {
		header.getConfiguration().forEach(s -> document.configure(s, s));
		header.getExternalResources().forEach(s -> document.externalResource(s));
	}

}
