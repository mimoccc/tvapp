//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.carousel](index.md)/[Carousel](-carousel.md)

# Carousel

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [Carousel](-carousel.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, itemCount: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, carouselState: [CarouselState](-carousel-state/index.md) = remember(itemCount) { CarouselState() }, autoScrollDurationMillis: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = CarouselDefaults.TimeToDisplayItemMillis, contentTransformStartToEnd: [ContentTransform](https://developer.android.com/reference/kotlin/androidx/compose/animation/ContentTransform.html) = CarouselDefaults.contentTransform, contentTransformEndToStart: [ContentTransform](https://developer.android.com/reference/kotlin/androidx/compose/animation/ContentTransform.html) = CarouselDefaults.contentTransform, carouselIndicator: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)[BoxScope](https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/BoxScope.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {
        CarouselDefaults.IndicatorRow(
            itemCount = itemCount,
            activeItemIndex = carouselState.activeItemIndex,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
        )
    }, isAutoScrollActive: [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; = remember(itemCount) { mutableStateOf(false) }, focusState: [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[FocusState](https://developer.android.com/reference/kotlin/androidx/compose/ui/focus/FocusState.html)&gt; = remember(itemCount) { mutableStateOf(FocusHelper(false)) }, content: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)[AnimatedContentScope](https://developer.android.com/reference/kotlin/androidx/compose/animation/AnimatedContentScope.html).(index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {})
