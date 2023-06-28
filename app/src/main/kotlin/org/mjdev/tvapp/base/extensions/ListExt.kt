/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.extensions

object ListExt {

    fun <T> MutableList<T>.addUnique(element: T, replace: Boolean = true) {
        val duplicate = firstOrNull { p ->
            p.toString().contentEquals(element.toString()) || p?.equals(element) == true
        }
        if (duplicate == null) {
            add(element)
        } else {
            if (replace) {
                remove(duplicate)
                add(element)
            } else {
                throw (RuntimeException("Duplicate item."))
            }
        }
    }

}