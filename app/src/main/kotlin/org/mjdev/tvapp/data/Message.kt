/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.data

import org.mjdev.tvapp.base.interfaces.ItemWithId
import org.mjdev.tvapp.base.interfaces.ItemWithMessage
import org.mjdev.tvapp.base.interfaces.ItemWithTitle

data class Message(

    override var id: Long = 0,
    override var title: Any? = "",
    override var message: Any? = "",

    val read: Boolean = false,
    val deleted: Boolean = false

) : ItemWithTitle, ItemWithMessage, ItemWithId