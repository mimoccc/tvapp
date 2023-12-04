package org.mjdev.tvlib.webclient.html

import org.mjdev.tvlib.webclient.javascript.JSI

@Suppress("MemberVisibilityCanBePrivate")
class HtmlPage(val url: String?, val html: String?, val jsi: JSI) {
    val documentElement get() = HtmlDocument(html, jsi)

    val head: HtmlHead get() = documentElement.head
    val body: HtmlBody get() = documentElement.body

    val title get() = head.title.text

    val videos: List<HtmlVideo> get() = body.getAllElements()
    val anchors: List<HtmlAnchor> get() = body.getAllElements()

    override fun toString(): String {
        return "${HtmlPage::class.simpleName} : ${url ?: ""}"
    }
}