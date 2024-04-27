/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.data.remote

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@Suppress("unused")
@JsonClass(generateAdapter = true)
class Stream {

    @Json(name = "channel")
    var channelId: String? = null

    @Json(name = "url")
    var url: String? = null

    @Json(name = "http_referrer")
    var httpReferrer: String? = null

    @Json(name = "user_agent")
    var userAgent: String? = null

    override fun equals(other: Any?): Boolean =
        if (other !is Stream) false else other.hashCode() == hashCode()

    override fun hashCode(): Int {
        var result = channelId?.hashCode() ?: 0
        result = 31 * result + (url?.hashCode() ?: 0)
        result = 31 * result + (httpReferrer?.hashCode() ?: 0)
        result = 31 * result + (userAgent?.hashCode() ?: 0)
        return result
    }

}
