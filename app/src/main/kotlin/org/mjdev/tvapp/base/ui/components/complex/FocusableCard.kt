/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.complex

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.CardBorder
import androidx.tv.material3.CardColors
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.CardGlow
import androidx.tv.material3.CardScale
import androidx.tv.material3.CardShape
import androidx.tv.material3.CompactCard
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvapp.base.extensions.ModifierExt.isFocused
import org.mjdev.tvapp.base.extensions.ModifierExt.rememberFocusState
import org.mjdev.tvapp.base.extensions.ModifierExt.touchable
import org.mjdev.tvapp.base.interfaces.ItemWithDescription
import org.mjdev.tvapp.base.interfaces.ItemWithImage
import org.mjdev.tvapp.base.interfaces.ItemWithSubtitle
import org.mjdev.tvapp.base.interfaces.ItemWithTitle
import org.mjdev.tvapp.base.ui.components.card.colorFocusBorder
import org.mjdev.tvapp.base.ui.components.card.colorFocusGlow
import org.mjdev.tvapp.base.ui.components.image.ImageAny
import org.mjdev.tvapp.base.ui.components.text.TextAny

@OptIn(ExperimentalTvMaterial3Api::class)
@SuppressLint("ModifierParameter")
@Preview
@Composable
fun FocusableCard(
    item: Any? = null,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    scale: CardScale = CardDefaults.scale(),
    shape: CardShape = CardDefaults.shape(),
    colors: CardColors = CardDefaults.colors(),
    border: CardBorder = CardDefaults.colorFocusBorder(Color.Green),
    glow: CardGlow = CardDefaults.colorFocusGlow(Color.Green),
    placeholder: @Composable () -> Unit = {},
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    focusState: MutableState<FocusState?> = rememberFocusState(),
    imageRenderer: @Composable (modifier: Modifier) -> Unit = {
        ImageAny(
            modifier = modifier,
            src = (item as? ItemWithImage)?.imageUrl,
            contentDescription = (item as? ItemWithDescription)?.description?.toString(),
            contentScale = contentScale,
            placeholder = placeholder
        )
    },
    onFocus: (item: Any?) -> Unit = {},
    onClick: (item: Any?) -> Unit = {},
) {
    val focusState = rememberSaveable {
        mutableStateOf<FocusState?>(null)
    }
    CompactCard(
        interactionSource = interactionSource,
        scale = scale,
        shape = shape,
        colors = colors,
        border = border,
        glow = glow,
        modifier = modifier
            .onFocusChanged { state ->
                focusState.value = state
            }
            .touchable(
                state = focusState,
                onClick = { onClick(item) },
                onFocus = { onFocus(item) }
            ),
        image = {
            imageRenderer(modifier)
        },
        title = {
            TextAny(
                modifier = Modifier.padding(4.dp),
                text = (item as? ItemWithTitle)?.title
            )
        },
        subtitle = {
            TextAny(
                modifier = Modifier.padding(4.dp),
                text = (item as? ItemWithSubtitle)?.subtitle
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