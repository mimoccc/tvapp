/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.tv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.ComposeExt.isEditMode
import org.mjdev.tvapp.base.extensions.StringExt.asException
import org.mjdev.tvapp.base.interfaces.ItemWithTitle
import org.mjdev.tvapp.base.network.NetworkStatus
import org.mjdev.tvapp.base.network.isNotConnected
import org.mjdev.tvapp.base.ui.components.carousel.BigCarousel

@TvPreview
@Composable
fun BrowseView(
    modifier: Modifier = Modifier,
    showHeader: Boolean = true,
    showNetworkState: Boolean = true,
    title: Any? = "test",
    messages: List<Any?> = listOf(Unit, Unit, Unit),
    categories: List<Any?> = listOf(Unit, Unit, Unit),
    featuredItems: List<Any?> = listOf(Unit, Unit, Unit),
    categoriesAndItemsMap: Map<*, List<*>> = mapOf<Any?, List<Any?>>(
        Unit to listOf(Unit, Unit, Unit, Unit),
        Unit to listOf(Unit, Unit, Unit, Unit),
        Unit to listOf(Unit, Unit, Unit, Unit),
    ),
    networkState: State<NetworkStatus?> = mutableStateOf(NetworkStatus.Unknown),
    errorState: State<Throwable?> = mutableStateOf(null),
    backgroundColor: Color = Color.DarkGray,
    roundCornerSize: Dp = 0.dp,
    backgroundShape: Shape = RoundedCornerShape(roundCornerSize),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(32.dp),
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    onItemClick: (item: Any?) -> Unit = {}
) {
    val isEdit = isEditMode()
    ScrollableTvLazyRow(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor, backgroundShape),
        verticalArrangement = verticalArrangement,
        contentPadding = contentPadding
    ) {
        if (isEdit || showHeader) item {
            Header(
                title = title,
                messagesCount = messages.size
            )
        }
        if (showNetworkState && (isEdit || networkState.isNotConnected)) item {
            ErrorMessage(
                error = stringResource(R.string.error_no_network).asException(),
                backgroundColor = Color.Black,
                dismissible = false
            )
        }
        if (isEdit || (errorState.value is Exception)) item {
            ErrorMessage(
                error = errorState.value,
                dismissible = false
            )
        }
        if (isEdit || categories.isNotEmpty()) item {
            Tabs(
                items = categories.map { category ->
                    (category as? ItemWithTitle)?.title
                }
            )
        }
        if (isEdit || featuredItems.isNotEmpty()) item {
            BigCarousel(
                modifier = Modifier,
                items = featuredItems,
                onItemClicked = onItemClick
            )
        }
        items(categoriesAndItemsMap.map {
            Pair(it.key, it.value)
        }) { entry ->
            CategoryRow(
                title = (entry.first as? ItemWithTitle)?.title,
                items = entry.second,
                onItemClick = onItemClick
            )
        }
    }
}