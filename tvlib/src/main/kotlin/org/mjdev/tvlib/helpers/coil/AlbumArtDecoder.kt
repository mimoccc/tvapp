/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.helpers.coil

import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.util.Xml
import androidx.core.graphics.drawable.toDrawable
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.decode.AssetMetadata
import coil.decode.ContentMetadata
import coil.decode.DecodeResult
import coil.decode.Decoder
import coil.decode.ImageSource
import coil.decode.ResourceMetadata
import coil.fetch.SourceResult
import coil.request.Options
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import org.mjdev.tvlib.network.CacheInterceptor
import org.xmlpull.v1.XmlPullParser
import timber.log.Timber
import java.io.BufferedInputStream
import java.io.File
import java.io.InputStream
import okhttp3.logging.HttpLoggingInterceptor

@Suppress("PrivatePropertyName")
class AlbumArtDecoder(
    private val source: ImageSource,
    private val options: Options
) : Decoder {

    private val USER_AGENT =
        "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/115.0"

    private val context get() = options.context

    private val httpCache by lazy {
        Cache(
            directory = File(
                context.applicationContext.cacheDir,
                "http_cache"
            ),
            maxSize = 1024L * 1024L * 1024L
        )
    }

    private val userAgentInterceptor by lazy {
        UserAgentInterceptor(USER_AGENT)
    }

    private val cacheInterceptor by lazy {
        CacheInterceptor()
    }

    private val httpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().apply {
            addNetworkInterceptor(userAgentInterceptor)
            addNetworkInterceptor(cacheInterceptor)
            addNetworkInterceptor(httpLoggingInterceptor)
            cache(httpCache)
        }.build()
    }

    private fun call(url: String) = httpClient.newCall(
        Request.Builder().url(url).build()
    ).execute()

    override suspend fun decode() = MediaMetadataRetriever().use { retriever ->
        retriever.setDataSource(source)
        var rawData = try {
            retriever.embeddedPicture
        } catch (e: Exception) {
            Timber.e(e)
            null
        }
        if (rawData == null) {
            var albumName = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
            val artistName = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
            if (albumName == null && artistName == null) {
                albumName = source.file().name.substringBeforeLast(".")
            }
            rawData = downloadAlbumArt(albumName, artistName)
        }
        checkNotNull(rawData) {
            "Failed to decode embedded album art picture."
        }
        val rawBitmap = BitmapFactory.decodeByteArray(rawData, 0, rawData.size)
        DecodeResult(
            drawable = rawBitmap.toDrawable(options.context.resources),
            isSampled = true
        )
    }

    private fun downloadAlbumArt(albumName: String?, artistName: String?): ByteArray? {
        var releaseGroupID: String? = null
        var musicSearchUrl = "http://www.musicbrainz.org/ws/2/release-group?query=$albumName"
        if (artistName != null) {
            musicSearchUrl += " AND artist:$artistName"
        }
        musicSearchUrl = musicSearchUrl.replace(" ".toRegex(), "%20")
        val musicSearchIs = retrieveXMLFromURL(musicSearchUrl)
        if (musicSearchIs != null) {
            try {
                releaseGroupID = parse(musicSearchIs)
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
        if (releaseGroupID != null) {
            val url = "http://coverartarchive.org/release-group/$releaseGroupID/front"
            val ins = try {
                val response = call(url)
                if (response.code == 200) {
                    response.body.byteStream()
                } else {
                    Timber.e(response.message)
                    null
                }
            } catch (e: Exception) {
                Timber.e(e)
                null
            }
            return BufferedInputStream(ins).readBytes()
        } else {
            return null
        }
    }

    private fun retrieveXMLFromURL(url: String): InputStream? = try {
        val response = call(url)
        if (response.code == 200) {
            response.body.byteStream()
        } else {
            Timber.e(response.message)
            null
        }
    } catch (e: Exception) {
        Timber.e(e)
        null
    }

    @Throws(Exception::class)
    fun parse(ins: InputStream): String? = ins.use { inst ->
        val parser = Xml.newPullParser()
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(inst, null)
        parser.nextTag()
        readFeed(parser)
    }

    @Throws(Exception::class)
    private fun readFeed(parser: XmlPullParser): String? {
        var releaseGroupID: String? = null
        val ns: String? = null
        parser.require(XmlPullParser.START_TAG, ns, "metadata")
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            val name = parser.name
            Timber.i(name)
            if (name == "release-group-list") {
                releaseGroupID = readReleaseGroupID(parser)
                break
            } else {
                skip(parser)
            }
        }
        return releaseGroupID
    }

    @Throws(Exception::class)
    private fun readReleaseGroupID(parser: XmlPullParser): String? {
        var releaseGroupID: String? = null
        val ns: String? = null
        parser.require(XmlPullParser.START_TAG, ns, "release-group-list")
        val numOfReleaseGroups = parser.getAttributeValue("", "count").toInt()
        if (numOfReleaseGroups == 0) {
            throw Exception("No release id in stream.")
        }
        Timber.i(parser.name)
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                Timber.i(parser.name)
                continue
            }
            val name = parser.name
            Timber.i(name)
            if (name == "release-group") {
                releaseGroupID = parser.getAttributeValue("", "id")
                Timber.i(releaseGroupID)
                break
            } else {
                skip(parser)
            }
        }
        return releaseGroupID
    }

    @Throws(Exception::class)
    private fun skip(parser: XmlPullParser) {
        check(parser.eventType == XmlPullParser.START_TAG)
        var depth = 1
        while (depth != 0) {
            when (parser.next()) {
                XmlPullParser.END_TAG -> depth--
                XmlPullParser.START_TAG -> depth++
            }
        }
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