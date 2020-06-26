package com.github.dev.objectnotation;

/**
 * Header must has '#' at beginning.
 */
final class HeaderReader implements IntToFunction {

	private final Parser parser;

	private StringBuilder builder;

	private boolean hasCRLF = false;

	HeaderReader(Parser parser) {
		this.parser = parser;
		builder = new StringBuilder(Constants.KEY_MAX_LENGTH);
	}

	@Override
	public IntToFunction apply(int i) {
		if (i == -1) {
			return this;
		}
		char c = (char) i;
		if (hasCRLF && (c == '\n' || c == '\r')) {
			parser.n = -1;
			return this;
		}
		if (parser.n == 0) {
			if (c == '#') {
				return this;
			} else {
				return parser.offset.apply(i);
			}
		}
		if (c == '\n' || c == '\r') {
			hasCRLF = true;
			parser.n = -1;
			parser.addRow();
			if (builder.length() > 0) {
				String h = builder.toString();
				builder = new StringBuilder(Constants.KEY_MAX_LENGTH);
				h = h.trim();
			}
		} else {
			builder.append(c);
		}
		return this;
	}

}
