//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.gallery](index.md)/[Gallery](-gallery.md)

# Gallery

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [Gallery](-gallery.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, list: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?&gt; = listOf(Unit), index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, delayedHide: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 5000, controlsState: [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; = remember { mutableStateOf(true) }, currentItemIndex: [MutableIntState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableIntState.html) = remember { mutableIntStateOf(index) }, focusRequester: [FocusRequester](https://developer.android.com/reference/kotlin/androidx/compose/ui/focus/FocusRequester.html) = rememberFocusRequester(), imageScaleType: [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[ContentScale](https://developer.android.com/reference/kotlin/androidx/compose/ui/layout/ContentScale.html)&gt; = rememberSaveable(
        saver = contentScaleSaver
    ) {
        mutableStateOf(ContentScale.Crop)
    }, listState: [TvLazyListState](https://developer.android.com/reference/kotlin/androidx/tv/foundation/lazy/list/TvLazyListState.html) = rememberTvLazyListState(), switchImageScale: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {
        imageScaleType.value = when (imageScaleType.value) {
            ContentScale.Fit -&gt; ContentScale.Crop
            ContentScale.Crop -&gt; ContentScale.Fit
            else -&gt; ContentScale.Fit
        }
    }, customContentViewer: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)(src: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, type: [ItemType](../org.mjdev.tvlib.helpers.media/-item-type/index.md), list: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?&gt;) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { _, _, _ -&gt; })
