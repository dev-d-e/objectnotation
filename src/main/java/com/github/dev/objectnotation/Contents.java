package com.github.dev.objectnotation;

/**
 * Contents.
 */
public interface Contents {

	void preKey(int offset);

	void key(int i);

	void postKey();

	void preText();

	void text(int i);

	void textArray();

	void postText();

	void error(int row, int n);
}
