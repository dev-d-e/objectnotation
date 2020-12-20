package com.github.dev.objectnotation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public final class Header {

	private final List<String> configuration = new ArrayList<>();

	private final List<String> externalResources = new ArrayList<>();

	public List<String> getConfiguration() {
		return configuration;
	}

	public List<String> getExternalResources() {
		return externalResources;
	}

	public boolean isEmpty() {
		return configuration.isEmpty() && externalResources.isEmpty();
	}

}
