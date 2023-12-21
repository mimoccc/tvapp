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
fun <T> CssSelectable.content(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("content$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.shadow(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("shadow$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.slot(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("slot$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.template(cssSelector: String = "", init: CssSelector.() -> T): T =
    selection("template$cssSelector", init)
