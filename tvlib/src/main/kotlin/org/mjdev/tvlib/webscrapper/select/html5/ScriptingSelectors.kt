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
fun <T> CssSelectable.script(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("script$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.canvas(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("canvas$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.noscript(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("noscript$cssSelector", init)
