/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.gallery

import android.view.Gravity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Brush.Companion.verticalGradient
import androidx.compose.ui.graphics.Color
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvlib.extensions.ComposeExt
import org.mjdev.tvlib.extensions.ComposeExt.createVerticalColorBrush
import org.mjdev.tvlib.ui.components.immersivelist.ImmersiveListScope

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ImmersiveListScope.ImmersiveInnerList(
    modifier: Modifier = Modifier,
    visible: Boolean = ComposeExt.isEditMode(),
    backgroundColor: Color = Color.Transparent,
    enter: EnterTransition = fadeIn() + slideInVertically(initialOffsetY = { it / 2 }),
    exit: ExitTransition = fadeOut() + slideOutVertically(targetOffsetY = { it / 2 }),
    content: @Composable ImmersiveListScope.() -> Unit = {},
) {
    val background: State<Brush> = remember(backgroundColor) {
        derivedStateOf {
            if (backgroundColor == Color.Transparent) {
                verticalGradient(
                    listOf(
                        Color.Transparent,
                        Color.Transparent
                    )
                )
            } else {
                createVerticalColorBrush(
                    backgroundColor,
                    Gravity.BOTTOM
                )
            }
        }
    }
    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = enter,
        exit = exit
    ) {
        Box(
            modifier = modifier.background(background.value)
        ) {
            content()
        }
    }
}