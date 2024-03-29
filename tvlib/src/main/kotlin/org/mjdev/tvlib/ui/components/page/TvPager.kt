/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.page

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.NavExt.rememberNavControllerEx
import org.mjdev.tvlib.navigation.MenuItemClickListener
import org.mjdev.tvlib.navigation.MenuItem
import org.mjdev.tvlib.navigation.NavHostControllerEx
import org.mjdev.tvlib.ui.components.page.Page.Companion.EMPTY_PAGE

@SuppressLint("AutoboxingStateValueProperty", "UnusedContentLambdaTargetStateParameter")
@Previews
@Composable
fun TvPager(
    startIndex: Int = 0,
    @Suppress("UNUSED_ANONYMOUS_PARAMETER")
    onPageChange: (index: Int, page: Page?) -> Unit = { index, page -> },
    navController: NavHostControllerEx = rememberNavControllerEx(),
    pages: PagerScope.() -> Unit = { page(EMPTY_PAGE) }
) {
    val currentPage = remember { mutableStateOf<Page?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val pagerScope = rememberPagerScope(navController, startIndex, pages, onPageChange)

    val goToPage: (
        page: Page?
    ) -> Unit = { page ->
        coroutineScope.launch {
            if (page != null) {
                val itemIndex = pagerScope.indexOfPage(page)
                if (itemIndex > -1) {
                    if (currentPage.value != page) {
                        currentPage.value = page
                        pagerScope.scrollToPage(itemIndex)
                    }
                }
            }
        }
    }

    val listener = object : MenuItemClickListener {
        override fun onMenuItemClick(item: MenuItem) {
            if (item.isPage) {
                goToPage(item.menuPage)
            }
        }
    }

    navController.addMenuClickListener(listener)

    AnimatedContent(
        targetState = pagerScope.currentPage.value,
        transitionSpec = {
            if (targetState > initialState) {
                (slideInVertically { height ->
                    height
                } + fadeIn()).togetherWith(slideOutVertically { height ->
                    -height
                } + fadeOut())
            } else {
                (slideInVertically { height ->
                    -height
                } + fadeIn()).togetherWith(slideOutVertically { height ->
                    height
                } + fadeOut())
            }.using(
                SizeTransform(clip = false)
            )
        },
        label = "TvPager"
    ) { pageIndex ->
        val page = if (pagerScope.size > pageIndex) pagerScope[pageIndex] else null
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            page?.content()
        }
    }

}