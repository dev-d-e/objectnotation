package com.github.dev.objectnotation.html;

import java.util.List;
import java.util.Optional;

import com.github.dev.objectnotation.ResolveTextInvoker;
import com.github.dev.objectnotation.Target;

/**
 * Converter.
 */
public class HtmlConverter {

	public static String convert(CharSequence charSequence) {
		return convert(ResolveTextInvoker.accept(charSequence));
	}

	public static String convert(List<Target> doc) {
		Optional<Target> op = doc.stream().filter(o -> "html".equals(o.getName().toLowerCase())).findAny();
		if (op.isEmpty()) {
			return "";
		}
		return convert(op.get()).make();
	}

	private static Element convert(Target target) {
		Element e = new Element(target.getName());
		target.getValue().forEach(o -> fill(e, o));
		return e;
	}

	private static void fill(Element e, Target target) {
		String k = target.getName();
		if (Attributes.containsAttr(k)) {
			target.getValue().forEach(o -> o.getText().forEach(s -> e.addAttr(o.getName(), s)));
		} else if (Attributes.isAttr(k) || ElementAttributes.isAttr(e.getName(), k)) {
			target.getText().forEach(s -> e.addAttr(k, s));
		} else {
			Element c = createElement(k, e);
			target.getText().forEach(s -> c.setText(s));
			target.getValue().forEach(o -> fill(c, o));
		}
	}

	private static Element createElement(String name, Element p) {
		Element e = new Element(name);
		p.addElement(e);
		return e;
	}

}
