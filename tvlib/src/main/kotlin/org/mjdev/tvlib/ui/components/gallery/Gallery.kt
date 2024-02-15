/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.gallery

import androidx.compose.foundation.focusable
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
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyListState
import androidx.tv.foundation.lazy.list.itemsIndexed
import androidx.tv.foundation.lazy.list.rememberTvLazyListState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import kotlinx.coroutines.delay
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.rememberItemType
import org.mjdev.tvlib.extensions.ComposeExt.rememberDerivedState
import org.mjdev.tvlib.extensions.ComposeExt.rememberFocusRequester
import org.mjdev.tvlib.extensions.ComposeExt.rememberImageFromItem
import org.mjdev.tvlib.extensions.GlobalExt.toggle
import org.mjdev.tvlib.extensions.ModifierExt.swipeGestures
import org.mjdev.tvlib.helpers.media.ItemType
import org.mjdev.tvlib.ui.components.card.PhotoCard
import org.mjdev.tvlib.ui.components.image.ImageAny
import org.mjdev.tvlib.ui.components.immersivelist.ImmersiveList
import org.mjdev.tvlib.ui.components.tv.TVRow
import timber.log.Timber

@OptIn(ExperimentalTvMaterial3Api::class)
@Previews
@Composable
fun Gallery(
    modifier: Modifier = Modifier,
    list: List<Any?> = listOf(Unit),
    index: Int = 0,
    delayedHide: Long = 5000,
    controlsState: MutableState<Boolean> = remember { mutableStateOf(true) },
    currentItemIndex: MutableIntState = remember { mutableIntStateOf(index) },
    focusRequester: FocusRequester = rememberFocusRequester(),
    imageScaleType: MutableState<ContentScale> = rememberSaveable(
        saver = contentScaleSaver
    ) {
        mutableStateOf(ContentScale.Crop)
    },
    listState: TvLazyListState = rememberTvLazyListState(),
    switchImageScale: () -> Unit = {
        imageScaleType.value = when (imageScaleType.value) {
            ContentScale.Fit -> ContentScale.Crop
            ContentScale.Crop -> ContentScale.Fit
            else -> ContentScale.Fit
        }
    },
    customContentViewer: @Composable (
        src: Any?,
        type: ItemType,
        list: List<Any?>
    ) -> Unit = { _, _, _ -> },
) {
    val initialized = remember(list, index) { mutableStateOf(false) }

    val nextItem: () -> Unit = {
        if (currentItemIndex.intValue < (list.size - 1)) {
            currentItemIndex.intValue += 1
        }
        controlsState.value = true
    }
    val prevItem: () -> Unit = {
        if (currentItemIndex.intValue > 0) {
            currentItemIndex.intValue -= 1
        }
        controlsState.value = true
    }

    val itemState = rememberDerivedState(currentItemIndex.intValue) {
        list[currentItemIndex.intValue]
    }
    val itemType = rememberItemType(itemState.value)
    val imageSrc = rememberImageFromItem(itemState.value)

    BoxWithControls(
        modifier = modifier,
        src = itemState.value,
        controlsState = controlsState,
        controls = { src, bckColor, state ->
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                ItemInfo(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth(),
                    src = src,
                    visible = state.value,
                    backgroundColor = bckColor
                )
            }
        }
    ) { _, bckColor, state ->
        ImmersiveList(
            modifier = Modifier.fillMaxSize(),
            currentItemIndex = currentItemIndex,
            listAlignment = Alignment.BottomStart,
            background = { _, _ ->
                ImageAny(
                    src = imageSrc.value,
                    contentDescription = null,
                    contentScale = imageScaleType.value,
                    modifier = Modifier
                        .fillMaxSize()
                        .focusable()
                        .focusRequester(focusRequester)
                        .swipeGestures(
                            onTap = { state.toggle() },
                            onDoubleTap = { switchImageScale() },
                            onSwipeLeft = { nextItem() },
                            onSwipeRight = { prevItem() },
                            onSwipeUp = { nextItem() },
                            onSwipeDown = { prevItem() },
                        )
                )
                customContentViewer(imageSrc.value, itemType, list)
            }
        ) {
            ImmersiveInnerList(
                modifier = Modifier.fillMaxWidth(),
                visible = controlsState.value,
                backgroundColor = bckColor
            ) {
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
                            modifier = Modifier.immersiveListItem(index),
                            item = item,
                            focusRequester = fr,
                        )
                        SideEffect {
                            val isFocused = (currentItemIndex.intValue == index)
                            if (isFocused) {
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
        }
    }

    LaunchedEffect(currentItemIndex.intValue, controlsState.value) {
        if (!initialized.value) {
            listState.scrollToItem(currentItemIndex.intValue)
            initialized.value = true
        } else {
            if (controlsState.value && (delayedHide > 0)) {
                delay(delayedHide)
                controlsState.value = false
            }
        }
    }

}

val contentScaleSaver = Saver<MutableState<ContentScale>, String>(
    save = { value -> value.value.toString() },
    restore = { str ->
        mutableStateOf(
            when (str) {
                "Crop" -> ContentScale.Crop
                "Fit" -> ContentScale.Fit
                else -> ContentScale.Crop
            }
        )
    }
)
