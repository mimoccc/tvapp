/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.audiopreview.dailymotion

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Suppress("unused")
@JsonClass(generateAdapter = true)
data class SearchResult (

    @Json(name = "page")
    var page: Long = 0,

    @Json(name = "limit")
    var limit: Long = 0,

    @Json(name = "explicit")
    var explicit: Boolean = false,

    @Json(name = "total")
    var total: Long = 0,

    @Json(name = "hasMore")
    var hasMore: Boolean = false,

    @Json(name = "list")
    var list: List<SearchItem> = emptyList(),

    )