package org.mjdev.tvapp.base.page

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.ComposeExt.isEditMode
import org.mjdev.tvapp.base.navigation.MenuItem
import org.mjdev.tvapp.base.page.Page.Companion.EMPTY_PAGE
import org.mjdev.tvapp.base.state.ScreenState

@SuppressLint("AutoboxingStateValueProperty")
@TvPreview
@Composable
fun Pager(
    navController: NavHostController? = null,
    screenState: ScreenState? = null,
    menuItems: MutableList<MenuItem> = mutableListOf(),
    startIndex: Int = 0,
    roundCornerSize: Dp = 0.dp,
    backGroundColor: Color = Color.Transparent,
    backGroundShape: Shape = RoundedCornerShape(roundCornerSize),
    pages: @Composable PagerScope.(pagerState: PagerState) -> Unit = {}
) {

    val isEdit = isEditMode()
    val pagerScope = PagerScope(navController, screenState, menuItems)
    val pageState = remember { PagerState(startIndex) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backGroundColor, backGroundShape),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        pages.invoke(pagerScope, pageState)

        val index = pageState.index.value

        val page = if (pagerScope.size > index) pagerScope[index]
        else if (isEdit) EMPTY_PAGE
        else null

        page?.let { p ->
            p.navController = navController
            p.screenState = screenState
            p.pagerState = pageState
            p.content()
        }

    }

}