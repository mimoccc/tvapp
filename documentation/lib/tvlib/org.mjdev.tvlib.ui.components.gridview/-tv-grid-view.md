//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.gridview](index.md)/[TvGridView](-tv-grid-view.md)

# TvGridView

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [TvGridView](-tv-grid-view.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, items: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?&gt; = listOf(
        Unit, Unit, Unit,
        Unit, Unit, Unit,
        Unit, Unit, Unit,
        Unit, Unit, Unit,
        Unit, Unit, Unit,
        Unit, Unit, Unit
    ), state: [TvLazyGridState](https://developer.android.com/reference/kotlin/androidx/tv/foundation/lazy/grid/TvLazyGridState.html) = rememberTvLazyGridState(), cardWidth: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = computeCardWidth(), aspectRatio: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) = 16f / 9f, contentScale: [ContentScale](https://developer.android.com/reference/kotlin/androidx/compose/ui/layout/ContentScale.html) = ContentScale.Crop, onItemFocus: (item: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, fromUser: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onItemClick: (item: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, verticalArrangement: [Arrangement.HorizontalOrVertical](https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/Arrangement.HorizontalOrVertical.html) = Arrangement.spacedBy(16.dp), horizontalArrangement: [Arrangement.HorizontalOrVertical](https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/Arrangement.HorizontalOrVertical.html) = Arrangement.spacedBy(16.dp), contentOfItem: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)(item: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { item -&gt;
        PhotoCard(
            item = item,
            aspectRatio = aspectRatio,
            contentScale = contentScale,
            cardWidth = cardWidth,
            onClick = onItemClick,
            onFocus = onItemFocus
        )
    })
