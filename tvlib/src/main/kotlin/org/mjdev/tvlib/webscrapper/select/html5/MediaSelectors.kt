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
fun <T> CssSelectable.area(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("area$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.audio(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("audio$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.img(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("img$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.map(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("map$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.track(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("track$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.video(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("video$cssSelector", init)
