/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.page

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.ComposeExt.isEditMode
import org.mjdev.tvapp.base.extensions.NavExt.rememberNavControllerEx
import org.mjdev.tvapp.base.ui.components.navigation.MenuItemClickListener
import org.mjdev.tvapp.base.ui.components.navigation.MenuItem
import org.mjdev.tvapp.base.ui.components.navigation.NavHostControllerEx
import org.mjdev.tvapp.base.ui.components.page.Page.Companion.EMPTY_PAGE

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("AutoboxingStateValueProperty")
@TvPreview
@Composable
fun TvPager(
    navController: NavHostControllerEx = rememberNavControllerEx(),
    startIndex: Int = 0,
    roundCornerSize: Dp = 0.dp,
    backGroundColor: Color = Color.Transparent,
    backGroundShape: Shape = RoundedCornerShape(roundCornerSize),
    pages: PagerScope.() -> Unit = {}
) {

    val isEdit = isEditMode()

    val currentPage = remember { mutableStateOf<Page?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val pagerScope = rememberPagerScope(navController, pages)
    val pagerState = rememberPagerState(
        pageCount = { pagerScope.size },
        initialPage = startIndex,
    )

    val goToPage: (
        page: Page?
    ) -> Unit = { page ->
        coroutineScope.launch {
            if (page != null) {
                val itemIndex = pagerScope.indexOfPage(page)
                if (itemIndex > -1) {
                    if (currentPage.value != page) {
                        currentPage.value = page
                        pagerState.animateScrollToPage(itemIndex)
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

    VerticalPager(
        modifier = Modifier
            .fillMaxSize()
            .background(backGroundColor, backGroundShape),
        state = pagerState,
        beyondBoundsPageCount = 0
    ) { pageIndex ->

        val page = if (pagerScope.size > pageIndex) pagerScope[pageIndex]
        else if (isEdit) EMPTY_PAGE
        else null

        page?.content()

    }

}