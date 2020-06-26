package com.github.dev.objectnotation;

/**
 * Parse key.
 */
final class Key implements IntToFunction {

	private final Parser parser;

	private StringBuilder builder;

	Key(Parser parser) {
		this.parser = parser;
		builder = new StringBuilder(Constants.KEY_MAX_LENGTH);
	}

	@Override
	public IntToFunction apply(int i) {
		if (i == -1) {
			throw new IllegalCharException(parser.getRow(), parser.n);
		}
		char c = (char) i;
		if (c == ':') {
			if (builder.length() < 1) {
				throw new IllegalCharException(parser.getRow(), parser.n);
			}
			String k = builder.toString();
			builder = new StringBuilder(Constants.KEY_MAX_LENGTH);
			k = k.trim();
			if (k.isEmpty()) {
				throw new IllegalCharException(parser.getRow(), parser.n);
			}
			parser.keyConsumer.accept(parser.offset.getOffsetNumber(), k);
			return parser.value;
		} else if (c == '\n' || c == '\r') {
			throw new IllegalCharException(parser.getRow(), parser.n);
		} else {
			builder.append(c);
			if (builder.length() > Constants.KEY_MAX_LENGTH) {
				throw new IllegalCharException(parser.getRow(), parser.n);
			}
			return this;
		}
	}

}
