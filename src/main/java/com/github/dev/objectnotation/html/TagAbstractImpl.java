package com.github.dev.objectnotation.html;

/**
 * Abstract class for {@code Tag}.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

abstract class TagAbstractImpl implements Tag {

	private final String name;

	protected final Map<String, String> attributeMap = new LinkedHashMap<>();

	protected String value;

	protected final List<Tag> tags = new ArrayList<>();

	public TagAbstractImpl(String name) {
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

	public abstract boolean isAttribute(String str);

	@Override
	public Tag createTag(String name) {
		Tag t = createTag0(name);
		tags.add(t);
		return t;
	}

	private Tag createTag0(String name) {
		switch (name) {
		case "a":
			return new TagA();
		case "abbr":
			return new TagCommon(name);
		case "address":
			return new TagCommon(name);
		case "area":
			return new TagArea();
		case "article":
			return new TagCommon(name);
		case "aside":
			return new TagCommon(name);
		case "audio":
			return new TagAudio();
		case "b":
			return new TagCommon(name);
		case "base":
			return new TagBase();
		case "bdi":
			return new TagCommon(name);
		case "bdo":
			return new TagCommon(name);
		case "blockquote":
			return new TagBlockquote();
		case "body":
			return new TagBody();
		case "br":
			return new TagCommon(name);
		case "button":
			return new TagButton();
		case "canvas":
			return new TagCanvas();
		case "caption":
			return new TagCommon(name);
		case "cite":
			return new TagCommon(name);
		case "code":
			return new TagCommon(name);
		case "col":
			return new TagCol(name);
		case "colgroup":
			return new TagCol(name);
		case "data":
			return new TagData();
		case "datalist":
			return new TagCommon(name);
		case "dd":
			return new TagCommon(name);
		case "del":
			return new TagDel();
		case "details":
			return new TagDetails();
		case "dfn":
			return new TagCommon(name);
		case "dialog":
			return new TagDialog();
		case "div":
			return new TagCommon(name);
		case "dl":
			return new TagCommon(name);
		case "dt":
			return new TagCommon(name);
		case "em":
			return new TagCommon(name);
		case "embed":
			return new TagEmbed();
		case "fieldset":
			return new TagFieldset();
		case "figcaption":
			return new TagCommon(name);
		case "figure":
			return new TagCommon(name);
		case "footer":
			return new TagCommon(name);
		case "form":
			return new TagForm();
		case "h1":
			return new TagCommon(name);
		case "h2":
			return new TagCommon(name);
		case "h3":
			return new TagCommon(name);
		case "h4":
			return new TagCommon(name);
		case "h5":
			return new TagCommon(name);
		case "h6":
			return new TagCommon(name);
		case "header":
			return new TagCommon(name);
		case "hgroup":
			return new TagCommon(name);
		case "hr":
			return new TagCommon(name);
		case "head":
			return new TagHead();
		case "i":
			return new TagCommon(name);
		case "iframe":
			return new TagIframe();
		case "img":
			return new TagImg();
		case "input":
			return new TagInput();
		case "ins":
			return new TagIns();
		case "kbd":
			return new TagCommon(name);
		case "label":
			return new TagLabel();
		case "legend":
			return new TagCommon(name);
		case "li":
			return new TagLi();
		case "link":
			return new TagLink();
		case "main":
			return new TagCommon(name);
		case "map":
			return new TagMap();
		case "mark":
			return new TagCommon(name);
		case "math":
			return new TagCommon(name);
		case "menu":
			return new TagCommon(name);
		case "meta":
			return new TagMeta();
		case "meter":
			return new TagMeter();
		case "nav":
			return new TagCommon(name);
		case "noscript":
			return new TagCommon(name);
		case "object":
			return new TagObject();
		case "ol":
			return new TagOl();
		case "optgroup":
			return new TagOptgroup();
		case "option":
			return new TagOption();
		case "output":
			return new TagOutput();
		case "p":
			return new TagCommon(name);
		case "param":
			return new TagParam();
		case "picture":
			return new TagCommon(name);
		case "pre":
			return new TagCommon(name);
		case "progress":
			return new TagProgress();
		case "q":
			return new TagQ();
		case "rp":
			return new TagCommon(name);
		case "rt":
			return new TagCommon(name);
		case "ruby":
			return new TagCommon(name);
		case "s":
			return new TagCommon(name);
		case "samp":
			return new TagCommon(name);
		case "script":
			return new TagScript();
		case "section":
			return new TagCommon(name);
		case "select":
			return new TagSelect();
		case "slot":
			return new TagSlot();
		case "small":
			return new TagCommon(name);
		case "source":
			return new TagSource();
		case "span":
			return new TagCommon(name);
		case "strong":
			return new TagCommon(name);
		case "style":
			return new TagStyle();
		case "sub":
			return new TagCommon(name);
		case "summary":
			return new TagCommon(name);
		case "sup":
			return new TagCommon(name);
		case "svg":
			return new TagCommon(name);
		case "table":
			return new TagCommon(name);
		case "tbody":
			return new TagCommon(name);
		case "td":
			return new TagTd();
		case "template":
			return new TagCommon(name);
		case "textarea":
			return new TagTextarea();
		case "tfoot":
			return new TagCommon(name);
		case "th":
			return new TagTh();
		case "thead":
			return new TagCommon(name);
		case "time":
			return new TagTime();
		case "title":
			return new TagCommon(name);
		case "tr":
			return new TagCommon(name);
		case "track":
			return new TagTrack();
		case "u":
			return new TagCommon(name);
		case "ul":
			return new TagCommon(name);
		case "var":
			return new TagCommon(name);
		case "video":
			return new TagVideo();
		case "wbr":
			return new TagCommon(name);
		default:
			return new TagCommon(name);
		}
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
