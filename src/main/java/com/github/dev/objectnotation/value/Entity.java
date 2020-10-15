package com.github.dev.objectnotation.value;

/**
 * Entity.
 */
public interface Entity extends Cloneable {

	/**
	 * Performs this operation on the given argument.
	 *
	 * @param c the input argument
	 */
	Entity accept(char c);

	/**
	 * Finish the operation.
	 */
	void finish();

	/**
	 * Returns true if this entity contains no value.
	 *
	 * @return true if this entity contains no value
	 */
	boolean isEmpty();

}
