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
fun <T> CssSelectable.details(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("details$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.dialog(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("dialog$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.menu(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("menu$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.menuitem(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("menuitem$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.summary(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("summary$cssSelector", init)
