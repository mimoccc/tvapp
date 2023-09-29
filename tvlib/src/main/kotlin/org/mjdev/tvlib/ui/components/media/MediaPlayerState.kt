/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.media

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.DeviceInfo
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.text.CueGroup
import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import org.json.JSONObject
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.MediaItemExt.mediaItem
import org.mjdev.tvlib.extensions.MediaItemExt.uri
import timber.log.Timber

@Suppress("unused", "MemberVisibilityCanBePrivate")
@JsonClass(generateAdapter = true)
class MediaPlayerState(
    initItems: List<MediaItem> = emptyList(),
    initItemIndex: Int = 0,
    autoPlay: Boolean = true,
    startSeek: Long? = null,
    @Json(name = "play_next_on_error", ignore = true)
    val playNextOnError: Boolean = true,
    @Json(name = "on_error", ignore = true)
    val onError: (e: Exception) -> Boolean = { false },
) : Player.Listener {

    @Json(name = "media_items")
    var mediaItems: List<MediaItem> = initItems

    @Json(name = "current_item_index")
    var currentItemIndex = if (initItemIndex < 0) 0 else initItemIndex

    @Json(name = "current_position")
    var currentPosition = startSeek ?: 0L

    @Json(name = "is_playing")
    var isPlaying: Boolean = autoPlay

    @Json(name = "error", ignore = true)
    var error: Exception? = null

    @Json(name = "has_media_to_play", ignore = true)
    val hasMediaToPlay get() = mediaItems.isNotEmpty()

    @Json(name = "is_auto_play", ignore = true)
    val isAutoPlay get() = isPlaying

    @Json(name = "current_item", ignore = true)
    val currentItem get() = mediaItems[currentItemIndex].mediaItem

    @Json(name = "player", ignore = true)
    var player: IMediaPlayer = IMediaPlayer.EMPTY
        set(value) {
            field.stop()
            field.release()
            field.dispose()
            field.removeListener(this)
            field = value
            field.addListener(this)
        }

    fun play() {
        isPlaying = true
        player.play()
    }

    fun stop() {
        isPlaying = false
        player.stop()
    }

    fun pause() {
        isPlaying = false
        player.pause()
    }

    fun resume() {
        isPlaying = true
        player.resume()
    }

    fun seekTo(ms: Long) {
        currentPosition = ms
        player.seekTo(ms)
    }

    fun dispose() {
        if (player.isPlaying) {
            player.stop()
        }
        player.release()
        player.dispose()
    }

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        this.isPlaying = isPlaying
    }

    override fun onPlayerError(error: PlaybackException) {
        onPlayerErrorChanged(error)
    }

    override fun onPlayerErrorChanged(error: PlaybackException?) {
        isPlaying = false
        this.error = error
        Timber.e(error)
        val handled: Boolean = error?.let { e -> onError(e) } ?: true
        if ((!handled) && playNextOnError) {
            player.playWhenReady = true
            player.seekToNext()
            player.prepare()
        }
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        currentPosition = player.currentPosition
    }

    override fun onDeviceInfoChanged(deviceInfo: DeviceInfo) {
        currentPosition = player.currentPosition
    }

    override fun onSeekForwardIncrementChanged(seekForwardIncrementMs: Long) {
        currentPosition = player.currentPosition
    }

    override fun onSeekBackIncrementChanged(seekBackIncrementMs: Long) {
        currentPosition = player.currentPosition
    }

    override fun onCues(cueGroup: CueGroup) {
        currentPosition = player.currentPosition
    }

    override fun onMaxSeekToPreviousPositionChanged(maxSeekToPreviousPositionMs: Long) {
        currentPosition = player.currentPosition
    }

    fun startPlay() {
        val mediaItems = mediaItems
        val currentItemIndex = currentItemIndex
        val currentPosition = currentPosition
        val isAutoPlay = isAutoPlay
        if (hasMediaToPlay) {
            player.setMediaItems(
                mediaItems,
                currentItemIndex,
                currentPosition
            )
            player.prepare()
            player.playWhenReady = isAutoPlay
        }
    }

    companion object {

        @Composable
        fun rememberMediaPlayerState(
            items: List<MediaItem> = listOf(),
            itemToPlay: Int = 0,
            autoPlay: Boolean = true,
            playNextOnError: Boolean = true,
            startSeek: Long? = null,
            context: Context = LocalContext.current,
            onError: (e: Exception) -> Boolean = { false },
            isEdit: Boolean = isEditMode()
        ) = rememberSaveable(
            key = MediaPlayerState::class.simpleName,
            inputs = arrayOf(items, itemToPlay),
            saver = mediaPlayerStateSaver(context),
        ) {
            MediaPlayerState(
                items,
                itemToPlay,
                autoPlay,
                startSeek,
                playNextOnError,
                onError
            ).apply {
                player = when (isEdit) {
                    true -> MediaPlayerContainerDefaults.empty(context)
                    false -> MediaPlayerContainerDefaults.exoPlayer(context)
                }
            }
        }

        fun mediaPlayerStateSaver(
            context: Context
        ): Saver<MediaPlayerState, String> = Saver(
            save = { mps ->
                Moshi.Builder()
                    .add(MediaItemAdapter())
                    .build()
                    .adapter(MediaPlayerState::class.java)
                    .toJson(mps)
            },
            restore = { json ->
                Moshi.Builder()
                    .add(MediaItemAdapter())
                    .build()
                    .adapter(MediaPlayerState::class.java)
                    .fromJson(json)?.apply {
                        player = MediaPlayerContainerDefaults.exoPlayer(context)
                    }
            }
        )

        // todo
        class MediaItemAdapter {
            // todo
            @ToJson
            fun toJson(item: MediaItem): String {
                return StringBuilder()
                    .append("{")
                    .append("  ", "uri", "=", "\"", item.uri, "\"")
                    .append("}")
                    .toString()
            }

            // todo
            @FromJson
            fun fromJson(json: String): MediaItem {
                return JSONObject(json).let { obj ->
                    MediaItem.Builder()
                        .setUri(obj.getString("uri"))
                        .build()
                }
            }
        }

    }

}