/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.card

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.tv.material3.CardBorder
import androidx.tv.material3.CardColors
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.CardGlow
import androidx.tv.material3.CardScale
import androidx.tv.material3.CardShape
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvapp.base.extensions.ModifierExt.tvAspectRatio
import org.mjdev.tvapp.base.interfaces.ItemWithDescription
import org.mjdev.tvapp.base.interfaces.ItemWithImage
import org.mjdev.tvapp.base.ui.components.complex.FocusableCard
import org.mjdev.tvapp.base.ui.components.image.ImageAny

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
    colors: CardColors = CardDefaults.colors(),
    border: CardBorder = CardDefaults.colorFocusBorder(Color.Green),
    glow: CardGlow = CardDefaults.colorFocusGlow(Color.Green),
    placeholder: @Composable () -> Unit = {},
    aspectRatio: Float? = 16f / 9f,
    imageRenderer: @Composable (modifier: Modifier) -> Unit = {
        ImageAny(
            modifier = modifier.tvAspectRatio(aspectRatio),
            src = (item as? ItemWithImage)?.imageUrl,
            contentDescription = (item as? ItemWithDescription)?.description?.toString(),
            contentScale = contentScale,
            placeholder = placeholder
        )
    },
    onClick: (item: Any?) -> Unit = {},
) = FocusableCard(
    modifier = modifier,
    item = item,
    contentScale = contentScale,
    scale = scale,
    shape = shape,
    colors = colors,
    border = border,
    glow = glow,
    imageRenderer = imageRenderer,
    placeholder = placeholder,
    onClick = onClick,
)