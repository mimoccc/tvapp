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
fun <T> CssSelectable.blockquote(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("blockquote$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.dd(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("dd$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.dir(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("dir$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.dl(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("dl$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.dt(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("dt$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.figcaption(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("figcaption$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.figure(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("figure$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.hr(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("hr$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.li(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("li$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.ol(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("ol$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.ul(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("ul$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.p(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("p$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.pre(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("pre$cssSelector", init)
