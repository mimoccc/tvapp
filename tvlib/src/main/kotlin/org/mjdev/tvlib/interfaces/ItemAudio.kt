/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.interfaces

@Suppress("unused")
interface ItemAudio {

    val uri: Any?

    companion object {

        val ItemAudio.hasImage get() = uri?.toString()?.isNotEmpty() == true

    }

}
