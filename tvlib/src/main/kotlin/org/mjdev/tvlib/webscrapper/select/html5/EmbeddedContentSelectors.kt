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
fun <T> CssSelectable.applet(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("applet$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.embed(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("embed$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.iframe(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("iframe$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.noembed(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("noembed$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.`object`(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("object$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.param(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("param$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.picture(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("picture$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.source(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("source$cssSelector", init)
