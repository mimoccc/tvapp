package org.mjdev.tvapp.base.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.state.ScreenState

@TvPreview
@Composable
fun Pager(
    screenState: ScreenState? = null,
    pages: @Composable () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray, RectangleShape)
    ) {

        pages()

    }
}