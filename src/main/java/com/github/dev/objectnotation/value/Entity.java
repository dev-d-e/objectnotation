package com.github.dev.objectnotation.value;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Entity.
 */
public interface Entity extends Cloneable {

	/**
	 * Set value.
	 * 
	 * @param cs the char sequence.
	 */
	Entity setValue(CharSequence cs);

	/**
	 * Returns true if this entity contains no value.
	 *
	 * @return true if this entity contains no value
	 */
	boolean isEmpty();

	/**
	 * Returns value.
	 */
	String getValue();

	/**
	 * Returns boolean value literals 'true' and 'false'.
	 */
	boolean booleanValue();

	/**
	 * Returns byte value, 0 for non-number.
	 */
	byte byteValue();

	/**
	 * Returns short value, 0 for non-number.
	 *
	 */
	short shortValue();

	/**
	 * Returns int value, 0 for non-number.
	 *
	 */
	int intValue();

	/**
	 * Returns long value, 0 for non-number.
	 */
	long longValue();

	/**
	 * Returns float value, 0.0 for non-number.
	 */
	float floatValue();

	/**
	 * Returns double value, 0.0 for non-number.
	 */
	double doubleValue();

	/**
	 * Returns {@link BigInteger} value, BigInteger.ZERO for non-number.
	 */
	BigInteger bigIntegerValue();

	/**
	 * Returns {@link BigDecimal} value, BigDecimal.ZERO for non-number.
	 */
	BigDecimal bigDecimalValue();

	/**
	 * Returns exception.
	 */
	Exception getException();

}
