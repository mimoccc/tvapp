/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.complex

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.CardBorder
import androidx.tv.material3.CardColors
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.CardGlow
import androidx.tv.material3.CardScale
import androidx.tv.material3.CardShape
import androidx.tv.material3.CompactCard
import androidx.tv.material3.MaterialTheme
import org.mjdev.tvlib.R
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.computeCardWidth
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.ComposeExt.isFocused
import org.mjdev.tvlib.extensions.ComposeExt.rememberFocusRequester
import org.mjdev.tvlib.extensions.ComposeExt.rememberFocusState
import org.mjdev.tvlib.extensions.ModifierExt.conditional
import org.mjdev.tvlib.extensions.ModifierExt.focusState
import org.mjdev.tvlib.extensions.ModifierExt.requestFocusOnTouch
import org.mjdev.tvlib.interfaces.ItemWithDescription
import org.mjdev.tvlib.interfaces.ItemWithImage
import org.mjdev.tvlib.interfaces.ItemWithSubtitle
import org.mjdev.tvlib.interfaces.ItemWithTitle
import org.mjdev.tvlib.ui.components.card.FocusHelper
import org.mjdev.tvlib.ui.components.card.colorFocusBorder
import org.mjdev.tvlib.ui.components.card.colorFocusGlow
import org.mjdev.tvlib.ui.components.image.ImageAny
import org.mjdev.tvlib.ui.components.text.AutoHideEmptyText
import org.mjdev.tvlib.ui.components.text.TextAny

@SuppressLint("ModifierParameter")
@Previews
@Composable
fun FocusableCard(
    item: Any? = null,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    scale: CardScale = CardDefaults.scale(),
    shape: CardShape = CardDefaults.shape(),
    textColor: Color = Color.White,
    textBackgroundUnselected: Color = Color.DarkGray.copy(alpha = 0.5f),
    textBackgroundSelected: Color = Color.Green.copy(alpha = 0.5f),
    colors: CardColors = CardDefaults.colors(),
    border: CardBorder = CardDefaults.colorFocusBorder(Color.Green),
    glow: CardGlow = CardDefaults.colorFocusGlow(Color.Green),
    showTitle: Boolean = true,
    placeholder: @Composable () -> Unit = {
        Image(
            painter = painterResource(R.drawable.broken_image),
            contentDescription = "",
            contentScale = contentScale
        )
    },
    imageRenderer: @Composable () -> Unit = {
        ImageAny(
            modifier = Modifier.fillMaxSize(),
            src = (item as? ItemWithImage<*>)?.image,
            contentDescription = (item as? ItemWithDescription<*>)?.description?.toString(),
            contentScale = contentScale,
            placeholder = placeholder
        )
    },
    focused: Boolean = isEditMode(),
    focusRequester: FocusRequester = rememberFocusRequester(item),
    focusState: MutableState<FocusState> = rememberFocusState(
        item,
        FocusHelper(focused)
    ),
    onFocus: ((item: Any?, fromUser: Boolean) -> Unit)? = null,
    onFocusChange: (state: FocusState) -> Unit = { state ->
        if (state.isFocused || state.hasFocus) {
            // todo focused from user ?
            onFocus?.invoke(item, false)
        }
    },
    titlePadding: PaddingValues = PaddingValues(8.dp),
    cardWidth: Dp = computeCardWidth(),
    aspectRatio: Float = 16f / 9f,
    isEdit: Boolean = isEditMode(),
    onClick: ((item: Any?) -> Unit)? = null,
) = CompactCard(
    scale = scale,
    shape = shape,
    colors = colors,
    border = border,
    glow = glow,
    modifier = modifier
        .size(
            width = cardWidth,
            height = cardWidth / aspectRatio
        )
        .focusState(focusState)
        .onFocusChanged { state ->
            onFocusChange(state)
        }
        .requestFocusOnTouch(focusRequester) {
            if (focusState.isFocused) onClick?.invoke(item)
        }
        .conditional(isEdit) {
            defaultMinSize(80.dp)
        },
    image = {
        imageRenderer()
    },
    title = {
        if (showTitle) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        if (focusState.isFocused)
                            textBackgroundSelected
                        else
                            textBackgroundUnselected,
                        RectangleShape
                    )
            ) {
                TextAny(
                    modifier = Modifier.padding(titlePadding),
                    maxLines = 1,
                    color = textColor,
                    style = MaterialTheme.typography.bodyMedium,
                    text = if (isEdit) "title" else (item as? ItemWithTitle<*>)?.title
                )
            }
        }
    },
    subtitle = {
        if (showTitle) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        if (focusState.isFocused)
                            textBackgroundSelected
                        else
                            textBackgroundUnselected,
                        RectangleShape
                    )
            ) {
                AutoHideEmptyText(
                    modifier = Modifier.padding(titlePadding),
                    maxLines = 1,
                    color = textColor,
                    style = MaterialTheme.typography.bodySmall,
                    text = if (isEdit) "subtitle" else (item as? ItemWithSubtitle<*>)?.subtitle
                )
            }
        }
    },
    description = {
        // todo
    },
    onClick = {
        onClick?.invoke(item)
    },
)