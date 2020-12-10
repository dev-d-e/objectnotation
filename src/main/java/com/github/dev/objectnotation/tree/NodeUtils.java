package com.github.dev.objectnotation.tree;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * NodeUtils.
 */
public final class NodeUtils {

	private NodeUtils() {
	}

	public static void ifBranch(Node node, Consumer<BranchNode> c) {
		if (node instanceof BranchNode) {
			c.accept((BranchNode) node);
		}
	}

	public static Optional<BranchNode> ifBranch(Node node) {
		if (node instanceof BranchNode) {
			return Optional.ofNullable((BranchNode) node);
		}
		return Optional.empty();
	}

	public static void ifLeaf(Node node, Consumer<LeafNode> c) {
		if (node instanceof LeafNode) {
			c.accept((LeafNode) node);
		}
	}

	public static Optional<LeafNode> ifLeaf(Node node) {
		if (node instanceof LeafNode) {
			return Optional.ofNullable((LeafNode) node);
		}
		return Optional.empty();
	}

}
