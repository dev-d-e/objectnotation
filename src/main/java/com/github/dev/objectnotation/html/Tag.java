package com.github.dev.objectnotation.html;

import java.util.Map;

/**
 * HTML Tag.
 */
interface Tag {

	/**
	 * Returns tag name.
	 */
	String getTagName();

	/**
	 * Returns attribute.
	 */
	String getAttribute(String attrName);

	/**
	 * Returns all attributes.
	 */
	Map<String, String> getAttributeMap();

	/**
	 * Set attribute.
	 */
	Tag setAttribute(String attrName, String attrValue);

	/**
	 * Returns true if it's a attribute.
	 */
	boolean isAttribute(String str);

	/**
	 * Returns tag value.
	 */
	String getTagValue();

	/**
	 * Set tag value.
	 */
	Tag setTagValue(String tagValue);

	/**
	 * Create a child tag.
	 */
	Tag createTag(String tagName);

	/**
	 * Returns all child tags.
	 */
	Tag[] toArray();

	/**
	 * Returns HTML String.
	 */
	String asHtml();

}
