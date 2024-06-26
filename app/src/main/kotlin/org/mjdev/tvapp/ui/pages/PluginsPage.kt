/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import org.mjdev.tvapp.R
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.ui.components.card.Card
import org.mjdev.tvlib.ui.components.card.CardContent
import org.mjdev.tvlib.ui.components.page.Page
import org.mjdev.tvlib.ui.components.text.TextAny

class PluginsPage : Page() {
    override val title: Int = R.string.title_plugins
    override val icon: ImageVector = Icons.Default.SettingsInputComponent

    @Previews
    @Composable
    override fun Content() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Card(
                onClick = null,
                onLongClick = null,
                modifier = Modifier.fillMaxSize(),
                shape = CardDefaults.shape(),
                aspectRatio = null,
                colors = CardDefaults.colors(
                    containerColor = Color(0xff242424)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        CardContent(
                            modifier = Modifier.fillMaxWidth(),
                            title = {
                                TextAny(
                                    color = Color.White,
                                    text = title
                                )
                            },
                            subtitle = {
                                TextAny(
                                    color = Color.White,
                                    text = R.string.msg_unimplemented_yet
                                )
                            },
                            description = {
                            }
                        )
                    }
                )
            }
        }
    }
}