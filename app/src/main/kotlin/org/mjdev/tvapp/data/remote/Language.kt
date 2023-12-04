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
class Language {

    @Json(name = "code")
    var code : String? = null

    @Json(name = "name")
    var name : String? = null

    override fun equals(other: Any?): Boolean =
        if (other !is Language) false else other.hashCode() == hashCode()

    override fun hashCode(): Int {
        var result = code?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        return result
    }

}