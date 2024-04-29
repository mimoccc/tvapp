//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.card](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [FocusHelper](-focus-helper/index.md) | [androidJvm]<br>class [FocusHelper](-focus-helper/index.md)(focused: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) : [FocusState](https://developer.android.com/reference/kotlin/androidx/compose/ui/focus/FocusState.html) |

## Functions

| Name | Summary |
|---|---|
| [Card](-card.md) | [androidJvm]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>fun [Card](-card.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, scale: [CardScale](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardScale.html) = CardScale.None, onClick: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onLongClick: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, shape: [CardShape](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardShape.html) = CardDefaults.shape(), colors: [CardColors](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardColors.html) = CardDefaults.colors(), border: [CardBorder](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardBorder.html) = CardDefaults.NO_BORDER, focusGlowColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.Green, unFocusGlowColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.Transparent, glow: [CardGlow](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardGlow.html) = CardDefaults.colorFocusGlow(         focusGlowColor,         unFocusGlowColor     ), aspectRatio: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)? = 16f / 9f, interactionSource: [MutableInteractionSource](https://developer.android.com/reference/kotlin/androidx/compose/foundation/interaction/MutableInteractionSource.html) = rememberMutableInteractionSource(), content: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)[ColumnScope](https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/ColumnScope.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}) |
| [CardContent](-card-content.md) | [androidJvm]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>fun [CardContent](-card-content.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, subtitleAlpha: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) = 0.6f, descriptionAlpha: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) = 0.8f, title: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {         AutoHideEmptyText(&quot;title&quot;)     }, subtitle: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {         Box(Modifier.graphicsLayer {             alpha = subtitleAlpha         }) {             AutoHideEmptyText(&quot;subtitle&quot;)         }     }, description: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {         Box(Modifier.graphicsLayer {             alpha = descriptionAlpha         }) {             AutoHideEmptyText(&quot;description&quot;)         }     }) |
| [colorFocusBorder](color-focus-border.md) | [androidJvm]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>fun [CardDefaults](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardDefaults.html).[colorFocusBorder](color-focus-border.md)(colorFocused: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.Green, colorUnFocused: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.Black, colorPressed: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.Yellow, width: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = 1.dp, roundCornerSize: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = 8.dp): [CardBorder](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardBorder.html) |
| [colorFocusGlow](color-focus-glow.md) | [androidJvm]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>fun [CardDefaults](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardDefaults.html).[colorFocusGlow](color-focus-glow.md)(focusColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.Green, onUnFocusColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.Transparent, elevation: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = 10.dp): [CardGlow](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardGlow.html) |
| [ItemCard](-item-card.md) | [androidJvm]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>fun [ItemCard](-item-card.md)(item: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = null, modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, contentScale: [ContentScale](https://developer.android.com/reference/kotlin/androidx/compose/ui/layout/ContentScale.html) = ContentScale.Crop, scale: [CardScale](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardScale.html) = CardDefaults.scale(), shape: [CardShape](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardShape.html) = CardDefaults.shape(), textColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.White, colors: [CardColors](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardColors.html) = CardDefaults.colors(), border: [CardBorder](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardBorder.html) = CardDefaults.colorFocusBorder(Color.Green), glow: [CardGlow](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardGlow.html) = CardDefaults.colorFocusGlow(Color.Green), aspectRatio: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) = 16f / 9f, placeholder: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {         Image(             painter = painterResource(R.drawable.broken_image),             &quot;&quot;,             modifier         )     }, imageRenderer: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {         ImageAny(             modifier = modifier.fillMaxSize(),             src = (item as? ItemWithImage&lt;*&gt;)?.image,             contentDescription = (item as? ItemWithDescription&lt;*&gt;)?.description?.toString(),             contentScale = contentScale,             placeholder = placeholder         )     }, cardWidth: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = computeCardWidth(), focused: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = isEditMode(), focusState: [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[FocusState](https://developer.android.com/reference/kotlin/androidx/compose/ui/focus/FocusState.html)&gt; = rememberFocusState(         item,         FocusHelper(focused)     ), focusRequester: [FocusRequester](https://developer.android.com/reference/kotlin/androidx/compose/ui/focus/FocusRequester.html) = rememberFocusRequester(item), titlePadding: [PaddingValues](https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/PaddingValues.html) = PaddingValues(8.dp), showTitle: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, onFocus: (item: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, fromUser: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onClick: (item: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null) |
| [PhotoCard](-photo-card.md) | [androidJvm]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>fun [PhotoCard](-photo-card.md)(item: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = null, modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, contentScale: [ContentScale](https://developer.android.com/reference/kotlin/androidx/compose/ui/layout/ContentScale.html) = ContentScale.Fit, textColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.White, focused: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = isEditMode(), focusState: [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[FocusState](https://developer.android.com/reference/kotlin/androidx/compose/ui/focus/FocusState.html)&gt; = rememberFocusState(         item,         FocusHelper(focused)     ), focusRequester: [FocusRequester](https://developer.android.com/reference/kotlin/androidx/compose/ui/focus/FocusRequester.html) = rememberFocusRequester(item), @[FloatRange](https://developer.android.com/reference/kotlin/androidx/annotation/FloatRange.html)(from = 0.0, to = 10.0)contrast: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) = 5.0f, @[FloatRange](https://developer.android.com/reference/kotlin/androidx/annotation/FloatRange.html)(from = -255.0, to = 255.0)brightness: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) = -255f, placeholder: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {         ImageAny(             modifier = Modifier.fillMaxSize().padding(64.dp),             src = R.drawable.broken_image,             contentScale = contentScale         )     }, imageRenderer: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {         PhotoImage(             modifier = Modifier.fillMaxSize(),             src = (item as? ItemWithImage&lt;*&gt;)?.image,             contentScale = contentScale,             contrast = contrast,             brightness = brightness,             placeholder = placeholder,             borderColor = if (focusState.isFocused) Color.Green else Color.Black,             contentDescription = (item as? ItemWithDescription&lt;*&gt;)?.description?.toString(),         )     }, showTitle: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, cardWidth: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = computeCardWidth(), aspectRatio: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) = 16f / 9f, scale: [CardScale](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardScale.html) = CardDefaults.scale(), titlePadding: [PaddingValues](https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/PaddingValues.html) = PaddingValues(8.dp), onFocus: (item: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, fromUser: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onClick: (item: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null) |
| [rainbowBorder](rainbow-border.md) | [androidJvm]<br>fun [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html).[rainbowBorder](rainbow-border.md)(borderSize: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = 4.dp, borderRound: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = 8.dp, shape: [Shape](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Shape.html) = RoundedCornerShape(borderRound)): [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) |
| [rainbowColorsBrush](rainbow-colors-brush.md) | [androidJvm]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>fun [rainbowColorsBrush](rainbow-colors-brush.md)(key: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = null): [Brush](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Brush.html) |

## Properties

| Name | Summary |
|---|---|
| [NO_BORDER](-n-o_-b-o-r-d-e-r.md) | [androidJvm]<br>@get:[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>val [CardDefaults](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardDefaults.html).[NO_BORDER](-n-o_-b-o-r-d-e-r.md): [CardBorder](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardBorder.html) |
| [NO_GLOW](-n-o_-g-l-o-w.md) | [androidJvm]<br>@get:[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>val [CardDefaults](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardDefaults.html).[NO_GLOW](-n-o_-g-l-o-w.md): [CardGlow](https://developer.android.com/reference/kotlin/androidx/tv/material3/CardGlow.html) |