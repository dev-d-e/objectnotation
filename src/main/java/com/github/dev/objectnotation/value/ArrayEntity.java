package com.github.dev.objectnotation.value;

/**
 * Array entity.
 */
public interface ArrayEntity extends Iterable<Entity> {

	/**
	 * Returns the number of entities.
	 */
	int size();

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
