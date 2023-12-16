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
import java.util.function.IntConsumer;

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

	private IntConsumer currentFunction;

	private Consumer<Header> headerConsumer;

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

	void apply(int i) {
		n++;
		currentFunction.accept(i);
	}

	/**
	 * parse structure.
	 */
	private StringBuilder builder;

	private IntConsumer functionHeader0;

	private IntConsumer functionHeader1;

	private Header header = new Header();

	private ParseNewLine headerParseNewLine;

	private IntConsumer functionOffset0;

	private IntConsumer functionOffset1;

	private IntConsumer functionOffset2;

	private int offsetAvailable = 1;

	private int offsetNumber = -1;

	private IntConsumer functionKey0;

	private IntConsumer functionKey1;

	private IntConsumer functionKeyBackslash;

	private IntConsumer functionText0;

	private IntConsumer functionText1;

	private ParseNewLine textParseNewLine;

	private IntConsumer functionNext0;

	private IntConsumer functionNext1;

	{
		functionHeader0 = i -> {
			builder = new StringBuilder();
			if (i == NUMBERSIGN) {
				currentFunction = functionHeader1;
				return;
			}
			if (isCRLF(i)) {
				error();
				return;
			}
			currentFunction = functionOffset0;
			currentFunction.accept(i);
		};

		currentFunction = functionHeader0;

		functionHeader1 = i -> {
			if (i == NUMBERSIGN) {
				return;
			}
			if (isCRLF(i)) {
				if (!header.isEmpty()) {
					headerConsumer.accept(header);
				}
				currentFunction = headerParseNewLine;
				currentFunction.accept(i);
				return;
			}
			builder.append((char) i);
			return;
		};

		headerParseNewLine = new ParseNewLine(() -> row++, i -> {
			if (builder.length() > 0) {
				String h = builder.toString().trim();
				if (!h.isEmpty()) {
					header.getConfiguration().add(h);
				}
			}
			n = 0;
			currentFunction = functionHeader0;
			currentFunction.accept(i);
		});

		functionOffset0 = i -> {
			builder = new StringBuilder();
			if (isDigit(i)) {
				currentFunction = functionOffset1;
				currentFunction.accept(i);
			} else if (isCRLF(i)) {
				currentFunction = functionOffset2;
				currentFunction.accept(i);
			} else if (i == -1) {
				return;
			}
			currentFunction = functionOffset2;
			error();
		};

		functionOffset1 = i -> {
			if (isDigit(i)) {
				builder.append((char) i);
				return;
			} else if (i == TILDE) {
				String offsetStr = builder.toString();
				offsetNumber = Integer.parseInt(offsetStr);
				if (offsetNumber == 0) {
					offsetAvailable = 1;
					currentFunction = functionKey0;
					return;
				} else if (offsetNumber > 0 && offsetNumber <= offsetAvailable) {
					offsetAvailable = offsetNumber + 1;
					currentFunction = functionKey0;
					return;
				}
				offsetAvailable = 0;
			} else if (isCRLF(i)) {
				currentFunction = functionOffset2;
				currentFunction.accept(i);
			}
			currentFunction = functionOffset2;
			error();
		};

		functionOffset2 = i -> {
			if (isCRLF(i)) {
				currentFunction = new ParseNewLine(() -> row++, j -> {
					n = 0;
					currentFunction = functionOffset0;
					currentFunction.accept(j);
				});
				currentFunction.accept(i);
			}
		};

		functionKey0 = i -> {
			contents.preKey(offsetNumber);
			if (isCRLF(i)) {
				error();
				currentFunction = functionOffset2;
				currentFunction.accept(i);
			} else {
				currentFunction = functionKey1;
				currentFunction.accept(i);
			}
		};

		functionKey1 = i -> {
			if (i == COLON) {
				contents.postKey();
				currentFunction = functionText0;
				return;
			} else if (i == BACKSLASH) {
				currentFunction = functionKeyBackslash;
				return;
			} else if (isCRLF(i)) {
				contents.postKey();
				contents.postText();
				currentFunction = functionOffset2;
				currentFunction.accept(i);
				return;
			} else if (i == -1) {
				contents.postKey();
				contents.postText();
				currentFunction = functionOffset0;
				return;
			}
			builder.append((char) i);
		};

		functionKeyBackslash = i -> {
			if (isCRLF(i)) {
				currentFunction = functionKey1;
				currentFunction.accept(i);
			}
			builder.append((char) i);
			currentFunction = functionKey1;
		};

		functionText0 = i -> {
			contents.preText();
			if (i > -1 && !isCRLF(i)) {
				offsetAvailable = offsetNumber;
			}
			currentFunction = functionText1;
			currentFunction.accept(i);
		};

		functionText1 = i -> {
			if (i == -1) {
				contents.postText();
				currentFunction = functionOffset0;
				return;
			} else if (isCRLF(i)) {
				currentFunction = textParseNewLine;
				currentFunction.accept(i);
			}
			contents.text(i);
		};

		textParseNewLine = new ParseNewLine(i -> {
			contents.text(i);
			if (offsetAvailable > offsetNumber) {
				offsetAvailable = offsetNumber;
			}
		}, () -> row++, i -> {
			if (i == VERTICAL) {
				textParseNewLine.output();
			}
			n = 0;
			currentFunction = functionNext0;
			currentFunction.accept(i);
		});

		functionNext0 = i -> {
			if (i == -1) {
				contents.postText();
				currentFunction = functionOffset0;
				return;
			}
			if (i == SPACE || i == VERTICAL || i == PLUS) {
				currentFunction = functionNext1;
				return;
			}
			contents.postText();
			currentFunction = functionOffset0;
			currentFunction.accept(i);
		};

		functionNext1 = i -> {
			if (i == -1) {
				contents.postText();
				currentFunction = functionOffset0;
				return;
			}
			if (i == SPACE) {
				return;
			} else if (i == VERTICAL || i == PLUS) {
				currentFunction = functionText1;
				return;
			}
			contents.postText();
			error();
			currentFunction = functionOffset2;
			currentFunction.accept(i);
		};

	}

	private void error() {
		contents.error(row, n);
	}
}
