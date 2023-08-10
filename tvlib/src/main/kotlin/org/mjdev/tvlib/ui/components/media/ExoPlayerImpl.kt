/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.media

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Looper
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.TextureView
import android.view.View
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.getSystemService
import androidx.lifecycle.Lifecycle.Event.ON_PAUSE
import androidx.lifecycle.Lifecycle.Event.ON_RESUME
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.DeviceInfo
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.PlaybackException
import androidx.media3.common.PlaybackParameters
import androidx.media3.common.Player
import androidx.media3.common.Player.STATE_ENDED
import androidx.media3.common.Player.STATE_READY
import androidx.media3.common.Timeline
import androidx.media3.common.TrackSelectionParameters
import androidx.media3.common.Tracks
import androidx.media3.common.VideoSize
import androidx.media3.common.text.CueGroup
import androidx.media3.common.util.Size
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerControlView
import androidx.media3.ui.PlayerNotificationManager
import androidx.media3.ui.PlayerView
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.extensions.LifecycleExt.rememberLifeCycleEventObserver
import org.mjdev.tvlib.extensions.ModifierExt.recomposeHighlighter
import org.mjdev.tvlib.extensions.ViewExt.controller
import org.mjdev.tvlib.extensions.ViewExt.findView
import org.mjdev.tvlib.ui.components.audiopreview.AudioPreview

@Suppress("DEPRECATION")
@UnstableApi
class ExoPlayerImpl(
    override val context: Context,
) : IMediaPlayer, PlayerNotificationManager.MediaDescriptionAdapter {

//    private val sessionToken by lazy {
//        MediaSessionCompat.Token.fromToken(
//            SessionToken(
//                context, ComponentName(
//                    context,
//                    PlaybackService::class.java
//                )
//            )
//        )
//    }

    private val playerNotificationManager by lazy {
        val notifyChannelName = this::class.java.`package`?.name ?: "MediaPlayer"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = context.getSystemService<NotificationManager>()
            notificationManager?.createNotificationChannel(
                NotificationChannel(
                    notifyChannelName,
                    notifyChannelName,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            )
        }
        val notificationId = 1
        PlayerNotificationManager.Builder(
            context,
            notificationId,
            notifyChannelName,
            this
        )
            .setMediaDescriptionAdapter(this@ExoPlayerImpl)
            .build()
            .apply {
//                setMediaSessionToken(sessionToken)
            }
    }

    // todo from session
    private val exoPlayer: ExoPlayer by lazy {
        ExoPlayer.Builder(context)
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(C.USAGE_MEDIA)
                    .setContentType(C.AUDIO_CONTENT_TYPE_MOVIE)
                    .build(), true
            ).build()
    }

    override fun getCurrentContentTitle(player: Player): String =
        currentMediaItem?.mediaMetadata?.title?.toString() ?: ""

    override fun getCurrentContentText(player: Player): String =
        currentMediaItem?.mediaMetadata?.description?.toString() ?: ""

    // todo app icon if artwork is missing
    override fun getCurrentLargeIcon(
        player: Player,
        callback: PlayerNotificationManager.BitmapCallback
    ): Bitmap? = currentMediaItem?.mediaMetadata?.artworkData?.let { data ->
        BitmapFactory.decodeByteArray(data, 0, data.size)
    }

    // todo app pending intent
    override fun createCurrentContentIntent(player: Player): PendingIntent? {
        return null
    }

    @Suppress("UNUSED_VARIABLE")
    @UnstableApi
    @Composable
    override fun GetPlayerView() {
        val context = LocalContext.current
        val width = LocalConfiguration.current.screenWidthDp
        val height = LocalConfiguration.current.screenHeightDp
        val isEdit = isEditMode()
        val audioPlayerView = remember(exoPlayer) {
            AudioPreview(context)
        }
        val playerView = remember(exoPlayer) {
            object : PlayerView(context) {
                init {
                    setBackgroundColor(0)
                    setShutterBackgroundColor(0)
                    drawingCacheBackgroundColor = 0
                    controllerAutoShow = true
                    controllerHideOnTouch = true
                    useController = true
                    useArtwork = false
                    artworkDisplayMode = ARTWORK_DISPLAY_MODE_OFF
                    player = exoPlayer
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        outlineAmbientShadowColor = 0
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        outlineSpotShadowColor = 0
                    }
                    setShowBuffering(SHOW_BUFFERING_ALWAYS)
                    setShowMultiWindowTimeBar(true)
                    addView(audioPlayerView)
                    findView<PlayerControlView>()?.apply {
                        removeView(this)
                        addView(this)
                    }
                }
            }
        }
        val lifecycleObserver = rememberLifeCycleEventObserver(
            exoPlayer
        ) { _, event ->
            when (event) {
                ON_PAUSE -> {
                    audioPlayerView.pause()
                    playerView.player = null
                    playerView.visibility = View.INVISIBLE
                }

                ON_RESUME -> {
                    audioPlayerView.resume()
                    playerView.visibility = View.VISIBLE
                    playerView.player = exoPlayer
                }

                else -> {
                }
            }
        }
        Box(
            modifier = Modifier
                .recomposeHighlighter()
                .size(width.dp, height.dp)
                .onPreviewKeyEvent { ev ->
                    val controller = playerView.controller
                    if (controller?.isShown == true) {
                        playerView.controller?.dispatchKeyEvent(ev.nativeKeyEvent) ?: false
                    } else {
                        controller?.show()
                        true
                    }
                }
                .focusable()
        ) {
            if (!isEdit) {
                AndroidView(
                    modifier = Modifier
                        .size(width.dp, height.dp)
                        .recomposeHighlighter(),
                    factory = {
                        playerView
                    }
                )
                exoPlayer.addListener(object : Player.Listener {
                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        if (isPlaying) {
                            audioPlayerView.resume()
                        } else {
                            audioPlayerView.pause()
                        }
                    }

                    override fun onPlaybackStateChanged(playbackState: Int) {
                        when (playbackState) {
                            STATE_READY -> {
                                audioPlayerView.setData(currentMediaItem)
                            }

                            STATE_ENDED -> {
                                audioPlayerView.stop()
                            }

                            else -> {
                                // no op
                            }
                        }
                    }
                })
            }
        }
        playerNotificationManager.setPlayer(exoPlayer)
    }

    override fun setMediaUri(uri: Uri) {
        exoPlayer.setMediaItem(MediaItem.fromUri(uri))
    }

    override fun prepare() {
        exoPlayer.prepare()
    }

    override fun play() {
        playerNotificationManager.setPlayer(exoPlayer)
        exoPlayer.play()
    }

    override fun pause() {
        playerNotificationManager.setPlayer(exoPlayer)
        exoPlayer.pause()
    }

    override fun resume() {
        playerNotificationManager.setPlayer(exoPlayer)
        exoPlayer.playWhenReady = true
    }

    override fun stop() {
        playerNotificationManager.setPlayer(null)
        exoPlayer.stop()
    }

    override fun dispose() {
        stop()
        exoPlayer.release()
    }

    override fun seekTo(value: Long) {
        exoPlayer.seekTo(value)
    }

    override fun seekTo(mediaItemIndex: Int, positionMs: Long) {
        exoPlayer.seekTo(mediaItemIndex, positionMs)
    }

    override fun getApplicationLooper(): Looper {
        return exoPlayer.applicationLooper
    }

    override fun addListener(listener: Player.Listener) {
        exoPlayer.addListener(listener)
    }

    override fun removeListener(listener: Player.Listener) {
        exoPlayer.removeListener(listener)
    }

    override fun setMediaItems(mediaItems: MutableList<MediaItem>) {
        exoPlayer.setMediaItems(mediaItems)
    }

    override fun setMediaItems(mediaItems: MutableList<MediaItem>, resetPosition: Boolean) {
        exoPlayer.setMediaItems(mediaItems, resetPosition)
    }

    override fun setMediaItems(
        mediaItems: MutableList<MediaItem>,
        startIndex: Int,
        startPositionMs: Long
    ) {
        exoPlayer.setMediaItems(mediaItems, startIndex, startPositionMs)
    }

    override fun setMediaItem(mediaItem: MediaItem) {
        exoPlayer.setMediaItem(mediaItem)
    }

    override fun setMediaItem(mediaItem: MediaItem, startPositionMs: Long) {
        exoPlayer.setMediaItem(mediaItem, startPositionMs)
    }

    override fun setMediaItem(mediaItem: MediaItem, resetPosition: Boolean) {
        exoPlayer.setMediaItem(mediaItem, resetPosition)
    }

    override fun addMediaItem(mediaItem: MediaItem) {
        exoPlayer.addMediaItem(mediaItem)
    }

    override fun addMediaItem(index: Int, mediaItem: MediaItem) {
        exoPlayer.addMediaItem(index, mediaItem)
    }

    override fun addMediaItems(mediaItems: MutableList<MediaItem>) {
        exoPlayer.addMediaItems(mediaItems)
    }

    override fun addMediaItems(index: Int, mediaItems: MutableList<MediaItem>) {
        exoPlayer.addMediaItems(index, mediaItems)
    }

    override fun moveMediaItem(currentIndex: Int, newIndex: Int) {
        exoPlayer.moveMediaItem(currentIndex, newIndex)
    }

    override fun moveMediaItems(fromIndex: Int, toIndex: Int, newIndex: Int) {
        exoPlayer.moveMediaItems(fromIndex, toIndex, newIndex)
    }

    override fun replaceMediaItem(index: Int, mediaItem: MediaItem) {
        exoPlayer.replaceMediaItem(index, mediaItem)
    }

    override fun replaceMediaItems(
        fromIndex: Int,
        toIndex: Int,
        mediaItems: MutableList<MediaItem>
    ) {
        exoPlayer.replaceMediaItems(fromIndex, toIndex, mediaItems)
    }

    override fun removeMediaItem(index: Int) {
        exoPlayer.removeMediaItem(index)
    }

    override fun removeMediaItems(fromIndex: Int, toIndex: Int) {
        exoPlayer.removeMediaItems(fromIndex, toIndex)
    }

    override fun clearMediaItems() {
        exoPlayer.clearMediaItems()
    }

    override fun isCommandAvailable(command: Int): Boolean {
        return exoPlayer.isCommandAvailable(command)
    }

    override fun canAdvertiseSession(): Boolean {
        return exoPlayer.canAdvertiseSession()
    }

    override fun getAvailableCommands(): Player.Commands {
        return exoPlayer.availableCommands
    }

    override fun getPlaybackState(): Int {
        return exoPlayer.playbackState
    }

    override fun getPlaybackSuppressionReason(): Int {
        return exoPlayer.playbackSuppressionReason
    }

    override fun isPlaying(): Boolean {
        return exoPlayer.isPlaying
    }

    override fun getPlayerError(): PlaybackException? {
        return exoPlayer.playerError
    }

    override fun setPlayWhenReady(playWhenReady: Boolean) {
        exoPlayer.playWhenReady = playWhenReady
    }

    override fun getPlayWhenReady(): Boolean {
        return exoPlayer.playWhenReady
    }

    override fun setRepeatMode(repeatMode: Int) {
        exoPlayer.repeatMode = repeatMode
    }

    override fun getRepeatMode(): Int {
        return exoPlayer.repeatMode
    }

    override fun setShuffleModeEnabled(shuffleModeEnabled: Boolean) {
        exoPlayer.shuffleModeEnabled = shuffleModeEnabled
    }

    override fun getShuffleModeEnabled(): Boolean {
        return exoPlayer.shuffleModeEnabled
    }

    override fun isLoading(): Boolean {
        return exoPlayer.isLoading
    }

    override fun seekToDefaultPosition() {
        exoPlayer.seekToDefaultPosition()
    }

    override fun seekToDefaultPosition(mediaItemIndex: Int) {
        exoPlayer.seekToDefaultPosition(mediaItemIndex)
    }

    override fun getSeekBackIncrement(): Long {
        return exoPlayer.seekBackIncrement
    }

    override fun seekBack() {
        exoPlayer.seekBack()
    }

    override fun getSeekForwardIncrement(): Long {
        return exoPlayer.seekForwardIncrement
    }

    override fun seekForward() {
        exoPlayer.seekForward()
    }

    @Deprecated("Deprecated in Java")
    override fun hasPrevious(): Boolean {
        return exoPlayer.hasPrevious()
    }

    @Deprecated("Deprecated in Java")
    override fun hasPreviousWindow(): Boolean {
        return exoPlayer.hasPreviousWindow()
    }

    override fun hasPreviousMediaItem(): Boolean {
        return exoPlayer.hasPreviousMediaItem()
    }

    @Deprecated("Deprecated in Java")
    override fun previous() {
        exoPlayer.previous()
    }

    @Deprecated("Deprecated in Java")
    override fun seekToPreviousWindow() {
        exoPlayer.seekToPreviousWindow()
    }

    override fun seekToPreviousMediaItem() {
        exoPlayer.seekToPreviousMediaItem()
    }

    override fun getMaxSeekToPreviousPosition(): Long {
        return exoPlayer.maxSeekToPreviousPosition
    }

    override fun seekToPrevious() {
        exoPlayer.seekToPrevious()
    }

    @Deprecated("Deprecated in Java")
    override fun hasNext(): Boolean {
        return exoPlayer.hasNext()
    }

    @Deprecated("Deprecated in Java")
    override fun hasNextWindow(): Boolean {
        return exoPlayer.hasNextWindow()
    }

    override fun hasNextMediaItem(): Boolean {
        return exoPlayer.hasNextMediaItem()
    }

    @Deprecated("Deprecated in Java")
    override fun next() {
        exoPlayer.next()
    }

    @Deprecated("Deprecated in Java")
    override fun seekToNextWindow() {
        exoPlayer.seekToNextWindow()
    }

    override fun seekToNextMediaItem() {
        exoPlayer.seekToNextMediaItem()
    }

    override fun seekToNext() {
        exoPlayer.seekToNext()
    }

    override fun setPlaybackParameters(playbackParameters: PlaybackParameters) {
        exoPlayer.playbackParameters = playbackParameters
    }

    override fun setPlaybackSpeed(speed: Float) {
        exoPlayer.setPlaybackSpeed(speed)
    }

    override fun getPlaybackParameters(): PlaybackParameters {
        return exoPlayer.playbackParameters
    }

    override fun release() {
        stop()
        exoPlayer.release()
    }

    override fun getCurrentTracks(): Tracks {
        return exoPlayer.currentTracks
    }

    override fun getTrackSelectionParameters(): TrackSelectionParameters {
        return exoPlayer.trackSelectionParameters
    }

    override fun setTrackSelectionParameters(parameters: TrackSelectionParameters) {
        exoPlayer.trackSelectionParameters = parameters
    }

    override fun getMediaMetadata(): MediaMetadata {
        return exoPlayer.mediaMetadata
    }

    override fun getPlaylistMetadata(): MediaMetadata {
        return exoPlayer.playlistMetadata
    }

    override fun setPlaylistMetadata(mediaMetadata: MediaMetadata) {
        exoPlayer.playlistMetadata = mediaMetadata
    }

    override fun getCurrentManifest(): Any? {
        return exoPlayer.currentManifest
    }

    override fun getCurrentTimeline(): Timeline {
        return exoPlayer.currentTimeline
    }

    override fun getCurrentPeriodIndex(): Int {
        return exoPlayer.currentPeriodIndex
    }

    @Deprecated("Deprecated in Java")
    override fun getCurrentWindowIndex(): Int {
        return exoPlayer.currentWindowIndex
    }

    override fun getCurrentMediaItemIndex(): Int {
        return exoPlayer.currentMediaItemIndex
    }

    @Deprecated("Deprecated in Java")
    override fun getNextWindowIndex(): Int {
        return exoPlayer.currentMediaItemIndex
    }

    override fun getNextMediaItemIndex(): Int {
        return exoPlayer.nextMediaItemIndex
    }

    @Deprecated("Deprecated in Java")
    override fun getPreviousWindowIndex(): Int {
        return exoPlayer.previousWindowIndex
    }

    override fun getPreviousMediaItemIndex(): Int {
        return exoPlayer.previousMediaItemIndex
    }

    override fun getCurrentMediaItem(): MediaItem? {
        return exoPlayer.currentMediaItem
    }

    override fun getMediaItemCount(): Int {
        return exoPlayer.mediaItemCount
    }

    override fun getMediaItemAt(index: Int): MediaItem {
        return exoPlayer.getMediaItemAt(index)
    }

    override fun getDuration(): Long {
        return exoPlayer.duration
    }

    override fun getCurrentPosition(): Long {
        return exoPlayer.currentPosition
    }

    override fun getBufferedPosition(): Long {
        return exoPlayer.bufferedPosition
    }

    override fun getBufferedPercentage(): Int {
        return exoPlayer.bufferedPercentage
    }

    override fun getTotalBufferedDuration(): Long {
        return exoPlayer.totalBufferedDuration
    }

    @Deprecated("Deprecated in Java")
    override fun isCurrentWindowDynamic(): Boolean {
        return exoPlayer.isCurrentWindowDynamic
    }

    override fun isCurrentMediaItemDynamic(): Boolean {
        return exoPlayer.isCurrentMediaItemDynamic
    }

    @Deprecated("Deprecated in Java")
    override fun isCurrentWindowLive(): Boolean {
        return exoPlayer.isCurrentWindowLive
    }

    override fun isCurrentMediaItemLive(): Boolean {
        return exoPlayer.isCurrentMediaItemLive
    }

    override fun getCurrentLiveOffset(): Long {
        return exoPlayer.currentLiveOffset
    }

    @Deprecated("Deprecated in Java")
    override fun isCurrentWindowSeekable(): Boolean {
        return exoPlayer.isCurrentWindowSeekable
    }

    override fun isCurrentMediaItemSeekable(): Boolean {
        return exoPlayer.isCurrentMediaItemSeekable
    }

    override fun isPlayingAd(): Boolean {
        return exoPlayer.isPlayingAd
    }

    override fun getCurrentAdGroupIndex(): Int {
        return exoPlayer.currentAdGroupIndex
    }

    override fun getCurrentAdIndexInAdGroup(): Int {
        return exoPlayer.currentAdIndexInAdGroup
    }

    override fun getContentDuration(): Long {
        return exoPlayer.contentDuration
    }

    override fun getContentPosition(): Long {
        return exoPlayer.contentPosition
    }

    override fun getContentBufferedPosition(): Long {
        return exoPlayer.contentBufferedPosition
    }

    override fun getAudioAttributes(): AudioAttributes {
        return exoPlayer.audioAttributes
    }

    override fun setVolume(volume: Float) {
        exoPlayer.volume = volume
    }

    override fun getVolume(): Float {
        return exoPlayer.volume
    }

    override fun clearVideoSurface() {
        return exoPlayer.clearVideoSurface()
    }

    override fun clearVideoSurface(surface: Surface?) {
        return exoPlayer.clearVideoSurface(surface)
    }

    override fun setVideoSurface(surface: Surface?) {
        exoPlayer.setVideoSurface(surface)
    }

    override fun setVideoSurfaceHolder(surfaceHolder: SurfaceHolder?) {
        exoPlayer.setVideoSurfaceHolder(surfaceHolder)
    }

    override fun clearVideoSurfaceHolder(surfaceHolder: SurfaceHolder?) {
        exoPlayer.clearVideoSurfaceHolder(surfaceHolder)
    }

    override fun setVideoSurfaceView(surfaceView: SurfaceView?) {
        exoPlayer.setVideoSurfaceView(surfaceView)
    }

    override fun clearVideoSurfaceView(surfaceView: SurfaceView?) {
        exoPlayer.clearVideoSurfaceView(surfaceView)
    }

    override fun setVideoTextureView(textureView: TextureView?) {
        exoPlayer.setVideoTextureView(textureView)
    }

    override fun clearVideoTextureView(textureView: TextureView?) {
        exoPlayer.clearVideoTextureView(textureView)
    }

    override fun getVideoSize(): VideoSize {
        return exoPlayer.videoSize
    }

    override fun getSurfaceSize(): Size {
        return exoPlayer.surfaceSize
    }

    override fun getCurrentCues(): CueGroup {
        return exoPlayer.currentCues
    }

    override fun getDeviceInfo(): DeviceInfo {
        return exoPlayer.deviceInfo
    }

    override fun getDeviceVolume(): Int {
        return exoPlayer.deviceVolume
    }

    override fun isDeviceMuted(): Boolean {
        return exoPlayer.isDeviceMuted
    }

    @Deprecated("Deprecated in Java")
    override fun setDeviceVolume(volume: Int) {
        return exoPlayer.setDeviceVolume(volume)
    }

    override fun setDeviceVolume(volume: Int, flags: Int) {
        exoPlayer.setDeviceVolume(volume, flags)
    }

    @Deprecated("Deprecated in Java")
    override fun increaseDeviceVolume() {
        exoPlayer.increaseDeviceVolume()
    }

    override fun increaseDeviceVolume(flags: Int) {
        exoPlayer.increaseDeviceVolume(flags)
    }

    @Deprecated("Deprecated in Java")
    override fun decreaseDeviceVolume() {
        exoPlayer.decreaseDeviceVolume()
    }

    override fun decreaseDeviceVolume(flags: Int) {
        exoPlayer.decreaseDeviceVolume(flags)
    }

    @Deprecated("Deprecated in Java")
    override fun setDeviceMuted(muted: Boolean) {
        exoPlayer.isDeviceMuted = muted
    }

    override fun setDeviceMuted(muted: Boolean, flags: Int) {
        exoPlayer.setDeviceMuted(muted, flags)
    }

}