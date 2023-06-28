/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.ProvideTextStyle

@Composable
fun CardContent(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    subtitle: @Composable () -> Unit = {},
    description: @Composable () -> Unit = {},
    subtitleAlpha: Float = 0.6f,
    descriptionAlpha: Float = 0.8f,
) {

    Column(
        modifier = modifier
    ) {

        ProvideTextStyle(MaterialTheme.typography.titleMedium) {
            title.invoke()
        }

        ProvideTextStyle(MaterialTheme.typography.bodySmall) {
            Box(Modifier.graphicsLayer { alpha = subtitleAlpha }) {
                subtitle.invoke()
            }
        }

        ProvideTextStyle(MaterialTheme.typography.bodySmall) {
            Box(Modifier.graphicsLayer { alpha = descriptionAlpha }) {
                description.invoke()
            }
        }

    }

}