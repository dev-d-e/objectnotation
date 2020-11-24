package com.github.dev.objectnotation.value;

import java.math.BigDecimal;
import java.math.BigInteger;

class EntityImpl implements Entity {

	private CharSequence value;

	private Exception exception;

	public EntityImpl(CharSequence cs) {
		this.value = cs;
	}

	public EntityImpl() {
	}

	@Override
	public Entity setValue(CharSequence cs) {
		this.value = cs;
		return this;
	}

	@Override
	public boolean isEmpty() {
		return value == null || value.length() == 0;
	}

	@Override
	public String getValue() {
		return value == null ? null : value.toString();
	}

	@Override
	public String toString() {
		return getValue();
	}

	@Override
	public boolean booleanValue() {
		try {
			return Boolean.parseBoolean(getValue());
		} catch (Exception e) {
			exception = e;
			return false;
		}
	}

	@Override
	public byte byteValue() {
		try {
			return Byte.parseByte(getValue());
		} catch (Exception e) {
			exception = e;
			return (byte) 0;
		}
	}

	@Override
	public short shortValue() {
		try {
			return Short.parseShort(getValue());
		} catch (Exception e) {
			exception = e;
			return (short) 0;
		}
	}

	@Override
	public int intValue() {
		try {
			return Integer.parseInt(getValue());
		} catch (Exception e) {
			exception = e;
			return 0;
		}
	}

	@Override
	public long longValue() {
		try {
			return Long.parseLong(getValue());
		} catch (Exception e) {
			exception = e;
			return (long) 0;
		}
	}

	@Override
	public float floatValue() {
		try {
			return Float.parseFloat(getValue());
		} catch (Exception e) {
			exception = e;
			return (float) 0.0;
		}
	}

	@Override
	public double doubleValue() {
		try {
			return Double.parseDouble(getValue());
		} catch (Exception e) {
			exception = e;
			return (double) 0.0;
		}
	}

	@Override
	public BigInteger bigIntegerValue() {
		try {
			return new BigInteger(getValue());
		} catch (Exception e) {
			exception = e;
			return BigInteger.ZERO;
		}
	}

	@Override
	public BigDecimal bigDecimalValue() {
		try {
			return new BigDecimal(getValue());
		} catch (Exception e) {
			exception = e;
			return BigDecimal.ZERO;
		}
	}

	@Override
	public Exception getException() {
		return exception;
	}

}
