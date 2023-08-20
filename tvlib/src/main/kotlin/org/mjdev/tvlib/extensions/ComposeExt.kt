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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.DrawerState
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import coil.decode.VideoFrameDecoder
import coil.disk.DiskCache
import coil.request.CachePolicy
import kotlinx.coroutines.CoroutineScope
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.mjdev.tvlib.extensions.ContextExt.isATv
import org.mjdev.tvlib.helpers.coil.AlbumArtDecoder
import org.mjdev.tvlib.network.CacheInterceptor
import java.io.File

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

    // todo more types
    @Composable
    fun Any?.asException() : Exception? = when (this) {
        null -> null
        is String -> Exception(this)
        is Int -> Exception(stringResource(this))
        else -> Exception(this.toString())
    }

    @OptIn(ExperimentalTvMaterial3Api::class)
    val DrawerState.isOpen: Boolean
        get() = currentValue == DrawerValue.Open

    @OptIn(ExperimentalTvMaterial3Api::class)
    val DrawerState.isClosed: Boolean
        get() = currentValue == DrawerValue.Closed

    @Composable
    fun <T> rememberDerivedStateOf(
        function: () -> T
    ): State<T> = remember {
        derivedStateOf(function)
    }

    @SuppressLint("ComposableNaming")
    @Composable
    fun runOnceOnStart(
        key: Any?,
        block: suspend CoroutineScope.() -> Unit
    ) = LaunchedEffect(key, block)

    @Composable
    fun <T> textFrom(text: T?): String = when (text) {
        null -> ""
        is Unit -> ""
        is Int -> LocalContext.current.getString(text)
        is String -> text
        is MutableState<*> -> textFrom(text.value)
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
                .memoryCachePolicy(CachePolicy.DISABLED)
                .okHttpClient {
                    OkHttpClient.Builder()
                        .cache(
                            Cache(
                                directory = File(
                                    context.applicationContext.cacheDir,
                                    "http_cache"
                                ),
                                maxSize = 1024L * 1024L * 1024L
                            )
                        )
                        .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                        .addNetworkInterceptor(CacheInterceptor())
                        .build()
                }
                .diskCache {
                    DiskCache.Builder()
                        .directory(context.cacheDir.resolve("image_cache"))
                        .maxSizePercent(0.7)
                        .build()
                }
//                .respectCacheHeaders(false)
//                .networkObserverEnabled(true)
                .components {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        add(ImageDecoderDecoder.Factory())
                    } else {
                        add(GifDecoder.Factory())
                    }
                    add(AlbumArtDecoder.Factory())
                    add(VideoFrameDecoder.Factory())
                    add(SvgDecoder.Factory())
                }
                .build()
        }
    }

}