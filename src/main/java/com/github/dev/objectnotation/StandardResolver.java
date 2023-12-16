package com.github.dev.objectnotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.github.dev.objectnotation.tree.DocumentFactory;

/**
 * Constructs a {@code Parser}, Resolve key and text pair.
 */
class StandardResolver {

	private final Parser parser;

	private boolean quickStop = true;

	private final List<int[]> invalidChars = new ArrayList<>();

	StandardResolver(DocumentFactory documentFactory) {
		parser = new Parser(o -> documentFactory.add(o), documentFactory.getContents());
	}

	StandardResolver(Contents contents) {
		parser = new Parser(o -> {
		}, contents);
	}

	public void apply(int i) {
		parser.apply(i);
	}

	public StandardResolver setQuickStop(boolean quickStop) {
		this.quickStop = quickStop;
		return this;
	}

	private void setErr(int i, int j) {
		if (invalidChars.size() < 100) {
			invalidChars.add(new int[] { i, j });
		}
		if (quickStop) {
			throw new RuntimeException(exceptionStr());
		}
	}

	private String exceptionStr() {
		StringBuilder str = new StringBuilder("invalid char is probably at");
		invalidChars.forEach(e -> {
			str.append('(').append(e[0]).append(',').append(e[1]).append(')');
		});
		return str.toString();
	}

	public Optional<String> getExceptionStr() {
		if (invalidChars.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(exceptionStr());
	}

}
