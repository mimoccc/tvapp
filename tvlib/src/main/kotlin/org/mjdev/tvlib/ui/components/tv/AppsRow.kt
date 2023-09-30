/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.tv

import android.content.Context
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyListState
import androidx.tv.foundation.lazy.list.rememberTvLazyListState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvlib.extensions.ComposeExt.computeCardWidth
import org.mjdev.tvlib.helpers.apps.App
import org.mjdev.tvlib.helpers.apps.rememberAppsManager
import org.mjdev.tvlib.R
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.ui.components.card.ItemCard

@OptIn(ExperimentalTvMaterial3Api::class)
@Previews
@Composable
fun AppsRow(
    title: Any? = R.string.title_apps,
    rowState: TvLazyListState = rememberTvLazyListState(),
    padding: Dp = 8.dp,
    backgroundColor: Color = Color.DarkGray.copy(alpha = 0.3f),
    roundCornerSize: Dp = 8.dp,
    backgroundShape: Shape = RoundedCornerShape(roundCornerSize),
    startApp: Context.(app: App?) -> Unit = { app ->
        startActivity(app?.intent)
    },
    apps: State<List<App>> = rememberAppsManager().collectAsState(emptyList())
) {
    val context: Context = LocalContext.current
    val cardWidth = computeCardWidth(3f)
    if (apps.value.isNotEmpty()) {
        CategoryRow(
            title = title,
            items = apps.value,
            rowState = rowState,
            padding = padding,
            cardWidth = cardWidth,
            contentScale = ContentScale.Fit,
            backgroundColor = backgroundColor,
            roundCornerSize = roundCornerSize,
            backgroundShape = backgroundShape,
            onItemClick = { app ->
                startApp(context, app as? App)
            },
            contentOfItem = { item ->
                ItemCard(
                    item = item,
                    contentScale = ContentScale.Fit,
                    cardWidth = cardWidth,
                    onClick = { app ->
                        startApp(context, app as? App)
                    }
                )
            },
        )
    }
}