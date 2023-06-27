package org.mjdev.tvapp.base.page

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.navigation.NavHostController
import org.mjdev.tvapp.base.navigation.MenuItem
import org.mjdev.tvapp.base.state.ScreenState

@SuppressLint("AutoboxingStateValueProperty")
@Composable
fun Pager(
    navController: NavHostController? = null,
    screenState: ScreenState? = null,
    menuItems: MutableList<MenuItem> = mutableListOf(),
    startIndex: Int = 0,
    pages: @Composable PagerScope.(pagerState: PagerState) -> Unit = {}
) {

    val pagerScope = PagerScope(navController, screenState, menuItems)
    val pageState = remember { PagerState(startIndex) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray, RectangleShape)
    ) {

        pages.invoke(pagerScope, pageState)

        val index = pageState.index.value

        pagerScope[index].let { page ->

            page.navController = navController
            page.screenState = screenState
            page.pagerState = pageState

            page.content()

        }

    }

}