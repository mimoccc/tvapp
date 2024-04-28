//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.tv](index.md)/[ScrollableTvLazyColumn](-scrollable-tv-lazy-column.md)

# ScrollableTvLazyColumn

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [ScrollableTvLazyColumn](-scrollable-tv-lazy-column.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, contentPadding: [PaddingValues](https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/PaddingValues.html) = PaddingValues(0.dp), reverseLayout: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, verticalArrangement: [Arrangement.Vertical](https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/Arrangement.Vertical.html) = if (!reverseLayout) Arrangement.Top
    else Arrangement.Bottom, horizontalAlignment: [Alignment.Horizontal](https://developer.android.com/reference/kotlin/androidx/compose/ui/Alignment.Horizontal.html) = Alignment.Start, userScrollEnabled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, pivotOffsets: [PivotOffsets](https://developer.android.com/reference/kotlin/androidx/tv/foundation/PivotOffsets.html) = PivotOffsets(), state: [TvLazyListState](https://developer.android.com/reference/kotlin/androidx/tv/foundation/lazy/list/TvLazyListState.html) = rememberTvLazyListState(), content: [TvLazyListScope](https://developer.android.com/reference/kotlin/androidx/tv/foundation/lazy/list/TvLazyListScope.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {})
