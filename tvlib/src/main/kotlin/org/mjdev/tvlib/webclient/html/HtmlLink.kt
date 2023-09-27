package org.mjdev.tvlib.webclient.html

import org.mjdev.tvlib.webclient.javascript.JSI
import org.jsoup.nodes.Element
import org.mjdev.tvlib.webclient.html.HtmlElement

class HtmlLink(element: Element, jsi: JSI) : HtmlElement(element, jsi) {
    override val tag: String get() = "link"

    val rel: String get() = attr("rel")
    val href: String get() = attr("href")
}