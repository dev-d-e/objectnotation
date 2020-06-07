package com.github.dev.objectnotation;

/**
 * Represents a function that accepts a int-valued argument and produces a
 * {@code IntToFunction} result.
 */
@FunctionalInterface
public interface IntToFunction {

	/**
	 * Applies this function to the given argument.
	 *
	 * @param i the function argument
	 * @return the function result
	 */
	IntToFunction apply(int i);

}
