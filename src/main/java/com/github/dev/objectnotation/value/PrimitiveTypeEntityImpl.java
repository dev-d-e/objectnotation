package com.github.dev.objectnotation.value;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Primitive type entity.
 */
class PrimitiveTypeEntityImpl implements PrimitiveTypeEntity {

	private String value;

	private StringBuilder builder = new StringBuilder();

	@Override
	public PrimitiveTypeEntity accept(char c) {
		builder.append(c);
		return this;
	}

	@Override
	public void finish() {
		value = builder.toString();
	}

	@Override
	public boolean isEmpty() {
		return value == null || value.isEmpty();
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public PrimitiveTypeEntity accept(CharSequence cs) {
		builder.append(cs);
		return this;
	}

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
