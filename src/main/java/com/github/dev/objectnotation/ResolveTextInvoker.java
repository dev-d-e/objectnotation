package com.github.dev.objectnotation;

import static com.github.dev.objectnotation.Constants.END;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Invoker provides static methods.
 */
public final class ResolveTextInvoker {

	/**
	 * Suppress default constructor, ensuring non-instantiability.
	 */
	private ResolveTextInvoker() {
	}

	/**
	 * Resolve text, output text.
	 *
	 * @param array    the text.
	 * @param contents the consumer of the key and text.
	 */
	public static void accept(char[] array, Contents contents) {
		if (array == null || array.length == 0) {
			return;
		}
		Parser parser = new Parser(contents);
		for (int n = 0; n < array.length; n++) {
			parser.apply(array[n]);
		}
		parser.apply(END);
	}

	/**
	 * Resolve text, output text.
	 *
	 * @param charSequence the text.
	 * @param contents     the consumer of the key and text.
	 */
	public static void accept(CharSequence charSequence, Contents contents) {
		if (charSequence == null || charSequence.length() == 0) {
			return;
		}
		Parser parser = new Parser(contents);
		charSequence.chars().forEach(i -> parser.apply((char) i));
		parser.apply(END);
	}

	/**
	 * Resolve text, output text. the reader is not closed.
	 *
	 * @param reader   the text.
	 * @param contents the consumer of the key and text.
	 */
	public static void accept(Reader reader, Contents contents) throws IOException {
		if (reader == null) {
			return;
		}
		Parser parser = new Parser(contents);
		while (true) {
			int i = reader.read();
			if (i == -1) {
				i = END;
			}
			parser.apply((char) i);
			if (i == END) {
				break;
			}
		}
	}

	/**
	 * Resolve text, output {@code List<Target>}.
	 *
	 * @param array the text.
	 * @return List
	 */
	public static List<Target> accept(char[] array) {
		TreeBuilder builder = new TreeBuilder();
		accept(array, builder);
		return builder.build();
	}

	/**
	 * Resolve text, output {@code List<Target>}.
	 *
	 * @param charSequence the text.
	 * @return List
	 */
	public static List<Target> accept(CharSequence charSequence) {
		TreeBuilder builder = new TreeBuilder();
		accept(charSequence, builder);
		return builder.build();
	}

	/**
	 * Resolve text, output {@code List<Target>}. the reader is not closed.
	 *
	 * @param reader the text.
	 * @return List
	 */
	public static List<Target> accept(Reader reader) throws IOException {
		TreeBuilder builder = new TreeBuilder();
		accept(reader, builder);
		return builder.build();
	}

	/**
	 * Convert Target to Object.
	 *
	 * @param target Target.
	 * @param obj    T.
	 * @return T
	 */
	public static <T> T convert(Target target, T obj) {
		return ObjectBuilder.build(target, obj);
	}

	/**
	 * Convert Target to Object.
	 *
	 * @param targetList List.
	 * @param getObj     the output.
	 * @return List
	 */
	public static <T> List<T> convert(List<Target> targetList, Supplier<T> getObj) {
		List<T> rst = new ArrayList<>();
		targetList.forEach(target -> {
			T obj = convert(target, getObj.get());
			rst.add(obj);
		});
		return rst;
	}

	/**
	 * Convert Target to Object.
	 *
	 * @param target Target.
	 * @param type   Class.
	 * @return T
	 */
	public static <T> T convert(Target target, Class<T> type) {
		return ObjectBuilder.build(target, type);
	}

	/**
	 * Convert Target to Object.
	 *
	 * @param targetList List.
	 * @param type       Class.
	 * @return List
	 */
	public static <T> List<T> convert(List<Target> targetList, Class<T> type) {
		List<T> rst = new ArrayList<>();
		targetList.forEach(target -> {
			T obj = convert(target, type);
			rst.add(obj);
		});
		return rst;
	}

	/**
	 * Convert Target to Map. use separator to join key.
	 *
	 * @param target    Target
	 * @param separator String
	 * @return Map
	 */
	public static Map<String, List<String>> toMap(Target target, String separator) {
		Map<List<String>, List<String>> map = new HashMap<>();
		List<String> keyList = new ArrayList<>();
		targetToMap(target, keyList, map);
		Map<String, List<String>> rst = new HashMap<>();
		map.forEach((k, v) -> rst.put(toKey(k, separator), v));
		return rst;
	}

	/**
	 * Convert List Target to Map. use separator to join key.
	 *
	 * @param targetList List
	 * @param separator  String
	 * @return Map
	 */
	public static Map<String, List<String>> toMap(List<Target> targetList, String separator) {
		Map<List<String>, List<String>> map = new HashMap<>();
		for (Target target : targetList) {
			List<String> keyList = new ArrayList<>();
			targetToMap(target, keyList, map);
		}
		Map<String, List<String>> rst = new HashMap<>();
		map.forEach((k, v) -> rst.put(toKey(k, separator), v));
		return rst;
	}

	private static void targetToMap(Target target, List<String> keyList, Map<List<String>, List<String>> map) {
		keyList.add(target.getName());
		List<String> text = target.getText();
		if (text.size() > 0) {
			map.put(new ArrayList<>(keyList), text);
		}
		for (Target c : target.getValue()) {
			targetToMap(c, keyList, map);
		}
		keyList.remove(keyList.size() - 1);
	}

	private static String toKey(List<String> keyList, String separator) {
		if (keyList.isEmpty()) {
			return "";
		}
		if (separator == null) {
			separator = "";
		}
		StringBuilder builder = new StringBuilder();
		for (String s : keyList) {
			builder.append(s);
			builder.append(separator);
		}
		return builder.substring(0, builder.length() - separator.length());
	}
}
