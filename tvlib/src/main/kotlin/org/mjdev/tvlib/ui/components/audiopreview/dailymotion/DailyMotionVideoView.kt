/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.audiopreview.dailymotion

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.media.MediaMetadataRetriever
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import com.squareup.moshi.Moshi
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.mjdev.tvlib.ui.components.audiopreview.IPreviewEngine
import org.mjdev.tvlib.ui.components.audiopreview.dailymotion.ThreadOnce.Companion.runInThreadOnce
import org.mjdev.tvlib.helpers.coil.UserAgentInterceptor
import org.mjdev.tvlib.network.CacheInterceptor
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.net.URLEncoder

@Suppress("unused", "PrivatePropertyName", "DEPRECATION")
class DailyMotionVideoView(
    context: Context, attrs: AttributeSet? = null
) : PlayerWebView(
    context,
    attrs
), EventListener, IPreviewEngine {

    private val DM_URL =
        "https://api.dailymotion.com/videos?fields=id,thumbnail_url,title&search=%s&page=1&limit=1"

    private var dmListener: DailyMotionVideoViewEventListener? = null

    private val USER_AGENT =
        "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/115.0"

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

    init {
        background = ColorDrawable(0)
        visibility = INVISIBLE
        setEventListener(this)
    }

    override fun searchAndPlayIfFound(
        filePath: String?,
        muted: Boolean,
        success: () -> Unit,
        error: (error: Exception) -> Unit
    ) {
        if (muted) mute()
        var q: String?
        if (filePath != null) {
            var mr: MediaMetadataRetriever? = null
            try {
                mr = MediaMetadataRetriever().apply {
                    setDataSource(filePath)
                }
                val author = mr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
                val artist = mr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
                val genre = mr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE)
                val year = mr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_YEAR)
                val title = mr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
                q = String.format(
                    "%s %s %s %s %s",
                    genre ?: "",
                    author ?: "",
                    artist ?: "",
                    year ?: "",
                    title ?: ""
                )
            } catch (e: Exception) {
                q = File(filePath).name.substringBeforeLast(".")
                Timber.e(e)
            } finally {
                if (mr != null) {
                    try {
                        mr.release()
                    } catch (e: IOException) {
                        // todo
//                        Timber.e(e)
                    }
                }
            }
            if (q == null || q.trim { it <= ' ' }.isEmpty()) {
                q = File(filePath).name.substringBeforeLast(".")
            }
            runInThreadOnce {
                try {
                    val url = String.format(
                        DM_URL,
                        URLEncoder.encode(q)
                    )
                    val json = call(url).body?.string() ?: ""
                    val result: SearchResult? = fromSimpleJSONString<SearchResult>(json)
                    val videoId = if (result?.list != null && result.list.isNotEmpty())
                        result.list[0].id
                    else null
                    if (videoId != null) {
                        post {
                            visibility = VISIBLE
                            load(videoId)
                            success()
                        }
                    }
                } catch (e: Exception) {
                    Timber.e(e)
                    post {
                        visibility = GONE
                        error(e)
                    }
                }
            }
        } else {
            visibility = GONE
        }
    }

    private inline fun <reified T> fromSimpleJSONString(json: String): T? {
        return Moshi.Builder().build().adapter(T::class.java).fromJson(json)
    }

    fun setListener(listener: DailyMotionVideoViewEventListener?) {
        dmListener = listener
    }

    fun resume(position: Long) {
        super.seek(position.toDouble())
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return false
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        return false
    }

    override fun onEvent(event: String?, map: HashMap<String?, String?>?) {
        post {
            if (event.equals(EVENT_VIDEO_START, ignoreCase = true)) {
                visibility = VISIBLE
                background = ColorDrawable(-0x1000000)
            } else if (event.equals(EVENT_VIDEO_END, ignoreCase = true)) {
                visibility = INVISIBLE
                background = ColorDrawable(0)
            }
        }
    }

}