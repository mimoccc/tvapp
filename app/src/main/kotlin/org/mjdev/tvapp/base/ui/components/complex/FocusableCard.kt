/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.complex

import android.annotation.SuppressLint
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
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
import org.mjdev.tvapp.base.extensions.ComposeExt.isEditMode
import org.mjdev.tvapp.base.extensions.ComposeExt.isFocused
import org.mjdev.tvapp.base.extensions.ComposeExt.rememberFocusRequester
import org.mjdev.tvapp.base.extensions.ComposeExt.rememberFocusState
import org.mjdev.tvapp.base.extensions.ModifierExt.conditional
import org.mjdev.tvapp.base.extensions.ModifierExt.focusState
import org.mjdev.tvapp.base.extensions.ModifierExt.requestFocusOnTouch
import org.mjdev.tvapp.base.interfaces.ItemWithDescription
import org.mjdev.tvapp.base.interfaces.ItemWithImage
import org.mjdev.tvapp.base.interfaces.ItemWithSubtitle
import org.mjdev.tvapp.base.interfaces.ItemWithTitle
import org.mjdev.tvapp.base.ui.components.card.colorFocusBorder
import org.mjdev.tvapp.base.ui.components.card.colorFocusGlow
import org.mjdev.tvapp.base.ui.components.image.ImageAny
import org.mjdev.tvapp.base.ui.components.text.TextAny

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
    colors: CardColors = CardDefaults.colors(),
    border: CardBorder = CardDefaults.colorFocusBorder(Color.Green),
    glow: CardGlow = CardDefaults.colorFocusGlow(Color.Green),
    placeholder: @Composable () -> Unit = {},
    imageRenderer: @Composable (modifier: Modifier) -> Unit = {
        ImageAny(
            modifier = modifier,
            src = (item as? ItemWithImage)?.imageUrl,
            contentDescription = (item as? ItemWithDescription)?.description?.toString(),
            contentScale = contentScale,
            placeholder = placeholder
        )
    },
    focusRequester: FocusRequester = rememberFocusRequester(item),
    focusState: MutableState<FocusState?> = rememberFocusState(item),
    onFocusChange: (state: FocusState) -> Unit = {},
    onClick: (item: Any?) -> Unit = {},
) {
    val isEdit = isEditMode()
    val width = LocalConfiguration.current.let { config ->
        if (config.orientation == ORIENTATION_PORTRAIT) config.screenWidthDp * 0.5f
        else config.screenHeightDp * 0.5f
    }
    CompactCard(
        scale = scale,
        shape = shape,
        colors = colors,
        border = border,
        glow = glow,
        modifier = modifier
            .width(width.dp)
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