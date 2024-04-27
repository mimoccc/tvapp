//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.tv](index.md)/[LocalAudioRow](-local-audio-row.md)

# LocalAudioRow

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [LocalAudioRow](-local-audio-row.md)(title: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = R.string.title_audio_local, rowState: [TvLazyListState](https://developer.android.com/reference/kotlin/androidx/tv/foundation/lazy/list/TvLazyListState.html) = rememberTvLazyListState(), padding: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = 8.dp, backgroundColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.DarkGray.copy(alpha = 0.3f), roundCornerSize: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = 8.dp, backgroundShape: [Shape](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Shape.html) = RoundedCornerShape(roundCornerSize), selection: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, selectionArgs: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;? = null, sortOrder: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = AudioItem.SORT_ORDER_DATE_DESC, cardWidth: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = computeCardWidth(), contentScale: [ContentScale](https://developer.android.com/reference/kotlin/androidx/compose/ui/layout/ContentScale.html) = ContentScale.Crop, transform: ([Cursor](https://developer.android.com/reference/kotlin/android/database/Cursor.html)) -&gt; [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = { c -&gt; AudioItem(c) }, cursor: [Cursor](https://developer.android.com/reference/kotlin/android/database/Cursor.html)? = rememberCursor(
        uri = AudioItem.URI,
        projection = AudioItem.MEDIA_PROJECTION,
        selection = selection,
        selectionArgs = selectionArgs,
        sortOrder = sortOrder,
        transform = transform
    ), onItemFocus: (item: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, fromUser: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, openItem: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html).(item: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null)
