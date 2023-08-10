/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.media

import android.content.Context
import android.net.Uri
import android.os.Looper
import android.view.Surface
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.TextureView
import androidx.compose.runtime.Composable
import androidx.media3.common.AudioAttributes
import androidx.media3.common.DeviceInfo
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.PlaybackException
import androidx.media3.common.PlaybackParameters
import androidx.media3.common.Player
import androidx.media3.common.Player.REPEAT_MODE_OFF
import androidx.media3.common.Timeline
import androidx.media3.common.TrackSelectionParameters
import androidx.media3.common.Tracks
import androidx.media3.common.VideoSize
import androidx.media3.common.text.CueGroup
import androidx.media3.common.util.Size

@Suppress("DeprecatedCallableAddReplaceWith")
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
interface IMediaPlayer : Player {

    val context: Context

    @Composable
    fun GetPlayerView()

    fun setMediaUri(uri: Uri)

    override fun prepare()

    override fun play()

    override fun pause()

    fun resume()

    override fun stop()

    fun dispose()

    override fun seekTo(value: Long)

    companion object {

        val EMPTY = object : IMediaPlayer {
            override val context: Context get() = throw (Exception("Unimplemented"))

            @Composable
            override fun GetPlayerView() {
            }

            override fun setMediaUri(uri: Uri) {}

            override fun prepare() {}

            override fun play() {}

            override fun pause() {}

            override fun resume() {}

            override fun stop() {}

            override fun dispose() {}

            override fun seekTo(value: Long) {}

            override fun seekTo(mediaItemIndex: Int, positionMs: Long) {
            }

            override fun getApplicationLooper(): Looper {
                return Looper.getMainLooper()
            }

            override fun addListener(listener: Player.Listener) {
            }

            override fun removeListener(listener: Player.Listener) {
            }

            override fun setMediaItems(mediaItems: MutableList<MediaItem>) {
            }

            override fun setMediaItems(mediaItems: MutableList<MediaItem>, resetPosition: Boolean) {
            }

            override fun setMediaItems(
                mediaItems: MutableList<MediaItem>,
                startIndex: Int,
                startPositionMs: Long
            ) {
            }

            override fun setMediaItem(mediaItem: MediaItem) {
            }

            override fun setMediaItem(mediaItem: MediaItem, startPositionMs: Long) {
            }

            override fun setMediaItem(mediaItem: MediaItem, resetPosition: Boolean) {
            }

            override fun addMediaItem(mediaItem: MediaItem) {
            }

            override fun addMediaItem(index: Int, mediaItem: MediaItem) {
            }

            override fun addMediaItems(mediaItems: MutableList<MediaItem>) {
            }

            override fun addMediaItems(index: Int, mediaItems: MutableList<MediaItem>) {
            }

            override fun moveMediaItem(currentIndex: Int, newIndex: Int) {
            }

            override fun moveMediaItems(fromIndex: Int, toIndex: Int, newIndex: Int) {
            }

            override fun replaceMediaItem(index: Int, mediaItem: MediaItem) {
            }

            override fun replaceMediaItems(
                fromIndex: Int,
                toIndex: Int,
                mediaItems: MutableList<MediaItem>
            ) {
            }

            override fun removeMediaItem(index: Int) {
            }

            override fun removeMediaItems(fromIndex: Int, toIndex: Int) {
            }

            override fun clearMediaItems() {
            }

            override fun isCommandAvailable(command: Int): Boolean {
                return false
            }

            override fun canAdvertiseSession(): Boolean {
                return false
            }

            override fun getAvailableCommands(): Player.Commands {
                return Player.Commands.EMPTY
            }

            override fun getPlaybackState(): Int {
                return Player.STATE_IDLE
            }

            override fun getPlaybackSuppressionReason(): Int {
                return Player.PLAYBACK_SUPPRESSION_REASON_NONE
            }

            override fun isPlaying(): Boolean {
                return false
            }

            override fun getPlayerError(): PlaybackException? {
                return null
            }

            override fun setPlayWhenReady(playWhenReady: Boolean) {
            }

            override fun getPlayWhenReady(): Boolean {
                return false
            }

            override fun setRepeatMode(repeatMode: Int) {
            }

            override fun getRepeatMode(): Int {
                return REPEAT_MODE_OFF
            }

            override fun setShuffleModeEnabled(shuffleModeEnabled: Boolean) {
            }

            override fun getShuffleModeEnabled(): Boolean {
                return false
            }

            override fun isLoading(): Boolean {
                return false
            }

            override fun seekToDefaultPosition() {
            }

            override fun seekToDefaultPosition(mediaItemIndex: Int) {
            }

            override fun getSeekBackIncrement(): Long {
                return 0
            }

            override fun seekBack() {
            }

            override fun getSeekForwardIncrement(): Long {
                return 0
            }

            override fun seekForward() {
            }

            @Deprecated("Deprecated in Java")
            override fun hasPrevious(): Boolean {
                return false
            }

            @Deprecated("Deprecated in Java")
            override fun hasPreviousWindow(): Boolean {
                return false
            }

            override fun hasPreviousMediaItem(): Boolean {
                return false
            }

            @Deprecated("Deprecated in Java")
            override fun previous() {
            }

            @Deprecated("Deprecated in Java")
            override fun seekToPreviousWindow() {
            }

            override fun seekToPreviousMediaItem() {
            }

            override fun getMaxSeekToPreviousPosition(): Long {
                return 0
            }

            override fun seekToPrevious() {
            }

            @Deprecated("Deprecated in Java")
            override fun hasNext(): Boolean {
                return false
            }

            @Deprecated("Deprecated in Java")
            override fun hasNextWindow(): Boolean {
                return false
            }

            override fun hasNextMediaItem(): Boolean {
                return false
            }

            @Deprecated("Deprecated in Java")
            override fun next() {
            }

            @Deprecated("Deprecated in Java")
            override fun seekToNextWindow() {
            }

            override fun seekToNextMediaItem() {
            }

            override fun seekToNext() {
            }

            override fun setPlaybackParameters(playbackParameters: PlaybackParameters) {
            }

            override fun setPlaybackSpeed(speed: Float) {
            }

            override fun getPlaybackParameters(): PlaybackParameters {
                return PlaybackParameters(1f)
            }

            override fun release() {
            }

            override fun getCurrentTracks(): Tracks {
                return Tracks.EMPTY
            }

            override fun getTrackSelectionParameters(): TrackSelectionParameters {
                return TrackSelectionParameters.DEFAULT_WITHOUT_CONTEXT
            }

            override fun setTrackSelectionParameters(parameters: TrackSelectionParameters) {
            }

            override fun getMediaMetadata(): MediaMetadata {
                return MediaMetadata.EMPTY
            }

            override fun getPlaylistMetadata(): MediaMetadata {
                return MediaMetadata.EMPTY
            }

            override fun setPlaylistMetadata(mediaMetadata: MediaMetadata) {
            }

            override fun getCurrentManifest(): Any? {
                return null
            }

            override fun getCurrentTimeline(): Timeline {
                return Timeline.EMPTY
            }

            override fun getCurrentPeriodIndex(): Int {
                return 0
            }

            @Deprecated("Deprecated in Java")
            override fun getCurrentWindowIndex(): Int {
                return 0
            }

            override fun getCurrentMediaItemIndex(): Int {
                return 0
            }

            @Deprecated("Deprecated in Java")
            override fun getNextWindowIndex(): Int {
                return 0
            }

            override fun getNextMediaItemIndex(): Int {
                return 0
            }

            @Deprecated("Deprecated in Java")
            override fun getPreviousWindowIndex(): Int {
                return 0
            }

            override fun getPreviousMediaItemIndex(): Int {
                return 0
            }

            override fun getCurrentMediaItem(): MediaItem? {
                return null
            }

            override fun getMediaItemCount(): Int {
                return 0
            }

            override fun getMediaItemAt(index: Int): MediaItem {
                return MediaItem.EMPTY
            }

            override fun getDuration(): Long {
                return 0
            }

            override fun getCurrentPosition(): Long {
                return 0
            }

            override fun getBufferedPosition(): Long {
                return 0
            }

            override fun getBufferedPercentage(): Int {
                return 0
            }

            override fun getTotalBufferedDuration(): Long {
                return 0
            }

            @Deprecated("Deprecated in Java")
            override fun isCurrentWindowDynamic(): Boolean {
                return false
            }

            override fun isCurrentMediaItemDynamic(): Boolean {
                return false
            }

            @Deprecated("Deprecated in Java")
            override fun isCurrentWindowLive(): Boolean {
                return false
            }

            override fun isCurrentMediaItemLive(): Boolean {
                return false
            }

            override fun getCurrentLiveOffset(): Long {
                return 0
            }

            @Deprecated("Deprecated in Java")
            override fun isCurrentWindowSeekable(): Boolean {
                return false
            }

            override fun isCurrentMediaItemSeekable(): Boolean {
                return false
            }

            override fun isPlayingAd(): Boolean {
                return false
            }

            override fun getCurrentAdGroupIndex(): Int {
                return 0
            }

            override fun getCurrentAdIndexInAdGroup(): Int {
                return 0
            }

            override fun getContentDuration(): Long {
                return 0
            }

            override fun getContentPosition(): Long {
                return 0
            }

            override fun getContentBufferedPosition(): Long {
                return 0
            }

            override fun getAudioAttributes(): AudioAttributes {
                return AudioAttributes.DEFAULT
            }

            override fun setVolume(volume: Float) {
            }

            override fun getVolume(): Float {
                return 0f
            }

            override fun clearVideoSurface() {
            }

            override fun clearVideoSurface(surface: Surface?) {
            }

            override fun setVideoSurface(surface: Surface?) {
            }

            override fun setVideoSurfaceHolder(surfaceHolder: SurfaceHolder?) {
            }

            override fun clearVideoSurfaceHolder(surfaceHolder: SurfaceHolder?) {
            }

            override fun setVideoSurfaceView(surfaceView: SurfaceView?) {
            }

            override fun clearVideoSurfaceView(surfaceView: SurfaceView?) {
            }

            override fun setVideoTextureView(textureView: TextureView?) {
            }

            override fun clearVideoTextureView(textureView: TextureView?) {
            }

            override fun getVideoSize(): VideoSize {
                return VideoSize(0, 0)
            }

            override fun getSurfaceSize(): Size {
                return Size(0, 0)
            }

            override fun getCurrentCues(): CueGroup {
                return CueGroup.EMPTY_TIME_ZERO
            }

            override fun getDeviceInfo(): DeviceInfo {
                return DeviceInfo.UNKNOWN
            }

            override fun getDeviceVolume(): Int {
                return 0
            }

            override fun isDeviceMuted(): Boolean {
                return false
            }

            @Deprecated("Deprecated in Java")
            override fun setDeviceVolume(volume: Int) {
            }

            override fun setDeviceVolume(volume: Int, flags: Int) {}

            @Deprecated("Deprecated in Java")
            override fun increaseDeviceVolume() {
            }

            override fun increaseDeviceVolume(flags: Int) {}

            @Deprecated("Deprecated in Java")
            override fun decreaseDeviceVolume() {
            }

            override fun decreaseDeviceVolume(flags: Int) {}

            @Deprecated("Deprecated in Java")
            override fun setDeviceMuted(muted: Boolean) {
            }

            override fun setDeviceMuted(muted: Boolean, flags: Int) {}

        }

    }

}