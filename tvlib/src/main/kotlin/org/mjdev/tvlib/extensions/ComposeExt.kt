/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.tvlib.extensions

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.res.Configuration
import android.os.Build
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
import androidx.tv.material3.DrawerState
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.CachePolicy
import org.mjdev.tvlib.extensions.ContextExt.isATv

@Suppress("MemberVisibilityCanBePrivate")
object ComposeExt {

    @Composable
    fun isEditMode() = LocalInspectionMode.current

    @Composable
    fun isLandscapeMode(): Boolean =
        LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    @Composable
    fun isPortraitMode(): Boolean =
        LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT

    @Composable
    fun isAtv(): Boolean = LocalContext.current.isATv

    @OptIn(ExperimentalTvMaterial3Api::class)
    val DrawerState.isOpen: Boolean
        get() = currentValue == DrawerValue.Open

    @OptIn(ExperimentalTvMaterial3Api::class)
    val DrawerState.isClosed: Boolean
        get() = currentValue == DrawerValue.Closed

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
        initial: FocusState? = null,
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

    @SuppressLint("RememberReturnType", "ComposableNaming")
    @Composable
    fun rememberImageLoader(): ImageLoader {
        val context = LocalContext.current
        return remember {
            ImageLoader.Builder(context)
                .allowHardware(false)
                .addLastModifiedToFileCacheKey(true)
                .crossfade(false)
                .diskCachePolicy(CachePolicy.ENABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .respectCacheHeaders(true)
                .networkObserverEnabled(true)
                .components {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        add(ImageDecoderDecoder.Factory())
                    } else {
                        add(GifDecoder.Factory())
                    }
                }
                .build()
        }
    }

}