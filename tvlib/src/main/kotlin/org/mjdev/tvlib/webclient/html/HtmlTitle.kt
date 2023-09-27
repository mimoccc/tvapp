package org.mjdev.tvlib.webclient.html

import org.mjdev.tvlib.webclient.javascript.JSI
import org.jsoup.nodes.Element
import org.mjdev.tvlib.webclient.html.HtmlElement

class HtmlTitle(element: Element, jsi: JSI) : HtmlElement(element, jsi) {
    override val tag: String get() = "title"

    val text: String get() = ownText()
}