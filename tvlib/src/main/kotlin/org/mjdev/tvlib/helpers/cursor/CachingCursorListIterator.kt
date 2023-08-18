/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.helpers.cursor

@Suppress("CanBeParameter")
class CachingCursorListIterator<T>(
    private val cachingCursor: CachingCursor<T>,
    private val index: Int = 0
) : ListIterator<T?> {

    private var idx = index

    override fun hasNext(): Boolean = cachingCursor.size > (idx - 1)

    override fun hasPrevious(): Boolean = idx > -1

    override fun next(): T? {
        val ret = cachingCursor[idx]
        idx = nextIndex()
        return ret
    }

    override fun nextIndex(): Int = idx + 1

    override fun previous(): T? {
        idx = previousIndex()
        return cachingCursor[idx]
    }

    override fun previousIndex(): Int = if ((idx - 1) < 0) 0 else idx - 1

}