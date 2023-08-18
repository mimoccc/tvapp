/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.helpers.cursor

import android.content.Context

class PhotoCursor(
    context: Context,
) : CachingCursor<PhotoItem>(
    context = context,
    uri = PhotoItem.URI,
    projection = PhotoItem.MEDIA_PROJECTION,
    transform = { c -> PhotoItem(c) },
    sortOrder = PhotoItem.SORT_ORDER_DATE_DESC,
)