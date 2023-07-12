/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.extensions

import android.app.UiModeManager
import android.content.Context
import android.content.res.Configuration
import android.text.format.DateFormat
import java.util.Date

object ContextExt {

    val Context.isATv: Boolean
        get() {
            val uiModeManager = getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
            return uiModeManager.currentModeType == Configuration.UI_MODE_TYPE_TELEVISION
        }

    val Context.timeAsString: String
        get() = DateFormat.getTimeFormat(this).format(Date())

    val Context.dateAsString: String
        get() = DateFormat.getDateFormat(this).format(Date())

}