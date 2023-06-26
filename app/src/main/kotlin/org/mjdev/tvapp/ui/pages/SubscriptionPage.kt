package org.mjdev.tvapp.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.page.Page
import org.mjdev.tvapp.base.state.ScreenState
import org.mjdev.tvapp.base.ui.components.image.CircleImage

@TvPreview
@Composable
fun SubscriptionPage(
    navController: NavHostController? = null,
    screenState: ScreenState? = null,
) = Page(
    title = R.string.title_subscription,
) {

    Column(
        Modifier.fillMaxSize()
    ) {

        CircleImage(
            src = R.drawable.milanj
        )

    }

}