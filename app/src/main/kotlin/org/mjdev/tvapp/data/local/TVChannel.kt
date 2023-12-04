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
import org.mjdev.tvapp.data.remote.Channel
import org.mjdev.tvapp.data.remote.Stream
import org.mjdev.tvlib.interfaces.ItemVideo
import org.mjdev.tvlib.interfaces.ItemWithDescription
import org.mjdev.tvlib.interfaces.ItemWithId
import org.mjdev.tvlib.interfaces.ItemWithImage
import org.mjdev.tvlib.interfaces.ItemWithSubtitle
import org.mjdev.tvlib.interfaces.ItemWithTitle
import org.mjdev.tvlib.interfaces.ItemWithUri
import java.io.Serializable

@Suppress("unused")
@Entity
@JsonClass(generateAdapter = true)
class TVChannel() :
    Serializable,
    ItemWithId,
    ItemWithTitle<String>,
    ItemWithSubtitle<String>,
    ItemWithImage<String>,
    ItemWithUri<String>,
    ItemWithDescription<String>,
    ItemVideo {

    constructor(
        stream: Stream,
        channel: Channel?
    ) : this() {
        this.streamUrl = stream.url
        this.httpReferrer = stream.httpReferrer ?: ""
        this.userAgent = stream.userAgent ?: ""
        this.channelId = channel?.id ?: ""
        this.name = channel?.name ?: ""
        this.altNames = channel?.altNames ?: emptyList()
        this.broadcastArea = channel?.broadcastArea ?: emptyList()
        this.city = channel?.city ?: ""
        this.categories = channel?.categories ?: emptyList()
        this.closed = channel?.closed ?: ""
        this.country = channel?.country ?: ""
        this.isNsfw = channel?.isNsfw ?: false
        this.languages = channel?.languages ?: emptyList()
        this.launched = channel?.launched ?: ""
        this.logo = channel?.logo ?: ""
        this.network = channel?.network ?: ""
        this.owners = channel?.owners ?: emptyList()
        this.replacedBy = channel?.replacedBy ?: ""
        this.subdivision = channel?.subdivision ?: ""
        this.website = channel?.website ?: ""
    }

    @Id
    @Json(name = "id")
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

    override fun toString(): String = "Channel[$title : $uri]"

    override fun equals(other: Any?): Boolean =
        if (other !is TVChannel) false else other.hashCode() == hashCode()

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (channelId?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (altNames?.hashCode() ?: 0)
        result = 31 * result + (network?.hashCode() ?: 0)
        result = 31 * result + (owners?.hashCode() ?: 0)
        result = 31 * result + (country?.hashCode() ?: 0)
        result = 31 * result + (subdivision?.hashCode() ?: 0)
        result = 31 * result + (city?.hashCode() ?: 0)
        result = 31 * result + (broadcastArea?.hashCode() ?: 0)
        result = 31 * result + (languages?.hashCode() ?: 0)
        result = 31 * result + (categories?.hashCode() ?: 0)
        result = 31 * result + (isNsfw?.hashCode() ?: 0)
        result = 31 * result + (launched?.hashCode() ?: 0)
        result = 31 * result + (closed?.hashCode() ?: 0)
        result = 31 * result + (replacedBy?.hashCode() ?: 0)
        result = 31 * result + (website?.hashCode() ?: 0)
        result = 31 * result + (logo?.hashCode() ?: 0)
        result = 31 * result + (streamUrl?.hashCode() ?: 0)
        result = 31 * result + (httpReferrer?.hashCode() ?: 0)
        result = 31 * result + (userAgent?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (image?.hashCode() ?: 0)
        result = 31 * result + (subtitle?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (uri?.hashCode() ?: 0)
        return result
    }

    companion object {

        fun TVChannel.asMovie(category: String): Movie = Movie().also { m ->
            m.category = category
            m.country = country
            m.isNsfw = isNsfw ?: false
            m.studio = network
            m.uri = uri
            m.description = description
            m.image = image
            m.subtitle = subtitle
            m.title = name
        }

    }

}