//[tvlib](../../../index.md)/[org.mjdev.tvlib.ui.components.media](../index.md)/[MediaPlayerState](index.md)/[MediaPlayerState](-media-player-state.md)

# MediaPlayerState

[androidJvm]\
constructor(@Json(name = &quot;context&quot;, ignore = true)context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)? = null, @Json(name = &quot;initialItems&quot;)initItems: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[MediaItem](https://developer.android.com/reference/kotlin/androidx/media3/common/MediaItem.html)&gt; = emptyList(), @Json(name = &quot;initialIndex&quot;)initItemIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, @Json(name = &quot;autoPlay&quot;)autoPlay: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, @Json(name = &quot;seekAtStart&quot;)startSeek: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)? = null, @Json(name = &quot;playNextOnError&quot;)playNextOnError: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, @Json(name = &quot;onError&quot;, ignore = true)onError: [MediaPlayerState](index.md).(e: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) -&gt; [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = { false })