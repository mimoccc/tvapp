/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.helpers.cursor

import android.content.Context
import org.mjdev.tvlib.data.local.AudioItem

class AudioCursor(
    context: Context
) : CachingCursor<AudioItem>(
    context = context,
    uri = AudioItem.URI,
    projection = AudioItem.MEDIA_PROJECTION,
    transform = { c -> AudioItem(c) },
    sortOrder = AudioItem.SORT_ORDER_DATE_DESC,
)
