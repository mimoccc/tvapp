@file:Suppress("NOTHING_TO_INLINE", "unused")

package org.mjdev.tvlib.ui.toolkit

internal inline fun packFloats(val1: Float, val2: Float): Long {
    val v1 = val1.toBits().toLong()
    val v2 = val2.toBits().toLong()
    return v1.shl(32) or (v2 and 0xFFFFFFFF)
}

internal inline fun unpackFloat1(value: Long): Float {
    return Float.fromBits(value.shr(32).toInt())
}

internal inline fun unpackFloat2(value: Long): Float {
    return Float.fromBits(value.and(0xFFFFFFFF).toInt())
}

internal inline fun packInts(val1: Int, val2: Int): Long {
    return val1.toLong().shl(32) or (val2.toLong() and 0xFFFFFFFF)
}

internal inline fun unpackInt1(value: Long): Int {
    return value.shr(32).toInt()
}

internal inline fun unpackInt2(value: Long): Int {
    return value.and(0xFFFFFFFF).toInt()
}