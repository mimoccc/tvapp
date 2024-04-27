/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.helpers.other

class MemorySaver<T> : ISaver<T> {
    
    private var value: T? = null

    override fun get(defaultValue: T): T =
        value ?: defaultValue

    override fun set(value: T) {
        this.value = value
    }

}
