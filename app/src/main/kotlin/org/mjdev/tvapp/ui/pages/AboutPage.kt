/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvapp.R
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.ui.components.card.Card
import org.mjdev.tvlib.ui.components.page.Page
import org.mjdev.tvlib.ui.components.card.CardContent
import org.mjdev.tvlib.ui.components.image.CircleImage
import org.mjdev.tvlib.ui.components.text.TextAny

class AboutPage : Page() {

    override val title: Int = R.string.title_about
    override val icon: ImageVector = Icons.Default.Info

    @OptIn(ExperimentalTvMaterial3Api::class)
    @Previews
    @Composable
    override fun Content() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxSize(),
                onClick = {},
                onLongClick = {},
                shape = CardDefaults.shape(),
                aspectRatio = null,
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
                        CircleImage(
                            modifier = Modifier.size(128.dp),
                            contentPadding = 10.dp,
                            src = R.drawable.milanj
                        )
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
                                    text = R.string.text_app_description
                                )
                            },
                            description = {
                                TextAny(
                                    color = Color.White,
                                    text = R.string.text_author
                                )
                            }
                        )
                    }
                )
            }
        }
    }

}