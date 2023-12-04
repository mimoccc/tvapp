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

    fun <T> Iterable<T>.takeIf(
        n: Int,
        condition: T.() -> Boolean
    ): List<T> {
        require(n >= 0) { "Requested element count $n is less than zero." }
        if (n == 0) return emptyList()
        if (this is Collection<T>) {
            if (n >= size) return toList()
            if (n == 1) return listOf(first())
        }
        val list = ArrayList<T>(n)
        for (item in this) {
            if(item.condition()) {
                list.add(item)
            }
            if (list.size == n)
                break
        }
        return list.optimizeReadOnlyList()
    }

    private fun <T> List<T>.optimizeReadOnlyList() = when (size) {
        0 -> emptyList()
        1 -> listOf(this[0])
        else -> this
    }

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

    fun <T> List<T>.indexOf(
        predicate: (T) -> Boolean
    ) = firstOrNull(predicate)?.let { item ->
        indexOf(item)
    } ?: -1

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