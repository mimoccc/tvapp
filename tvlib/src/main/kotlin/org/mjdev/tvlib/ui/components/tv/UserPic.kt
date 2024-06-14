/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.tv

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.ModifierExt.conditional
import org.mjdev.tvlib.R
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.ui.components.complex.FocusableBox
import org.mjdev.tvlib.ui.components.image.CircleImage

@Previews
@Composable
fun UserPic(
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    src: Any? = R.drawable.person,
    backGroundColor: Color = MaterialTheme.colorScheme.secondary,
    borderColor: Color = Color.White,
    borderSize: Dp = 2.dp,
    contentPadding: Dp = 2.dp,
    onClick: (() -> Unit)? = null
) = FocusableBox(
    modifier = modifier.wrapContentSize(),
    shape = shape,
    onClick = onClick
) {
    val isEdit = isEditMode()
    CircleImage(
        modifier = modifier.conditional(isEdit) {
            size(64.dp)
        },
        backGroundColor = backGroundColor,
        borderSize = borderSize,
        borderColor = borderColor,
        contentPadding = contentPadding + borderSize,
        src = src
    )
}