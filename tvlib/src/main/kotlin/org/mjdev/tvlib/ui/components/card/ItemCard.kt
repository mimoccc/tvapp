/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.card

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.tv.material3.CardBorder
import androidx.tv.material3.CardColors
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.CardGlow
import androidx.tv.material3.CardScale
import androidx.tv.material3.CardShape
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvlib.extensions.ComposeExt.computeCardWidth
import org.mjdev.tvlib.extensions.ComposeExt.rememberFocusState
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.interfaces.ItemWithDescription
import org.mjdev.tvlib.interfaces.ItemWithImage
import org.mjdev.tvlib.ui.components.complex.FocusableCard
import org.mjdev.tvlib.ui.components.image.ImageAny

@SuppressLint("ModifierParameter")
@OptIn(ExperimentalTvMaterial3Api::class)
@Preview
@Composable
fun ItemCard(
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
    aspectRatio: Float = 16f / 9f,
    imageRenderer: @Composable () -> Unit = {
        ImageAny(
            modifier = modifier.fillMaxSize(),
            src = (item as? ItemWithImage<*>)?.image,
            contentDescription = (item as? ItemWithDescription<*>)?.description?.toString(),
            contentScale = contentScale,
            placeholder = placeholder
        )
    },
    cardWidth: Dp = computeCardWidth(),
    focusState: MutableState<FocusState?> = rememberFocusState(item),
    onClick: (item: Any?) -> Unit = {},
) = FocusableCard(
    modifier = modifier.recomposeHighlighter(),
    item = item,
    focusState = focusState,
    contentScale = contentScale,
    aspectRatio = aspectRatio,
    textColor = textColor,
    scale = scale,
    shape = shape,
    colors = colors,
    border = border,
    glow = glow,
    cardWidth = cardWidth,
    imageRenderer = imageRenderer,
    placeholder = placeholder,
    onClick = onClick,
)