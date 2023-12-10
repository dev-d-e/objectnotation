package com.github.dev.objectnotation;

/**
 * Some constants.
 */
public final class Constants {

	private Constants() {
	}

	/**
	 * The maximum length.
	 */
	public static final int MAX_LENGTH = 64;

	/**
	 * The maximum length of offset.
	 */
	public static final int OFFSET_MAX_LENGTH = 10;

	/**
	 * '\r'
	 */
	public static final int CR = '\r';

	/**
	 * '\n'
	 */
	public static final int LF = '\n';

	/**
	 * '#'
	 */
	public static final int NUMBERSIGN = '#';

	/**
	 * '~'
	 */
	public static final int TILDE = '~';

	/**
	 * ':'
	 */
	public static final int COLON = ':';

	/**
	 * '|'
	 */
	public static final int VERTICAL = '|';

	/**
	 * '+'
	 */
	public static final int PLUS = '+';

	/**
	 * '['
	 */
	public static final int BRACKET = '[';

	/**
	 * '\'
	 */
	public static final int BACKSLASH = '\\';

	/**
	 * ' '
	 */
	public static final int SPACE = ' ';

	/**
	 * ','
	 */
	public static final int COMMA = ',';

	public static boolean isCRLF(int i) {
		return i == CR || i == LF;
	}

	public static boolean isDigit(int i) {
		return (i >= '0' && i <= '9') || (i >= 'A' && i <= 'F') || (i >= 'a' && i <= 'f');
	}

}
