//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.complex](index.md)/[FocusableCard](-focusable-card.md)

# FocusableCard

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [FocusableCard](-focusable-card.md)(item: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = null, modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, contentScale: [ContentScale](https://developer.android.com/reference/kotlin/androidx/compose/ui/layout/ContentScale.html) = ContentScale.Crop, scale: [CardScale](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardScale.html) = CardDefaults.scale(), shape: [CardShape](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardShape.html) = CardDefaults.shape(), textColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.White, textBackgroundUnselected: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.DarkGray.copy(alpha = 0.5f), textBackgroundSelected: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.Green.copy(alpha = 0.5f), colors: [CardColors](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardColors.html) = CardDefaults.colors(), border: [CardBorder](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardBorder.html) = CardDefaults.colorFocusBorder(Color.Green), glow: [CardGlow](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardGlow.html) = CardDefaults.colorFocusGlow(Color.Green), showTitle: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, placeholder: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {
        Image(
            painter = painterResource(R.drawable.broken_image),
            contentDescription = &quot;&quot;,
            contentScale = contentScale
        )
    }, imageRenderer: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {
        ImageAny(
            modifier = Modifier.fillMaxSize(),
            src = (item as? ItemWithImage&lt;*&gt;)?.image,
            contentDescription = (item as? ItemWithDescription&lt;*&gt;)?.description?.toString(),
            contentScale = contentScale,
            placeholder = placeholder
        )
    }, focused: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = isEditMode(), focusRequester: [FocusRequester](https://developer.android.com/reference/kotlin/androidx/compose/ui/focus/FocusRequester.html) = rememberFocusRequester(item), focusState: [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[FocusState](https://developer.android.com/reference/kotlin/androidx/compose/ui/focus/FocusState.html)&gt; = rememberFocusState(
        item,
        FocusHelper(focused)
    ), onFocus: (item: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, fromUser: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onFocusChange: (state: [FocusState](https://developer.android.com/reference/kotlin/androidx/compose/ui/focus/FocusState.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { state -&gt;
        if (state.isFocused || state.hasFocus) {
            // todo focused from user ?
            onFocus?.invoke(item, false)
        }
    }, titlePadding: [PaddingValues](https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/PaddingValues.html) = PaddingValues(8.dp), cardWidth: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = computeCardWidth(), aspectRatio: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) = 16f / 9f, isEdit: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = isEditMode(), onClick: (item: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null)
