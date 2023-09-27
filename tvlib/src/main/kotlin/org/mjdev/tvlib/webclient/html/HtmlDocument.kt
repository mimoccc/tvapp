package org.mjdev.tvlib.webclient.html

import org.mjdev.tvlib.webclient.javascript.JSI
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import timber.log.Timber
import java.lang.Exception

class HtmlDocument(val html: String?, jsi: JSI) : HtmlElement(parseElement(html), jsi) {
    override val tag: String get() = "html"

    val head: HtmlHead get() = getElement()
    val body: HtmlBody get() = getElement()

    companion object {
        fun parseElement(html: String?): Element {
            return try {
                Jsoup.parse(html ?: "<html />")
            } catch (e: Exception) {
                Timber.e(e)
                Element("")
            }
        }
    }
}
