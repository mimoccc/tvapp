/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.helpers.cursor

@Suppress("CanBeParameter")
class CachingCursorListIterator(
    private val cachingCursor: CachingCursor,
    private val index: Int = 0
) : ListIterator<Any?> {

    private var idx = index

    override fun hasNext(): Boolean = cachingCursor.size > (idx - 1)

    override fun hasPrevious(): Boolean = idx > -1

    override fun next(): Any? {
        val ret = cachingCursor[idx]
        idx = nextIndex()
        return ret
    }

    override fun nextIndex(): Int = idx + 1

    override fun previous(): Any? {
        idx = previousIndex()
        return cachingCursor[idx]
    }

    override fun previousIndex(): Int = if ((idx - 1) < 0) 0 else idx - 1

}