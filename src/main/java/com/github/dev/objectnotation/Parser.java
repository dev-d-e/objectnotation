package com.github.dev.objectnotation;

import java.util.Objects;
import java.util.function.IntConsumer;

/**
 * Parser.
 */
final class Parser {

	/**
	 * counter
	 */
	int n = -1;

	/**
	 * row number
	 */
	private int row = 1;

	final IntStringConsumer keyConsumer;
	final IntConsumer valueConsumer;

	final HeaderReader header = new HeaderReader(this);
	final OffsetReader offset = new OffsetReader(this);
	final Key key = new Key(this);
	final Value value = new Value(this);
	final NextLineReader next = new NextLineReader(this);
	private IntToFunction currentFunction = header;

	/**
	 * Constructs a {@code Parser} with two consumers.
	 *
	 * @param keyConsumer   the consumer of the key.
	 * @param valueConsumer the consumer of the value.
	 */
	Parser(IntStringConsumer keyConsumer, IntConsumer valueConsumer) {
		Objects.requireNonNull(keyConsumer);
		Objects.requireNonNull(valueConsumer);
		this.keyConsumer = keyConsumer;
		this.valueConsumer = valueConsumer;
	}

	public void apply(int i) {
		n++;
		currentFunction = currentFunction.apply(i);
	}

	void addRow() {
		row++;
	}

	int getRow() {
		return row;
	}

}
