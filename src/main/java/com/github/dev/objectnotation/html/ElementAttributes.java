package com.github.dev.objectnotation.html;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Element attributes
 */
@SuppressWarnings("unused")
final class ElementAttributes {

	private static final String[] A = { "href", "target", "download", "ping", "rel", "hreflang", "type", "referrerpolicy" };
	private static final String[] AREA = { "alt", "coords", "shape", "href", "target", "download", "ping", "rel", "referrerpolicy" };
	private static final String[] AUDIO = { "src", "crossorigin", "preload", "autoplay", "loop", "muted", "controls" };
	private static final String[] BASE = { "href", "target" };
	private static final String[] BLOCKQUOTE = { "cite" };
	private static final String[] BODY = { "onafterprint", "onbeforeprint", "onbeforeunload", "onhashchange", "onlanguagechange", "onmessage", "onmessageerror", "onoffline", "ononline", "onpagehide",
			"onpageshow", "onpopstate", "onrejectionhandled", "onstorage", "onunhandledrejection", "onunload" };
	private static final String[] BUTTON = { "disabled", "form", "formaction", "formenctype", "formmethod", "formnovalidate", "formtarget", "name", "type", "value" };
	private static final String[] CANVAS = { "width", "height" };
	private static final String[] COL = { "span" };
	private static final String[] COLGROUP = { "span" };
	private static final String[] DATA = { "value" };
	private static final String[] DEL = { "cite", "datetime" };
	private static final String[] DETAILS = { "open" };
	private static final String[] DIALOG = { "open" };
	private static final String[] EMBED = { "src", "type", "width", "height" };
	private static final String[] FIELDSET = { "disabled", "form", "name" };
	private static final String[] FORM = { "accept-charset", "action", "autocomplete", "enctype", "method", "name", "novalidate", "target" };
	private static final String[] HTML = { "manifest" };
	private static final String[] IFRAME = { "src", "srcdoc", "name", "sandbox", "allow", "allowfullscreen", "allowpaymentrequest", "width", "height", "referrerpolicy", "loading" };
	private static final String[] IMG = { "alt", "src", "srcset", "sizes", "crossorigin", "usemap", "ismap", "width", "height", "referrerpolicy", "decoding", "loading" };
	private static final String[] INPUT = { "accept", "alt", "autocomplete", "checked", "dirname", "disabled", "form", "formaction", "formenctype", "formmethod ", "formnovalidate", "formtarget",
			"height", "list", "max", "maxlength ", "min", "minlength", "multiple", "name", "pattern", "placeholder", "readonly", "required", "size", "src", "step", "type", "value", "width" };
	private static final String[] INS = { "cite", "datetime" };
	private static final String[] LABEL = { "for" };
	private static final String[] LI = { "value" };
	private static final String[] LINK = { "href", "crossorigin", "rel", "as", "media", "hreflang", "type", "sizes", "imagesrcset", "imagesizes", "referrerpolicy", "integrity", "color", "disabled" };
	private static final String[] MAP = { "name" };
	private static final String[] META = { "name", "http-equiv", "content", "charset" };
	private static final String[] METER = { "value", "min", "max", "low", "high", "optimum" };
	private static final String[] OBJECT = { "data", "type", "name", "usemap", "form", "width", "height" };
	private static final String[] OL = { "reversed", "start", "type" };
	private static final String[] OPTGROUP = { "disabled", "label" };
	private static final String[] OPTION = { "disabled", "label", "selected", "value" };
	private static final String[] OUTPUT = { "for", "form", "name" };
	private static final String[] PARAM = { "name", "value" };
	private static final String[] PROGRESS = { "value", "max" };
	private static final String[] Q = { "cite" };
	private static final String[] SCRIPT = { "src", "type", "async", "defer", "crossorigin", "integrity", "referrerpolicy" };
	private static final String[] SELECT = { "autocomplete", "disabled", "form", "multiple", "name", "required", "size" };
	private static final String[] SLOT = { "name" };
	private static final String[] SOURCE = { "src", "type", "srcset", "sizes", "media" };
	private static final String[] STYLE = { "media" };
	private static final String[] TD = { "colspan", "rowspan", "headers" };
	private static final String[] TEXTAREA = { "cols", "dirname", "disabled", "form", "maxlength", "minlength", "name", "placeholder", "readonly", "required", "rows", "wrap" };
	private static final String[] TH = { "colspan", "rowspan", "headers", "scope", "abbr" };
	private static final String[] TIME = { "datetime" };
	private static final String[] TRACK = { "default", "kind", "label", "src", "srclang" };
	private static final String[] VIDEO = { "src", "crossorigin", "poster", "preload", "autoplay", "playsinline", "loop", "muted", "controls", "width", "height" };

	private static final Map<String, String[]> ELEMENTS = new HashMap<>();

	static {
		Arrays.stream(ElementAttributes.class.getDeclaredFields()).filter(f -> f.getType().isArray()).forEach(f -> {
			try {
				String[] arr = (String[]) f.get(null);
				ELEMENTS.put(f.getName().toLowerCase(), arr);
			} catch (Exception e) {
			}
		});
	}

	/**
	 * Returns true if an element has the attribute.
	 */
	public static boolean isAttribute(String n, String str) {
		if (n == null || n.isEmpty() || str == null || str.isEmpty()) {
			return false;
		}
		String[] arr = ELEMENTS.get(n.toLowerCase());
		if (arr == null) {
			return false;
		}
		str = str.toLowerCase();
		return Arrays.stream(arr).anyMatch(str::equals);
	}

}
