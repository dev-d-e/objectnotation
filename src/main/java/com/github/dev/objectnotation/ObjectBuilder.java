package com.github.dev.objectnotation;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import com.github.dev.objectnotation.ClassProperty.Property;
import com.github.dev.objectnotation.tree.BranchNode;
import com.github.dev.objectnotation.tree.Document;
import com.github.dev.objectnotation.tree.LeafNode;
import com.github.dev.objectnotation.tree.Node;
import com.github.dev.objectnotation.value.ArrayEntity;
import com.github.dev.objectnotation.value.Entity;
import com.github.dev.objectnotation.value.PrimitiveTypeEntity;

final class ObjectBuilder {

	static <T> T build(Document document, T object) {
		Objects.requireNonNull(object);
		return buildForObject(document.nodes(), object);
	}

	static <T> T build(Document document, Class<T> type) {
		Objects.requireNonNull(type);
		return buildByClass(document.nodes(), type);
	}

	static <T> T build(Node[] nodes, T object) {
		Objects.requireNonNull(object);
		return buildForObject(nodes, object);
	}

	static <T> T build(Node[] nodes, Class<T> type) {
		Objects.requireNonNull(type);
		return buildByClass(nodes, type);
	}

	private static <T> T buildByClass(Node[] nodes, Class<T> type) {
		try {
			T o = type.getDeclaredConstructor().newInstance();
			return buildForObject(nodes, o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static <T> T buildForObject(Node[] nodes, T object) {
		ClassProperty p = new ClassProperty(object.getClass());
		for (Node node : nodes) {
			Property property = p.getProperty(node.getKey());
			if (property == null) {
				continue;
			}
			Class<?> c = property.getType();
			if (node instanceof BranchNode) {
				Node[] childNodes = ((BranchNode) node).toArray();
				if (c.isArray()) {
					property.setForArray(object, buildByClass(childNodes, c.getComponentType()));
				} else if (Collection.class.isAssignableFrom(c)) {
					property.setForCollection(object, t -> buildByClass(childNodes, t));
				} else if (Map.class.isAssignableFrom(c)) {
					property.setForMap(object, t -> buildByClass(childNodes, t));
				} else {
					property.setForBean(object, t -> buildByClass(childNodes, t), o -> buildForObject(childNodes, o));
				}
			} else if (node instanceof LeafNode) {
				Entity en = ((LeafNode) node).getEntity();
				if (en == null) {
					continue;
				}
				if (en instanceof PrimitiveTypeEntity) {
					if (c.isArray()) {
						property.setForArrayByEntity(object, (PrimitiveTypeEntity) en);
					} else if (Collection.class.isAssignableFrom(c)) {
						property.setForCollectionByEntity(object, (PrimitiveTypeEntity) en);
					} else {
						property.invokeWriteMethodByEntity(object, (PrimitiveTypeEntity) en);
					}
				} else if (en instanceof ArrayEntity) {
					if (c.isArray()) {
						property.setForArrayByEntity(object, (ArrayEntity) en);
					} else if (Collection.class.isAssignableFrom(c)) {
						property.setForCollectionByEntity(object, (ArrayEntity) en);
					}
				}
			}
		}
		return object;
	}

}
