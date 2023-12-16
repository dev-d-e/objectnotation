package com.github.dev.objectnotation.tree;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import com.github.dev.objectnotation.Contents;
import com.github.dev.objectnotation.Header;
import com.github.dev.objectnotation.Target;

/**
 * Document factory.
 */
public final class DocumentFactory {

	private final DocumentImpl document = new DocumentImpl();

	private Node lastNode;

	public Document getDocument() {
		return document;
	}

	public List<Target> build() {
		return build(document.getNodes());
	}

	List<Target> build(LinkedHashMap<String, List<Node>> nodes) {
		List<Target> rst = new ArrayList<>();
		nodes.forEach((k, v) -> {
			Target o = new Target(k);
			v.forEach(node -> {
				node.getText().ifPresent(s -> o.getText().add(s));
				build(node.getNodes()).forEach(e -> o.getValue().add(e));
			});
		});
		return rst;
	}

	public void addNode(int offset, String key, CharSequence cs) {
		Objects.requireNonNull(key);
		if (offset < 0) {
			return;
		}
		Node node = new AbstractNode(offset, key, cs);
		if (offset == 0) {
			document.add(node);
			lastNode = node;
			return;
		}
		if (offset == (lastNode.getOffset() + 1)) {
			lastNode.add(node);
		} else {
			Node p = lastNode.getParentByOffset(offset);
			if (p == null) {
				return;
			}
			p.add(node);
		}
		lastNode = node;
	}

	public void add(Header header) {
		header.getConfiguration().forEach(s -> document.configure(s, s));
		header.getExternalResources().forEach(s -> document.externalResource(s));
	}

	public Contents getContents() {
		return new DocNode(this);
	}

}

class DocNode implements Contents {
	private DocumentFactory documentFactory;
	private int offset;
	private StringBuilder key = new StringBuilder();
	private StringBuilder text = new StringBuilder();

	DocNode(DocumentFactory documentFactory) {
		this.documentFactory = documentFactory;
	}

	@Override
	public void preKey(int offset) {
		this.offset = offset;
	}

	@Override
	public void key(int i) {
		key.append((char) i);
	}

	@Override
	public void postKey() {
	}

	@Override
	public void preText() {
	}

	@Override
	public void text(int i) {
		text.append((char) i);
	}

	@Override
	public void textArray() {
		documentFactory.addNode(offset, key.toString(), text);
		text = new StringBuilder();
	}

	@Override
	public void postText() {
		documentFactory.addNode(offset, key.toString(), text);
		key = new StringBuilder();
		text = new StringBuilder();
	}

	@Override
	public void error(int row, int n) {
	}

}
