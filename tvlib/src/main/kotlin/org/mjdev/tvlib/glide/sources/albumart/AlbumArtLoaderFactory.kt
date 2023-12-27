/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.glide.sources.albumart

import android.content.Context
import android.media.MediaMetadataRetriever
import android.util.Xml
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoader.LoadData
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import com.bumptech.glide.signature.ObjectKey
import kotlinx.coroutines.Job
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.mjdev.tvlib.BuildConfig
import org.mjdev.tvlib.extensions.GlobalExt.launch
import org.mjdev.tvlib.helpers.http.UserAgentInterceptor
import org.mjdev.tvlib.helpers.http.MimeTypeMapUtils
import org.mjdev.tvlib.helpers.http.NetworkConnectionInterceptor
import org.xmlpull.v1.XmlPullParser
import java.io.ByteArrayInputStream
import java.io.File
import java.io.InputStream

// todo better performance && precache && dagger
@Suppress("UNCHECKED_CAST")
class AlbumArtLoaderFactory(
    private val context: Context
) : ModelLoader<String?, InputStream?>, ModelLoaderFactory<String?, InputStream?> {

    private val httpCache by lazy {
        Cache(
            directory = File(
                context.cacheDir,
                "http_cache"
            ),
            maxSize = 1024L * 1024L * 1024L
        )
    }
    private val userAgentInterceptor by lazy { UserAgentInterceptor() }
    private val networkConnectionInterceptor by lazy { NetworkConnectionInterceptor(context) }

    //    private val adBlockInterceptor by lazy { AdBlockInterceptor(context) }
//    private val cacheInterceptor by lazy { CacheInterceptor() }
    private val httpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        )
    }
    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().apply {
            addNetworkInterceptor(networkConnectionInterceptor)
            addNetworkInterceptor(userAgentInterceptor)
            addNetworkInterceptor(httpLoggingInterceptor)
//            addNetworkInterceptor(cacheInterceptor)
//            addNetworkInterceptor(adBlockInterceptor)
            cache(httpCache)
        }.build()
    }

    private fun call(url: String) = httpClient.newCall(
        Request.Builder().url(url).build()
    ).execute()

    private fun downloadAlbumArt(
        albumName: String?,
        artistName: String?
    ): ByteArray? {
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
                // no op
            }
        }
        if (releaseGroupID != null) {
            val url = "http://coverartarchive.org/release-group/$releaseGroupID/front"
            val ins = try {
                val response = call(url)
                if (response.code == 200) {
                    response.body.byteStream()
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
            return ins?.readBytes()
        } else {
            return null
        }
    }

    private fun retrieveXMLFromURL(url: String): InputStream? = try {
        val response = call(url)
        if (response.code == 200) {
            response.body.byteStream()
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }

    @Throws(Exception::class)
    private fun parse(ins: InputStream): String? = ins.use { inst ->
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
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            val name = parser.name
            if (name == "release-group") {
                releaseGroupID = parser.getAttributeValue("", "id")
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

    override fun buildLoadData(
        source: String,
        width: Int,
        height: Int,
        options: Options
    ): LoadData<InputStream?> = LoadData<InputStream?>(ObjectKey(source), CoverFetcher(source))

    override fun handles(
        model: String
    ): Boolean = model.isAudioFile()

    override fun build(
        multiFactory: MultiModelLoaderFactory
    ): ModelLoader<String?, InputStream?> = AlbumArtLoaderFactory(context)

    override fun teardown() {
    }

    inner class CoverFetcher(
        private val source: String?
    ) : DataFetcher<InputStream?> {

        private var job: Job? = null

        override fun loadData(
            priority: Priority,
            callback: DataFetcher.DataCallback<in InputStream?>
        ) {
            job?.cancel()
            job = launch {
                try {
                    val data = getCoverData(source)
                    if (data != null) {
                        callback.onDataReady(ByteArrayInputStream(data))
                    } else {
                        callback.onLoadFailed(Exception("No cover"))
                    }
                } catch (e: Exception) {
                    callback.onLoadFailed(e)
                }
            }
        }

        private fun getCoverData(source: String?): ByteArray? {
            var rawData: ByteArray? = null
            MediaMetadataRetriever().use { retriever ->
                if (source?.isMP3() == true) {
                    retriever.setDataSource(source)
                    rawData = try {
                        retriever.embeddedPicture
                    } catch (e: Exception) {
                        null
                    }
                }
                if (rawData == null) {
                    var albumName =
                        retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
                    val artistName =
                        retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
                    if (albumName == null && artistName == null) {
                        albumName = File(source ?: "").name.substringBeforeLast(".")
                    }
                    rawData = downloadAlbumArt(albumName, artistName)
                }
            }
            return rawData
        }

        override fun cleanup() {
        }

        override fun cancel() {
            job?.cancel()
        }

        override fun getDataClass(): Class<InputStream?> {
            return InputStream::class.java as Class<InputStream?>
        }

        override fun getDataSource(): DataSource {
            return DataSource.LOCAL
        }

    }

}

private fun String.isAudioFile(): Boolean =
    MimeTypeMapUtils.getMimeTypeFromUrl(this)?.contains("audio") ?: false

private fun String.isMP3(): Boolean =
    contains(".mp3")
