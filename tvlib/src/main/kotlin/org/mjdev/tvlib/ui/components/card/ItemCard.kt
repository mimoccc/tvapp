/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.card

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
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
import org.mjdev.tvlib.R
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.computeCardWidth
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.ComposeExt.rememberFocusRequester
import org.mjdev.tvlib.extensions.ComposeExt.rememberFocusState
import org.mjdev.tvlib.interfaces.ItemWithDescription
import org.mjdev.tvlib.interfaces.ItemWithImage
import org.mjdev.tvlib.ui.components.complex.FocusableCard
import org.mjdev.tvlib.ui.components.image.ImageAny

@SuppressLint("ModifierParameter")
@Previews
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
    aspectRatio: Float = 16f / 9f,
    placeholder : @Composable () -> Unit = {
        Image(
            painter = painterResource(R.drawable.broken_image),
            "",
            modifier
        )
    },
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
    focused: Boolean = isEditMode(),
    focusState: MutableState<FocusState> = rememberFocusState(
        item,
        FocusHelper(focused)
    ),
    focusRequester: FocusRequester = rememberFocusRequester(item),
    titlePadding: PaddingValues = PaddingValues(8.dp),
    showTitle:Boolean = true,
    onFocus: ((item: Any?, fromUser:Boolean) -> Unit)? = null,
    onClick: ((item: Any?) -> Unit)? = null,
) = FocusableCard(
    modifier = modifier,
    item = item,
    focusState = focusState,
    focusRequester = focusRequester,
    focused = focused,
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
    titlePadding = titlePadding,
    onFocus = onFocus,
    onClick = onClick,
    showTitle = showTitle,
)