package com.github.dev.objectnotation.value;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Primitive type entity.
 */
class PrimitiveTypeEntityImpl implements PrimitiveTypeEntity {

	private String value;

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public PrimitiveTypeEntity setValue(String value) {
		this.value = value;
		return this;
	}

	@Override
	public boolean booleanValue() {
		return false;
	}

	@Override
	public byte byteValue() {
		return 0;
	}

	@Override
	public short shortValue() {
		return 0;
	}

	@Override
	public int intValue() {
		return 0;
	}

	@Override
	public long longValue() {
		return 0;
	}

	@Override
	public float floatValue() {
		return 0;
	}

	@Override
	public double doubleValue() {
		return 0;
	}

	@Override
	public BigInteger bigIntegerValue() {
		return null;
	}

	@Override
	public BigDecimal bigDecimalValue() {
		return null;
	}

}
