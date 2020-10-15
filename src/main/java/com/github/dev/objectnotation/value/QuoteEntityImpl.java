package com.github.dev.objectnotation.value;

/**
 * Quote type entity.
 */
class QuoteEntityImpl implements QuoteEntity {

	private String value;

	private StringBuilder builder = new StringBuilder();

	@Override
	public QuoteEntity accept(char c) {
		builder.append(c);
		return this;
	}

	@Override
	public void finish() {
		value = builder.toString();
	}

	@Override
	public boolean isEmpty() {
		return value == null || value.isEmpty();
	}

	@Override
	public String getValue() {
		return value;
	}

}
