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
	 * '0'
	 */
	public static final int _0 = '0';

	/**
	 * '9'
	 */
	public static final int _9 = '9';

	/**
	 * ','
	 */
	public static final int COMMA = ',';

	public static boolean isCRLF(int i) {
		return i == CR || i == LF;
	}

	public static boolean isMark(int i) {
		return i == BRACKET || i == BACKSLASH;
	}

	public static boolean isDigit(int i) {
		return i >= _0 && i <= _9;
	}

	public static boolean validChar(int i) {
		return !invalidChar(i);
	}

	public static boolean invalidChar(int i) {
		return i == NUMBERSIGN || i == TILDE || i == COLON || isMark(i) || isCntrl(i);
	}

	private static boolean isCntrl(int i) {
		return (i >= 0 && i <= 31) || i == 127;
	}

}
