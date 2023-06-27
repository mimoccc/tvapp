package org.mjdev.tvapp.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.page.Page
import org.mjdev.tvapp.base.ui.components.image.CircleImage

class AboutPage : Page() {

    override val title: Int = R.string.title_about
    override val icon: ImageVector = Icons.Default.Info

    @TvPreview
    @Composable
    override fun Content() {

        Column {

            CircleImage(
                modifier = Modifier.fillMaxSize(),
                src = Color.Black
            )

        }

    }

}