//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.text](index.md)/[AutoHideEmptyText](-auto-hide-empty-text.md)

# AutoHideEmptyText

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [AutoHideEmptyText](-auto-hide-empty-text.md)(text: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = &quot;test&quot;, modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, color: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.Unspecified, fontSize: [TextUnit](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/TextUnit.html) = TextUnit.Unspecified, fontStyle: [FontStyle](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/font/FontStyle.html)? = null, fontWeight: [FontWeight](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/font/FontWeight.html)? = null, fontFamily: [FontFamily](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/font/FontFamily.html)? = null, letterSpacing: [TextUnit](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/TextUnit.html) = TextUnit.Unspecified, textDecoration: [TextDecoration](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/style/TextDecoration.html)? = null, textAlign: [TextAlign](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/style/TextAlign.html)? = null, lineHeight: [TextUnit](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/TextUnit.html) = TextUnit.Unspecified, overflow: [TextOverflow](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/style/TextOverflow.html) = TextOverflow.Clip, softWrap: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, maxLines: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = Int.MAX_VALUE, onTextLayout: ([TextLayoutResult](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/TextLayoutResult.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, style: [TextStyle](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/TextStyle.html) = LocalTextStyle.current)