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

    override fun equals(other: Any?): Boolean =
        if (other !is Epg) false else other.hashCode() == hashCode()

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (channel?.hashCode() ?: 0)
        result = 31 * result + (site?.hashCode() ?: 0)
        result = 31 * result + (lang?.hashCode() ?: 0)
        result = 31 * result + (days ?: 0)
        result = 31 * result + (url?.hashCode() ?: 0)
        return result
    }

}
