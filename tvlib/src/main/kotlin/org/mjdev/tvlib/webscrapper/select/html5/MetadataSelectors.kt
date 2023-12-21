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
fun <T> CssSelectable.base(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("base$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.head(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("head$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.link(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("link$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.meta(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("meta$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.style(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("style$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.title(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("title$cssSelector", init)
