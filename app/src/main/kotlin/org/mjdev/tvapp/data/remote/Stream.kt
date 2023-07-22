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
class Stream {

    @Json(name = "channel")
    var channel: String? = null

    @Json(name = "url")
    var url: String? = null

    @Json(name = "http_referrer")
    var httpReferrer: String? = null

    @Json(name = "user_agent")
    var userAgent: String? = null

}