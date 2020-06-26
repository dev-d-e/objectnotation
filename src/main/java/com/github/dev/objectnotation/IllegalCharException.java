package com.github.dev.objectnotation;

/**
 * Thrown when text error.
 */
public class IllegalCharException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a IllegalCharException with the specified detail message.
	 *
	 * @param s the detail message.
	 */
	public IllegalCharException(String s) {
		super(s);
	}

	/**
	 * Constructs a IllegalCharException.
	 * 
	 * @param row the row number.
	 * @param col the column number.
	 */
	public IllegalCharException(int row, int col) {
		super(new StringBuilder("illegal char is probably at(").append(row).append(',').append(col).append(')').toString());
	}

}
