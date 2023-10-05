/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.data.local

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Suppress("unused")
@Entity
@JsonClass(generateAdapter = true)
class Country {

    @Id
    @Json(name = "id")
    var id: Long = 0

    @Json(name = "code")
    var code: String? = null

    @Json(name = "name")
    var name: String? = null

    @Json(name = "languages")
    var languages: List<String>? = null

    @Json(name = "flag")
    var flag: String? = null

}