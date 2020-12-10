package com.github.dev.objectnotation.html;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * HTML element.
 */
final class Element {

	private String name;

	private String text;

	private final Map<String, List<String>> attributes = new LinkedHashMap<>();

	private final List<Element> elements = new ArrayList<>();

	public Element() {
	}

	public Element(String name) {
		this.name = name;
	}

	/**
	 * Returns name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name.
	 */
	public Element setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Returns text.
	 */
	public String getText() {
		return text == null ? "" : text;
	}

	/**
	 * Set text.
	 */
	public Element setText(String text) {
		this.text = text;
		return this;
	}

	/**
	 * Returns attribute names.
	 */
	public Set<String> getAttrNames() {
		return attributes.keySet();
	}

	/**
	 * Returns attribute.
	 */
	public String getAttr(String attrName) {
		return joinAttr(attributes.get(attrName));
	}

	/**
	 * join attribute value with " ".
	 */
	private String joinAttr(List<String> attr) {
		if (attr == null || attr.isEmpty()) {
			return "";
		}
		if (attr.size() == 1) {
			return attr.get(0);
		}
		String str = "";
		for (String s : attr) {
			str = str + " " + s;
		}
		return str.substring(1);
	}

	/**
	 * Add attribute.
	 */
	public Element addAttr(String attrName, String attrValue) {
		List<String> v = attributes.get(attrName);
		if (v == null) {
			v = new ArrayList<>();
			attributes.put(attrName, v);
		}
		v.add(attrValue);
		return this;
	}

	/**
	 * Remove attribute.
	 */
	public Element removeAttr(String attrName) {
		attributes.remove(attrName);
		return this;
	}

	/**
	 * Returns child elements.
	 */
	public List<Element> getElements() {
		return elements;
	}

	/**
	 * Add child element.
	 */
	public Element addElement(Element element) {
		elements.add(element);
		return this;
	}

	/**
	 * Returns HTML String.
	 */
	public String make() {
		if (name == null || name.isEmpty()) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		builder.append('<');
		builder.append(name);
		for (Entry<String, List<String>> entry : attributes.entrySet()) {
			builder.append(' ');
			builder.append(entry.getKey());
			builder.append('=');
			String v = joinAttr(entry.getValue());
			if (v == null || v.isEmpty()) {
				builder.append("\"\"");
			} else if (v.indexOf(0) == '"' || v.indexOf(0) == '\'') {
				builder.append(v);
			} else {
				builder.append('"');
				builder.append(v);
				builder.append('"');
			}
		}
		builder.append('>');
		if (text != null) {
			builder.append(text);
		}
		for (Element e : elements) {
			builder.append(e.make());
		}
		builder.append('<');
		builder.append(name);
		builder.append("/>");
		return builder.toString();
	}

	@Override
	public String toString() {
		return new StringBuilder().append('<').append(name).append('>').toString();
	}

}
