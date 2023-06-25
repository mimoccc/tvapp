/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.data

data class Message(
    val id: Int = 0,
    val title: String = "",
    val message: String,
    val read: Boolean = false,
    val deleted: Boolean = false
)