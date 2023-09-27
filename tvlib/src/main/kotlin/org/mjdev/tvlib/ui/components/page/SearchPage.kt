/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.page

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import org.mjdev.tvlib.R
import org.mjdev.tvlib.annotations.Previews

open class SearchPage : Page() {

    override val title: Any? = R.string.menu_title_search
    override val icon: Any? = Icons.Default.Search

    @SuppressLint("ComposableNaming")
    @Previews
    @Composable
    override fun Content() = super.Content()

}