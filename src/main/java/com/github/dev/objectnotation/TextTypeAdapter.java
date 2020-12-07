package com.github.dev.objectnotation;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

/**
 * TextTypeAdapter.
 */
public class TextTypeAdapter {

	private String text;

	private Exception exception;

	private TextTypeAdapter() {
	}

	public static TextTypeAdapter of(String str) {
		return new TextTypeAdapter().setText(str);
	}

	/**
	 * Set text.
	 * 
	 * @param cs the char sequence.
	 */
	public TextTypeAdapter setText(String str) {
		this.text = str;
		return this;
	}

	/**
	 * Returns text.
	 */
	public Optional<String> getText() {
		return Optional.ofNullable(text);
	}

	/**
	 * Returns true if it contains no text.
	 */
	public boolean isEmpty() {
		return text == null || text.isEmpty();
	}

	/**
	 * Returns boolean text literals 'true' and 'false'.
	 */
	public boolean toBoolean() {
		try {
			return Boolean.parseBoolean(text);
		} catch (Exception e) {
			exception = e;
			return false;
		}
	}

	/**
	 * Returns char.
	 */
	public char toChar() {
		return isEmpty() ? (char) 0 : text.charAt(0);
	}

	/**
	 * Returns byte, 0 for non-number.
	 */
	public byte toByte() {
		try {
			return Byte.parseByte(text);
		} catch (Exception e) {
			exception = e;
			return (byte) 0;
		}
	}

	/**
	 * Returns short, 0 for non-number.
	 *
	 */
	public short toShort() {
		try {
			return Short.parseShort(text);
		} catch (Exception e) {
			exception = e;
			return (short) 0;
		}
	}

	/**
	 * Returns int, 0 for non-number.
	 *
	 */
	public int toInt() {
		try {
			return Integer.parseInt(text);
		} catch (Exception e) {
			exception = e;
			return 0;
		}
	}

	/**
	 * Returns long, 0 for non-number.
	 */
	public long toLong() {
		try {
			return Long.parseLong(text);
		} catch (Exception e) {
			exception = e;
			return (long) 0;
		}
	}

	/**
	 * Returns float, 0.0 for non-number.
	 */
	public float toFloat() {
		try {
			return Float.parseFloat(text);
		} catch (Exception e) {
			exception = e;
			return (float) 0.0;
		}
	}

	/**
	 * Returns double, 0.0 for non-number.
	 */
	public double toDouble() {
		try {
			return Double.parseDouble(text);
		} catch (Exception e) {
			exception = e;
			return (double) 0.0;
		}
	}

	/**
	 * Returns {@link BigInteger}, BigInteger.ZERO for non-number.
	 */
	public BigInteger toBigInteger() {
		try {
			return new BigInteger(text);
		} catch (Exception e) {
			exception = e;
			return BigInteger.ZERO;
		}
	}

	/**
	 * Returns {@link BigDecimal}, BigDecimal.ZERO for non-number.
	 */
	public BigDecimal toBigDecimal() {
		try {
			return new BigDecimal(text);
		} catch (Exception e) {
			exception = e;
			return BigDecimal.ZERO;
		}
	}

	/**
	 * Returns true if it contains exception.
	 */
	public boolean containsException() {
		return exception != null;
	}

	/**
	 * Returns exception.
	 */
	public Exception getException() {
		return exception;
	}

	/**
	 * Returns object, null if it's not primitive.
	 * 
	 * @param type the text's Class.
	 */
	public Optional<Object> toAnother(Class<?> type) {
		Object object = null;
		if (boolean.class == type || Boolean.TYPE == type) {
			object = toBoolean();
		} else if (char.class == type || Character.TYPE == type) {
			object = toChar();
		} else if (byte.class == type || Byte.TYPE == type) {
			object = toByte();
		} else if (short.class == type || Short.TYPE == type) {
			object = toShort();
		} else if (int.class == type || Integer.TYPE == type) {
			object = toInt();
		} else if (long.class == type || Long.TYPE == type) {
			object = toLong();
		} else if (float.class == type || Float.TYPE == type) {
			object = toFloat();
		} else if (double.class == type || Double.TYPE == type) {
			object = toDouble();
		} else if (BigInteger.class == type) {
			object = toBigInteger();
		} else if (BigDecimal.class == type) {
			object = toBigDecimal();
		} else if (String.class == type) {
			object = text;
		}
		return Optional.ofNullable(object);
	}

}
