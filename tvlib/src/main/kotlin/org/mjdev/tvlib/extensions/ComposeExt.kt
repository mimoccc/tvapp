/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.tvlib.extensions

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.Gravity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.media3.common.MediaItem
import androidx.tv.material3.DrawerState
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.bumptech.glide.Glide
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.mjdev.tvlib.extensions.BitmapExt.majorColor
import org.mjdev.tvlib.extensions.ContextExt.isTV
import org.mjdev.tvlib.extensions.MediaItemExt.uri
import org.mjdev.tvlib.helpers.media.ItemType
import org.mjdev.tvlib.interfaces.ItemPhoto
import org.mjdev.tvlib.interfaces.ItemWithBackground
import org.mjdev.tvlib.interfaces.ItemWithImage
import timber.log.Timber
import org.mjdev.tvlib.events.observe.observeEvent
import org.mjdev.tvlib.ui.components.card.FocusHelper

@Suppress("MemberVisibilityCanBePrivate")
object ComposeExt {

    inline fun <T> externalMediaPermission(
        onSdk33AndUp: () -> T,
        onSdk32AndDown: () -> T
    ): T {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            onSdk32AndDown()
        } else {
            onSdk33AndUp()
        }
    }

    private val imagePermissions = externalMediaPermission(
        onSdk33AndUp = { Manifest.permission.READ_MEDIA_IMAGES },
        onSdk32AndDown = { Manifest.permission.READ_EXTERNAL_STORAGE }
    )

    @OptIn(ExperimentalPermissionsApi::class)
    fun MultiplePermissionsState.handlePermissionState(
        context: Context,
        onGranted: () -> Unit
    ) {
        when {
            allPermissionsGranted -> onGranted()
            shouldShowRationale -> {
                val intent = Intent().apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    data = Uri.fromParts("package", context.packageName, null)
                }
                context.startActivity(intent)
            }

            else -> launchMultiplePermissionRequest()
        }
    }

    @SuppressLint("ComposableNaming")
    @OptIn(ExperimentalPermissionsApi::class)
    class ImagePickerHelper(
        private val context: Context,
        private val launcher: ManagedActivityResultLauncher<String, Uri?>,
        private val storagePermission: MultiplePermissionsState
    ) {
        fun pickImage() {
            storagePermission.handlePermissionState(
                context = context,
                onGranted = {
                    launcher.launch("image/*")
                }
            )
        }

        companion object {

            private val imagePermissions = externalMediaPermission(
                onSdk33AndUp = {
                    listOf(
                        Manifest.permission.READ_MEDIA_IMAGES,
                        Manifest.permission.READ_MEDIA_AUDIO,
                        Manifest.permission.READ_MEDIA_VIDEO
                    )
                },
                onSdk32AndDown = {
                    listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            )

            @OptIn(ExperimentalPermissionsApi::class)
            @Composable
            fun rememberImagePickerHelper(
                onResult: (uri: Uri?) -> Unit
            ): ImagePickerHelper {
                val context = LocalContext.current
                val currentOnResult by rememberUpdatedState(onResult)
                val storagePermission = rememberMultiplePermissionsState(imagePermissions)
                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent(),
                    onResult = currentOnResult
                )
                return remember {
                    ImagePickerHelper(context, launcher, storagePermission)
                }
            }

        }
    }

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

    @Composable
    fun isTV(): Boolean = LocalContext.current.isTV

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
        initial: FocusState = FocusHelper(false)
    ) = remember(initial) {
        mutableStateOf(initial)
    }

    @Composable
    fun rememberFocusState(
        key: Any?,
        initial: FocusState = FocusHelper(false),
    ) = remember(key) {
        mutableStateOf(initial)
    }

    @Composable
    fun rememberFocusRequester(
        key: Any? = Unit
    ) = remember(key) {
        FocusRequester()
    }

    @Composable
    inline fun <reified T> observeEvent(
        crossinline onEvent: T.() -> Unit
    ) {
        val scope = rememberCoroutineScope()
        scope.observeEvent<T> { event ->
            onEvent(event)
        }
    }

    fun <T> runNonNullBlock(
        scope: CoroutineScope,
        block: ((T) -> Unit)? = null,
        arg1: T
    ) = block?.let { b ->
        scope.launch { b.invoke(arg1) }
    }

    @SuppressLint("MutableCollectionMutableState")
    @Composable
    inline fun <reified T> observedEvents(): MutableState<MutableList<T>> {
        val events = mutableStateOf<MutableList<T>>(mutableListOf())
        val scope = rememberCoroutineScope()
        scope.observeEvent<T> { e ->
            events.value.add(e)
        }
        return remember { events }
    }

    val MutableState<FocusState>.isFocused
        get() = value.isFocused || value.hasFocus

    val MutableState<FocusState>.isNotFocused
        get() = !isFocused

    @Suppress("EXTENSION_SHADOWED_BY_MEMBER")
    val FocusState.isFocused
        get() = this.isFocused || this.hasFocus

    val FocusState.isNotFocused
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

    fun Modifier.onKey(
        keyCode: Int,
        action: Int = android.view.KeyEvent.ACTION_DOWN,
        block: () -> Unit
    ): Modifier = this then onKeyEvent { ev ->
        if (ev.nativeKeyEvent.action == action) {
            if (ev.nativeKeyEvent.keyCode == keyCode) {
                block()
                true
            } else false
        } else false
    }

    fun createVerticalColorBrush(
        color: Color,
        gravity: Int
    ): Brush = Brush.verticalGradient(
        when (gravity) {
            Gravity.TOP -> listOf(
                color,
                color,
                color,
                color.copy(alpha = 0.8f),
                color.copy(alpha = 0.5f),
                Color.Transparent
            )

            Gravity.BOTTOM -> listOf(
                Color.Transparent,
                color.copy(alpha = 0.5f),
                color.copy(alpha = 0.8f),
                color,
                color,
                color,
            )

            else -> listOf(color)
        }
    )

    // todo mediaItem only
    @Composable
    fun rememberImageFromItem(image: Any?): State<Any?> = remember(image) {
        derivedStateOf {
            if (image is MediaItem) {
                image.uri
            } else {
                val photo = (image as? ItemPhoto)?.uri
                val img = (image as? ItemWithImage<*>)?.image
                val background = (image as? ItemWithBackground<*>)?.background
                val color = image as? Color
                val string = image.toString()
                color ?: photo ?: img ?: background ?: string
            }
        }
    }

    @Composable
    fun <T> produceStateInCoroutine(
        initialValue: T,
        key: Any? = null,
        block: suspend () -> T
    ) = produceState(
        initialValue = initialValue,
        key1 = key
    ) {
        withContext(Dispatchers.IO) {
            value = block()
        }
    }

    @Composable
    fun <T> rememberDerivedState(
        key: Any? = Unit,
        initialValue: T,
        block: suspend () -> T
    ) = produceStateInCoroutine(initialValue, key, block)

    @Composable
    fun <T> rememberDerivedState(
        initialValue: T,
        block: suspend () -> T
    ) = produceStateInCoroutine(initialValue, initialValue, block)

    // todo mediaItem only
    @Composable
    fun rememberColorFromImage(
        image: Any?,
        initialColor: Color = Color.Transparent,
        context: Context = LocalContext.current,
        onError: (error: Throwable) -> Unit = { error -> Timber.e(error) }
    ): State<Color> = rememberDerivedState(image, initialColor) {
        runCatching {
            Glide
                .with(context)
                .load(image)
                .submit()
                .get()
                .toBitmap()
                .majorColor()
        }.onFailure { e ->
            onError(e)
        }.getOrNull() ?: initialColor
    }

    // todo more types
    @Composable
    fun rememberItemType(item: Any?): ItemType = remember(item) { ItemType(item) }

}
