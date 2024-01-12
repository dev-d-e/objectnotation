package com.github.dev.objectnotation;

/**
 * Some constants.
 */
public final class Constants {

	private Constants() {
	}

	/**
	 * '\r'
	 */
	public static final char CR = '\r';

	/**
	 * '\n'
	 */
	public static final char LF = '\n';

	/**
	 * '#'
	 */
	public static final char NUMBERSIGN = '#';

	/**
	 * '~'
	 */
	public static final char TILDE = '~';

	/**
	 * ':'
	 */
	public static final char COLON = ':';

	/**
	 * '|'
	 */
	public static final char VERTICAL = '|';

	/**
	 * '+'
	 */
	public static final char PLUS = '+';

	/**
	 * ' '
	 */
	public static final char SPACE = ' ';

	/**
	 * '\'
	 */
	public static final char BACKSLASH = '\\';

	/**
	 * 0
	 */
	public static final char END = 0;

	public static boolean isCRLF(char c) {
		return c == CR || c == LF;
	}

	public static boolean isDigit(char c) {
		return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
	}

}
