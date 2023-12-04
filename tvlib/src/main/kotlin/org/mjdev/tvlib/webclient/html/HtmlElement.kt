package org.mjdev.tvlib.webclient.html

import org.mjdev.tvlib.webclient.javascript.JSI
import org.jsoup.nodes.Element

@Suppress("unused", "MemberVisibilityCanBePrivate", "SpellCheckingInspection")
abstract class HtmlElement(var element: Element, val jsi: JSI) {
    abstract val tag: String

    val cssClass: String get() = attr("class")
    val id: String get() = attr("href")
    val name get() = attr("name")
    val childElementCount: Int get() = childrenSize()
    val childs: List<HtmlElement>
        get() = element.children().map { e -> parseElement(e, jsi) }
    val firstChild: HtmlElement
        get() = parseElement(element.child(0), jsi)

    fun childrenSize(): Int = element.childrenSize()

    fun getElementsByTagName(tagName: String): List<HtmlElement> {
        return element.getElementsByTag(tagName).map {
            parseElement(it, jsi)
        }
    }

    fun ownText(): String = element.ownText()

    inline fun <reified T> newInstance(jsi: JSI): T = T::class
        .java
        .constructors
        .first()
        .newInstance(
            Element("unknown"),
            jsi
        ) as T

    inline fun <reified T : HtmlElement> getAllElements(): List<T> {
        val instance: T = newInstance(jsi)
        return element.getElementsByTag(instance.tag).mapNotNull {
            parseElement(it, jsi) as T?
        }
    }

    inline fun <reified T : HtmlElement> getElement(): T {
        val instance: HtmlElement = newInstance(jsi)
        return element.getElementsByTag(instance.tag).first()?.let {
            parseElement(it, jsi) as? T?
        } ?: newInstance(jsi)
    }

    fun attr(attrName: String): String = element.attr(attrName)

    fun attr(attrName: String, value: Any?) {
        element.attr(attrName, value.toString())
    }

    override fun toString(): String {
        return element.toString()
    }

    companion object {
        fun parseElement(element: Element, jsi: JSI): HtmlElement {
            return when (element.tag().toString()) {
                "a" -> HtmlAnchor(element, jsi)
                "body" -> HtmlBody(element, jsi)
                "div" -> HtmlDiv(element, jsi)
                "head" -> HtmlHead(element, jsi)
                "img" -> HtmlImage(element, jsi)
                "link" -> HtmlLink(element, jsi)
                "meta" -> HtmlMeta(element, jsi)
                "noscript" -> HtmlNoScript(element, jsi)
                "script" -> HtmlScript(element, jsi)
                "source" -> HtmlSource(element, jsi)
                "title" -> HtmlTitle(element, jsi)
                "video" -> HtmlVideo(element, jsi)
                else -> object : HtmlElement(element, jsi) {
                    override val tag: String get() = ""
                }
            }
        }
    }
}