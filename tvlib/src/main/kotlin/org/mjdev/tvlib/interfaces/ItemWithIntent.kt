/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.interfaces

import android.content.Intent

@Suppress("unused")
interface ItemWithIntent {

    val intent: Intent?

    companion object {

        val ItemWithIntent.hasIntent get() = (intent != null)

    }

}
