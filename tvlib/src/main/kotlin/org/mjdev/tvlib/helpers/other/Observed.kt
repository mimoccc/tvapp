/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.tvlib.helpers.other

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

open class Observed<T>(
    initial: T
) : MutableState<T> {

    private var _value by mutableStateOf(initial)

    override var value: T = initial
        get() = _value
        set(value) {
            _value = value
            field = value
        }

    override fun component1(): T = value

    override fun component2(): (T) -> Unit = { v ->
        value = v
    }

}