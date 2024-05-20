/*
 *  Copyright (c) Milan Jurkulák 2024.
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
import android.view.View
import android.widget.FrameLayout
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.dailymotion.android.player.sdk.PlayerWebView
import com.dailymotion.android.player.sdk.events.AdEndEvent
import com.dailymotion.android.player.sdk.events.AdStartEvent
import com.dailymotion.android.player.sdk.events.EndEvent
import com.dailymotion.android.player.sdk.events.PlayEvent
import com.dailymotion.android.player.sdk.events.PlayerEvent
import com.squareup.moshi.Moshi
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import org.mjdev.tvlib.BuildConfig
import org.mjdev.tvlib.helpers.http.NetworkConnectionInterceptor
import org.mjdev.tvlib.helpers.http.UserAgentInterceptor
import org.mjdev.tvlib.ui.components.audiopreview.IPreviewEngine
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.net.URLEncoder

// todo remove retriever
@Suppress("DEPRECATION", "PrivatePropertyName")
class DailyMotionVideoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs), IPreviewEngine, PlayerWebView.EventListener {

    private val playerView by lazy {
        PlayerWebView(context).apply {
            mute()
            showControls(false)
            setEventListener(this@DailyMotionVideoView)
            setEventErrorListener(object : PlayerWebView.EventErrorListener {
                override fun onError(
                    playerEvent: String,
                    description: String,
                    map: Map<String, String?>
                ) {
                    Timber.e("dailymotion player error event $playerEvent")
                    Timber.e(description)
                }
            })
        }
    }

    private val DM_URL =
        "https://api.dailymotion.com/videos?fields=id,thumbnail_url,title&search=%s&page=1&limit=1"

//    private val httpCache by lazy {
//        Cache(
//            directory = File(
//                context.cacheDir,
//                "http_cache"
//            ),
//            maxSize = 1024L * 1024L * 1024L
//        )
//    }

    private val networkConnectionInterceptor by lazy { NetworkConnectionInterceptor(context) }
    private val userAgentInterceptor by lazy { UserAgentInterceptor() }

//    private val cacheInterceptor by lazy {
//        CacheInterceptor()
//    }

//    private val httpLoggingInterceptor by lazy {
//        HttpLoggingInterceptor().setLevel(
//            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
//            else HttpLoggingInterceptor.Level.NONE
//        )
//    }

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().apply {
            addNetworkInterceptor(networkConnectionInterceptor)
            addNetworkInterceptor(userAgentInterceptor)
//            addNetworkInterceptor(cacheInterceptor)
//            addNetworkInterceptor(httpLoggingInterceptor)
//            cache(httpCache)
        }.build()
    }

    private fun call(url: String) = httpClient.newCall(
        Request.Builder().url(url).build()
    ).execute()

    init {
        visibility = View.INVISIBLE
        background = ColorDrawable(Color.Black.toArgb())
        addView(playerView)
    }

    override fun searchAndPlayIfFound(
        filePath: String?,
        muted: Boolean,
        success: (() -> Unit)?,
        error: ((error: Exception) -> Unit)?
    ) {
        visibility = View.INVISIBLE
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
                        // omit
                    }
                }
            }
            ThreadOnce.runInThreadOnce {
                try {
                    val url = String.format(
                        DM_URL,
                        URLEncoder.encode(q)
                    )
                    val json = call(url).body.string()
                    val result: SearchResult? = fromSimpleJSONString<SearchResult>(json)
                    val videoId = if (result?.list != null && result.list.isNotEmpty())
                        result.list[0].id
                    else null
                    if (videoId != null) {
                        post {
                            playerView.load(mapOf("video" to videoId))
                            success?.invoke()
                        }
                    }
                } catch (e: Exception) {
                    Timber.e(e)
                    post {
                        error?.invoke(e)
                    }
                }
            }
        }
    }

    override fun pause() {
        playerView.pause()
    }

    override fun stop() {
        visibility = INVISIBLE
        playerView.pause()
    }

    override fun resume() {
        playerView.play()
    }

    override fun seekTo(seek: Long) {
        playerView.seek(seek.toDouble())
    }

    override fun release() {
        playerView.release()
    }

    private inline fun <reified T> fromSimpleJSONString(json: String): T? {
        return Moshi.Builder().build().adapter(T::class.java).fromJson(json)
    }

    override fun onEventReceived(event: PlayerEvent) {
        when (event) {
            is PlayEvent -> {
                visibility = VISIBLE
            }

            is EndEvent -> {
                visibility = INVISIBLE
            }

            is AdStartEvent -> {
                visibility = INVISIBLE
            }

            is AdEndEvent -> {
                visibility = VISIBLE
            }

            else -> {
                Timber.e("Unhandled event: $event")
            }
        }
    }

}