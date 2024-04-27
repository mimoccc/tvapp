//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.card](index.md)/[PhotoCard](-photo-card.md)

# PhotoCard

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [PhotoCard](-photo-card.md)(item: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = null, modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, contentScale: [ContentScale](https://developer.android.com/reference/kotlin/androidx/compose/ui/layout/ContentScale.html) = ContentScale.Fit, textColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.White, focused: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = isEditMode(), focusState: [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[FocusState](https://developer.android.com/reference/kotlin/androidx/compose/ui/focus/FocusState.html)&gt; = rememberFocusState(
        item,
        FocusHelper(focused)
    ), focusRequester: [FocusRequester](https://developer.android.com/reference/kotlin/androidx/compose/ui/focus/FocusRequester.html) = rememberFocusRequester(item), @[FloatRange](https://developer.android.com/reference/kotlin/androidx/annotation/FloatRange.html)(from = 0.0, to = 10.0)contrast: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) = 5.0f, @[FloatRange](https://developer.android.com/reference/kotlin/androidx/annotation/FloatRange.html)(from = -255.0, to = 255.0)brightness: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) = -255f, placeholder: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {
        ImageAny(
            modifier = Modifier.fillMaxSize().padding(64.dp),
            src = R.drawable.broken_image,
            contentScale = contentScale
        )
    }, imageRenderer: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {
        PhotoImage(
            modifier = Modifier.fillMaxSize(),
            src = (item as? ItemWithImage&lt;*&gt;)?.image,
            contentScale = contentScale,
            contrast = contrast,
            brightness = brightness,
            placeholder = placeholder,
            borderColor = if (focusState.isFocused) Color.Green else Color.Black,
            contentDescription = (item as? ItemWithDescription&lt;*&gt;)?.description?.toString(),
        )
    }, showTitle: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, cardWidth: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = computeCardWidth(), aspectRatio: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) = 16f / 9f, scale: [CardScale](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardScale.html) = CardDefaults.scale(), titlePadding: [PaddingValues](https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/PaddingValues.html) = PaddingValues(8.dp), onFocus: (item: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, fromUser: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onClick: (item: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null)
