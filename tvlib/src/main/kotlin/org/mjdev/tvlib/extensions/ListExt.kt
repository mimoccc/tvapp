/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.extensions

import androidx.core.os.bundleOf

@Suppress("unused")
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

    fun <T> List<T>.contains(block: (T) -> Boolean) = count(block) > 0

    fun <T> List<T>.containsNot(block: (T) -> Boolean) = count(block) == 0

    fun <E : Any?> List<E>.asMap(
        block: (E) -> Pair<String?, E?>?
    ): Map<String, List<E>> = mutableMapOf<String, MutableList<E>>().apply {
        this@asMap.forEach { e ->
            block.invoke(e)?.also { p ->
                if (p.first != null && p.second != null) {
                    var list = get(p.first)
                    if (list == null) {
                        list = mutableListOf()
                        put(p.first!!, list)
                    }
                    list.add(p.second!!)
                }
            }
        }
    }

    fun List<Pair<String, Any?>>.toBundle() = bundleOf(*this.toTypedArray())

}