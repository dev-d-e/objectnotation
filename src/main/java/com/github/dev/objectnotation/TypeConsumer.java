package com.github.dev.objectnotation;

/**
 * Type consumer.
 */
interface TypeConsumer {

	void opt(int i);

	/**
	 * Performs this operation on the given argument.
	 *
	 * @param i the input argument
	 */
	void accept(int i);

}
