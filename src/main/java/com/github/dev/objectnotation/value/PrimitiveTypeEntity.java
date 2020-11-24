package com.github.dev.objectnotation.value;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Primitive type entity.
 */
public interface PrimitiveTypeEntity {

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

}
