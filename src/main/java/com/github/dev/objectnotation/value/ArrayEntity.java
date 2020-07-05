package com.github.dev.objectnotation.value;

/**
 * Array entity.
 */
public interface ArrayEntity extends Entity, Iterable<Entity> {

	/**
	 * Returns all entities.
	 */
	Entity[] toArray();

	/**
	 * Add an entity.
	 * 
	 * @param entity an entity.
	 */
	ArrayEntity add(Entity entity);

}
