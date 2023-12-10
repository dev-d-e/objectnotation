package com.github.dev.objectnotation;

import static com.github.dev.objectnotation.Constants.BACKSLASH;
import static com.github.dev.objectnotation.Constants.COLON;
import static com.github.dev.objectnotation.Constants.COMMA;
import static com.github.dev.objectnotation.Constants.NUMBERSIGN;
import static com.github.dev.objectnotation.Constants.PLUS;
import static com.github.dev.objectnotation.Constants.SPACE;
import static com.github.dev.objectnotation.Constants.TILDE;
import static com.github.dev.objectnotation.Constants.VERTICAL;
import static com.github.dev.objectnotation.Constants.isCRLF;
import static com.github.dev.objectnotation.Constants.isDigit;

import java.util.Objects;
import java.util.function.BiConsumer;
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

	private IntStringConsumer keyConsumer;

	private IntConsumer textController;

	private IntConsumer textConsumer;

	private BiConsumer<Integer, Integer> errConsumer;

	/**
	 * Constructs a {@code Parser} with consumers.
	 *
	 * @param headerConsumer the consumer of the header.
	 * @param keyConsumer    the consumer of the key.
	 * @param textController the output controller.
	 * @param textConsumer   the consumer of the text.
	 * @param errConsumer    the consumer of the error.
	 */
	Parser(Consumer<Header> headerConsumer, IntStringConsumer keyConsumer, IntConsumer textController,
			IntConsumer textConsumer, BiConsumer<Integer, Integer> errConsumer) {
		Objects.requireNonNull(headerConsumer);
		Objects.requireNonNull(keyConsumer);
		Objects.requireNonNull(textController);
		Objects.requireNonNull(textConsumer);
		Objects.requireNonNull(errConsumer);
		this.headerConsumer = headerConsumer;
		this.keyConsumer = keyConsumer;
		this.textController = textController;
		this.textConsumer = textConsumer;
		this.errConsumer = errConsumer;
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

	private int offsetLength;

	private IntConsumer functionKey0;

	private IntConsumer functionKey1;

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
				currentFunction = headerParseNewLine;
				currentFunction.accept(i);
				return;
			}
			if (!header.isEmpty()) {
				headerConsumer.accept(header);
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
			} else {
				currentFunction = functionOffset2;
				errConsumer.accept(row, n);
			}
		};

		functionOffset1 = i -> {
			if (isDigit(i)) {
				builder.append((char) i);
				return;
			} else if (i == TILDE) {
				String offsetStr = builder.toString();
				offsetLength = offsetStr.length();
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
			errConsumer.accept(row, n);
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
			builder = new StringBuilder();
			if (isCRLF(i)) {
				errConsumer.accept(row, n);
				currentFunction = functionOffset2;
				currentFunction.accept(i);
			} else {
				currentFunction = functionKey1;
				currentFunction.accept(i);
			}
		};

		functionKey1 = i -> {
			if (i == COLON) {
				String k = builder.toString().trim();
				if (!k.isEmpty()) {
					keyConsumer.accept(offsetNumber, k);
					currentFunction = functionText0;
					return;
				}
			} else if (isCRLF(i)) {
				errConsumer.accept(row, n);
				currentFunction = functionOffset2;
				currentFunction.accept(i);
				return;
			}
			builder.append((char) i);
		};

		functionText0 = i -> {
			if (i > -1 && !isCRLF(i)) {
				offsetAvailable = offsetNumber;
			}
			currentFunction = functionText1;
			currentFunction.accept(i);
		};

		functionText1 = i -> {
			if (i == -1) {
				textController.accept(-1);
				currentFunction = functionOffset0;
				return;
			} else if (i == COMMA) {
				textController.accept(-2);
			} else if (i == BACKSLASH) {
				textConsumer.accept(i);
			} else if (isCRLF(i)) {
				currentFunction = textParseNewLine;
				currentFunction.accept(i);
			} else {
				textConsumer.accept(i);
			}
		};

		textParseNewLine = new ParseNewLine(i -> {
			textConsumer.accept(i);
			if (offsetAvailable > offsetNumber) {
				offsetAvailable = offsetNumber;
			}
		}, () -> row++, i -> {
			if (i == SPACE) {
				textParseNewLine.output();
			}
			n = 0;
			currentFunction = functionNext0;
			currentFunction.accept(i);
		});

		functionNext0 = i -> {
			if (i == -1) {
				textController.accept(-1);
				currentFunction = functionOffset0;
				return;
			}
			if (isDigit(i)) {
				textController.accept(-1);
				currentFunction = functionOffset0;
				currentFunction.accept(i);
				return;
			} else if (i == SPACE || i == VERTICAL || i == PLUS) {
				currentFunction = functionNext1;
				return;
			}
			currentFunction = functionNext1;
			currentFunction.accept(i);
		};

		functionNext1 = i -> {
			if (i == -1) {
				textController.accept(-1);
				currentFunction = functionOffset0;
				return;
			}
			if (i == SPACE) {
				if (n < offsetLength) {
					return;
				} else if (n == offsetLength) {
					currentFunction = functionText1;
					return;
				}
			} else if (i == VERTICAL || i == PLUS) {
				currentFunction = functionText1;
				return;
			}
			textController.accept(-1);
			errConsumer.accept(row, n);
			currentFunction = functionOffset2;
			currentFunction.accept(i);
		};

	}

}
