package com.github.dev.objectnotation.html;

import java.util.Map;

import com.github.dev.objectnotation.tree.Node;
import com.github.dev.objectnotation.value.Entity;

/**
 * HTML Tag.
 */
interface Tag {

	/**
	 * Returns tag name.
	 */
	String getTagName();

	/**
	 * Set tag name.
	 */
	Tag setTagName(String tagName);

	/**
	 * Set all child nodes.
	 */
	Tag setNode(Node[] nodes);

	/**
	 * Set entity.
	 */
	Tag setEntity(Entity entity);

	/**
	 * Returns attribute.
	 */
	String getAttribute(String name);

	/**
	 * Returns all attributes.
	 */
	Map<String, String> getAttributeMap();

	/**
	 * Returns tag value.
	 */
	String getTagValue();

	/**
	 * Returns all child tags.
	 */
	Tag[] toArray();

	/**
	 * Returns HTML String.
	 */
	String asHtml();

}
