package com.github.dev.objectnotation;

/**
 * Contents.
 */
public interface Contents {

	void preKey(int offset);

	void key(char i);

	void postKey();

	void preText();

	void text(char i);

	void textArray();

	void postText();

	void error(int row, int n);

}
