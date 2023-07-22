/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.data.local

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import org.mjdev.tvlib.interfaces.ItemWithId
import org.mjdev.tvlib.interfaces.ItemWithMessage
import org.mjdev.tvlib.interfaces.ItemWithTitle

@Entity
@JsonClass(generateAdapter = true)
class Message : ItemWithTitle<String>, ItemWithMessage<String>, ItemWithId {

    @Id
    @Json(name = "id")
    override var id: Long = 0

    @Json(name = "title")
    override var title: String? = ""

    @Json(name = "message")
    override var message: String? = ""

    @Json(name = "read")
    val read: Boolean = false

    @Json(name = "deleted")
    val deleted: Boolean = false

}