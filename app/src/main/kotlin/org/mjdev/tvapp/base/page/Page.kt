package org.mjdev.tvapp.base.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.ModifierExt.touchable
import org.mjdev.tvapp.base.state.ScreenState

@TvPreview
@Composable
fun Page(
    screenState: ScreenState? = null,
    title: Any? = R.string.app_name,
    block: @Composable () -> Unit = {},
) {

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.DarkGray, RectangleShape).touchable {
               freeFocus()
            }
    ) {

        screenState?.titleState?.value = title

        block()

    }

}