//[app](../../../index.md)/[org.mjdev.tvapp.base.media](../index.md)/[MediaPlayerState](index.md)

# MediaPlayerState

[androidJvm]\
class [MediaPlayerState](index.md)(uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html) = Uri.EMPTY, autoPlay: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, startSeek: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 0)

## Constructors

| | |
|---|---|
| [MediaPlayerState](-media-player-state.md) | [androidJvm]<br>constructor(uri: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, autoPlay: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, startSeek: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 0)constructor(uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html) = Uri.EMPTY, autoPlay: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, startSeek: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 0) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [pause](pause.md) | [androidJvm]<br>fun [pause](pause.md)() |
| [play](play.md) | [androidJvm]<br>fun [play](play.md)() |
| [resume](resume.md) | [androidJvm]<br>fun [resume](resume.md)() |
| [seekTo](seek-to.md) | [androidJvm]<br>fun [seekTo](seek-to.md)(ms: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)) |
| [stop](stop.md) | [androidJvm]<br>fun [stop](stop.md)() |

## Properties

| Name | Summary |
|---|---|
| [hasMediaToPlay](has-media-to-play.md) | [androidJvm]<br>val [hasMediaToPlay](has-media-to-play.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isAutoPlay](is-auto-play.md) | [androidJvm]<br>val [isAutoPlay](is-auto-play.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isPlaying](is-playing.md) | [androidJvm]<br>val [isPlaying](is-playing.md): [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; |
| [mediaUri](media-uri.md) | [androidJvm]<br>val [mediaUri](media-uri.md): [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)&gt; |
| [seek](seek.md) | [androidJvm]<br>val [seek](seek.md): [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)&gt; |
