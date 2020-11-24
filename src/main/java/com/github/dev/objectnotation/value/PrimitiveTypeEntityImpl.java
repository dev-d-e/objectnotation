package com.github.dev.objectnotation.value;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Primitive type entity.
 */
class PrimitiveTypeEntityImpl implements PrimitiveTypeEntity {

	private String value;

	@Override
	public boolean booleanValue() {
		return Boolean.parseBoolean(value);
	}

	@Override
	public byte byteValue() {
		return Byte.parseByte(value);
	}

	@Override
	public short shortValue() {
		return Short.parseShort(value);
	}

	@Override
	public int intValue() {
		return Integer.parseInt(value);
	}

	@Override
	public long longValue() {
		return Long.parseLong(value);
	}

	@Override
	public float floatValue() {
		return Float.parseFloat(value);
	}

	@Override
	public double doubleValue() {
		return Double.parseDouble(value);
	}

	@Override
	public BigInteger bigIntegerValue() {
		return new BigInteger(value);
	}

	@Override
	public BigDecimal bigDecimalValue() {
		return new BigDecimal(value);
	}

}
