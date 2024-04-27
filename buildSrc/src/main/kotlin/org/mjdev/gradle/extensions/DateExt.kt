/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.extensions

import java.text.SimpleDateFormat
import java.util.Date

@Suppress("SimpleDateFormat")
fun Date.toDateString(
    format: String = "dd.MM.yyyy"
): String = SimpleDateFormat(format).format(this)