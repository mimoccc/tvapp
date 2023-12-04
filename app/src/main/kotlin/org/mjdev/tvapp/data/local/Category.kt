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
import org.mjdev.tvlib.interfaces.ItemWithId
import org.mjdev.tvlib.interfaces.ItemWithImage
import org.mjdev.tvlib.interfaces.ItemWithSubtitle
import org.mjdev.tvlib.interfaces.ItemWithTitle
import java.io.Serializable

@Suppress("unused")
@Entity
@JsonClass(generateAdapter = true)
class Category() :
    Serializable,
    ItemWithId,
    ItemWithTitle<String>,
    ItemWithSubtitle<String>,
    ItemWithImage<String> {

    constructor(title: String) : this() {
        this.title = title
    }

    @Id
    @Json(name = "id")
    override var id: Long = 0

    @Json(name = "title")
    override var title: String? = null

    @Json(name = "subtitle")
    override val subtitle: String? = null

    @Json(name = "image")
    override val image: String? = null

    override fun toString(): String = "Category[$title]"

    override fun equals(other: Any?): Boolean =
        if (other !is Category) false else (other.hashCode() == hashCode())

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (subtitle?.hashCode() ?: 0)
        result = 31 * result + (image?.hashCode() ?: 0)
        return result
    }
}