//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.button](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [MimeType](-mime-type/index.md) | [androidJvm]<br>enum [MimeType](-mime-type/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[MimeType](-mime-type/index.md)&gt; |

## Functions

| Name | Summary |
|---|---|
| [Button](-button.md) | [androidJvm]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>fun [Button](-button.md)(text: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = &quot;test&quot;, modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, fontSize: [TextUnit](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/TextUnit.html) = 24.sp, fontWeight: [FontWeight](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/font/FontWeight.html) = FontWeight.Bold, color: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.White, containerColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.LightGray, contentPadding: [PaddingValues](https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/PaddingValues.html) = PaddingValues(0.dp), roundRectSize: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = 8.dp, shape: [Shape](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Shape.html) = RoundedCornerShape(roundRectSize), borderSize: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = 2.dp, borderColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.White, glow: [ButtonGlow](https://developer.android.com/reference/kotlin/androidx/tv/material3/ButtonGlow.html) = ButtonDefaults.NO_GLOW, scale: [ButtonScale](https://developer.android.com/reference/kotlin/androidx/tv/material3/ButtonScale.html) = ButtonDefaults.NO_SCALE, focusState: [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[FocusState](https://developer.android.com/reference/kotlin/androidx/compose/ui/focus/FocusState.html)&gt; = rememberFocusState(), content: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)[RowScope](https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/RowScope.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {         TextAny(             text = text,             color = color,             textAlign = TextAlign.Center,             maxLines = 1         )     }, onClick: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null) |
| [ShareButton](-share-button.md) | [androidJvm]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>fun [ShareButton](-share-button.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, data: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;Share&quot;, type: [MimeType](-mime-type/index.md) = MimeType.PlainText) |

## Properties

| Name | Summary |
|---|---|
| [NO_GLOW](-n-o_-g-l-o-w.md) | [androidJvm]<br>val [ButtonDefaults](https://developer.android.com/reference/kotlin/androidx/tv/material3/ButtonDefaults.html).[NO_GLOW](-n-o_-g-l-o-w.md): [ButtonGlow](https://developer.android.com/reference/kotlin/androidx/tv/material3/ButtonGlow.html) |
| [NO_SCALE](-n-o_-s-c-a-l-e.md) | [androidJvm]<br>val [ButtonDefaults](https://developer.android.com/reference/kotlin/androidx/tv/material3/ButtonDefaults.html).[NO_SCALE](-n-o_-s-c-a-l-e.md): [ButtonScale](https://developer.android.com/reference/kotlin/androidx/tv/material3/ButtonScale.html) |
