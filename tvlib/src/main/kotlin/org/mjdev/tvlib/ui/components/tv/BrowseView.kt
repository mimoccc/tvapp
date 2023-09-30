/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.tv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.interfaces.ItemWithTitle
import org.mjdev.tvlib.R
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt
import org.mjdev.tvlib.extensions.ComposeExt.rememberImageLoader
import org.mjdev.tvlib.ui.components.carousel.BigCarousel

@Previews
@Composable
fun BrowseView(
    modifier: Modifier = Modifier,
    showHeader: Boolean = true,
    showNetworkState: Boolean = true,
    showAppUpdateState: Boolean = true,
    title: Any? = "test",
    appIcon: Any? = R.drawable.person,
    userIcon: Any? = R.drawable.person,
    githubUser: String = "mimoccc",
    githubRepository: String = "tvapp",
    messages: List<Any?> = listOf(Unit, Unit, Unit),
    categories: List<Any?> = listOf(Unit, Unit, Unit),
    featuredItems: List<Any?> = listOf(Unit, Unit, Unit),
    categoriesAndItemsMap: Map<*, List<*>> = mapOf<Any?, List<Any?>>(
        Unit to listOf(Unit, Unit, Unit, Unit),
        Unit to listOf(Unit, Unit, Unit, Unit),
        Unit to listOf(Unit, Unit, Unit, Unit),
    ),
    errorState: State<Throwable?> = mutableStateOf(null),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(32.dp),
    contentPadding: PaddingValues = PaddingValues(8.dp),
    customRows: List<@Composable () -> Unit> = emptyList(),
    imageLoader: ImageLoader = rememberImageLoader(),
    onTitleClicked: () -> Unit = {},
    onClockClicked: () -> Unit = {},
    onMessageBadgeClicked: () -> Unit = {},
    onUserPicClicked: () -> Unit = {},
    onItemFocused: (item: Any?) -> Unit = {},
    onItemClicked: (item: Any?) -> Unit = {}
) {
    val isEdit = isEditMode()
    ScrollableTvLazyColumn(
        modifier = modifier
            .fillMaxSize()
            .recomposeHighlighter(),
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
                imageLoader = imageLoader,
            )
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 0.dp)
            ) {
                if (isEdit || showNetworkState) {
                    NetworkErrorMessage(
                        message = R.string.error_no_network,
                        dismissible = false
                    )
                }
                if (isEdit || showAppUpdateState) {
                    AppUpdateAvailableMessage(
                        githubUser = githubUser,
                        githubRepository = githubRepository,
                        title = R.string.title_app_update,
                        message = R.string.msg_update_available,
                    )
                }
                if (isEdit || (errorState.value is Exception)) {
                    ErrorMessage(
                        error = errorState.value,
                        dismissible = false
                    )
                }
            }
        }
        if (isEdit || categories.isNotEmpty()) item {
            Tabs(
                items = categories.map { category ->
                    (category as? ItemWithTitle<*>)?.title ?: category?.toString()
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
                onItemClicked = onItemClicked,
                imageLoader = imageLoader
            )
        }
        items(customRows) { row -> row.invoke() }
        items(categoriesAndItemsMap.map { entry ->
            Pair(entry.key, entry.value)
        }) { entry ->
            CategoryRow(
                title = (entry.first as? ItemWithTitle<*>)?.title ?: entry.first.toString(),
                items = entry.second,
                onItemFocus = {
                    onItemFocused(it)
                },
                onItemClick = onItemClicked,
                imageLoader = imageLoader,
            )
        }
    }
}