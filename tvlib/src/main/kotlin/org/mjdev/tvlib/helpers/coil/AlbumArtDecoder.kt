/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.helpers.coil

import android.graphics.BitmapFactory
import androidx.core.graphics.drawable.toDrawable
import coil.ImageLoader
import coil.decode.DecodeResult
import coil.decode.Decoder
import coil.decode.ImageSource
import coil.fetch.SourceResult
import coil.request.Options
import android.media.MediaMetadataRetriever
import coil.annotation.ExperimentalCoilApi
import coil.decode.AssetMetadata
import coil.decode.ContentMetadata
import coil.decode.ResourceMetadata

// todo downloader from net
class AlbumArtDecoder(
    private val source: ImageSource,
    private val options: Options
) : Decoder {

    override suspend fun decode() = MediaMetadataRetriever().use { retriever ->
        retriever.setDataSource(source)
        val rawData = retriever.embeddedPicture
        checkNotNull(rawData) {
            "Failed to decode embedded album art picture."
        }
        val rawBitmap = BitmapFactory.decodeByteArray(rawData, 0, rawData.size)
        DecodeResult(
            drawable = rawBitmap.toDrawable(options.context.resources),
            isSampled = true
        )
    }

    @OptIn(ExperimentalCoilApi::class)
    private fun MediaMetadataRetriever.setDataSource(source: ImageSource) {
        when (val metadata = source.metadata) {
            is AssetMetadata -> {
                options.context.assets.openFd(metadata.filePath).use {
                    setDataSource(it.fileDescriptor, it.startOffset, it.length)
                }
            }
            is ContentMetadata -> {
                setDataSource(options.context, metadata.uri)
            }
            is ResourceMetadata -> {
                setDataSource("android.resource://${metadata.packageName}/${metadata.resId}")
            }
            else -> {
                setDataSource(source.file().toFile().path)
            }
        }
    }

    class Factory : Decoder.Factory {

        override fun create(
            result: SourceResult,
            options: Options,
            imageLoader: ImageLoader
        ): Decoder? {
            return if (!isApplicable(result.mimeType)) {
                null
            } else {
                AlbumArtDecoder(result.source, options)
            }
        }

        private fun isApplicable(mimeType: String?): Boolean {
            return mimeType != null && mimeType.startsWith("audio/")
        }

        override fun equals(other: Any?) = other is Factory

        override fun hashCode() = javaClass.hashCode()

    }

}