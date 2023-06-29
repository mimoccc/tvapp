/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.extensions

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically

@Suppress("unused", "MemberVisibilityCanBePrivate")
object AnimExt {

    const val ANIMATION_DURATION = 1000

    val SlideInVertically = slideInVertically(animationSpec = tween(ANIMATION_DURATION))
    val SlideOutVertically = slideOutVertically(animationSpec = tween(ANIMATION_DURATION))

    val FadeIn = fadeIn(animationSpec = tween(ANIMATION_DURATION))
    val FadeOut = fadeOut(animationSpec = tween(ANIMATION_DURATION))

}