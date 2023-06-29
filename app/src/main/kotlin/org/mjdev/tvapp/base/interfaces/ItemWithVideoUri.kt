/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.interfaces

import android.net.Uri

interface ItemWithVideoUri {

    var videoUri: Any?

    val hasVideoUri
        get() = (videoUri != null) &&
            (videoUri != Uri.EMPTY) &&
            (videoUri.toString().isNotEmpty())

}