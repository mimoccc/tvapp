/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.data

import android.net.Uri
import org.mjdev.tvapp.base.interfaces.ItemWithBackground
import org.mjdev.tvapp.base.interfaces.ItemWithDescription
import org.mjdev.tvapp.base.interfaces.ItemWithId
import org.mjdev.tvapp.base.interfaces.ItemWithImage
import org.mjdev.tvapp.base.interfaces.ItemWithSubtitle
import org.mjdev.tvapp.base.interfaces.ItemWithTitle
import org.mjdev.tvapp.base.interfaces.ItemWithVideoUri
import java.io.Serializable

data class Movie(

    var studio: Any? = "",

    override var id: Long = 0,
    override var title: Any? = "",
    override var description: Any? = "",
    override var backgroundImageUrl: Any? = "",
    override var imageUrl: Any? = "",
    override var videoUri: Any? = Uri.EMPTY,

    ) : Serializable,
    ItemWithId,
    ItemWithTitle,
    ItemWithSubtitle,
    ItemWithImage,
    ItemWithVideoUri,
    ItemWithBackground,
    ItemWithDescription {

    override var subtitle: Any?
        get() = studio
        set(value) { studio = value }

    override fun toString(): String {
        return "Movie{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", videoUrl='" + videoUri.toString() + '\'' +
            ", backgroundImageUrl='" + backgroundImageUrl + '\'' +
            ", cardImageUrl='" + imageUrl + '\'' +
            '}'
    }

}