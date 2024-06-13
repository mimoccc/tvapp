/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.immersivelist

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut

object ImmersiveListDefaults {
    val EnterTransition: EnterTransition = fadeIn(animationSpec = tween(300))
    val ExitTransition: ExitTransition = fadeOut(animationSpec = tween(300))
}