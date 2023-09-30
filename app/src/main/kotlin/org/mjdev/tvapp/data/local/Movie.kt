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
import org.mjdev.tvlib.interfaces.ItemWithBackground
import org.mjdev.tvlib.interfaces.ItemWithDescription
import org.mjdev.tvlib.interfaces.ItemWithId
import org.mjdev.tvlib.interfaces.ItemWithImage
import org.mjdev.tvlib.interfaces.ItemWithSubtitle
import org.mjdev.tvlib.interfaces.ItemWithTitle
import org.mjdev.tvlib.interfaces.ItemWithUri
import java.io.Serializable
import java.util.Locale

@Suppress("unused")
@Entity
@JsonClass(generateAdapter = true)
class Movie :
    Serializable,
    ItemWithId,
    ItemWithTitle<String>,
    ItemWithSubtitle<String>,
    ItemWithImage<String>,
    ItemWithUri<String>,
    ItemWithBackground<String>,
    ItemWithDescription<String> {

    @Id
    @Json(name = "id")
    override var id: Long = 0

    @Json(name = "studio")
    var studio: String? = null

    @Json(name = "country")
    var country: String? = null

    @Json(name = "isNsfw")
    var isNsfw: Boolean = false

    @Json(name = "title")
    override var title: String? = null

    @Json(name = "description")
    override var description: String? = null

    @Json(name = "videoUri")
    override var uri: String? = null

    override var subtitle: String? = null
        get() = field ?: studio

    @Json(name = "category")
    var category: String? = ""
        get() = (field ?: country)?.replaceFirstChar { ch ->
            // todo : and
            ch.titlecase(Locale.ROOT)
        }

    @Json(name = "backgroundImageUrl")
    override var background: String? = null
        get() = field ?: image

    @Json(name = "imageUrl")
    override var image: String? = null
        get() = field ?: uri

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Movie) return false
        return uri == other.uri
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (category?.hashCode() ?: 0)
        result = 31 * result + (studio?.hashCode() ?: 0)
        result = 31 * result + (country?.hashCode() ?: 0)
        result = 31 * result + isNsfw.hashCode()
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (uri?.hashCode() ?: 0)
        result = 31 * result + (subtitle?.hashCode() ?: 0)
        return result
    }

}