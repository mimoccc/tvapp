/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.helpers.media

import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import org.mjdev.tvlib.extensions.MediaItemExt.mediaType
import org.mjdev.tvlib.interfaces.ItemAudio
import org.mjdev.tvlib.interfaces.ItemPhoto
import org.mjdev.tvlib.interfaces.ItemVideo

enum class ItemType {
    Undefined,
    Audio,
    Video,
    Photo;

    companion object {
        // todo more types
        operator fun invoke(item: Any?): ItemType {
            return if (item == null) Undefined else when (item) {
                is ItemAudio -> Audio
                is ItemVideo -> Video
                is ItemPhoto -> Photo
                is MediaItem -> when (item.liveConfiguration.mediaType) {
                    MediaMetadata.MEDIA_TYPE_MUSIC -> Audio
                    MediaMetadata.MEDIA_TYPE_MOVIE -> Video
                    MediaMetadata.MEDIA_TYPE_ALBUM -> Photo
                    else -> Undefined
                }

                else -> Undefined
            }
        }

    }

}
