package com.github.dev.objectnotation;

/**
 * Parse offset.
 */
final class Offset {

	private int available;

	private int number;

	private StringBuilder builder;

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public StringBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(StringBuilder builder) {
		this.builder = builder;
	}

	public void pre() {
		builder = new StringBuilder();
	}

	public void accept(char c) {
		builder.append(c);
	}

	public boolean post() {
		String offsetStr = builder.toString();
		int offsetNumber = Integer.parseInt(offsetStr);
		if (offsetNumber > this.available) {
			return false;
		}
		this.available = offsetNumber + 1;
		return true;
	}
}
