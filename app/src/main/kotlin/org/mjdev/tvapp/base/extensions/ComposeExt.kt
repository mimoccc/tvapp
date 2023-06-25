/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.tvapp.base.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import org.mjdev.tvapp.BuildConfig

@Suppress("MemberVisibilityCanBePrivate")
object ComposeExt {

    @Composable
    fun isDebug() = BuildConfig.DEBUG

    @Composable
    fun isEditMode() = LocalInspectionMode.current

    /**
     * Helper function to return text from any object is given as input.
     * Mainly: Int -> is represented as resource text id String -> String null
     * -> empty string other -> toString() call result
     *
     * @param text Text
     * @param T T type of result
     * @return String generated from any resource given
     */
    @Composable
    inline fun <reified T> textFrom(text: T?): String = when (text) {
        null -> ""
        is Unit -> ""
        is Int -> LocalContext.current.getString(text)
        is String -> text
        else -> text.toString()
    }

}