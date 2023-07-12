/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.tv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.extensions.StringExt.asException
import org.mjdev.tvlib.interfaces.ItemWithTitle
import org.mjdev.tvlib.network.NetworkStatus
import org.mjdev.tvlib.network.isNotConnected
import org.mjdev.tvlib.R
import org.mjdev.tvlib.ui.components.carousel.BigCarousel

@OptIn(ExperimentalTvMaterial3Api::class)
@TvPreview
@Composable
fun BrowseView(
    modifier: Modifier = Modifier,
    showHeader: Boolean = true,
    showNetworkState: Boolean = true,
    title: Any? = "test",
    appIcon: Any? = R.drawable.person,
    userIcon: Any? = R.drawable.person,
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
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(32.dp),
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    customRows: List<@Composable () -> Unit> = emptyList(),
    onTitleClicked: () -> Unit = {},
    onClockClicked: () -> Unit = {},
    onMessageBadgeClicked: () -> Unit = {},
    onUserPicClicked: () -> Unit = {},
    onItemFocused: (item: Any?) -> Unit = {},
    onItemClicked: (item: Any?) -> Unit = {}
) {
    val isEdit = isEditMode()
    ScrollableTvLazyRow(
        modifier = modifier
            .recomposeHighlighter()
            .fillMaxSize(),
        verticalArrangement = verticalArrangement,
        contentPadding = contentPadding
    ) {
        if (isEdit || showHeader) item {
            Header(
                title = title,
                appIcon = appIcon,
                userIcon = userIcon,
                messagesCount = messages.size,
                onTitleClick = onTitleClicked,
                onClockClick = onClockClicked,
                onMessageBadgeClick = onMessageBadgeClicked,
                onUserPicClick = onUserPicClicked,
            )
        }
        if (isEdit || (showNetworkState && networkState.isNotConnected)) item {
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
                    (category as? ItemWithTitle<*>)?.title
                },
                onItemClick = { //category ->
                    // todo
                }
            )
        }
        if (isEdit || featuredItems.isNotEmpty()) item {
            BigCarousel(
                modifier = Modifier.recomposeHighlighter(),
                items = featuredItems,
                onItemSelected = onItemFocused,
                onItemClicked = onItemClicked
            )
        }
        items(customRows) { row -> row.invoke() }
        items(categoriesAndItemsMap.map { entry ->
            Pair(entry.key, entry.value)
        }) { entry ->
            CategoryRow(
                title = (entry.first as? ItemWithTitle<*>)?.title,
                items = entry.second,
                onItemFocus = {
                    onItemFocused(it)
                },
                onItemClick = onItemClicked
            )
        }
    }
}