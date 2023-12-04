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
class Country {

    @Json(name = "code")
    var code: String? = null

    @Json(name = "name")
    var name: String? = null

    @Json(name = "languages")
    var languages: List<String>? = null

    @Json(name = "flag")
    var flag: String? = null

    override fun equals(other: Any?): Boolean =
        if (other !is Country) false else other.hashCode() == hashCode()

    override fun hashCode(): Int {
        var result = code?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (languages?.hashCode() ?: 0)
        result = 31 * result + (flag?.hashCode() ?: 0)
        return result
    }

}