package com.github.dev.objectnotation.html;

import java.util.HashSet;
import java.util.Set;

/**
 * Attributes
 */
final class Attributes {

	private static final Set<String> GLOBALS = new HashSet<>();
	private static final Set<String> EVENT_HANDLER = new HashSet<>();
	private static final String DATA = "data-";

	static {
		GLOBALS.add("class");
		GLOBALS.add("id");
		GLOBALS.add("slot");
		GLOBALS.add("accesskey");
		GLOBALS.add("autocapitalize");
		GLOBALS.add("autofocus");
		GLOBALS.add("contenteditable");
		GLOBALS.add("dir");
		GLOBALS.add("draggable");
		GLOBALS.add("enterkeyhint");
		GLOBALS.add("hidden");
		GLOBALS.add("inputmode");
		GLOBALS.add("is");
		GLOBALS.add("itemid");
		GLOBALS.add("itemprop");
		GLOBALS.add("itemref");
		GLOBALS.add("itemscope");
		GLOBALS.add("itemtype");
		GLOBALS.add("lang");
		GLOBALS.add("nonce");
		GLOBALS.add("style");
		GLOBALS.add("tabindex");
		GLOBALS.add("title");
		GLOBALS.add("translate");

		EVENT_HANDLER.add("onabort");
		EVENT_HANDLER.add("onauxclick");
		EVENT_HANDLER.add("onblur");
		EVENT_HANDLER.add("oncancel");
		EVENT_HANDLER.add("oncanplay");
		EVENT_HANDLER.add("oncanplaythrough");
		EVENT_HANDLER.add("onchange");
		EVENT_HANDLER.add("onclick");
		EVENT_HANDLER.add("onclose");
		EVENT_HANDLER.add("oncontextmenu");
		EVENT_HANDLER.add("oncopy");
		EVENT_HANDLER.add("oncuechange");
		EVENT_HANDLER.add("oncut");
		EVENT_HANDLER.add("ondblclick");
		EVENT_HANDLER.add("ondrag");
		EVENT_HANDLER.add("ondragend");
		EVENT_HANDLER.add("ondragenter");
		EVENT_HANDLER.add("ondragexit");
		EVENT_HANDLER.add("ondragleave");
		EVENT_HANDLER.add("ondragover");
		EVENT_HANDLER.add("ondragstart");
		EVENT_HANDLER.add("ondrop");
		EVENT_HANDLER.add("ondurationchange");
		EVENT_HANDLER.add("onemptied");
		EVENT_HANDLER.add("onended");
		EVENT_HANDLER.add("onerror");
		EVENT_HANDLER.add("onfocus");
		EVENT_HANDLER.add("onformdata");
		EVENT_HANDLER.add("oninput");
		EVENT_HANDLER.add("oninvalid");
		EVENT_HANDLER.add("onkeydown");
		EVENT_HANDLER.add("onkeypress");
		EVENT_HANDLER.add("onkeyup");
		EVENT_HANDLER.add("onload");
		EVENT_HANDLER.add("onloadeddata");
		EVENT_HANDLER.add("onloadedmetadata");
		EVENT_HANDLER.add("onloadstart");
		EVENT_HANDLER.add("onmousedown");
		EVENT_HANDLER.add("onmouseenter");
		EVENT_HANDLER.add("onmouseleave");
		EVENT_HANDLER.add("onmousemove");
		EVENT_HANDLER.add("onmouseout");
		EVENT_HANDLER.add("onmouseover");
		EVENT_HANDLER.add("onmouseup");
		EVENT_HANDLER.add("onpaste");
		EVENT_HANDLER.add("onpause");
		EVENT_HANDLER.add("onplay");
		EVENT_HANDLER.add("onplaying");
		EVENT_HANDLER.add("onprogress");
		EVENT_HANDLER.add("onratechange");
		EVENT_HANDLER.add("onreset");
		EVENT_HANDLER.add("onresize");
		EVENT_HANDLER.add("onscroll");
		EVENT_HANDLER.add("onsecuritypolicyviolation");
		EVENT_HANDLER.add("onseeked");
		EVENT_HANDLER.add("onseeking");
		EVENT_HANDLER.add("onselect");
		EVENT_HANDLER.add("onslotchange");
		EVENT_HANDLER.add("onstalled");
		EVENT_HANDLER.add("onsubmit");
		EVENT_HANDLER.add("onsuspend");
		EVENT_HANDLER.add("ontimeupdate");
		EVENT_HANDLER.add("ontoggle");
		EVENT_HANDLER.add("onvolumechange");
		EVENT_HANDLER.add("onwaiting");
		EVENT_HANDLER.add("onwheel");
	}

	public static boolean isAttr(String str) {
		return GLOBALS.contains(str) || EVENT_HANDLER.contains(str) || str.startsWith(DATA);
	}

}
