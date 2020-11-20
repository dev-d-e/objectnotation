package com.github.dev.objectnotation.html;

import com.github.dev.objectnotation.DirectTextInvoker;
import com.github.dev.objectnotation.tree.BranchNode;
import com.github.dev.objectnotation.tree.Document;
import com.github.dev.objectnotation.tree.LeafNode;
import com.github.dev.objectnotation.tree.Node;
import com.github.dev.objectnotation.value.Entity;
import com.github.dev.objectnotation.value.PrimitiveTypeEntity;

/**
 * Converter.
 */
public class HtmlConverter {

	public static String convert(CharSequence charSequence) {
		Document document = DirectTextInvoker.accept(charSequence);
		TagHtml t = convert(document);
		return t.asHtml();
	}

	private static TagHtml convert(Document doc) {
		TagHtml t = new TagHtml();
		Node[] nodes = doc.nodes();
		if (nodes == null || nodes.length == 0) {
			return t;
		}
		Node node = null;
		if (nodes.length == 1) {
			node = nodes[0];
		} else {
			for (Node o : nodes) {
				if ("html".equalsIgnoreCase(o.getKey())) {
					node = o;
					break;
				}
			}
		}
		if (node == null) {
			return t;
		}
		if (node instanceof BranchNode) {
			((BranchNode) node).forEach(o -> fillTag(t, o));
		}
		return t;
	}

	private static void fillTag(Tag t, Node node) {
		String k = node.getKey().toLowerCase();
		if (node instanceof BranchNode) {
			Tag n = t.createTag(k);
			((BranchNode) node).forEach(o -> fillTag(n, o));
		} else if (node instanceof LeafNode) {
			Entity entity = ((LeafNode) node).getEntity();
			if (Attributes.isAttr(k) || t.isAttribute(k)) {
				if (entity != null && entity instanceof PrimitiveTypeEntity) {
					t.setAttribute(k, ((PrimitiveTypeEntity) entity).getValue());
				}
			} else {
				Tag n = t.createTag(k);
				if (entity != null) {
					if (entity instanceof PrimitiveTypeEntity) {
						n.setTagValue(((PrimitiveTypeEntity) entity).getValue());
					}
				}
			}
		}
	}

}
