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

@Suppress("unused")
class NoAdBlock : IAdBlocker {
    override fun isBlocked(reqUrl: String?): Boolean = false

    override fun isBlocked(reqUrl: URL?): Boolean = false

    override fun isBlocked(reqUrl: Uri?): Boolean = false
}