/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.carousel

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.tv.material3.CardScale
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.ComposeExt.rememberFocusState
import org.mjdev.tvlib.extensions.ModifierExt.conditional
import org.mjdev.tvlib.ui.components.card.FocusHelper
import org.mjdev.tvlib.ui.components.card.PhotoCard

@SuppressLint("ModifierParameter")
@OptIn(ExperimentalTvMaterial3Api::class)
@Previews
@Composable
fun CarouselCard(
    item: Any? = null,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    scale: CardScale = CardScale.None,
    focused: Boolean = isEditMode(),
    focusState: MutableState<FocusState> = rememberFocusState(
        item,
        FocusHelper(focused)
    ),
    titlePadding: PaddingValues = PaddingValues(8.dp, 12.dp, 8.dp, 12.dp),
    onFocus: ((item: Any?, fromUser: Boolean) -> Unit)? = null,
    onClick: ((item: Any?) -> Unit)? = null
) {
    val isEdit = isEditMode()
    PhotoCard(
        item = item,
        modifier = modifier
            .conditional(isEdit) {
                defaultMinSize(260.dp)
            },
        aspectRatio = 1f,
        focused = focused,
        focusState = focusState,
        contentScale = contentScale,
        scale = scale,
        showTitle = false,
        titlePadding = titlePadding,
        onFocus = onFocus,
        onClick = {
            onClick?.invoke(item)
        }
    )
}