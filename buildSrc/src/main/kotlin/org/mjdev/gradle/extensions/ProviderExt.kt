/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.extensions

import org.gradle.api.provider.Provider

val <T> Provider<T>.int: Int
    get() = get().toString().toInt()

val <T> Provider<T>.string: String
    get() = get().toString()