/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.audiopreview.dailymotion

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.squareup.moshi.Moshi
import timber.log.Timber
import java.io.IOException
import java.net.URLDecoder
import java.util.Locale

/**
 * @noinspection unused
 */
@Suppress(
    "SameParameterValue", "unused", "DEPRECATION", "OVERRIDE_DEPRECATION",
    "MemberVisibilityCanBePrivate"
)
open class PlayerWebView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : WebView(context, attrs) {

    private val commandList by lazy { ArrayList<Command>() }

    private var isPlaying = false
    private var disallowIntercept = false
    private var videoId: String? = null
    private var apiReady = false
    private var position = 0f
    private var visible = false
    private var hasMetadata = false
    private var eventListener: EventListener? = null
    private var isWebContentsDebuggingEnabled = true
    private var controlsCommandRunnable: Runnable? = null
    private var muteCommandRunnable: Runnable? = null
    private var loadCommandRunnable: Runnable? = null
    private var videoPaused = false
    private var bufferedTime = 0.0
    private var duration = 0.0
    private var isSeeking = false
    private var isEnded = false
    private var isInitialized = false
    private var isFullScreen = false
    private var controlsLastTime: Long = 0
    private var muteLastTime: Long = 0
    private var loadLastTime: Long = 0

    var quality: String = ""
        set(quality) {
            field = quality
            queueCommand(COMMAND_QUALITY, quality)
        }

    var playWhenReady: Boolean = true
        set(playWhenReady) {
            field = playWhenReady
            updatePlayState()
        }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initialize(
        baseUrl: String?,
        queryParameters: Map<String, String>?,
        httpHeaders: Map<String?, String?>?
    ) {
        isInitialized = true
        val mWebSettings = settings
        mWebSettings.domStorageEnabled = true
        mWebSettings.javaScriptEnabled = true
        mWebSettings.pluginState = WebSettings.PluginState.ON
        mWebSettings.mediaPlaybackRequiresUserGesture = false
        setWebContentsDebuggingEnabled(isWebContentsDebuggingEnabled)
        addJavascriptInterface(object : Any() {
            @JavascriptInterface
            fun triggerEvent(e: String) {
                handler.post {
                    handleEvent(e)
                }
            }
        }, "dmpNativeBridge")
        webViewClient = object : WebViewClient() {
            override fun shouldInterceptRequest(view: WebView, url: String): WebResourceResponse? {
                if (url.startsWith(ASSETS_SCHEME)) {
                    val asset = url.substring(ASSETS_SCHEME.length)
                    if (asset.endsWith(".ttf") || asset.endsWith(".otf")) {
                        try {
                            val inputStream = context.assets.open(asset)
                            val response: WebResourceResponse?
                            val encoding = "UTF-8"
                            val statusCode = 200
                            val reasonPhase = "OK"
                            val responseHeaders: MutableMap<String, String> = HashMap()
                            responseHeaders["Access-Control-Allow-Origin"] = "*"
                            response = WebResourceResponse(
                                "font/ttf",
                                encoding,
                                statusCode,
                                reasonPhase,
                                responseHeaders,
                                inputStream
                            )
                            return response
                        } catch (e: IOException) {
                            Timber.e(e)
                        }
                    }
                }
                return null
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return true
            }

        }
        webChromeClient = object : WebChromeClient() {
            override fun getVideoLoadingProgressView(): View {
                val pb = ProgressBar(context)
                pb.isIndeterminate = true
                return pb
            }

            override fun onShowCustomView(view: View, callback: CustomViewCallback) {
            }

            override fun getDefaultVideoPoster(): Bitmap {
                val colors = IntArray(1)
                colors[0] = Color.TRANSPARENT
                return Bitmap.createBitmap(
                    colors,
                    0,
                    1,
                    1,
                    1,
                    Bitmap.Config.ARGB_8888
                )
            }

            override fun onHideCustomView() {
            }
        }
        val parameters: MutableMap<String, String> = HashMap()
        parameters["app"] = context.packageName
        parameters["api"] = "nativeBridge"
        parameters.putAll(queryParameters!!)
        val builder = StringBuilder()
        builder.append(baseUrl)
        var isFirstParameter = true
        for ((key, value) in parameters) {
            if (isFirstParameter) {
                isFirstParameter = false
                builder.append('?')
            } else {
                builder.append('&')
            }
            builder.append(key)
            builder.append('=')
            builder.append(value)
        }
        loadUrl(builder.toString(), httpHeaders!!)
    }

    fun setVisible(visible: Boolean) {
        if (this.visible != visible) {
            this.visible = visible
            if (!this.visible) {
                playWhenReady = false
            }
            if (!this.visible) {
                pauseTimers()
                onPause()
            } else {
                resumeTimers()
                onResume()
            }
        }
    }

    private fun updatePlayState() {
        if (!visible) {
            pause()
        } else {
            if (playWhenReady) {
                play()
            } else {
                pause()
            }
        }
    }

    fun setMinimizeProgress(p: Float) = showControls(p <= 0)

    fun setIsLiked(isLiked: Boolean) = queueCommand(COMMAND_NOTIFY_LIKECHANGED, isLiked)

    fun setIsInWatchLater(isInWatchLater: Boolean) =
        queueCommand(COMMAND_NOTIFY_WATCHLATERCHANGED, isInWatchLater)

    private fun sendCommand(command: Command) {
        when (command.methodName) {
            COMMAND_MUTE -> callPlayerMethod(if (command.params[0] as Boolean) "mute" else "unmute")
            COMMAND_CONTROLS -> callPlayerMethod(
                "api",
                "controls",
                if (command.params[0] as Boolean) "true" else "false"
            )

            COMMAND_QUALITY -> callPlayerMethod("api", "quality", command.params[0])
            COMMAND_SUBTITLE -> callPlayerMethod("api", "subtitle", command.params[0])
            COMMAND_TOGGLE_CONTROLS -> callPlayerMethod("api", "toggle-controls", command.params)
            COMMAND_TOGGLE_PLAY -> callPlayerMethod("api", "toggle-play", command.params)
            else -> callPlayerMethod(command.methodName, *command.params)
        }
    }

    private fun handleEvent(e: String) {
        var ev = e
        ev = URLDecoder.decode(ev)
        val p = ev.split("&".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val map = HashMap<String?, String?>()
        for (s in p) {
            val s2 = s.split("=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            when (s2.size) {
                1 -> map[s2[0]] = null
                2 -> map[s2[0]] = s2[1]
                else -> {
                    Timber.e("bad param: $s")
                }
            }
        }
        val event = map["event"]
        if (event == null) {
            Timber.e("bad event 2: $e")
            return
        }
        if (event != "timeupdate") {
            Timber.d("[%d] event %s", hashCode(), e)
        }
        when (event) {
            EVENT_AD_START -> {}
            EVENT_AD_PLAY -> {}
            EVENT_AD_END -> {}
            EVENT_VIDEO_START -> {
                hideControls()
                isPlaying = true
            }

            EVENT_APIREADY -> apiReady = true
            EVENT_START -> {
                isEnded = false
                loadCommandRunnable?.also { c ->
                    handler.removeCallbacks(c)
                }
                loadCommandRunnable = null
            }

            EVENT_END -> isEnded = true
            EVENT_PROGRESS -> bufferedTime = map["time"]?.toDouble() ?: 0.0
            EVENT_TIMEUPDATE -> position = map["time"]?.toFloat() ?: 0f
            EVENT_DURATION_CHANGE -> duration = map["duration"]?.toDouble() ?: 0.0
            EVENT_GESTURE_START, EVENT_MENU_DID_SHOW -> disallowIntercept = true
            EVENT_GESTURE_END, EVENT_MENU_DID_HIDE -> disallowIntercept = false
            EVENT_VIDEO_END -> isPlaying = false
            EVENT_PLAY -> {
                videoPaused = false
                playWhenReady = true
            }

            EVENT_PAUSE -> {
                videoPaused = true
                playWhenReady = false
            }

            EVENT_CONTROLSCHANGE -> {
                controlsCommandRunnable?.also { c ->
                    handler.removeCallbacks(c)
                }
                controlsCommandRunnable = null
            }

            EVENT_VOLUMECHANGE -> {
                muteCommandRunnable?.also { c ->
                    handler.removeCallbacks(c)
                }
                muteCommandRunnable = null
            }

            EVENT_LOADEDMETADATA -> hasMetadata = true
            EVENT_QUALITY -> quality = map["quality"] ?: ""
            EVENT_SEEKED -> {
                isSeeking = false
                position = map["time"]?.toFloat() ?: 0f
            }

            EVENT_SEEKING -> {
                isSeeking = true
                position = map["time"]?.toFloat() ?: 0f
            }

            EVENT_FULLSCREEN_TOGGLE_REQUESTED -> {}
        }
        eventListener?.onEvent(event, map)
        tick()
    }

    private fun tick() {
        if (!apiReady) {
            return
        }
        val iterator = commandList.iterator()
        while (iterator.hasNext()) {
            val command = iterator.next()
            when (command.methodName) {
                COMMAND_NOTIFY_LIKECHANGED -> {
                    if (!hasMetadata) {
                        continue
                    }
                }

                COMMAND_NOTIFY_WATCHLATERCHANGED -> {
                    if (!hasMetadata) {
                        continue
                    }
                }

                COMMAND_MUTE -> {
                    if (System.currentTimeMillis() - muteLastTime < 1000) {
                        continue
                    }
                    muteLastTime = System.currentTimeMillis()
                }

                COMMAND_LOAD -> {
                    if (System.currentTimeMillis() - loadLastTime < 1000) {
                        continue
                    }
                    loadLastTime = System.currentTimeMillis()
                }

                COMMAND_CONTROLS -> {
                    if (System.currentTimeMillis() - controlsLastTime < 1000) {
                        continue
                    }
                    controlsLastTime = System.currentTimeMillis()
                }
            }
            iterator.remove()
            sendCommand(command)
        }
    }

    private fun queueCommand(method: String, vararg params: Any) {
        var iterator = commandList.iterator()
        while (iterator.hasNext()) {
            if (iterator.next().methodName == method) {
                iterator.remove()
            }
        }
        if (method == COMMAND_LOAD) {
            position = 0f
            disallowIntercept = false
            videoId = params[0] as String?
            hasMetadata = false
            iterator = commandList.iterator()
            while (iterator.hasNext()) {
                when (iterator.next().methodName) {
                    COMMAND_NOTIFY_LIKECHANGED,
                    COMMAND_NOTIFY_WATCHLATERCHANGED,
                    COMMAND_SEEK,
                    COMMAND_PAUSE,
                    COMMAND_PLAY -> iterator.remove()
                }
            }
        }
        val command = Command()
        command.methodName = method
        command.params = params
        commandList.add(command)
        tick()
    }

    private fun callPlayerMethod(method: String, vararg params: Any) {
        val builder = StringBuilder()
        builder.append("javascript:player.")
        builder.append(method)
        builder.append('(')
        var count = 0
        for (o in params) {
            count++
            when (o) {
                is String -> builder.append("'").append(o).append("'")
                is Number -> builder.append(o)
                is Boolean -> builder.append(o)
                else -> builder.append("JSON.parse('" + toSimpleJSON(o) + "')")
            }
            if (count < params.size) {
                builder.append(",")
            }
        }
        builder.append(')')
        val js = builder.toString()
        loadUrl(js)
    }

    private inline fun <reified T> toSimpleJSON(o: T): String {
        return Moshi.Builder().build().adapter(T::class.java).toJson(o)
    }

    fun mute(mute: Boolean) {
        queueCommand(COMMAND_MUTE, mute)
    }

    fun mute() {
        mute(true)
    }

    fun unmute() {
        mute(false)
    }

    fun play() {
        queueCommand(COMMAND_PLAY)
    }

    fun pause() {
        queueCommand(COMMAND_PAUSE)
    }

    fun seek(time: Double) {
        queueCommand(COMMAND_SEEK, time)
    }

    fun showControls(show: Boolean) {
        queueCommand(COMMAND_CONTROLS, show)
    }

    fun hideControls() {
        queueCommand(COMMAND_CONTROLS, false)
    }

    fun setFullscreenButton(fullScreen: Boolean) {
        if (fullScreen != isFullScreen) {
            isFullScreen = fullScreen
            queueCommand(COMMAND_NOTIFYFULLSCREENCHANGED)
        }
    }

    fun setEventListener(listener: EventListener?) {
        eventListener = listener
    }

    fun release() {
        loadUrl("about:blank")
        onPause()
    }

    @JvmOverloads
    fun load(videoId: String?, params: Map<String, String> = emptyMap()) {
        if (!isInitialized) {
            val defaultQueryParameters: MutableMap<String, String> = HashMap()
            defaultQueryParameters["sharing-enable"] = "false"
            defaultQueryParameters["watchlater-enable"] = "false"
            defaultQueryParameters["like-enable"] = "false"
            defaultQueryParameters["collections-enable"] = "false"
            defaultQueryParameters["fullscreen-action"] = "trigger_event"
            defaultQueryParameters["locale"] = Locale.getDefault().language
            initialize(
                "https://www.dailymotion.com/embed/",
                defaultQueryParameters,
                HashMap()
            )
        }
        Timber.d("Playing video: $videoId")
        queueCommand(COMMAND_LOAD, videoId ?: "", params)
    }

    fun setSubtitle(languageCode: String) = queueCommand(COMMAND_SUBTITLE, languageCode)

    fun toggleControls() = queueCommand(COMMAND_TOGGLE_CONTROLS)

    fun togglePlay() = queueCommand(COMMAND_TOGGLE_PLAY)

    fun getPosition(): Long = (position * 1000).toLong()

    fun setIsWebContentsDebuggingEnabled(isWebContentsDebuggingEnabled: Boolean) {
        this.isWebContentsDebuggingEnabled = isWebContentsDebuggingEnabled
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (disallowIntercept) {
            requestDisallowInterceptTouchEvent(true)
        }
        return super.onTouchEvent(event)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    companion object {

        const val EVENT_APIREADY = "apiready"
        const val EVENT_TIMEUPDATE = "timeupdate"
        const val EVENT_DURATION_CHANGE = "durationchange"
        const val EVENT_PROGRESS = "progress"
        const val EVENT_SEEKED = "seeked"
        const val EVENT_SEEKING = "seeking"
        const val EVENT_GESTURE_START = "gesture_start"
        const val EVENT_GESTURE_END = "gesture_end"
        const val EVENT_MENU_DID_SHOW = "menu_did_show"
        const val EVENT_MENU_DID_HIDE = "menu_did_hide"
        const val EVENT_VIDEO_START = "video_start"
        const val EVENT_VIDEO_END = "video_end"
        const val EVENT_AD_START = "ad_start"
        const val EVENT_AD_PLAY = "ad_play"
        const val EVENT_AD_END = "ad_end"
        const val EVENT_ADD_TO_COLLECTION_REQUESTED = "add_to_collection_requested"
        const val EVENT_LIKE_REQUESTED = "like_requested"
        const val EVENT_WATCH_LATER_REQUESTED = "watch_later_requested"
        const val EVENT_SHARE_REQUESTED = "share_requested"
        const val EVENT_FULLSCREEN_TOGGLE_REQUESTED = "fullscreen_toggle_requested"
        const val EVENT_PLAY = "play"
        const val EVENT_PAUSE = "pause"
        const val EVENT_LOADEDMETADATA = "loadedmetadata"
        const val EVENT_PLAYING = "playing"
        const val EVENT_START = "start"
        const val EVENT_END = "end"
        const val EVENT_CONTROLSCHANGE = "controlschange"
        const val EVENT_VOLUMECHANGE = "volumechange"
        const val EVENT_QUALITY = "qualitychange"
        private const val ASSETS_SCHEME = "asset://"
        const val COMMAND_NOTIFY_LIKECHANGED = "notifyLikeChanged"
        const val COMMAND_NOTIFY_WATCHLATERCHANGED = "notifyWatchLaterChanged"
        const val COMMAND_NOTIFYFULLSCREENCHANGED = "notifyFullscreenChanged"
        const val COMMAND_LOAD = "load"
        const val COMMAND_MUTE = "mute"
        const val COMMAND_CONTROLS = "controls"
        const val COMMAND_PLAY = "play"
        const val COMMAND_PAUSE = "pause"
        const val COMMAND_SEEK = "seek"
        const val COMMAND_SETPROP = "setProp"
        const val COMMAND_QUALITY = "quality"
        const val COMMAND_SUBTITLE = "subtitle"
        const val COMMAND_TOGGLE_CONTROLS = "toggle-controls"
        const val COMMAND_TOGGLE_PLAY = "toggle-play"

    }

}