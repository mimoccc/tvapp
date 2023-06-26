package org.mjdev.tvapp.base.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import org.mjdev.tvapp.base.annotations.TvPreview

@TvPreview
@Composable
fun Pager(
    pages: @Composable () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red, RectangleShape)
    ) {

        pages()

    }
}