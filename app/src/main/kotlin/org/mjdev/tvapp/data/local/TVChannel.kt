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
import org.mjdev.tvlib.interfaces.ItemWithDescription
import org.mjdev.tvlib.interfaces.ItemWithId
import org.mjdev.tvlib.interfaces.ItemWithImage
import org.mjdev.tvlib.interfaces.ItemWithSubtitle
import org.mjdev.tvlib.interfaces.ItemWithTitle
import org.mjdev.tvlib.interfaces.ItemWithUri
import java.io.Serializable

// todo

@Suppress("unused")
@Entity
@JsonClass(generateAdapter = true)
class TVChannel :
    Serializable,
    ItemWithId,
    ItemWithTitle<String>,
    ItemWithSubtitle<String>,
    ItemWithImage<String>,
    ItemWithUri<String>,
    ItemWithDescription<String> {

    @Id
    override var id: Long = 0

    @Json(name = "id")
    var channelId: String? = null

    @Json(name = "name")
    var name: String? = null

    @Json(name = "alt_names")
    var altNames: List<String>? = null

    @Json(name = "network")
    var network: String? = null

    @Json(name = "owners")
    var owners: List<String>? = null

    @Json(name = "country")
    var country: String? = null

    @Json(name = "subdivision")
    var subdivision: String? = null

    @Json(name = "city")
    var city: String? = null

    @Json(name = "broadcast_area")
    var broadcastArea: List<String>? = null

    @Json(name = "languages")
    var languages: List<String>? = null

    @Json(name = "categories")
    var categories: List<String>? = null

    @Json(name = "is_nsfw")
    var isNsfw: Boolean? = null

    @Json(name = "launched")
    var launched: String? = null

    @Json(name = "closed")
    var closed: String? = null

    @Json(name = "replaced_by")
    var replacedBy: String? = null

    @Json(name = "website")
    var website: String? = null

    @Json(name = "logo")
    var logo: String? = null

    @Json(name = "url")
    var streamUrl: String? = null

    @Json(name = "http_referrer")
    var httpReferrer: String? = null

    @Json(name = "user_agent")
    var userAgent: String? = null

    override val description: String? get() = null

    override val image: String? get() = logo

    override val subtitle: String? get() = network

    override val title: String? get() = name

    override val uri: String? get() = streamUrl

}