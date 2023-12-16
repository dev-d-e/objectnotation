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
	 * ' '
	 */
	public static final int SPACE = ' ';

	/**
	 * '\'
	 */
	public static final int BACKSLASH = '\\';

	public static boolean isCRLF(int i) {
		return i == CR || i == LF;
	}

	public static boolean isDigit(int i) {
		return (i >= '0' && i <= '9') || (i >= 'A' && i <= 'F') || (i >= 'a' && i <= 'f');
	}

}
