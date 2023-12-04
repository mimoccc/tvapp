package org.mjdev.tvlib.webclient.html

import org.mjdev.tvlib.webclient.javascript.JSI
import org.jsoup.nodes.Element

@Suppress("MemberVisibilityCanBePrivate", "unused")
class HtmlMeta(element: Element, jsi: JSI) : HtmlElement(element, jsi) {
    override val tag: String get() = "meta"

    val charset: String get() = attr("charset")
    val content: String get() = attr("content")
}