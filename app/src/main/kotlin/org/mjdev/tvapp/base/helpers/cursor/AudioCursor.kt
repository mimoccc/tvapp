/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.helpers.cursor

import android.content.ContentResolver
import android.content.Context

class AudioCursor(
    context: Context,
    val contentResolver: ContentResolver = context.contentResolver
) : CachingCursor(
    context,
    AudioItem.URI,
    AudioItem.MEDIA_PROJECTION,
    transform = { AudioItem(contentResolver, it) }
)