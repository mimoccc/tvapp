/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.extensions

import android.app.UiModeManager
import android.content.Context
import android.content.res.Configuration
import android.text.format.DateFormat
import java.util.Date

object ContextExt {

//    @Suppress("DEPRECATION")
//    @Dimension(unit = Dimension.DP)
//    fun Context.pxToDp(@Dimension(unit = Dimension.PX) px: Int): Int {
//        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
//        val displayMetrics = DisplayMetrics()
//        windowManager.defaultDisplay.getRealMetrics(displayMetrics)
//        return (px / displayMetrics.densityDpi.toFloat() * DisplayMetrics.DENSITY_DEFAULT)
//            .roundToInt()
//    }

//    val Context.isPhone: Boolean
//        get() = packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY)

    val Context.isATv: Boolean
        get() {
            val uiModeManager = getSystemService(Context.UI_MODE_SERVICE) as UiModeManager
            return uiModeManager.currentModeType == Configuration.UI_MODE_TYPE_TELEVISION
        }

//    @Suppress("DEPRECATION")
//    val Context.isTablet: Boolean
//        get() {
//            val outSize = Point()
//            val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
//            windowManager.defaultDisplay.getRealSize(outSize)
//            outSize.x = pxToDp(outSize.x)
//            outSize.y = pxToDp(outSize.y)
//            val shorterSideDp: Int
//            val longerSideDp: Int
//            if (outSize.x > outSize.y) {
//                shorterSideDp = outSize.y
//                longerSideDp = outSize.x
//            } else {
//                shorterSideDp = outSize.x
//                longerSideDp = outSize.y
//            }
//            return (shorterSideDp > 480) && (longerSideDp > 640) && (!isPhone) && (!isATv)
//        }

    val Context.timeAsString: String
        get() = DateFormat.getTimeFormat(this).format(Date())

    val Context.dateAsString: String
        get() = DateFormat.getDateFormat(this).format(Date())

}