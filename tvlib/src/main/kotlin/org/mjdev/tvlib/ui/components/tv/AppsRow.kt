/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.tv

import android.content.ComponentName
import android.content.Context
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
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
import org.mjdev.tvlib.extensions.ComposeExt.computeCardWidth
import org.mjdev.tvlib.helpers.apps.rememberAppsManager
import org.mjdev.tvlib.R
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.data.local.App
import org.mjdev.tvlib.ui.components.card.ItemCard

@Previews
@Composable
fun AppsRow(
    title: Any? = R.string.title_apps,
    rowState: LazyListState = rememberLazyListState(),
    padding: Dp = 8.dp,
    backgroundColor: Color = Color.DarkGray.copy(alpha = 0.3f),
    roundCornerSize: Dp = 8.dp,
    backgroundShape: Shape = RoundedCornerShape(roundCornerSize),
    startApp: Context.(app: App?) -> Unit = { app ->
        startActivity(app?.intent)
    },
    excludedActivities:List<ComponentName> = emptyList(),
    apps: State<List<App>> = rememberAppsManager(
        excluded = excludedActivities.toTypedArray()
    ).collectAsState(emptyList()),
    cardWidth: Dp = computeCardWidth(3f)
) {
    val context: Context = LocalContext.current
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