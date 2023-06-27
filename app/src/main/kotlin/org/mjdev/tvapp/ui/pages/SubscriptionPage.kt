package org.mjdev.tvapp.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.page.Page
import org.mjdev.tvapp.base.ui.components.image.CircleImage

class SubscriptionPage : Page() {

    override val title : Int = R.string.title_subscription
    override val icon : ImageVector = Icons.Default.ShoppingCart

    @TvPreview
    @Composable
    override fun Content () {

        Column(
            Modifier.fillMaxSize()
        ) {

            CircleImage(
                src = R.drawable.milanj
            )

        }

    }

}