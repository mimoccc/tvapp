package org.mjdev.tvlib.webclient.html

import org.mjdev.tvlib.webclient.javascript.JSI
import org.jsoup.nodes.Element

class HtmlHead(element: Element, jsi: JSI) : HtmlElement(element, jsi) {
    override val tag: String get() = "head"

    val title: HtmlTitle get() = getElement()

    val meta: List<HtmlMeta> get() = getAllElements()
    val links: List<HtmlLink> get() = getAllElements()
    val scripts: List<HtmlScript> get() = getAllElements()
    val noScript: List<HtmlNoScript> get() = getAllElements()
}