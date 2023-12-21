/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper.select.html5

import org.mjdev.tvlib.webscrapper.select.CssSelectable
import org.mjdev.tvlib.webscrapper.select.CssSelector

@Suppress("unused")
fun <T> CssSelectable.caption(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("caption$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.col(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("col$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.colgroup(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("colgroup$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.table(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("table$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.tbody(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("tbody$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.td(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("td$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.tfoot(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("tfoot$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.th(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("th$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.thead(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("thead$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.tr(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("tr$cssSelector", init)
