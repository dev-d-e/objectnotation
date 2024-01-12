package com.github.dev.objectnotation;

/**
 * Parse offset.
 */
final class Offset {

	/**
	 * max available value for next offset.
	 */
	private int available;

	/**
	 * offset.
	 */
	private int number;

	private StringBuilder builder;

	public int getAvailable() {
		return available;
	}

	public int getNumber() {
		return number;
	}

	public void pre() {
		builder = new StringBuilder();
	}

	public void accept(char c) {
		builder.append(c);
	}

	public boolean post() {
		String offsetStr = builder.toString();
		try {
			number = Integer.parseInt(offsetStr, 16);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
		if (number > available) {
			return false;
		}
		available = number + 1;
		return true;
	}
}
