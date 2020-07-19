package com.github.dev.objectnotation.html;

/**
 * Abstract class for {@code Tag}.
 */
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.github.dev.objectnotation.tree.Node;
import com.github.dev.objectnotation.value.Entity;
import com.github.dev.objectnotation.value.PrimitiveTypeEntity;

abstract class TagAbstractImpl implements Tag {

	private String name;

	protected Map<String, String> attributes = new LinkedHashMap<>();

	protected List<Tag> tags = new ArrayList<>();

	protected StringBuilder value = new StringBuilder();

	protected TagAbstractImpl(Node node) {
		name = node.getKey();
		setNode(node.toArray());
		setEntity(node.getEntity());
	}

	@Override
	public String getTagName() {
		return name;
	}

	@Override
	public Tag setTagName(String tagName) {
		name = tagName;
		return this;
	}

	@Override
	public Tag setNode(Node[] nodes) {
		if (nodes == null) {
			return this;
		}
		for (Node node : nodes) {
			String k = node.getKey();
			if (isAttribute(k.toLowerCase())) {
				Entity attributeValue = node.getEntity();
				if (attributeValue != null && attributeValue instanceof PrimitiveTypeEntity) {
					put(k, ((PrimitiveTypeEntity) attributeValue).getValue());
				} else {
					put(k, "");
				}
			} else {
				Tag tag = createTag(k.toLowerCase(), node);
				if (tag != null) {
					tags.add(tag);
				}
			}
		}
		return this;
	}

	private void put(String key, String value) {
		String s = attributes.get(key);
		if (s != null && s.length() > 0) {
			if (value == null || value.isEmpty()) {
				return;
			}
			value = new StringBuilder(s).append(' ').append(value).toString();
		}
		attributes.put(key, value);
	}

	protected abstract boolean isAttribute(String str);

	protected abstract Tag createTag(String str, Node node);

	@Override
	public Tag setEntity(Entity entity) {
		if (entity == null) {
			return this;
		}
		if (entity instanceof PrimitiveTypeEntity) {
			value.append(((PrimitiveTypeEntity) entity).getValue());
		}
		return this;
	}

	@Override
	public String getAttribute(String name) {
		return attributes.get(name);
	}

	@Override
	public Map<String, String> getAttributeMap() {
		return attributes;
	}

	@Override
	public String getTagValue() {
		return value.length() == 0 ? "" : value.toString();
	}

	@Override
	public Tag[] toArray() {
		return tags.toArray(new Tag[tags.size()]);
	}

	@Override
	public String asHtml() {
		StringBuilder builder = new StringBuilder();
		builder.append('<');
		builder.append(name);
		for (Entry<String, String> entry : attributes.entrySet()) {
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
