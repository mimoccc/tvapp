package org.mjdev.tvlib.webclient.html

import org.mjdev.tvlib.webclient.javascript.JSI
import org.jsoup.nodes.Element

class HtmlDiv(element: Element, jsi: JSI) : HtmlElement(element, jsi) {
    override val tag: String get() = "div"
}