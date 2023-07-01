/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.page

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.navigation.MenuItem

open class SearchPage : Page() {

    override val title: Any? = R.string.menu_title_search
    override val icon: Any? = Icons.Default.Search
    override val menuGravity = MenuItem.Gravity.Top

    @SuppressLint("ComposableNaming")
    @TvPreview
    @Composable
    override fun Content() = super.Content()

}