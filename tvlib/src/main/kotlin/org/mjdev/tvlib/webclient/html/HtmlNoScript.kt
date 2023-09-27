package org.mjdev.tvlib.webclient.html

import org.mjdev.tvlib.webclient.javascript.JSI
import org.jsoup.nodes.Element

class HtmlNoScript(element: Element, jsi: JSI) : HtmlElement(element, jsi) {
    override val tag: String get() = "noscript"

    val links: List<HtmlLink> get() = getAllElements()
}