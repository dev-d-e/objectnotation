package com.github.dev.objectnotation.value;

/**
 * Object entity.
 */
public interface ObjectEntity extends Entity {

	/**
	 * Set a key and an entity.
	 * 
	 * @param key    a key.
	 * @param entity an entity.
	 */
	ObjectEntity set(String key, Entity entity);

}
