/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Suppress("unused")
@JsonClass(generateAdapter = true)
class Channel {

    @Json(name = "id")
    var id: String? = null

    @Json(name = "name")
    var name: String? = null

    @Json(name = "alt_names")
    var altNames: List<String>? = null

    @Json(name = "network")
    var network: String? = null

    @Json(name = "owners")
    var owners: List<String>? = null

    @Json(name = "country")
    var country: String? = null

    @Json(name = "subdivision")
    var subdivision: String? = null

    @Json(name = "city")
    var city: String? = null

    @Json(name = "broadcast_area")
    var broadcastArea: List<String>? = null

    @Json(name = "languages")
    var languages: List<String>? = null

    @Json(name = "categories")
    var categories: List<String>? = null

    @Json(name = "is_nsfw")
    var isNsfw: Boolean? = null

    @Json(name = "launched")
    var launched: String? = null

    @Json(name = "closed")
    var closed: String? = null

    @Json(name = "replaced_by")
    var replacedBy: String? = null

    @Json(name = "website")
    var website: String? = null

    @Json(name = "logo")
    var logo: String? = null

}