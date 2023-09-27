package org.mjdev.tvlib.webclient.html

import org.mjdev.tvlib.webclient.javascript.JSI
import org.jsoup.nodes.Element
import org.mjdev.tvlib.webclient.html.HtmlElement

class HtmlImage(element: Element, jsi: JSI) : HtmlElement(element, jsi) {
    override val tag: String get() = "img"

    val src get() = attr("src")
    val alt get() = attr("alt")

    val width get() = attr("width") // todo css
    val height get() = attr("height") // todo css
}