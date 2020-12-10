package com.github.dev.objectnotation;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.github.dev.objectnotation.ClassProperty.Property;
import com.github.dev.objectnotation.tree.BranchNode;
import com.github.dev.objectnotation.tree.Document;
import com.github.dev.objectnotation.tree.LeafNode;
import com.github.dev.objectnotation.tree.Node;
import com.github.dev.objectnotation.tree.NodeUtils;

final class ObjectBuilder {

	static <T> T build(Document document, T object) {
		Objects.requireNonNull(object);
		return buildForObject(document.nodes(), object);
	}

	static <T> T build(Document document, Class<T> type) {
		Objects.requireNonNull(type);
		return buildByClass(document.nodes(), type);
	}

	static <T> T build(List<Node> nodes, T object) {
		Objects.requireNonNull(object);
		return buildForObject(nodes, object);
	}

	static <T> T build(List<Node> nodes, Class<T> type) {
		Objects.requireNonNull(type);
		return buildByClass(nodes, type);
	}

	private static <T> T buildByClass(List<Node> nodes, Class<T> type) {
		try {
			T o = type.getDeclaredConstructor().newInstance();
			return buildForObject(nodes, o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static <T> T buildForObject(List<Node> nodes, T object) {
		ClassProperty p = new ClassProperty(object.getClass());
		nodes.forEach(node -> {
			p.getProperty(node.getKey()).ifPresent(property -> {
				NodeUtils.ifBranch(node, o -> buildForNode(object, property, o));
				NodeUtils.ifLeaf(node, o -> buildForNode(object, property, o));
			});
		});
		return object;
	}

	private static void buildForNode(Object object, Property property, BranchNode node) {
		Class<?> c = property.getType();
		List<Node> childNodes = node.getAll();
		if (c.isArray()) {
			property.setForArray(object, buildByClass(childNodes, c.getComponentType()));
		} else if (Collection.class.isAssignableFrom(c)) {
			property.setForCollection(object, t -> buildByClass(childNodes, t));
		} else if (Map.class.isAssignableFrom(c)) {
			property.setForMap(object, t -> buildByClass(childNodes, t));
		} else {
			property.setForBean(object, t -> buildByClass(childNodes, t), o -> buildForObject(childNodes, o));
		}
	}

	private static void buildForNode(Object object, Property property, LeafNode node) {
		Class<?> c = property.getType();
		if (c.isArray()) {
			property.setForArrayByType(object, node.getTypeAdapter());
		} else if (Collection.class.isAssignableFrom(c)) {
			property.setForCollectionByType(object, node.getTypeAdapter());
		} else {
			property.invokeWriteMethodByType(object, node.getTypeAdapter());
		}
	}

}
