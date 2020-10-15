package com.github.dev.objectnotation;

import com.github.dev.objectnotation.value.Entity;

/**
 * Type consumer.
 */
interface TypeConsumer {

	void setEntity(Entity entity);

	/**
	 * Performs this operation on the given argument.
	 *
	 * @param i the input argument
	 */
	void accept(int i);

}
