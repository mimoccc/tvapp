package org.mjdev.tvlib.ui.components.audiopreview.dailymotion

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Suppress("unused")
@JsonClass(generateAdapter = true)
data class SearchItem(

    @Json(name = "id")
    var id: String? = null,

    @Json(name = "thumbnail_url")
    var thumbnailUrl: String? = null,

    @Json(name = "title")
    var title: String? = null,

)