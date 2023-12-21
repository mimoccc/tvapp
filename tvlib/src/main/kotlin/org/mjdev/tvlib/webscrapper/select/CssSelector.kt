/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper.select

import org.jsoup.nodes.Document

 @Suppress("MemberVisibilityCanBePrivate")
 class CssSelector(
     var rawCssSelector: String = "",
     var withClass: CssClassName? = null,
     var withId: String? = null,
     var withAttributeKey: String? = null,
     var withAttributeKeys: List<String>? = null,
     var withAttribute: Pair<String, String>? = null,
     var withAttributes: List<Pair<String, String>>? = null,
     val doc: CssSelectable = Doc(Document(""))
) : CssSelectable() {
    override val toCssSelector: String
        get() = ("${doc.toCssSelector} $this").trim()

    override fun applySelector(rawCssSelector: String): List<DocElement> =
        doc.applySelector("$this $rawCssSelector".trim())

    override fun toString(): String = rawCssSelector.trim() + buildString {
        append(withId.toIdSelector())
        append(withClass.toClassesSelector())
        append(withAttributeKey.toAttributeKeySelector())
        append(withAttributeKeys.toAttributesKeysSelector())
        append(withAttribute.toAttributeSelector())
        append(withAttributes.toAttributesSelector())
    }

    private fun String?.toIdSelector() = this?.let { "#$it" }.orEmpty().withoutSpaces()

    private fun CssClassName?.toClassesSelector() = this?.let { ".$it" }.orEmpty().withoutSpaces()

    private fun String?.toAttributeKeySelector() = this?.let { "[$it]" }.orEmpty().withoutSpaces()

    private fun List<String>?.toAttributesKeysSelector() =
        this?.joinToString(prefix = "['", separator = "']['", postfix = "']").orEmpty().withoutSpaces()

    private fun Pair<String, String>?.toAttributeSelector() =
        this?.let { "[${it.first.withoutSpaces()}='${it.second}']" }.orEmpty()

    private fun List<Pair<String, String>>?.toAttributesSelector() =
        this?.joinToString(separator = "") { it.toAttributeSelector() }.orEmpty()

    private fun String.withoutSpaces() = replace("\\s".toRegex(), "")
}

typealias CssClassName = String

infix fun CssClassName.and(
    value: String
): String = "$this.$value"

infix fun Pair<String, String>.and(
    pair: Pair<String, String>
): MutableList<Pair<String, String>> = mutableListOf(this).apply { add(pair) }
