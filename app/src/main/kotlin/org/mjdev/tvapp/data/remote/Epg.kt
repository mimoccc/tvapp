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
class Epg {

    @Json(name = "id")
    var id: Long = 0

    @Json(name = "channel")
    val channel: String? = null

    @Json(name = "site")
    val site: String? = null

    @Json(name = "lang")
    val lang: String? = null

    @Json(name = "days")
    val days: Int? = null

    @Json(name = "url")
    val url: String? = null

}