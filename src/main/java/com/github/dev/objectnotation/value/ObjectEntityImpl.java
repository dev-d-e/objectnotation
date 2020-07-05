package com.github.dev.objectnotation.value;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Object entity.
 */
class ObjectEntityImpl implements ObjectEntity {

	private Map<String, Entity> values = new LinkedHashMap<>();

	@Override
	public ObjectEntity set(String key, Entity entity) {
		values.put(key, entity);
		return this;
	}

}
