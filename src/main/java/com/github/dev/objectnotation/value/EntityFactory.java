package com.github.dev.objectnotation.value;

/**
 * Entity factory.
 */
public class EntityFactory {

	public static PrimitiveTypeEntity createPrimitiveTypeEntity() {
		return new PrimitiveTypeEntityImpl();
	}

	public static ArrayEntity createArrayEntity() {
		return new ArrayEntityImpl();
	}

	public static QuoteEntity createQuoteEntity() {
		return new QuoteEntityImpl();
	}

}
