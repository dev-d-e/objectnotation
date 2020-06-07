package com.github.dev.objectnotation;

/**
 * Thrown when text error.
 */
public class IllegalCharException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a IllegalCharException with no detail message.
	 */
	public IllegalCharException() {
		super();
	}

	/**
	 * Constructs a IllegalCharException with the specified detail message.
	 *
	 * @param s the detail message.
	 */
	public IllegalCharException(String s) {
		super(s);
	}

}
