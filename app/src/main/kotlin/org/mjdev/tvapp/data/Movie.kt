/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import org.mjdev.tvapp.base.interfaces.ItemWithBackground
import org.mjdev.tvapp.base.interfaces.ItemWithDescription
import org.mjdev.tvapp.base.interfaces.ItemWithId
import org.mjdev.tvapp.base.interfaces.ItemWithImage
import org.mjdev.tvapp.base.interfaces.ItemWithSubtitle
import org.mjdev.tvapp.base.interfaces.ItemWithTitle
import org.mjdev.tvapp.base.interfaces.ItemWithUri
import java.io.Serializable

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

    @Json(name = "category")
    var category: String? = ""

    @Json(name = "studio")
    var studio: String? = ""

    @Json(name = "title")
    override var title: String? = ""

    @Json(name = "description")
    override var description: String? = ""

    @Json(name = "backgroundImageUrl")
    override var background: String? = ""

    @Json(name = "imageUrl")
    override var image: String? = ""

    @Json(name = "videoUri")
    override var uri: String? = ""

    override var subtitle: String?
        get() = studio
        set(value) {
            studio = value
        }

    override fun toString(): String = "Movie{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", videoUrl='" + uri.toString() + '\'' +
        ", backgroundImageUrl='" + background + '\'' +
        ", cardImageUrl='" + image + '\'' +
        '}'

}