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
fun <T> CssSelectable.a(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("a$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.abbr(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("abbr$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.b(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("b$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.bdi(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("bdi$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.bdo(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("bdo$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.br(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("br$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.cite(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("cite$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.code(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("code$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.data(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("data$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.dfn(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("dfn$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.em(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("em$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.i(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("i$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.kbd(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("kbd$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.mark(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("mark$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.q(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("q$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.rb(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("rb$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.rtc(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("rtc$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.ruby(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("ruby$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.s(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("s$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.samp(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("samp$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.small(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("small$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.span(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("span$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.strong(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("strong$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.sub(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("sub$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.sup(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("sup$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.time(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("time$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.tt(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("tt$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.u(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("u$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.`var`(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("var$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.wbr(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("wbr$cssSelector", init)
