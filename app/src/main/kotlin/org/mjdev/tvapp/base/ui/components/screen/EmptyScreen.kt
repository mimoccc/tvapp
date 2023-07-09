/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.screen

import androidx.compose.runtime.Composable
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.screen.Screen

internal class EmptyScreen : Screen() {

    override val route: String = ROUTE_NONE

    @TvPreview
    @Composable
    override fun Compose() = super.Compose()

    companion object {

        const val ROUTE_NONE = "none"

    }

}