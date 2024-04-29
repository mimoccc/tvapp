/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.events.util

import java.util.logging.Level

interface ILogger {
    fun log(level: Level, msg: String)
    fun log(level: Level, msg: String, th: Throwable)
}
