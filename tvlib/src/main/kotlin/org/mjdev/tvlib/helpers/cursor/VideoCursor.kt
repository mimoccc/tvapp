/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.helpers.cursor

import android.content.Context

class VideoCursor(
    context: Context
) : CachingCursor(
    context = context,
    uri = VideoItem.URI,
    projection = VideoItem.MEDIA_PROJECTION,
    transform = { c -> VideoItem(c) },
    sortOrder = VideoItem.SORT_ORDER_DATE_DESC,
)