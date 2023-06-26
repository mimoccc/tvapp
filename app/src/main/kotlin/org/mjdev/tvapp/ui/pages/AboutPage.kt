package org.mjdev.tvapp.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.page.Page
import org.mjdev.tvapp.base.state.ScreenState
import org.mjdev.tvapp.base.ui.components.image.CircleImage

@TvPreview
@Composable
fun AboutPage(
    navController: NavHostController? = null,
    screenState: ScreenState? = null,
) = Page(
    title = R.string.title_about
) {

    Column {

        CircleImage(
            src = Color.Black
        )

    }

}