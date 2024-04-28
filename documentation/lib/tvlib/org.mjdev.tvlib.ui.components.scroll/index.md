//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.scroll](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [ScrollBarVerticalConfigScope](-scroll-bar-vertical-config-scope/index.md) | [androidJvm]<br>data class [ScrollBarVerticalConfigScope](-scroll-bar-vertical-config-scope/index.md)(val iconSize: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = 24.dp, val iconPadding: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = 16.dp, val handleColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.Black, val handleBorderColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.Black, val handleShape: [Shape](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Shape.html) = RoundedCornerShape(12.0.dp), val handleWidth: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = 8.dp, val handleBorderWidth: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = 1.dp) |

## Functions

| Name | Summary |
|---|---|
| [ScrollBarVertical](-scroll-bar-vertical.md) | [androidJvm]<br>@[Preview](https://developer.android.com/reference/kotlin/androidx/compose/ui/tooling/preview/Preview.html)<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>fun [ScrollBarVertical](-scroll-bar-vertical.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, config: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)[ScrollBarVerticalConfigScope](-scroll-bar-vertical-config-scope/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, visible: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, scrollState: [ScrollState](https://developer.android.com/reference/kotlin/androidx/compose/foundation/ScrollState.html) = rememberScrollState()) |
| [WithScope](-with-scope.md) | [androidJvm]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>inline fun &lt;[T](-with-scope.md)&gt; [WithScope](-with-scope.md)(scope: [T](-with-scope.md), noinline config: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)[T](-with-scope.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), block: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)[T](-with-scope.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) |
