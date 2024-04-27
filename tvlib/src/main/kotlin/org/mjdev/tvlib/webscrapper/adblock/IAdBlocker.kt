/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper.adblock

import android.net.Uri
import java.net.URL

interface IAdBlocker {

    fun isBlocked(reqUrl: String?): Boolean
    fun isBlocked(reqUrl: URL?): Boolean
    fun isBlocked(reqUrl: Uri?): Boolean

}
