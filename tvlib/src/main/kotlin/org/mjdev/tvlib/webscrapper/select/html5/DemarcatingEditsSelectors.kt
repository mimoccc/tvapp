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
fun <T> CssSelectable.del(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("del$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.ins(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("ins$cssSelector", init)
