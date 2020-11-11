package com.github.dev.objectnotation.html;

/**
 * Abstract class for {@code Tag}.
 */
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.github.dev.objectnotation.tree.BranchNode;
import com.github.dev.objectnotation.tree.LeafNode;
import com.github.dev.objectnotation.tree.Node;
import com.github.dev.objectnotation.value.Entity;
import com.github.dev.objectnotation.value.PrimitiveTypeEntity;

abstract class TagAbstractImpl implements Tag {

	private String name;

	protected Map<String, String> attributeMap = new LinkedHashMap<>();

	protected List<Tag> tags = new ArrayList<>();

	protected StringBuilder value = new StringBuilder();

	protected TagAbstractImpl(Node node) {
		name = node.getKey();
		if (node instanceof BranchNode) {
			setNode(((BranchNode) node).toArray());
		}
		if (node instanceof LeafNode) {
			setEntity(((LeafNode) node).getEntity());
		}
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
			if (Attributes.isAttr(k.toLowerCase()) || isAttribute(k.toLowerCase())) {
				if (node instanceof LeafNode) {
					Entity attributeValue = ((LeafNode) node).getEntity();
					if (attributeValue != null && attributeValue instanceof PrimitiveTypeEntity) {
						put(k, ((PrimitiveTypeEntity) attributeValue).getValue());
					}
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
		String s = attributeMap.get(key);
		if (s != null && s.length() > 0) {
			if (value == null || value.isEmpty()) {
				return;
			}
			value = new StringBuilder(s).append(' ').append(value).toString();
		}
		attributeMap.put(key, value);
	}

	protected abstract boolean isAttribute(String str);

	protected Tag createTag(String str, Node node) {
		switch (str) {
		case "a":
			return new TagA(node);
		case "abbr":
			return new TagCommon(node);
		case "address":
			return new TagCommon(node);
		case "area":
			return new TagArea(node);
		case "article":
			return new TagCommon(node);
		case "aside":
			return new TagCommon(node);
		case "audio":
			return new TagAudio(node);
		case "b":
			return new TagCommon(node);
		case "base":
			return new TagBase(node);
		case "bdi":
			return new TagCommon(node);
		case "bdo":
			return new TagCommon(node);
		case "blockquote":
			return new TagBlockquote(node);
		case "br":
			return new TagCommon(node);
		case "button":
			return new TagButton(node);
		case "canvas":
			return new TagCanvas(node);
		case "caption":
			return new TagCommon(node);
		case "cite":
			return new TagCommon(node);
		case "code":
			return new TagCommon(node);
		case "col":
			return new TagCol(node);
		case "colgroup":
			return new TagCol(node);
		case "data":
			return new TagData(node);
		case "datalist":
			return new TagCommon(node);
		case "dd":
			return new TagCommon(node);
		case "del":
			return new TagDel(node);
		case "details":
			return new TagDetails(node);
		case "dfn":
			return new TagCommon(node);
		case "dialog":
			return new TagDialog(node);
		case "div":
			return new TagCommon(node);
		case "dl":
			return new TagCommon(node);
		case "dt":
			return new TagCommon(node);
		case "em":
			return new TagCommon(node);
		case "embed":
			return new TagEmbed(node);
		case "fieldset":
			return new TagFieldset(node);
		case "figcaption":
			return new TagCommon(node);
		case "figure":
			return new TagCommon(node);
		case "footer":
			return new TagCommon(node);
		case "form":
			return new TagForm(node);
		case "h1":
			return new TagCommon(node);
		case "h2":
			return new TagCommon(node);
		case "h3":
			return new TagCommon(node);
		case "h4":
			return new TagCommon(node);
		case "h5":
			return new TagCommon(node);
		case "h6":
			return new TagCommon(node);
		case "header":
			return new TagCommon(node);
		case "hgroup":
			return new TagCommon(node);
		case "hr":
			return new TagCommon(node);
		case "i":
			return new TagCommon(node);
		case "iframe ":
			return new TagIframe(node);
		case "img":
			return new TagImg(node);
		case "input":
			return new TagInput(node);
		case "ins":
			return new TagIns(node);
		case "kbd":
			return new TagCommon(node);
		case "label":
			return new TagLabel(node);
		case "legend":
			return new TagCommon(node);
		case "li":
			return new TagLi(node);
		case "link":
			return new TagLink(node);
		case "main":
			return new TagCommon(node);
		case "map":
			return new TagMap(node);
		case "mark":
			return new TagCommon(node);
		case "math":
			return new TagCommon(node);
		case "menu":
			return new TagCommon(node);
		case "meta":
			return new TagMeta(node);
		case "meter":
			return new TagMeter(node);
		case "nav":
			return new TagCommon(node);
		case "noscript":
			return new TagCommon(node);
		case "object":
			return new TagObject(node);
		case "ol":
			return new TagOl(node);
		case "optgroup":
			return new TagOptgroup(node);
		case "option":
			return new TagOption(node);
		case "output":
			return new TagOutput(node);
		case "p":
			return new TagCommon(node);
		case "param":
			return new TagParam(node);
		case "picture":
			return new TagCommon(node);
		case "pre":
			return new TagCommon(node);
		case "progress":
			return new TagProgress(node);
		case "q":
			return new TagQ(node);
		case "rp":
			return new TagCommon(node);
		case "rt":
			return new TagCommon(node);
		case "ruby":
			return new TagCommon(node);
		case "s":
			return new TagCommon(node);
		case "samp":
			return new TagCommon(node);
		case "script":
			return new TagScript(node);
		case "section":
			return new TagCommon(node);
		case "select":
			return new TagSelect(node);
		case "slot":
			return new TagSlot(node);
		case "small":
			return new TagCommon(node);
		case "source":
			return new TagSource(node);
		case "span":
			return new TagCommon(node);
		case "strong":
			return new TagCommon(node);
		case "style":
			return new TagStyle(node);
		case "sub":
			return new TagCommon(node);
		case "summary":
			return new TagCommon(node);
		case "sup":
			return new TagCommon(node);
		case "svg":
			return new TagCommon(node);
		case "table":
			return new TagCommon(node);
		case "tbody":
			return new TagCommon(node);
		case "td":
			return new TagTd(node);
		case "template":
			return new TagCommon(node);
		case "textarea":
			return new TagTextarea(node);
		case "tfoot":
			return new TagCommon(node);
		case "th":
			return new TagTh(node);
		case "thead":
			return new TagCommon(node);
		case "time":
			return new TagTime(node);
		case "title":
			return new TagCommon(node);
		case "tr":
			return new TagCommon(node);
		case "track":
			return new TagTrack(node);
		case "u":
			return new TagCommon(node);
		case "ul":
			return new TagCommon(node);
		case "var":
			return new TagCommon(node);
		case "video":
			return new TagVideo(node);
		case "wbr":
			return new TagCommon(node);
		default:
			return new TagCommon(node);
		}
	}

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
		return attributeMap.get(name);
	}

	@Override
	public Map<String, String> getAttributeMap() {
		return attributeMap;
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
