/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.data.events

import org.mjdev.tvapp.data.local.Media

data class SyncEvent(
    val syncerName: String,
    val data : Media
)