package org.mjdev.tvlib.webclient.html

import org.mjdev.tvlib.webclient.javascript.JSI
import org.jsoup.nodes.Element

@Suppress("unused")
class HtmlScript(element: Element, jsi: JSI) : HtmlElement(element, jsi) {
    override val tag: String get() = "script"

    val type: String get() = attr("type")
    val async: String get() = attr("async")
    val src: String get() = attr("src")
}