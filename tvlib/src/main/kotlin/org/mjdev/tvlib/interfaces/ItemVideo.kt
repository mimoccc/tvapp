/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.interfaces

@Suppress("unused")
interface ItemVideo {

    val uri: Any?

    companion object {

        val ItemVideo.hasImage get() = uri?.toString()?.isNotEmpty() == true

    }

}
