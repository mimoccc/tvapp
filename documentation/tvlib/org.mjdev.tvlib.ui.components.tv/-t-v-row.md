//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.tv](index.md)/[TVRow](-t-v-row.md)

# TVRow

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [TVRow](-t-v-row.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, state: [TvLazyListState](https://developer.android.com/reference/kotlin/androidx/tv/foundation/lazy/list/TvLazyListState.html) = rememberTvLazyListState(), contentPadding: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = 0.dp, reverseLayout: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, horizontalArrangement: [Arrangement.Horizontal](https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/Arrangement.Horizontal.html) = if (!reverseLayout) Arrangement.Start
    else Arrangement.End, verticalAlignment: [Alignment.Vertical](https://developer.android.com/reference/kotlin/androidx/compose/ui/Alignment.Vertical.html) = Alignment.Top, userScrollEnabled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, pivotOffsets: [PivotOffsets](https://developer.android.com/reference/kotlin/androidx/tv/foundation/PivotOffsets.html) = PivotOffsets(), content: [TvLazyListScope](https://developer.android.com/reference/kotlin/androidx/tv/foundation/lazy/list/TvLazyListScope.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {})
