/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.extensions

import android.app.Activity
import android.app.UiModeManager
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.Build
import android.text.format.DateFormat
import timber.log.Timber
import java.util.Date

@Suppress("unused", "UnusedReceiverParameter")
object ContextExt {

    val Context.isEink: Boolean
        get() =
            Build.DEVICE == "Mudita Kompakt"

    val Context.isTV: Boolean
        get() = runCatching {
            val uiModeManager = getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
            return uiModeManager.currentModeType == Configuration.UI_MODE_TYPE_TELEVISION
        }.getOrNull() ?: false

    val Context.timeAsString: String
        get() = DateFormat.getTimeFormat(this).format(Date())

    val Context.dateAsString: String
        get() = DateFormat.getDateFormat(this).format(Date())

    inline fun <reified T : Activity> Context.activity(
        messageBlock: (context: Context) -> String = { ctx ->
            "Expected an activity context but instead found: $ctx"
        }
    ): T = let { context ->
        var ctx: Context? = context
        while (ctx != null && ctx is ContextWrapper) {
            if (ctx is T) {
                return@let ctx
            }
            ctx = try {
                ctx.baseContext
            } catch (e: Exception) {
                Timber.e(e)
                null
            }
        }
        throw IllegalStateException(messageBlock(this))
    }

}
