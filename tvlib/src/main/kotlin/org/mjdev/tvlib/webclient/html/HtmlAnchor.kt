package org.mjdev.tvlib.webclient.html

import org.mjdev.tvlib.webclient.javascript.JSI
import org.jsoup.nodes.Element
import org.mjdev.tvlib.extensions.GlobalExt.isUrl

class HtmlAnchor(element: Element, jsi: JSI) : HtmlElement(element, jsi) {
    override val tag: String get() = "a"

    val href get() = attr("href")

    fun fullHref(page: HtmlPage): String {
        val href = this.href.let {
            if(it.first() == '/') it.replaceFirst("/", "")
            else it
        }
        return if(href.isUrl) {
            href
        } else {
            "${page.url}$href"
        }
    }
}