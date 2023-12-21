package com.github.dev.objectnotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Constructs a {@code Parser}, Resolve key and text pair.
 */
class StandardResolver {

	private final Parser parser;

	StandardResolver(Consumer<Header> consumer, Contents contents) {
		parser = new Parser(consumer, contents);
	}

	StandardResolver(Contents contents) {
		this(o -> {
		}, contents);
	}

	public void apply(int i) {
		parser.apply((char) i);
	}

}
