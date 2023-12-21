package com.github.dev.objectnotation;

/**
 * Represents an operation that accepts a char argument, and returns no result.
 */
@FunctionalInterface
public interface CharConsumer {

	/**
	 * Performs this operation on the given arguments.
	 *
	 * @param i the input argument
	 */
	void accept(char i);

}
