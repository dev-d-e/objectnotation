package com.github.dev.objectnotation.html;

import java.util.List;
import java.util.Optional;

import com.github.dev.objectnotation.ResolveTextInvoker;
import com.github.dev.objectnotation.Target;

/**
 * Converter.
 */
public class HtmlConverter {

	/**
	 * Convert text to HTML String.
	 */
	public static String convert(CharSequence charSequence) {
		return convert(ResolveTextInvoker.accept(charSequence));
	}

	/**
	 * Convert List Target to HTML String.
	 */
	public static String convert(List<Target> targetList) {
		Optional<Target> op = targetList.stream().filter(o -> "html".equals(o.getName().toLowerCase())).findAny();
		if (op.isEmpty()) {
			return "";
		}
		return convert(op.get());
	}

	/**
	 * Convert Target to HTML String.
	 */
	public static String convert(Target target) {
		StringBuilder builder = new StringBuilder();
		convert(builder, target);
		return builder.toString();
	}

	/**
	 * Convert.
	 */
	private static void convert(StringBuilder builder, Target target) {
		String name = target.getName();
		if (name == null || name.isEmpty()) {
			return;
		}
		target.getText().forEach(t -> {
			addTag(builder, name, null, t);
		});
		StringBuilder attrbuilder = new StringBuilder();
		StringBuilder childbuilder = new StringBuilder();
		target.getValue().forEach(t -> {
			String c = t.getName();
			if ((Attributes.isAttr(c) && !isElement(name, c)) || ElementAttributes.isAttr(name, c)) {
				addAttribute(attrbuilder, c, t.getText());
			} else {
				convert(childbuilder, t);
			}
		});
		if (attrbuilder.length() > 0 || childbuilder.length() > 0) {
			addTag(builder, name, attrbuilder, childbuilder);
		}
	}

	/**
	 * append tag.
	 */
	private static void addTag(StringBuilder builder, String tagName, CharSequence attribute, CharSequence text) {
		builder.append('<');
		builder.append(tagName);
		if (attribute != null && attribute.length() > 0) {
			if (attribute.charAt(0) != ' ') {
				builder.append(' ');
			}
			builder.append(attribute);
		}
		builder.append('>');
		if (text != null && text.length() > 0) {
			builder.append(text);
		}
		builder.append('<');
		builder.append('/');
		builder.append(tagName);
		builder.append('>');
	}

	/**
	 * append attribute.
	 */
	private static void addAttribute(StringBuilder builder, String attributeName, List<String> attr) {
		builder.append(' ');
		builder.append(attributeName);
		builder.append('=');
		String attributeValue = joinAttr(attr);
		if (attributeValue == null || attributeValue.isEmpty()) {
			builder.append("\"\"");
		} else if (attributeValue.indexOf(0) == '"' || attributeValue.indexOf(0) == '\'') {
			builder.append(attributeValue);
		} else {
			builder.append('"');
			builder.append(attributeValue);
			builder.append('"');
		}
	}

	/**
	 * join attribute value with ' '.
	 */
	private static String joinAttr(List<String> attr) {
		if (attr == null || attr.isEmpty()) {
			return "";
		}
		if (attr.size() == 1) {
			return attr.get(0);
		}
		StringBuilder builder = new StringBuilder();
		attr.forEach(s -> {
			builder.append(' ');
			builder.append(s);
		});
		return builder.substring(1);
	}

	/**
	 * special case.
	 */
	private static boolean isElement(String element, String s) {
		if ("head".equals(element) && "title".equals(s)) {
			return true;
		}
		return false;
	}
}
