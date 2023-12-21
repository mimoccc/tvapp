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

@Suppress("MemberVisibilityCanBePrivate")
abstract class CssSelectable {
    abstract val toCssSelector: String

    internal abstract fun applySelector(rawCssSelector: String): List<DocElement>

    fun <T> selection(cssSelector: String, init: CssSelector.() -> T): T =
        CssSelector(rawCssSelector = cssSelector, doc = this).init()

    operator fun <T> String.invoke(init: CssSelector.() -> T): T =
        this@CssSelectable.selection(this, init)

    open fun makeDefault(cssSelector: String, relaxed: Boolean = false): DocElement {
        return DocElement(Element("${UUID.randomUUID()}"), relaxed)
    }

    infix fun findAll(cssSelector: String): List<DocElement> =
        this.applySelector(cssSelector)

    fun findByIndex(index: Int, cssSelector: String = ""): DocElement =
        findAll(cssSelector).getOrElse(index) { makeDefault(cssSelector) }

    operator fun Int.invoke(cssSelector: String = ""): DocElement =
        findByIndex(this, cssSelector)

    fun findBySelectorMatching(regex: Regex): List<DocElement> =
        this@CssSelectable.findAll("*").filter { it.ownCssSelector.matches(regex) }

    operator fun Regex.invoke(): List<DocElement> =
        findBySelectorMatching(this)

    infix fun findFirst(cssSelector: String): DocElement =
        findByIndex(0, cssSelector)

    fun findSecond(cssSelector: String = ""): DocElement =
        findByIndex(1, cssSelector)

    fun findThird(cssSelector: String = ""): DocElement =
        findByIndex(2, cssSelector)

    fun findLast(cssSelector: String = ""): DocElement =
        findAll(cssSelector).last()

    fun findSecondLast(cssSelector: String = ""): DocElement =
        findAll(cssSelector).let { it.getOrElse(it.lastIndex - 1) { makeDefault(cssSelector) } }

    fun <T> findAll(cssSelector: String = "", init: List<DocElement>.() -> T): T =
        findAll(cssSelector).init()

    fun <T> findByIndex(index: Int, cssSelector: String = "", init: DocElement.() -> T): T =
        findByIndex(index, cssSelector).init()

    operator fun <T> Int.invoke(cssSelector: String = "", init: DocElement.() -> T): T =
        this(cssSelector).init()

    fun <T> findBySelectorMatching(regex: Regex, init: List<DocElement>.() -> T): T =
        findBySelectorMatching(regex).init()

    operator fun <T> Regex.invoke(init: List<DocElement>.() -> T): T =
        this().init()

    fun <T> findFirst(cssSelector: String = "", init: DocElement.() -> T): T =
        findFirst(cssSelector).init()

    fun <T> findSecond(cssSelector: String = "", init: DocElement.() -> T): T =
        findSecond(cssSelector).init()

    fun <T> findThird(cssSelector: String = "", init: DocElement.() -> T): T =
        findThird(cssSelector).init()

    fun <T> findLast(cssSelector: String = "", init: DocElement.() -> T): T =
        findLast(cssSelector).init()

    fun <T> findSecondLast(cssSelector: String = "", init: DocElement.() -> T): T =
        findSecondLast(cssSelector).init()
}
