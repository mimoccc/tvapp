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
fun <T> CssSelectable.body(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("body$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.div(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("div$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.section(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("section$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.nav(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("nav$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.article(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("article$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.aside(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("aside$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.h1(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("h1$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.h2(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("h2$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.h3(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("h3$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.h4(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("h4$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.h5(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("h5$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.h6(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("h6$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.header(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("header$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.footer(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("footer$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.address(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("address$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.main(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("main$cssSelector", init)
