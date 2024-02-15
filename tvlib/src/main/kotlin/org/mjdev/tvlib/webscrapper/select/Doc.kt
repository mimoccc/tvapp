/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper.select

import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

open class Doc(
    val document: Document,
    override var relaxed: Boolean = false
) : DomTreeElement() {
    override val element: Element
        get() = this.document

    @Suppress("unused")
    val wholeText: String by lazy { document.wholeText() }

    @Suppress("unused")
    val titleText: String by lazy { document.title() }

    override val toCssSelector: String = ""

    override fun makeDefaultElement(cssSelector: String): DocElement {
        return DocElement(Element(cssSelector), this.relaxed)
    }

    override fun makeDefault(cssSelector: String, relaxed: Boolean): DocElement {
        return super.makeDefault(cssSelector, this.relaxed)
    }
}
