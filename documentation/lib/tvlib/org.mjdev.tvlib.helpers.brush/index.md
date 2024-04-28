//[tvlib](../../index.md)/[org.mjdev.tvlib.helpers.brush](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [BrushBuilder](-brush-builder/index.md) | [androidJvm]<br>class [BrushBuilder](-brush-builder/index.md) |
| [GradientAngle](-gradient-angle/index.md) | [androidJvm]<br>enum [GradientAngle](-gradient-angle/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[GradientAngle](-gradient-angle/index.md)&gt; |
| [GradientOffset](-gradient-offset/index.md) | [androidJvm]<br>data class [GradientOffset](-gradient-offset/index.md)(val start: [Offset](https://developer.android.com/reference/kotlin/androidx/compose/ui/geometry/Offset.html), val end: [Offset](https://developer.android.com/reference/kotlin/androidx/compose/ui/geometry/Offset.html)) |
| [SquashedOval](-squashed-oval/index.md) | [androidJvm]<br>class [SquashedOval](-squashed-oval/index.md) : [Shape](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Shape.html) |

## Functions

| Name | Summary |
|---|---|
| [angledGradientBackground](angled-gradient-background.md) | [androidJvm]<br>fun [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html).[angledGradientBackground](angled-gradient-background.md)(colorStops: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Pair](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)&lt;[Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html), [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html)&gt;&gt;, degrees: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)): [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) |
| [gradientBrushLinear](gradient-brush-linear.md) | [androidJvm]<br>fun [gradientBrushLinear](gradient-brush-linear.md)(vararg colors: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Brush](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Brush.html) |
| [GradientOffset](-gradient-offset.md) | [androidJvm]<br>fun [GradientOffset](-gradient-offset.md)(angle: [GradientAngle](-gradient-angle/index.md) = GradientAngle.CW0): [GradientOffset](-gradient-offset/index.md) |
