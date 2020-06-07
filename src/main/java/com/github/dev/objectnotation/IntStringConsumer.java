package com.github.dev.objectnotation;

/**
 * Represents an operation that accepts a int-valued argument and a
 * string-valued argument, and returns no result.
 */
@FunctionalInterface
public interface IntStringConsumer {

	/**
	 * Performs this operation on the given arguments.
	 *
	 * @param i the first input argument
	 * @param s the second input argument
	 */
	void accept(int i, String s);

}
