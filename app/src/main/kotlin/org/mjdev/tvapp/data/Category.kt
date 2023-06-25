/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.data

import androidx.compose.runtime.Stable

@Stable
data class Category(
    var name: String,
    var movieList: List<Movie>
)