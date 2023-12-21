/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper.select

import org.jsoup.nodes.Element

@Suppress("MemberVisibilityCanBePrivate")
abstract class DomTreeElement : CssSelectable() {
    abstract val element: Element

    abstract val relaxed: Boolean

    val text: String by lazy { element.text() }

    val html: String by lazy { element.html() }

    val outerHtml: String by lazy { element.outerHtml() }

    val allElements: List<DocElement> by lazy {
        element.allElements.map {
            DocElement(
                it,
                this.relaxed
            )
        }
    }

    val children: List<DocElement> by lazy {
        element.children().map { DocElement(it, this.relaxed) }
    }

    @Suppress("unused")
    fun <T> children(init: List<DocElement>.() -> T): T = children.init()

    fun eachAttribute(attributeKey: String): List<String> =
        allElements.map { it attribute attributeKey }
            .filter { it.isNotEmpty() }

    @Suppress("unused")
    val eachHref: List<String> by lazy {
        eachAttribute("href").filter { it.isNotEmpty() }
    }

    @Suppress("unused")
    val eachSrc: List<String> by lazy {
        eachAttribute("src").filter { it.isNotEmpty() }
    }

    @Suppress("unused")
    val eachLink: Map<String, String>
        get(): Map<String, String> = allElements.filter {
            it.hasAttribute("href")
        }.associate {
            it.text to it.attribute("href")
        }

    @Suppress("unused")
    val eachImage: Map<String, String>
        get(): Map<String, String> =
            allElements.filter { it.tagName == "img" }
                .filter { it.hasAttribute("src") }
                .associate { it.attribute("alt") to it.attribute("src") }

    val eachVideo: List<String>
        get() {
            val elements = allElements
            val videos = elements.filter { e -> (e.tagName == "video") || (e.tagName == "source") }
            val videosWithSource = videos.filter { e -> e.hasAttribute("src") }
            // todo type
            return videosWithSource.map { e -> e.attribute("src") }
        }

    open fun makeDefaultElement(cssSelector: String): DocElement {
        return super.makeDefault(cssSelector, this.relaxed)
    }

    override fun makeDefault(cssSelector: String, relaxed: Boolean): DocElement {
        return if (relaxed) makeDefaultElement(cssSelector) else throw ElementNotFoundException(
            cssSelector
        )
    }

    override fun applySelector(rawCssSelector: String): List<DocElement> {
        if (rawCssSelector.isEmpty()) {
            return allElements
        }

        val queried = element.children().select(rawCssSelector).map { DocElement(it, relaxed) }
        val selected = queried.takeIf { it.isNotEmpty() }

        return if (relaxed) selected.orEmpty() else selected ?: throw ElementNotFoundException(
            rawCssSelector
        )
    }

    override fun toString(): String = element.toString()
}

open class ElementNotFoundException(selector: String, tag: String = "") :
    Exception("Could not find element \"$tag$selector\"")
