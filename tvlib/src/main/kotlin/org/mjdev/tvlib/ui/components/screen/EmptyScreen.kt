/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.screen

import androidx.compose.runtime.Composable
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.screen.Screen

internal class EmptyScreen : Screen() {

    override val route: String = ROUTE_NONE

    @Previews
    @Composable
    override fun Content() = super.Content()

    companion object {

        const val ROUTE_NONE = "none"

    }

}