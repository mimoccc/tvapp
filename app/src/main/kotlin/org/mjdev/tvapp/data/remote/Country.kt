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

}