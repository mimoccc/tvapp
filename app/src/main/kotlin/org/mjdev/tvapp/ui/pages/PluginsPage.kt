/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SettingsInputComponent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.ui.components.card.Card
import org.mjdev.tvapp.base.ui.components.page.Page

class PluginsPage : Page() {

    override val title: Int = R.string.title_plugins
    override val icon: ImageVector = Icons.Default.SettingsInputComponent

    @OptIn(ExperimentalTvMaterial3Api::class)
    @TvPreview
    @Composable
    override fun Content() {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor, background),
            onClick = {},
            onLongClick = {},
            shape = CardDefaults.shape(),
            colors = CardDefaults.colors(
                containerColor = Color(0xff242424)
            ),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                content = {
                }
            )
        }
    }

}