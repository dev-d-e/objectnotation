package com.github.dev.objectnotation;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Set Bean.
 */
final class ObjectBuilder {

	/**
	 * Build T object by Target.
	 *
	 * @param target Target
	 * @param object T
	 * @return T
	 */
	static <T> T build(Target target, T object) {
		Objects.requireNonNull(object);
		if (object.getClass().getSimpleName().equalsIgnoreCase(target.getName())) {
			return buildListForObject(target.getValue(), object);
		}
		return buildForObject(target, object);
	}

	/**
	 * Build T object by Target.
	 *
	 * @param target Target
	 * @param type   Class
	 * @return T
	 */
	static <T> T build(Target target, Class<T> type) {
		Objects.requireNonNull(type);
		return buildByClass(target, type);
	}

	/**
	 * Build T object by Target.
	 */
	private static <T> T buildByClass(Target target, Class<T> type) {
		try {
			T o = type.getDeclaredConstructor().newInstance();
			if (type.getSimpleName().equalsIgnoreCase(target.getName())) {
				return buildListForObject(target.getValue(), o);
			}
			return buildForObject(target, o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * each target is a field in T object.
	 */
	private static <T> T buildListForObject(List<Target> targetList, T object) {
		targetList.forEach(t -> {
			buildForObject(t, object);
		});
		return object;
	}

	/**
	 * a target is a field in T object.
	 */
	private static <T> T buildForObject(Target target, T object) {
		ClassProperty p = new ClassProperty(object.getClass());
		p.getProperty(target.getName()).ifPresent(property -> {
			Class<?> c = property.getType();
			List<String> text = target.getText();
			if (text.size() > 0) {
				if (c.isArray()) {
					text.forEach(s -> {
						property.setForArrayByType(object, TextTypeAdapter.of(s));
					});
				} else if (Collection.class.isAssignableFrom(c)) {
					text.forEach(s -> {
						property.setForCollectionByType(object, TextTypeAdapter.of(s));
					});
				} else {
					text.forEach(s -> {
						property.invokeWriteMethodByType(object, TextTypeAdapter.of(s));
					});
				}
			} else {
				if (c.isArray()) {
					property.setForArray(object, buildByClass(target, c.getComponentType()));
				} else if (Collection.class.isAssignableFrom(c)) {
					property.setForCollection(object, t -> buildByClass(target, t));
				} else if (Map.class.isAssignableFrom(c)) {
					property.setForMap(object, t -> buildByClass(target, t));
				} else {
					property.setForBean(object, t -> buildByClass(target, t),
							o -> buildListForObject(target.getValue(), o));
				}
			}
		});
		return object;
	}

}
