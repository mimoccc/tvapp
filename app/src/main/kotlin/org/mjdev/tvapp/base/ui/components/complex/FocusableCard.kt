/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.complex

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.CardBorder
import androidx.tv.material3.CardColors
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.CardGlow
import androidx.tv.material3.CardScale
import androidx.tv.material3.CardShape
import androidx.tv.material3.CompactCard
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import org.mjdev.tvapp.base.extensions.ComposeExt.computeCardWidth
import org.mjdev.tvapp.base.extensions.ComposeExt.isEditMode
import org.mjdev.tvapp.base.extensions.ComposeExt.isFocused
import org.mjdev.tvapp.base.extensions.ComposeExt.rememberFocusRequester
import org.mjdev.tvapp.base.extensions.ComposeExt.rememberFocusState
import org.mjdev.tvapp.base.extensions.ModifierExt.conditional
import org.mjdev.tvapp.base.extensions.ModifierExt.focusState
import org.mjdev.tvapp.base.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvapp.base.extensions.ModifierExt.requestFocusOnTouch
import org.mjdev.tvapp.base.interfaces.ItemWithDescription
import org.mjdev.tvapp.base.interfaces.ItemWithImage
import org.mjdev.tvapp.base.interfaces.ItemWithSubtitle
import org.mjdev.tvapp.base.interfaces.ItemWithTitle
import org.mjdev.tvapp.base.ui.components.card.colorFocusBorder
import org.mjdev.tvapp.base.ui.components.card.colorFocusGlow
import org.mjdev.tvapp.base.ui.components.image.ImageAny
import org.mjdev.tvapp.base.ui.components.text.AutoHideEmptyText

@SuppressLint("ModifierParameter")
@OptIn(ExperimentalTvMaterial3Api::class)
@Preview
@Composable
fun FocusableCard(
    item: Any? = null,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    scale: CardScale = CardDefaults.scale(),
    shape: CardShape = CardDefaults.shape(),
    textColor: Color = Color.White,
    colors: CardColors = CardDefaults.colors(),
    border: CardBorder = CardDefaults.colorFocusBorder(Color.Green),
    glow: CardGlow = CardDefaults.colorFocusGlow(Color.Green),
    placeholder: @Composable () -> Unit = {},
    imageRenderer: @Composable () -> Unit = {
        ImageAny(
            modifier = Modifier.fillMaxSize(),
            src = (item as? ItemWithImage<*>)?.image,
            contentDescription = (item as? ItemWithDescription<*>)?.description?.toString(),
            contentScale = contentScale,
            placeholder = placeholder
        )
    },
    focusRequester: FocusRequester = rememberFocusRequester(item),
    focusState: MutableState<FocusState?> = rememberFocusState(item),
    onFocusChange: (state: FocusState) -> Unit = {},
    cardWidth: Dp = computeCardWidth(),
    aspectRatio: Float = 16f / 9f,
    onClick: (item: Any?) -> Unit = {},
) {
    val isEdit = isEditMode()
    CompactCard(
        scale = scale,
        shape = shape,
        colors = colors,
        border = border,
        glow = glow,
        modifier = modifier
            .recomposeHighlighter()
            .size(
                width = cardWidth,
                height = cardWidth / aspectRatio
            )
            .focusState(focusState)
            .onFocusChanged { state ->
                onFocusChange(state)
            }
            .requestFocusOnTouch(focusRequester) {
                if (focusState.isFocused) {
                    onClick(item)
                }
            }
            .conditional(isEdit) {
                defaultMinSize(80.dp)
            },
        image = {
            imageRenderer()
        },
        title = {
            AutoHideEmptyText(
                modifier = Modifier.padding(4.dp),
                maxLines = 1,
                color = textColor,
                style = MaterialTheme.typography.titleSmall,
                text = (item as? ItemWithTitle<*>)?.title
            )
        },
        subtitle = {
            AutoHideEmptyText(
                modifier = Modifier.padding(4.dp),
                maxLines = 1,
                color = textColor,
                style = MaterialTheme.typography.displaySmall,
                text = (item as? ItemWithSubtitle<*>)?.subtitle
            )
        },
        description = {
            // todo
        },
        onClick = {
            onClick(item)
        },
    )
}