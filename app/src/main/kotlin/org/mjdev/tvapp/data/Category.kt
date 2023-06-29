/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.data

import androidx.compose.runtime.Stable
import org.mjdev.tvapp.base.interfaces.ItemWithId
import org.mjdev.tvapp.base.interfaces.ItemWithTitle
import java.io.Serializable

@Stable
data class Category(

    override var id: Long = 0L,
    override var title: Any? = "",

    var movieList: List<Movie> = emptyList(),

) : Serializable, ItemWithTitle, ItemWithId