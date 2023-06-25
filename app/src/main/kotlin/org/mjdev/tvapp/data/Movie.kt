/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.data

import android.net.Uri
import org.mjdev.tvapp.base.interfaces.ItemWithImage
import org.mjdev.tvapp.base.interfaces.ItemWithTitle
import org.mjdev.tvapp.base.interfaces.ItemWithVideoUri
import java.io.Serializable

data class Movie(

    var id: Long = 0,
    override var title: String = "",
    var description: String = "",
    override var backgroundImageUrl: String = "",
    var cardImageUrl: String = "",
    override var videoUri: Uri = Uri.EMPTY,
    var studio: String = ""

) : Serializable, ItemWithTitle, ItemWithImage, ItemWithVideoUri {

    val hasVideoUri get() = videoUri != Uri.EMPTY

    override fun toString(): String {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", videoUrl='" + videoUri.toString() + '\'' +
                ", backgroundImageUrl='" + backgroundImageUrl + '\'' +
                ", cardImageUrl='" + cardImageUrl + '\'' +
                '}'
    }

    companion object {
        internal const val serialVersionUID = 727566175075960653L
    }

}