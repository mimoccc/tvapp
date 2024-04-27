/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.helpers.cursor

import android.content.Context
import org.mjdev.tvlib.data.local.VideoItem

class VideoCursor(
    context: Context
) : CachingCursor<VideoItem>(
    context = context,
    uri = VideoItem.URI,
    projection = VideoItem.MEDIA_PROJECTION,
    transform = { c -> VideoItem(c) },
    sortOrder = VideoItem.SORT_ORDER_DATE_DESC,
)
