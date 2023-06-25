//[app](../../index.md)/[org.mjdev.tvapp.base.media](index.md)/[MediaPlayerContainer](-media-player-container.md)

# MediaPlayerContainer

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [MediaPlayerContainer](-media-player-container.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)? = Uri.EMPTY, autoPlay: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, startSeek: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 0, mediaPlayer: [IMediaPlayer](-i-media-player/index.md) = MediaPlayerContainerDefaults.exoPlayer, background: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)(state: [MediaPlayerState](-media-player-state/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, mediaPlayerOverlay: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)(state: [MediaPlayerState](-media-player-state/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, mediaPlayerControls: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)(state: [MediaPlayerState](-media-player-state/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {})
