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
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import org.mjdev.tvlib.activity.ComposableActivity
import org.mjdev.tvlib.extensions.ContextExt.activity
import org.mjdev.tvlib.helpers.coil.AlbumArtDecoder
import org.mjdev.tvlib.network.CacheInterceptor
import java.io.File

@Suppress("MemberVisibilityCanBePrivate")
object ComposeExt {

    @Composable
    fun isEditMode() = LocalInspectionMode.current

    @Composable
    fun isLandscapeMode(): Boolean = with(LocalConfiguration.current) {
        (orientation == Configuration.ORIENTATION_LANDSCAPE) || (screenWidthDp > screenHeightDp)
    }

    @Composable
    fun isPortraitMode(): Boolean = with(LocalConfiguration.current) {
        (orientation == Configuration.ORIENTATION_PORTRAIT) || (screenHeightDp > screenWidthDp)
    }

    // todo more types
    @Composable
    fun Any?.asException(): Exception? = when (this) {
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
    @ExperimentalTvMaterial3Api
    fun rememberDrawerState(
        initialValue: DrawerValue = if (isEditMode()) DrawerValue.Open else DrawerValue.Closed
    ): DrawerState {
        return rememberSaveable(saver = DrawerState.Saver) {
            DrawerState(initialValue)
        }
    }

    @Composable
    fun <T> rememberDerivedStateOf(
        function: () -> T,
        key: Any? = function
    ): State<T> = remember(key) {
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
    fun rememberMutableInteractionSource(
        key: Any? = null
    ) = remember(key) {
        MutableInteractionSource()
    }

    @Composable
    fun rememberFocusState(
        initial: FocusState? = null
    ) = remember(initial) {
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
    fun rememberContentResolver(): ContentResolver {
        val context = LocalContext.current
        return remember(context) {
            context.contentResolver
        }
    }

    @Composable
    fun Bitmap.toDrawable(
        resources: Resources = LocalContext.current.resources
    ): BitmapDrawable = BitmapDrawable(resources, this)

    @SuppressLint("RememberReturnType", "ComposableNaming")
    @Composable
    fun rememberImageLoader(): ImageLoader {
        val context = LocalContext.current
        val activity = runCatching { context.activity<ComposableActivity>() }.getOrNull()
        val cachedImageLoader = activity?.imageLoader
        return cachedImageLoader ?: remember(context) {
            createImageLoader(context)
        }
    }

    fun createImageLoader(context: Context) = ImageLoader.Builder(context)
        .allowHardware(false)
        .allowRgb565(true)
        .addLastModifiedToFileCacheKey(false)
        .crossfade(false)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.DISABLED)
        .respectCacheHeaders(false)
        .networkObserverEnabled(true)
        .okHttpClient {
            OkHttpClient.Builder()
                .cache(
                    Cache(
                        directory = File(
                            context.cacheDir,
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