/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.page

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import org.mjdev.tvapp.base.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvapp.base.extensions.NavExt.rememberNavControllerEx
import org.mjdev.tvapp.base.navigation.MenuItemClickListener
import org.mjdev.tvapp.base.navigation.MenuItem
import org.mjdev.tvapp.base.navigation.NavHostControllerEx
import org.mjdev.tvapp.base.ui.components.page.Page.Companion.EMPTY_PAGE

@SuppressLint("AutoboxingStateValueProperty", "UnusedContentLambdaTargetStateParameter")
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
    val pagerScope = rememberPagerScope(navController, startIndex, pages)

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
    val pageIndex = pagerScope.currentPage.value

    AnimatedContent(
        targetState = pageIndex,
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
        }
    ) {
        Box(
            modifier = Modifier.recomposeHighlighter()
                .fillMaxSize()
                .background(backGroundColor, backGroundShape),
        ) {
            val page = if (pagerScope.size > pageIndex) pagerScope[pageIndex]
            else if (isEdit) EMPTY_PAGE
            else null
            page?.content()
        }
    }

}