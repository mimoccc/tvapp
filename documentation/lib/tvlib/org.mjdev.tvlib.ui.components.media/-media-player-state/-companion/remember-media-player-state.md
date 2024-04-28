//[tvlib](../../../../index.md)/[org.mjdev.tvlib.ui.components.media](../../index.md)/[MediaPlayerState](../index.md)/[Companion](index.md)/[rememberMediaPlayerState](remember-media-player-state.md)

# rememberMediaPlayerState

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [rememberMediaPlayerState](remember-media-player-state.md)(items: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[MediaItem](https://developer.android.com/reference/kotlin/androidx/media3/common/MediaItem.html)&gt; = listOf(), itemToPlay: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, autoPlay: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, playNextOnError: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, startSeek: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null, context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html) = LocalContext.current, onError: [MediaPlayerState](../index.md).(error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) -&gt; [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = { false }, isEdit: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = isEditMode()): [MediaPlayerState](../index.md)
