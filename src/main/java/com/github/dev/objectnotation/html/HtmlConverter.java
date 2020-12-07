package com.github.dev.objectnotation.html;

import java.util.List;

import com.github.dev.objectnotation.DirectTextInvoker;
import com.github.dev.objectnotation.tree.BranchNode;
import com.github.dev.objectnotation.tree.Document;
import com.github.dev.objectnotation.tree.LeafNode;
import com.github.dev.objectnotation.tree.Node;

/**
 * Converter.
 */
public class HtmlConverter {

	public static String convert(CharSequence charSequence) {
		Document document = DirectTextInvoker.accept(charSequence);
		TagImpl t = convert(document);
		return t.asHtml();
	}

	private static TagImpl convert(Document doc) {
		List<Node> nodes = doc.nodes();
		if (nodes == null || nodes.isEmpty()) {
			return TagImpl.EMPTY;
		}
		Node node = null;
		if (nodes.size() == 1) {
			node = nodes.get(0);
		} else {
			for (Node o : nodes) {
				if ("html".equalsIgnoreCase(o.getKey())) {
					node = o;
					break;
				}
			}
		}
		if (node == null) {
			return TagImpl.EMPTY;
		}
		TagImpl t = new TagImpl(node.getKey());
		if (node instanceof BranchNode) {
			((BranchNode) node).nodes().forEach(o -> fillTag(t, o));
		}
		return t;
	}

	private static void fillTag(Tag t, Node node) {
		String k = node.getKey();
		if (node instanceof BranchNode) {
			if (Attributes.containsAttr(k)) {
				((BranchNode) node).nodes().stream().filter(o -> o instanceof LeafNode).map(o -> ((LeafNode) o)).forEach(o -> o.getText().ifPresent(s -> t.setAttribute(o.getKey(), s)));
			} else {
				Tag n = t.createTag(k);
				((BranchNode) node).nodes().forEach(o -> fillTag(n, o));
			}
		} else if (node instanceof LeafNode) {
			if (Attributes.isAttr(k) || ElementAttributes.isAttribute(t.getTagName(), k)) {
				((LeafNode) node).getText().ifPresent(s -> t.setAttribute(k, s));
			} else {
				Tag n = t.createTag(k);
				((LeafNode) node).getText().ifPresent(s -> n.setTagValue(s));
			}
		}
	}

}
