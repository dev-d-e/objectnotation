package com.github.dev.objectnotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.IntConsumer;

import com.github.dev.objectnotation.tree.DocumentFactory;

/**
 * Constructs a {@code Parser}, Resolve key and text pair.
 */
class StandardResolver {

	private final Parser parser;

	private int curOffset = -1;

	private String curKey;

	private StringBuilder builder;

	private boolean quickStop = true;

	private final List<int[]> invalidChars = new ArrayList<>();

	StandardResolver(DocumentFactory documentFactory) {
		parser = new Parser(o -> documentFactory.add(o), (i, s) -> {
			curOffset = i;
			curKey = s;
			builder = new StringBuilder();
		}, i -> {
			if (builder != null) {
				if (i == -1) {
					documentFactory.addNode(curOffset, curKey, builder);
					builder = null;
				} else if (i == -2) {
					documentFactory.addNode(curOffset, curKey, builder);
					builder = new StringBuilder();
				}
			}
		}, i -> {
			if (builder != null) {
				builder.append((char) i);
			}
		}, (i, j) -> setErr(i, j));
	}

	StandardResolver(IntStringConsumer keyConsumer, IntConsumer textConsumer) {
		parser = new Parser(o -> {
		}, (i, s) -> {
			curOffset = i;
			curKey = s;
			keyConsumer.accept(i, s);
		}, i -> {
			if (i == -2) {
				keyConsumer.accept(curOffset, curKey);
			}
		}, textConsumer, (i, j) -> setErr(i, j));
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
