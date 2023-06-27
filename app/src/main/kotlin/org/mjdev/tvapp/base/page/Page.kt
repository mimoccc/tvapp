package org.mjdev.tvapp.base.page

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.navigation.NavHostController
import org.mjdev.tvapp.base.extensions.ModifierExt.touchable
import org.mjdev.tvapp.base.navigation.MenuItem
import org.mjdev.tvapp.base.state.ScreenState

abstract class Page {


    open val title: Any? = null
    open val icon: Any? = null

    var navController: NavHostController? = null
    var screenState: ScreenState? = null

    lateinit var pagerState: PagerState

    val menuItem get() = title?.let { t -> MenuItem(t, icon) }

    @SuppressLint("ComposableNaming")
    @Composable
    fun content() {

        Column(
            Modifier
                .fillMaxSize()
                .background(Color.DarkGray, RectangleShape)
                .touchable {
                    freeFocus()
                }
        ) {

            screenState?.titleState?.value = title

            Content()

        }

    }

    @Composable
    abstract fun Content()


}