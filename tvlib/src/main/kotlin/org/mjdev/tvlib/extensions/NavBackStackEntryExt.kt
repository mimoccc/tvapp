/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.extensions

import androidx.navigation.NavBackStackEntry

object NavBackStackEntryExt {

    @Suppress("UNCHECKED_CAST", "DEPRECATION")
    fun <T : Any> NavBackStackEntry.arg(
        argId: String,
        defaultValue: T?
    ): T? = (arguments?.get(argId) as? T?) ?: defaultValue

}