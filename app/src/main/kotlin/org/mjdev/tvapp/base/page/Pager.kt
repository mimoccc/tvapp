/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.page

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.ComposeExt.isEditMode
import org.mjdev.tvapp.base.navigation.MenuItemClickListener
import org.mjdev.tvapp.base.navigation.MenuItem
import org.mjdev.tvapp.base.navigation.NavHostControllerEx
import org.mjdev.tvapp.base.page.Page.Companion.EMPTY_PAGE
import org.mjdev.tvapp.base.state.ScreenState

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("AutoboxingStateValueProperty")
@TvPreview
@Composable
fun Pager(
    navController: NavHostControllerEx? = null,
    screenState: ScreenState? = null,
    menuItems: MutableList<MenuItem> = mutableListOf(),
    startIndex: Int = 0,
    roundCornerSize: Dp = 0.dp,
    backGroundColor: Color = Color.Transparent,
    backGroundShape: Shape = RoundedCornerShape(roundCornerSize),
    pages: @Composable PagerScope.() -> Unit = {}
) {

    val coroutineScope = rememberCoroutineScope()

    val isEdit = isEditMode()

    val pagerScope = PagerScope(navController, screenState, menuItems).apply {
        pages.invoke(this)
    }

    val pagerState = rememberPagerState(
        pageCount = { pagerScope.size },
        initialPage = startIndex,
    )

    val listener = object : MenuItemClickListener {
        override fun onMenuItemClick(item: MenuItem) {
            coroutineScope.launch {
                pagerState.animateScrollToPage(menuItems.indexOf(item))
            }
        }
    }

    navController?.addMenuClickListener(listener)

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

        page?.let { p ->
            p.navController = navController
            p.screenState = screenState
            p.pagerState = pagerState
            p.content()
        }

    }

}