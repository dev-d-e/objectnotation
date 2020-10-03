package com.github.dev.objectnotation;

/**
 * Every line must has a offset number at beginning, then contact with '~'.
 */
final class ParseOffset implements IntToFunction {

	private final Parser parser;

	private StringBuilder builder;

	private int available = 1;

	private boolean isChildAvailable = true;

	private String offset = "0";

	private int offsetNumber = -1;

	ParseOffset(Parser parser) {
		this.parser = parser;
		builder = new StringBuilder(Constants.OFFSET_MAX_LENGTH);
	}

	@Override
	public IntToFunction apply(int i) {
		if (i == -1) {
			return this;
		}
		char c = (char) i;
		if (c >= '0' && c <= '9') {
			builder.append(c);
			if (parser.n > Constants.OFFSET_MAX_LENGTH) {
				builder = new StringBuilder(Constants.OFFSET_MAX_LENGTH);
				throw new IllegalCharException(parser.getRow(), parser.n);
			}
			return this;
		} else if (c == '~') {
			offset = builder.toString();
			builder = new StringBuilder(Constants.OFFSET_MAX_LENGTH);
			if (offset.isEmpty()) {
				throw new IllegalCharException(parser.getRow(), parser.n);
			}
			offsetNumber = Integer.parseInt(offset);
			if (isChildAvailable) {
				if (offsetNumber == 0 || offsetNumber == available) {
					setChildAvailable();
					return parser.key;
				} else {
					throw new IllegalCharException(parser.getRow(), parser.n);
				}
			} else {
				if (offsetNumber == 0 || offsetNumber <= available) {
					setChildAvailable();
					return parser.key;
				} else {
					throw new IllegalCharException(parser.getRow(), parser.n);
				}
			}
		} else {
			builder = new StringBuilder(Constants.OFFSET_MAX_LENGTH);
			throw new IllegalCharException(parser.getRow(), parser.n);
		}
	}

	/**
	 * Get offset number.
	 */
	int getOffsetNumber() {
		return offsetNumber;
	}

	/**
	 * Get length of offset String.
	 */
	int getOffsetLength() {
		return offset.length();
	}

	/**
	 * Set next available offset number as brother.
	 */
	void setBrotherAvailable() {
		if (available != offsetNumber) {
			this.available = offsetNumber;
			isChildAvailable = false;
		}
	}

	/**
	 * Set next available offset number as child.
	 */
	void setChildAvailable() {
		this.available = offsetNumber + 1;
		isChildAvailable = true;
	}

}