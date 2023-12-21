package com.github.dev.objectnotation;

import static com.github.dev.objectnotation.Constants.BACKSLASH;
import static com.github.dev.objectnotation.Constants.COLON;
import static com.github.dev.objectnotation.Constants.NUMBERSIGN;
import static com.github.dev.objectnotation.Constants.PLUS;
import static com.github.dev.objectnotation.Constants.SPACE;
import static com.github.dev.objectnotation.Constants.TILDE;
import static com.github.dev.objectnotation.Constants.VERTICAL;
import static com.github.dev.objectnotation.Constants.isCRLF;
import static com.github.dev.objectnotation.Constants.isDigit;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Parser.
 */
final class Parser {

	/**
	 * counter
	 */
	private int n = -1;

	/**
	 * row number
	 */
	private int row = 1;

	private CharConsumer currentFunction;

	private Consumer<Header> headerConsumer;

	private Offset offset;

	private Contents contents;

	/**
	 * Constructs a {@code Parser} with consumers.
	 *
	 * @param headerConsumer the consumer of the header.
	 * @param contents       the consumer of the key,text,error.
	 */
	Parser(Consumer<Header> headerConsumer, Contents contents) {
		Objects.requireNonNull(headerConsumer);
		Objects.requireNonNull(contents);
		this.headerConsumer = headerConsumer;
		this.contents = contents;

	}

	void apply(char i) {
		n++;
		currentFunction.accept(i);
	}

	/**
	 * parse structure.
	 */
	private StringBuilder builder;

	private CharConsumer acceptPreHeader;

	private CharConsumer acceptHeader;

	private CharConsumer headerNewLine;

	private Header header = new Header();

	private ParseNewLine newLine;

	private CharConsumer acceptPreOffset;

	private CharConsumer acceptOffset;

	private CharConsumer offsetNewLine;

	private CharConsumer offsetError;

	private CharConsumer acceptPreKey;

	private CharConsumer acceptKey;

	private CharConsumer keyBackslash;

	private CharConsumer keyError;

	private CharConsumer acceptPreText;

	private CharConsumer acceptText;

	private CharConsumer textNewLine;

	private CharConsumer textMore;

	private CharConsumer textMoreError;

	{
		acceptPreHeader = i -> {
			builder = new StringBuilder();
			if (i == NUMBERSIGN) {
				currentFunction = acceptHeader;
				return;
			}
			if (isCRLF(i)) {
				error();
				return;
			}
			currentFunction = acceptPreOffset;
			currentFunction.accept(i);
		};

		currentFunction = acceptPreHeader;

		acceptHeader = i -> {
			if (i == NUMBERSIGN) {
				return;
			}
			if (isCRLF(i)) {
				if (!header.isEmpty()) {
					headerConsumer.accept(header);
				}
				currentFunction = headerNewLine;
				currentFunction.accept(i);
				return;
			}
			builder.append(i);
			return;
		};

		headerNewLine = i -> {
			char[] s = newLine.accept(i);
			if (s.length > 0) {
				row++;
				n = 0;
			}
			if (isCRLF(i)) {
				return;
			}
			currentFunction = acceptPreHeader;
			currentFunction.accept(i);
		};

		acceptPreOffset = i -> {
			offset.pre();
			if (isDigit(i)) {
				currentFunction = acceptOffset;
				currentFunction.accept(i);
			} else if (isCRLF(i)) {
				currentFunction = offsetNewLine;
				currentFunction.accept(i);
			} else if (i == -1) {
				return;
			}
			currentFunction = offsetError;
			error();
		};

		acceptOffset = i -> {
			if (isDigit(i)) {
				offset.accept(i);
				return;
			} else if (i == TILDE) {
				if (offset.post()) {
					currentFunction = acceptPreKey;
					return;
				}
			} else if (isCRLF(i)) {
				error();
				currentFunction = offsetNewLine;
				currentFunction.accept(i);
			} else if (i == -1) {
				return;
			}
			currentFunction = offsetError;
			error();
		};

		offsetNewLine = i -> {
			char[] s = newLine.accept(i);
			if (s.length > 0) {
				row++;
				n = 0;
			}
			if (isCRLF(i)) {
				return;
			}
			currentFunction = acceptPreOffset;
			currentFunction.accept(i);
		};

		offsetError = i -> {
			if (isCRLF(i)) {
				currentFunction = offsetNewLine;
				currentFunction.accept(i);
			}
		};

		acceptPreKey = i -> {
			contents.preKey(offset.getNumber());
			if (i == COLON) {
				currentFunction = keyError;
				error();
				return;
			} else if (isCRLF(i)) {
				error();
				currentFunction = offsetNewLine;
				currentFunction.accept(i);
				return;
			}
			currentFunction = acceptKey;
			currentFunction.accept(i);
		};

		acceptKey = i -> {
			if (i == COLON) {
				contents.postKey();
				currentFunction = acceptPreText;
				return;
			} else if (i == BACKSLASH) {
				currentFunction = keyBackslash;
				return;
			} else if (isCRLF(i)) {
				contents.postKey();
				contents.postText();
				currentFunction = offsetNewLine;
				currentFunction.accept(i);
				return;
			} else if (i == -1) {
				contents.postKey();
				contents.postText();
				currentFunction = acceptPreOffset;
				return;
			}
			contents.key(i);
		};

		keyBackslash = i -> {
			if (isCRLF(i)) {
				currentFunction = acceptKey;
				currentFunction.accept(i);
			}
			contents.key(i);
			currentFunction = acceptKey;
		};

		keyError = i -> {
			if (isCRLF(i)) {
				currentFunction = acceptPreOffset;
				currentFunction.accept(i);
			}
		};

		acceptPreText = i -> {
			contents.preText();
			if (isCRLF(i)) {
				currentFunction = textNewLine;
				currentFunction.accept(i);
				return;
			}
			currentFunction = acceptText;
			currentFunction.accept(i);
		};

		acceptText = i -> {
			if (isCRLF(i)) {
				currentFunction = textNewLine;
				currentFunction.accept(i);
				return;
			} else if (i == -1) {
				currentFunction = textNewLine;
				currentFunction.accept(i);
				return;
			}
			contents.text(i);
		};

		textNewLine = i -> {
			if (i == SPACE) {
				return;
			}
			char[] s = newLine.accept(i);
			if (s.length > 0) {
				row++;
				n = 0;
			} else if (isCRLF(i)) {
				return;
			}
			if (s.length > 0 && i == VERTICAL) {
				for (int n = 0; n < s.length; n++) {
					contents.text(s[n]);
				}
			}
			if (i == VERTICAL || i == PLUS) {
				currentFunction = textMore;
				currentFunction.accept(i);
				return;
			}
			contents.postText();
			currentFunction = acceptPreOffset;
			currentFunction.accept(i);
		};

		textMore = i -> {
			if (i == VERTICAL) {
				currentFunction = acceptText;
				return;
			}
			if (i == PLUS) {
				contents.textArray();
				currentFunction = acceptText;
				return;
			}
			error();
			if (isCRLF(i)) {
				currentFunction = textNewLine;
				currentFunction.accept(i);
				return;
			}
			currentFunction = textMoreError;
		};

		textMoreError = i -> {
			if (isCRLF(i)) {
				currentFunction = textNewLine;
				currentFunction.accept(i);
			}
		};

	}

	private void error() {
		contents.error(row, n);
	}
}
