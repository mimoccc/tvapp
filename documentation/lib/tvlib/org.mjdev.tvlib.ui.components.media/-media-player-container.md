//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.media](index.md)/[MediaPlayerContainer](-media-player-container.md)

# MediaPlayerContainer

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [MediaPlayerContainer](-media-player-container.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, visible: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, enableSwipeGestures: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, onError: [MediaPlayerState](-media-player-state/index.md).(error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) -&gt; [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = { e -&gt;
        Timber.e(e)
        false
    }, state: [MediaPlayerState](-media-player-state/index.md) = rememberMediaPlayerState(
        onError = onError
    ))
