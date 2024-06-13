/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.immersivelist

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.tv.material3.ExperimentalTvMaterial3Api

@Immutable
@ExperimentalTvMaterial3Api
class ImmersiveListBackgroundScope(
    boxScope: BoxScope
) : BoxScope by boxScope {

    @Suppress("unused")
    @Composable
    fun AnimatedVisibility(
        visible: Boolean,
        modifier: Modifier = Modifier,
        enter: EnterTransition = ImmersiveListDefaults.EnterTransition,
        exit: ExitTransition = ImmersiveListDefaults.ExitTransition,
        label: String = "AnimatedVisibility",
        content: @Composable AnimatedVisibilityScope.() -> Unit
    ) {
        androidx.compose.animation.AnimatedVisibility(
            visible,
            modifier,
            enter,
            exit,
            label,
            content
        )
    }

    @Suppress("unused")
    @Composable
    fun AnimatedContent(
        targetState: Int,
        modifier: Modifier = Modifier,
        transitionSpec: AnimatedContentTransitionScope<Int>.() -> ContentTransform = {
            ImmersiveListDefaults.EnterTransition.togetherWith(ImmersiveListDefaults.ExitTransition)
        },
        contentAlignment: Alignment = Alignment.TopStart,
        label: String = "AnimatedContent",
        content: @Composable AnimatedVisibilityScope.(targetState: Int) -> Unit
    ) {
        androidx.compose.animation.AnimatedContent(
            targetState,
            modifier,
            transitionSpec,
            contentAlignment,
            content = content,
            label = label
        )
    }
}