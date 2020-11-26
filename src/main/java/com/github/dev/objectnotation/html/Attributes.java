package com.github.dev.objectnotation.html;

import java.util.Arrays;

/**
 * Attributes
 */
final class Attributes {

	private static final String[] GLOBALS = { "class", "id", "slot", "accesskey", "autocapitalize", "autofocus", "contenteditable", "dir", "draggable", "enterkeyhint", "hidden", "inputmode", "is",
			"itemid", "itemprop", "itemref", "itemscope", "itemtype", "lang", "nonce", "spellcheck", "style", "tabindex", "title", "translate" };

	private static final String[] EVENT_HANDLER = { "onabort", "onauxclick", "onblur", "oncancel", "oncanplay", "oncanplaythrough", "onchange", "onclick", "onclose", "oncontextmenu", "oncopy",
			"oncuechange", "oncut", "ondblclick", "ondrag", "ondragend", "ondragenter", "ondragexit", "ondragleave", "ondragover", "ondragstart", "ondrop", "ondurationchange", "onemptied", "onended",
			"onerror", "onfocus", "onformdata", "oninput", "oninvalid", "onkeydown", "onkeypress", "onkeyup", "onload", "onloadeddata", "onloadedmetadata", "onloadstart", "onmousedown",
			"onmouseenter", "onmouseleave", "onmousemove", "onmouseout", "onmouseover", "onmouseup", "onpaste", "onpause", "onplay", "onplaying", "onprogress", "onratechange", "onreset", "onresize",
			"onscroll", "onsecuritypolicyviolation", "onseeked", "onseeking", "onselect", "onslotchange", "onstalled", "onsubmit", "onsuspend", "ontimeupdate", "ontoggle", "onvolumechange",
			"onwaiting", "onwheel" };

	private static final String DATA = "data-";

	private static final String ATTRIBUTE = "attribute";

	public static boolean isAttr(String str) {
		return Arrays.stream(GLOBALS).anyMatch(s -> s.equalsIgnoreCase(str)) || Arrays.stream(EVENT_HANDLER).anyMatch(s -> s.equalsIgnoreCase(str))
				|| (str.length() >= 5 && str.substring(0, 5).equalsIgnoreCase(DATA));
	}

	public static boolean containsAttr(String str) {
		return ATTRIBUTE.equalsIgnoreCase(str);
	}

}
