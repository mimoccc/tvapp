/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.gallery

import android.view.Gravity
import android.view.KeyEvent.ACTION_DOWN
import android.view.KeyEvent.KEYCODE_DPAD_CENTER
import android.view.KeyEvent.KEYCODE_DPAD_DOWN
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.KeyEvent.KEYCODE_SYSTEM_NAVIGATION_DOWN
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyListState
import androidx.tv.foundation.lazy.list.itemsIndexed
import androidx.tv.foundation.lazy.list.rememberTvLazyListState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import kotlinx.coroutines.delay
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.ComposeExt.rememberFocusRequester
import org.mjdev.tvlib.extensions.GlobalExt.toggle
import org.mjdev.tvlib.extensions.ModifierExt.detectSwipe
import org.mjdev.tvlib.interfaces.ItemPhoto
import org.mjdev.tvlib.interfaces.ItemWithBackground
import org.mjdev.tvlib.interfaces.ItemWithImage
import org.mjdev.tvlib.ui.components.card.PhotoCard
import org.mjdev.tvlib.ui.components.image.ImageAny
import org.mjdev.tvlib.ui.components.immersivelist.ImmersiveList
import org.mjdev.tvlib.ui.components.image.ImageBackground
import org.mjdev.tvlib.ui.components.tv.TVRow
import timber.log.Timber

@OptIn(ExperimentalTvMaterial3Api::class, ExperimentalAnimationApi::class)
@TvPreview
@Composable
fun Gallery(
    modifier: Modifier = Modifier,
    list: List<Any?> = listOf(Unit),
    index: Int = 0,
    createColorBrush: (
        color: Color,
        gravity: Int
    ) -> Brush = { color, g ->
        Brush.verticalGradient(
            when (g) {
                Gravity.TOP -> listOf(
                    color,
                    color,
                    color,
                    color.copy(alpha = 0.8f),
                    color.copy(alpha = 0.5f),
                    Color.Transparent
                )

                else -> listOf(
                    Color.Transparent,
                    color.copy(alpha = 0.5f),
                    color.copy(alpha = 0.8f),
                    color,
                    color,
                    color,
                )
            }
        )
    },
    infoVisible: MutableState<Boolean> = remember { mutableStateOf(true) },
    currentItemIndex: MutableIntState = remember { mutableIntStateOf(index) },
    focusRequester: FocusRequester = rememberFocusRequester(),
    imageScaleType: MutableState<ContentScale> = remember { mutableStateOf(ContentScale.Crop) },
    imageFromItem: (index: Int) -> Any? = { idx ->
        list[idx].let { data ->
            val photo = (data as? ItemPhoto)?.uri
            val image = (data as? ItemWithImage<*>)?.image
            val background = (data as? ItemWithBackground<*>)?.background
            photo ?: image ?: background
        }
    },
    switchImageScale: () -> Unit = {
        imageScaleType.value = when (imageScaleType.value) {
            ContentScale.Fit -> ContentScale.Crop
            ContentScale.Crop -> ContentScale.Fit
            else -> ContentScale.Fit
        }
    },
    handleKey: (event: KeyEvent) -> Boolean = { ev ->
        val action = ev.nativeKeyEvent.action
        if (action == ACTION_DOWN) {
            val code = ev.nativeKeyEvent.keyCode
            if ((code == KEYCODE_ENTER) || (code == KEYCODE_DPAD_CENTER)) {
                switchImageScale()
                true
            } else if (infoVisible.value) {
                infoVisible.value = true
                if ((code == KEYCODE_DPAD_DOWN) || (code == KEYCODE_SYSTEM_NAVIGATION_DOWN)) {
                    infoVisible.value = false
                    true
                } else {
                    false
                }
            } else {
                infoVisible.value = true
                true
            }
        } else {
            false
        }
    },
    listState: TvLazyListState = rememberTvLazyListState(),

    customOverlay: @Composable (item: Any?) -> Unit = {}
) {
    val initialized = remember { mutableStateOf(false) }
    val imageState: MutableState<Any?> = remember { mutableStateOf(Color.DarkGray) }
    Box(
        modifier = modifier.background(Color.Black, RectangleShape),
        contentAlignment = Alignment.BottomCenter,
    ) {
        ImageBackground(
            modifier = modifier,
            imageState = imageState,
        )
        ImmersiveList(
            modifier = Modifier.fillMaxSize(),
            currentItemIndex = currentItemIndex,
            listAlignment = Alignment.BottomStart,
            background = { _, _ ->
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    ImageAny(
                        src = imageFromItem(currentItemIndex.intValue),
                        contentDescription = null,
                        contentScale = imageScaleType.value,
                        modifier = Modifier
                            .fillMaxSize()
                            .focusable()
                            .focusRequester(focusRequester)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onDoubleTap = {
                                        switchImageScale()
                                    },
                                    onTap = {
                                        infoVisible.toggle()
                                    }
                                )
                            }
                            .pointerInput(Unit) {
                                detectSwipe(
                                    onSwipeLeft = {
                                        if (currentItemIndex.intValue < (list.size - 1)) {
                                            currentItemIndex.intValue += 1
                                            imageState.value = list[currentItemIndex.intValue]
                                        }
                                    },
                                    onSwipeRight = {
                                        if (currentItemIndex.intValue > 0) {
                                            currentItemIndex.intValue -= 1
                                            imageState.value = list[currentItemIndex.intValue]
                                        }
                                    },
                                )
                            }
                            .onKeyEvent { ev -> handleKey(ev) }
                    )
                    customOverlay(list[currentItemIndex.intValue])
                    ItemInfo(
                        src = list[currentItemIndex.intValue],
                        infoVisible = infoVisible,
                        createColorBrush = createColorBrush
                    )
                }
            }
        ) {
            AnimatedVisibility(
                visible = infoVisible.value,
                enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 }),
                exit = fadeOut() + slideOutVertically(targetOffsetY = { it / 2 }),
            ) {
                ImageBackground(
                    modifier = Modifier.fillMaxWidth(),
                    imageState = imageState,
                    transform = { color -> createColorBrush(color, Gravity.BOTTOM) }
                ) {
                    Spacer(modifier = Modifier.height(32.dp))
                    TVRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentPadding = 16.dp,
                        state = listState
                    ) {
                        itemsIndexed(list) { index, item ->
                            val fr = rememberFocusRequester()
                            PhotoCard(
                                modifier = Modifier
                                    .immersiveListItem(index)
                                    .onKeyEvent { ev -> handleKey(ev) },
                                item = item,
                                focusRequester = fr,
                            )
                            SideEffect {
                                val isFocused = (currentItemIndex.intValue == index)
                                if (isFocused) {
                                    imageState.value = list[currentItemIndex.intValue]
                                    try {
                                        fr.requestFocus()
                                    } catch (e: Exception) {
                                        Timber.e(e)
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                }
                LaunchedEffect(currentItemIndex.intValue) {
                    if (transition.currentState != transition.targetState) {
                        if (!infoVisible.value) {
                            try {
                                focusRequester.requestFocus()
                            } catch (e: Exception) {
                                Timber.e(e)
                            }
                        } else {
                            listState.scrollToItem(currentItemIndex.intValue)
                        }
                    }
                }
            }
        }
        LaunchedEffect(currentItemIndex.intValue) {
            if (!initialized.value) {
                listState.scrollToItem(currentItemIndex.intValue)
                initialized.value = true
            }
            delay(5000)
            infoVisible.value = false
        }
    }
}