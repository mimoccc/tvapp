/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.data.local

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Keep
@Entity
@JsonClass(generateAdapter = true)
class ParsedLink {

    @Id
    @Json(name = "id")
    var id: Long = 0

    @Json(name = "title")
    var title: String? = ""

    @Json(name = "title")
    var url: String = ""

    @Json(name = "title")
    var thumbUrl: String? = ""

    @Json(name = "parsed")
    var parsed: Long = 0L
}