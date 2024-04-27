/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.helpers.http

@Suppress("unused")
enum class CacheType {
    NORMAL,
    FORCE;

    companion object {
        const val KEY_CACHE = "Cache"
    }

}
