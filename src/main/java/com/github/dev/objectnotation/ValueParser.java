package com.github.dev.objectnotation;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

import com.github.dev.objectnotation.value.Entity;

/**
 * ValueParser.
 */
final class ValueParser {

	final Consumer<Entity> entityConsumer;
	final IntConsumer valueConsumer;

	final ParseValue value = new ParseValue(this);
	final ParseBackslash backslash = new ParseBackslash(this);
	final ParseQuote quote = new ParseQuote(this);
	final ParseArray array = new ParseArray(this);
	private IntToFunction currentFunction = value;

	/**
	 * Constructs a {@code ValueParser} with two consumers.
	 * 
	 * @param entityConsumer the consumer of the entity.
	 * @param valueConsumer  the consumer of the value.
	 */
	ValueParser(Consumer<Entity> entityConsumer, IntConsumer valueConsumer) {
		Objects.requireNonNull(entityConsumer);
		Objects.requireNonNull(valueConsumer);
		this.entityConsumer = entityConsumer;
		this.valueConsumer = valueConsumer;
	}

	public void apply(int i) {
		currentFunction = currentFunction.apply(i);
	}

}
