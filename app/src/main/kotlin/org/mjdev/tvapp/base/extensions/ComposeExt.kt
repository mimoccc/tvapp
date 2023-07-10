/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.tvapp.base.extensions

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.res.Configuration
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mjdev.tvapp.BuildConfig

@Suppress("MemberVisibilityCanBePrivate")
object ComposeExt {

    @Composable
    fun isDebug() = BuildConfig.DEBUG

    @Composable
    fun isEditMode() = LocalInspectionMode.current

    @Composable
    inline fun <reified T> textFrom(text: T?): String = when (text) {
        null -> ""
        is Unit -> ""
        is Int -> LocalContext.current.getString(text)
        is String -> text
        else -> text.toString()
    }

    @Composable
    fun rememberMutableInteractionSource() = remember {
        MutableInteractionSource()
    }

    @Composable
    fun rememberFocusState(
        initial: FocusState? = null
    ) = remember {
        mutableStateOf(initial)
    }

    @Composable
    fun rememberFocusState(
        key: Any?,
        initial: FocusState? = null
    ) = remember(key) {
        mutableStateOf(initial)
    }

    @Composable
    fun rememberFocusRequester(
        key: Any? = Unit
    ) = remember(key) {
        FocusRequester()
    }

    val MutableState<FocusState?>.isFocused
        get() = (value?.isFocused == true) || (value?.hasFocus == true)

    val MutableState<FocusState?>.isNotFocused
        get() = !isFocused

    val FocusState?.isFocused
        get() = (this?.isFocused == true) || (this?.hasFocus == true)

    val FocusState?.isNotFocused
        get() = !isFocused

    @Composable
    fun computeCardWidth(
        ratio: Float = 2.5f
    ): Dp = LocalConfiguration.current.let { config ->
        if (config.orientation == Configuration.ORIENTATION_PORTRAIT)
            config.screenWidthDp / ratio
        else
            config.screenHeightDp / ratio
    }.dp

    @SuppressLint("RememberReturnType", "ComposableNaming")
    @Composable
    fun rememberContentResolver(
        key: Any? = Unit
    ): ContentResolver {
        val context = LocalContext.current
        return remember(key) {
            context.contentResolver
        }
    }

}