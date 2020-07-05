package com.github.dev.objectnotation.value;

/**
 * Entity factory.
 */
public class EntityFactory {

	public static PrimitiveTypeEntity createPrimitiveTypeEntity(String str) {
		return new PrimitiveTypeEntityImpl().setValue(str);
	}

	public static ArrayEntity createArrayEntity() {
		return new ArrayEntityImpl();
	}

	public static ObjectEntity createObjectEntity() {
		return new ObjectEntityImpl();
	}

}
