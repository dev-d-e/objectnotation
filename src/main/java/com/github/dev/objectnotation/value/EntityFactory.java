package com.github.dev.objectnotation.value;

/**
 * Entity factory.
 */
public class EntityFactory {

	public static Entity createEntity(CharSequence cs) {
		return new EntityImpl(cs);
	}

}
