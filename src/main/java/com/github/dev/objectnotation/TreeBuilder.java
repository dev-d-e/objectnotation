package com.github.dev.objectnotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * TreeBuilder.
 */
final class TreeBuilder implements Contents {

	private final Root root = new Root();

	private int offset;

	private StringBuilder key = new StringBuilder();

	private StringBuilder text = new StringBuilder();

	private boolean quickStop = true;

	private final List<int[]> invalidChars = new ArrayList<>();

	@Override
	public void preKey(int offset) {
		this.offset = offset;
		key = new StringBuilder();
	}

	@Override
	public void key(char i) {
		key.append(i);
	}

	@Override
	public void postKey() {
	}

	@Override
	public void preText() {
		text = new StringBuilder();
	}

	@Override
	public void text(char i) {
		text.append(i);
	}

	@Override
	public void textArray() {
		postText();
		preText();
	}

	@Override
	public void postText() {
		root.addNode(offset, key.toString(), text);
		text = new StringBuilder();
	}

	@Override
	public void error(int row, int n) {
		setErr(row, n);
	}

	public List<Target> build() {
		return root.build();
	}

	public TreeBuilder setQuickStop(boolean quickStop) {
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
