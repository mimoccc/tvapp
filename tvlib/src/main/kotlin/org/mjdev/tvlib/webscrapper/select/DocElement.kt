/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper.select

import org.jsoup.nodes.Element
import java.util.*

@Suppress("unused", "MemberVisibilityCanBePrivate")
class DocElement internal constructor(
    override val element: Element,
    override val relaxed: Boolean,
) : DomTreeElement() {
    constructor(element: Element) : this(element, false)

    val tagName: String by lazy { element.tagName() }

    val ownText: String by lazy { element.ownText() }

    val wholeText: String by lazy { element.wholeText() }

    val attributes: Map<String, String> by lazy {
        element.attributes().associate { it.key to it.value }
    }

    val attributeKeys: List<String> by lazy { attributes.map { it.key } }

    val attributeValues: List<String> by lazy { attributes.map { it.value } }

    infix fun attribute(attributeKey: String): String = attributes[attributeKey].orEmpty()

    fun hasAttribute(attributeKey: String): Boolean = attribute(attributeKey).isNotBlank()

    val dataAttributes: Map<String, String> by lazy { attributes.filter { it.key.startsWith("data-") } }

    val className: String by lazy { attribute("class").trim() }

    val classNames: Set<String> by lazy { className.split(" ").filter { it.isNotBlank() }.toSet() }

    fun hasClass(className: String): Boolean =
        classNames.map { it.lowercase(Locale.getDefault()) }
            .contains(className.lowercase(Locale.getDefault()))

    val id: String by lazy { attribute("id").trim() }

    val parents: List<DocElement> by lazy { element.parents().map { DocElement(it, this.relaxed) } }

    fun <T> parents(init: List<DocElement>.() -> T): T = parents.init()

    val parent: DocElement by lazy {
        try {
            parents.first()
        } catch (e: NoSuchElementException) {
            throw ElementNotFoundException("parent")
        }
    }

    fun <T> parent(init: DocElement.() -> T): T = parent.init()

    val siblings: List<DocElement> by lazy {
        element.siblingElements().map { DocElement(it, this.relaxed) }
    }

    fun <T> siblings(init: List<DocElement>.() -> T): T = siblings.init()

    val isPresent: Boolean by lazy { allElements.isNotEmpty() }

    val isNotPresent: Boolean by lazy { !isPresent }

    override val toCssSelector: String
        get() = element.cssSelector()

    val parentsCssSelector: String by lazy {
        parents {
            when {
                isNotEmpty() -> reversed().joinToString(separator = " > ") { it.tagName }
                else -> ""
            }
        }
    }

    val ownCssSelector: String by lazy {
        fun String.orNull(): String? = ifBlank { null }
        fun List<String>.orNull(): List<String>? = ifEmpty { null }
        CssSelector(
            rawCssSelector = tagName,
            withClass = classNames.joinToString(separator = ".").orNull(),
            withId = id.orNull(),
            withAttributes = attributes
                .filterNot { it.key == "id" }
                .filterNot { it.key == "class" }
                .filterNot { it.value.isBlank() }
                .toList(),
            withAttributeKeys = attributes.filterValues { it.isBlank() }.map { it.key }.orNull(),
        ).toString()
    }

    override fun makeDefault(cssSelector: String, relaxed: Boolean): DocElement {
        return super.makeDefault(cssSelector, this.relaxed)
    }
}

val List<DocElement>.text: String
    get(): String = joinToString(separator = " ") { it.text }

val List<DocElement>.html: String
    get(): String = joinToString(separator = "\n") { it.outerHtml }

val List<DocElement>.isPresent: Boolean
    get(): Boolean = isNotEmpty()

@Suppress("unused")
val List<DocElement>.isNotPresent: Boolean
    get(): Boolean = !isPresent

@Suppress("unused")
val List<DocElement>.eachText: List<String>
    get(): List<String> = map { it.text }

@Suppress("unused")
val List<DocElement>.eachTagName: List<String>
    get(): List<String> = map { it.tagName }

@Suppress("unused")
val List<DocElement>.eachAttribute: Map<String, String>
    get() = map { it.attributes }.flatMap { it.toList() }.toMap()

@Suppress("unused")
val List<DocElement>.eachDataAttribute: Map<String, String>
    get() = map { it.dataAttributes }.flatMap { it.toList() }.toMap()

@Suppress("unused")
infix fun List<DocElement>.attribute(attributeKey: String): String =
    filter { it.hasAttribute(attributeKey) }
        .joinToString { it.attribute(attributeKey) }

@Suppress("unused")
infix fun List<DocElement>.eachAttribute(attributeKey: String): List<String> =
    map { it attribute attributeKey }
        .filter { it.isNotEmpty() }

@Suppress("unused")
val List<DocElement>.eachClassName: List<String>
    get(): List<String> = flatMap { it.classNames }.distinct()

@Suppress("unused")
val List<DocElement>.eachHref: List<String>
    get(): List<String> = eachAttribute("href")

@Suppress("unused")
val List<DocElement>.eachSrc: List<String>
    get(): List<String> = eachAttribute("src")

@Suppress("unused")
val List<DocElement>.eachLink: Map<String, String>
    get(): Map<String, String> =
        filter { it.hasAttribute("href") }
            .associate { it.text to it.attribute("href") }

@Suppress("unused")
val List<DocElement>.eachImage: Map<String, String>
    get(): Map<String, String> =
        filter { it.tagName == "img" }
            .filter { it.hasAttribute("src") }
            .associate { it.attribute("alt") to it.attribute("src") }

@Suppress("unused")
fun <T> List<DocElement>.forEachLink(init: (text: String, url: String) -> T) {
    eachLink.forEach { init(it.key, it.value) }
}

@Suppress("unused")
fun <T> List<DocElement>.forEachImage(init: (altText: String, url: String) -> T) {
    eachImage.forEach { init(it.key, it.value) }
}
