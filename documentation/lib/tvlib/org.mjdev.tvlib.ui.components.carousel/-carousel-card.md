//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.carousel](index.md)/[CarouselCard](-carousel-card.md)

# CarouselCard

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [CarouselCard](-carousel-card.md)(item: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = null, modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, contentScale: [ContentScale](https://developer.android.com/reference/kotlin/androidx/compose/ui/layout/ContentScale.html) = ContentScale.Crop, scale: [CardScale](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardScale.html) = CardScale.None, focused: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = isEditMode(), focusState: [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[FocusState](https://developer.android.com/reference/kotlin/androidx/compose/ui/focus/FocusState.html)&gt; = rememberFocusState(
        item,
        FocusHelper(focused)
    ), titlePadding: [PaddingValues](https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/PaddingValues.html) = PaddingValues(8.dp, 12.dp, 8.dp, 12.dp), onFocus: (item: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, fromUser: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onClick: (item: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null)
