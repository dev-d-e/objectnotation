package com.github.dev.objectnotation;

/**
 * Contents.
 */
public interface Contents {

	/**
	 * Accept offset and prepare to accept key.
	 */
	void preKey(int offset);

	/**
	 * Accept char for key.
	 */
	void key(char c);

	/**
	 * End of key.
	 */
	void postKey();

	/**
	 * Prepare to accept text.
	 */
	void preText();

	/**
	 * Accept char for text.
	 */
	void text(char c);

	/**
	 * Prepare to accept next text in array.
	 */
	void textArray();

	/**
	 * End of text.
	 */
	void postText();

	/**
	 * Wrong char in the row and number.
	 */
	void error(int row, int n);

}
