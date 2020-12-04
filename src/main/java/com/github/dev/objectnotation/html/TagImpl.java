package com.github.dev.objectnotation.html;

/**
 * TagImpl {@code Tag}.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class TagImpl implements Tag {

	public static final TagImpl EMPTY = new TagImpl(null);

	private final String name;

	private final Map<String, String> attributeMap = new LinkedHashMap<>();

	private String value;

	private final List<Tag> tags = new ArrayList<>();

	public TagImpl(String name) {
		this.name = name;
	}

	@Override
	public String getTagName() {
		return name;
	}

	@Override
	public Tag setAttribute(String attrName, String attrValue) {
		String s = attributeMap.get(attrName);
		if (s != null && s.length() > 0) {
			if (attrValue == null || attrValue.isEmpty()) {
				return this;
			}
			attrValue = new StringBuilder(s).append(' ').append(attrValue).toString();
		}
		attributeMap.put(attrName, attrValue);
		return this;
	}

	@Override
	public Tag createTag(String name) {
		Tag t = new TagImpl(name);
		tags.add(t);
		return t;
	}

	@Override
	public String getAttribute(String name) {
		return attributeMap.get(name);
	}

	@Override
	public Map<String, String> getAttributeMap() {
		return Collections.unmodifiableMap(attributeMap);
	}

	@Override
	public String getTagValue() {
		return value == null ? "" : value;
	}

	@Override
	public Tag setTagValue(String tagValue) {
		value = tagValue;
		return this;
	}

	@Override
	public List<Tag> tags() {
		return tags;
	}

	@Override
	public String asHtml() {
		if (name == null) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		builder.append('<');
		builder.append(name);
		for (Entry<String, String> entry : attributeMap.entrySet()) {
			builder.append(' ');
			builder.append(entry.getKey());
			builder.append('=');
			String v = entry.getValue();
			if (v == null) {
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
		builder.append(getTagValue());
		for (Tag tag : tags) {
			builder.append(tag.asHtml());
		}
		builder.append('<');
		builder.append(name);
		builder.append("/>");
		return builder.toString();
	}

}
