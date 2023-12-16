package com.github.dev.objectnotation;

import java.util.ArrayList;
import java.util.List;

/**
 * Target.
 */
public final class Target {
	private String name;
	private List<String> text;
	private List<Target> value;

	public Target(String name) {
		this.name = name;
		this.text = new ArrayList<>();
		this.value = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public List<String> getText() {
		return text;
	}

	public void setText(List<String> text) {
		this.text = text;
	}

	public List<Target> getValue() {
		return value;
	}

	public void setValue(List<Target> value) {
		this.value = value;
	}
}
