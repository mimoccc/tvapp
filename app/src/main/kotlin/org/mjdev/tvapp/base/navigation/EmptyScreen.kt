/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.navigation

import androidx.compose.runtime.Composable
import org.mjdev.tvapp.base.annotations.TvPreview

internal class EmptyScreen : Screen() {

    override val completeRoute: String = ROUTE_NONE

    @TvPreview
    @Composable
    override fun Compose() = super.Compose()

    companion object {

        const val ROUTE_NONE = "none"

    }

}