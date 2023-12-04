/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.card

import android.annotation.SuppressLint
import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.CardScale
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvlib.R
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.computeCardWidth
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.ComposeExt.isFocused
import org.mjdev.tvlib.extensions.ComposeExt.rememberFocusRequester
import org.mjdev.tvlib.extensions.ComposeExt.rememberFocusState
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.interfaces.ItemWithDescription
import org.mjdev.tvlib.interfaces.ItemWithImage
import org.mjdev.tvlib.ui.components.image.ImageAny
import org.mjdev.tvlib.ui.components.image.PhotoImage

@SuppressLint("ModifierParameter")
@OptIn(ExperimentalTvMaterial3Api::class)
@Previews
@Composable
fun PhotoCard(
    item: Any? = null,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    textColor: Color = Color.White,
    focused: Boolean = isEditMode(),
    focusState: MutableState<FocusState?> = rememberFocusState(
        item,
        FocusHelper(focused)
    ),
    focusRequester: FocusRequester = rememberFocusRequester(item),
    @FloatRange(from = 0.0, to = 10.0)
    contrast: Float = 5f,
    @FloatRange(from = -255.0, to = 255.0)
    brightness: Float = -255f,
    placeholder: @Composable () -> Unit = {
        ImageAny(
            modifier = Modifier.fillMaxSize().padding(64.dp),
            src = R.drawable.broken_image
        )
    },
    imageRenderer: @Composable () -> Unit = {
        PhotoImage(
            modifier = Modifier.fillMaxSize(),
            src = (item as? ItemWithImage<*>)?.image,
            contentScale = contentScale,
            contrast = contrast,
            brightness = brightness,
            placeholder = placeholder,
            borderColor = if (focusState.isFocused) Color.Green else Color.Black,
            contentDescription = (item as? ItemWithDescription<*>)?.description?.toString(),
        )
    },
    showTitle: Boolean = true,
    cardWidth: Dp = computeCardWidth(),
    aspectRatio: Float = 16f / 9f,
    scale: CardScale = CardDefaults.scale(),
    titlePadding: PaddingValues = PaddingValues(8.dp),
    onFocus: (item: Any?) -> Unit = {},
    onClick: (item: Any?) -> Unit = {},
) {
    ItemCard(
        item = item,
        modifier = modifier.recomposeHighlighter(),
        contentScale = contentScale,
        textColor = textColor,
        focusState = focusState,
        focusRequester = focusRequester,
        focused = focused,
        aspectRatio = aspectRatio,
        cardWidth = cardWidth,
        scale = scale,
        imageRenderer = imageRenderer,
        titlePadding = titlePadding,
        onFocus = onFocus,
        onClick = onClick,
        showTitle = showTitle
    )
}