/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.ProvideTextStyle
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.ui.components.text.AutoHideEmptyText

@OptIn(ExperimentalTvMaterial3Api::class)
@Previews
@Composable
fun CardContent(
    modifier: Modifier = Modifier,
    subtitleAlpha: Float = 0.6f,
    descriptionAlpha: Float = 0.8f,
    title: @Composable () -> Unit = {
        AutoHideEmptyText("title")
    },
    subtitle: @Composable () -> Unit = {
        Box(Modifier.graphicsLayer {
            alpha = subtitleAlpha
        }) {
            AutoHideEmptyText("subtitle")
        }
    },
    description: @Composable () -> Unit = {
        Box(Modifier.graphicsLayer {
            alpha = descriptionAlpha
        }) {
            AutoHideEmptyText("description")
        }
    },
) {
    Column(
        modifier = modifier
    ) {
        ProvideTextStyle(MaterialTheme.typography.titleMedium) {
            title.invoke()
        }
        ProvideTextStyle(MaterialTheme.typography.bodySmall) {
            subtitle.invoke()
        }
        ProvideTextStyle(MaterialTheme.typography.bodySmall) {
            description.invoke()
        }
    }
}