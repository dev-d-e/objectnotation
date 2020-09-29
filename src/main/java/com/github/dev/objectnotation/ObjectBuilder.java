package com.github.dev.objectnotation;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import com.github.dev.objectnotation.ClassProperty.Property;
import com.github.dev.objectnotation.tree.Document;
import com.github.dev.objectnotation.tree.Node;
import com.github.dev.objectnotation.value.ArrayEntity;
import com.github.dev.objectnotation.value.Entity;
import com.github.dev.objectnotation.value.PrimitiveTypeEntity;

public final class ObjectBuilder {

	public static <T> T build(Document document, T object) {
		Objects.requireNonNull(object);
		T o = buildForObject(document.nodes(), object);
		return o;
	}

	public static <T> T build(Document document, Class<T> type) {
		Objects.requireNonNull(type);
		T o = buildByClass(document.nodes(), type);
		return o;
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
			if (node.isBranch()) {
				if (c.isArray()) {
					Object aa = buildByClass(node.toArray(), c.getComponentType());
					property.setForArray(object, aa);
				} else if (Collection.class.isAssignableFrom(c)) {
					property.setForCollection(object, t -> buildByClass(node.toArray(), t));
				} else if (Map.class.isAssignableFrom(c)) {
					property.setForMap(object, t -> buildByClass(node.toArray(), t));
				} else {
					property.setForBean(object, t -> buildByClass(node.toArray(), t), o -> buildForObject(node.toArray(), o));
				}
			} else {
				Entity en = node.getEntity();
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
