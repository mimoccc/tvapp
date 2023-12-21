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
fun <T> CssSelectable.button(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("button$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.datalist(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("datalist$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.fieldset(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("fieldset$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.form(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("form$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.input(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("input$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.label(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("label$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.legend(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("legend$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.meter(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("meter$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.optgroup(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("optgroup$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.option(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("option$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.output(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("output$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.progress(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("progress$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.select(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("select$cssSelector", init)

@Suppress("unused")
fun <T> CssSelectable.textarea(
    cssSelector: String = "",
    init: CssSelector.() -> T
): T = selection("textarea$cssSelector", init)
