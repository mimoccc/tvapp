//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.card](index.md)/[Card](-card.md)

# Card

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [Card](-card.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, scale: [CardScale](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardScale.html) = CardScale.None, onClick: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onLongClick: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, shape: [CardShape](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardShape.html) = CardDefaults.shape(), colors: [CardColors](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardColors.html) = CardDefaults.colors(), border: [CardBorder](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardBorder.html) = CardDefaults.NO_BORDER, focusGlowColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.Green, unFocusGlowColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.Transparent, glow: [CardGlow](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardGlow.html) = CardDefaults.colorFocusGlow(
        focusGlowColor,
        unFocusGlowColor
    ), aspectRatio: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)? = 16f / 9f, interactionSource: [MutableInteractionSource](https://developer.android.com/reference/kotlin/androidx/compose/foundation/interaction/MutableInteractionSource.html) = rememberMutableInteractionSource(), content: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)[ColumnScope](https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/ColumnScope.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {})
