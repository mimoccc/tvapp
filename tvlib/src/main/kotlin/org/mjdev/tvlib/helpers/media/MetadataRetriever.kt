/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.helpers.media

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.exifinterface.media.ExifInterface
import org.mjdev.tvlib.interfaces.ItemAudio
import org.mjdev.tvlib.interfaces.ItemPhoto
import org.mjdev.tvlib.interfaces.ItemVideo

class MetadataRetriever(
    val context: Context,
    val translateMeta: (name: String, value: String) -> String = { n, v ->
        String.format("%s %s", n, v)
    }
) {

    fun getInfo(
        src: Any?,
        lineSeparator: String = "\n"
    ): String = StringBuilder().apply {
        parseMetadata(src)
            .toList()
            .filter { v ->
                v.second != null && v.second.toString().isNotEmpty()
            }
            .apply {
                val maxTitleLength = (maxOfOrNull { v -> v.first.length } ?: 0) + 1
                forEach { vp ->
                    val translated =
                        translateMeta(vp.first.padEnd(maxTitleLength, ' ') + "\t", vp.second ?: "")
                    append(translated)
                    if (indexOf(vp) < (size - 1)) {
                        append(lineSeparator)
                    }
                }
            }
    }.toString()

    private fun parseMetadata(
        src: Any?
    ): Map<String, String?> = when (src) {
        is ItemPhoto -> {
            mutableMapOf<String, String?>().also { map ->
                ExifInterface(src.uri.toString()).apply {
                    EXIF_DATA_ALL.forEach { attrName ->
                        map[attrName] = getAttribute(attrName)
                    }
                }
            }
        }

        is ItemVideo -> {
            mapOf()
        }

        is ItemAudio -> {
            mapOf()
        }

        // todo
        else -> emptyMap()
    }

    companion object {

        val EXIF_DATA_ALL = listOf(
            ExifInterface.TAG_DATETIME,
            ExifInterface.TAG_ARTIST,
            ExifInterface.TAG_CAMERA_OWNER_NAME,
            ExifInterface.TAG_COPYRIGHT,
            ExifInterface.TAG_DATETIME_ORIGINAL,
            ExifInterface.TAG_GPS_ALTITUDE,
            ExifInterface.TAG_GPS_DEST_LONGITUDE,
            ExifInterface.TAG_GPS_AREA_INFORMATION,
            ExifInterface.TAG_IMAGE_DESCRIPTION,
            ExifInterface.TAG_IMAGE_WIDTH,
            ExifInterface.TAG_IMAGE_LENGTH,
            ExifInterface.TAG_MAKER_NOTE,
            ExifInterface.TAG_SOFTWARE,
            ExifInterface.TAG_USER_COMMENT,
        )

        @Composable
        fun rememberMetaDataRetriever(
            translateMeta: (name: String, value: String) -> String = { n, v ->
                String.format("%s %s", n, v)
            }
        ) = LocalContext.current.let { context ->
            remember { MetadataRetriever(context, translateMeta) }
        }

    }

}