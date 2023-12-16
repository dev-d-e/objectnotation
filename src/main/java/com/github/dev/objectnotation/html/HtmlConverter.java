package com.github.dev.objectnotation.html;

import java.util.Optional;

import com.github.dev.objectnotation.ResolveTextInvoker;
import com.github.dev.objectnotation.tree.Document;
import com.github.dev.objectnotation.tree.Node;

/**
 * Converter.
 */
public class HtmlConverter {

	public static String convert(CharSequence charSequence) {
		return convert(ResolveTextInvoker.accept(charSequence));
	}

	public static String convert(Document doc) {
		Optional<Node> op = doc.nodes().stream().filter(o -> "html".equals(o.getKey().toLowerCase())).findAny();
		if (op.isEmpty()) {
			return "";
		}
		return convert(op.get()).make();
	}

	private static Element convert(Node node) {
		Element e = new Element(node.getKey());
		node.getAll().forEach(o -> fill(e, o));
		return e;
	}

	private static void fill(Element e, Node node) {
		String k = node.getKey();
		if (Attributes.containsAttr(k)) {
			node.getAll().forEach(o -> o.getText().ifPresent(s -> e.addAttr(o.getKey(), s)));
		} else if (Attributes.isAttr(k) || ElementAttributes.isAttr(e.getName(), k)) {
			node.getText().ifPresent(s -> e.addAttr(k, s));
		} else {
			Element c = createElement(k, e);
			node.getText().ifPresent(s -> c.setText(s));
			node.getAll().forEach(o -> fill(c, o));
		}
	}

	private static Element createElement(String name, Element p) {
		Element e = new Element(name);
		p.addElement(e);
		return e;
	}

}
