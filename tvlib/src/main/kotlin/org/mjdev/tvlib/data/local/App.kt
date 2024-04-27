/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.data.local

import android.content.Intent
import android.graphics.drawable.Drawable
import org.mjdev.tvlib.interfaces.ItemWithImage
import org.mjdev.tvlib.interfaces.ItemWithIntent
import org.mjdev.tvlib.interfaces.ItemWithTitle

data class App(
    override var title: String?,
    override var image: Drawable?,
    override var intent: Intent?,
) : ItemWithTitle<String>, ItemWithImage<Drawable>, ItemWithIntent {

    override fun equals(other: Any?): Boolean =
        if (other !is App) false else other.hashCode() == hashCode()

    override fun hashCode(): Int {
        var result = title?.hashCode() ?: 0
        result = 31 * result + (image?.hashCode() ?: 0)
        result = 31 * result + (intent?.hashCode() ?: 0)
        return result
    }

}
