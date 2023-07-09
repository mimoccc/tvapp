/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.tv

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyListState
import androidx.tv.foundation.lazy.list.rememberTvLazyListState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.helpers.apps.App
import org.mjdev.tvapp.base.helpers.apps.rememberAppsManager
import org.mjdev.tvapp.base.ui.components.card.ItemCard

@OptIn(ExperimentalTvMaterial3Api::class)
@TvPreview
@Composable
fun AppsRow(
    title: Any? = R.string.title_apps,
    rowState: TvLazyListState = rememberTvLazyListState(),
    padding: Dp = 16.dp,
    backgroundColor: Color = Color.DarkGray,
    startApp: Context.(app: App?) -> Unit = { app ->
        startActivity(app?.intent)
    },
) {
    val context: Context = LocalContext.current
    val apps = rememberAppsManager().collectAsState(emptyList())
    if (apps.value.isNotEmpty()) {
        CategoryRow(
            title = title,
            items = apps.value,
            rowState = rowState,
            padding = padding,
            cardHeight = 80.dp,
            contentScale = ContentScale.Fit,
            backgroundColor = backgroundColor,
            onItemClick = { app ->
                startApp(context, app as? App)
            },
            contentOfItem = { item ->
                ItemCard(
                    item = item,
                    contentScale = ContentScale.Fit,
                    cardHeight = 80.dp,
                    onClick = { app ->
                        startApp(context, app as? App)
                    },
                )
            },
        )
    }
}