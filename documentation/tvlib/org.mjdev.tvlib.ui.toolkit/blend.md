//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.toolkit](index.md)/[blend](blend.md)

# blend

[androidJvm]\
fun [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html).[blend](blend.md)(color: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html), @[FloatRange](https://developer.android.com/reference/kotlin/androidx/annotation/FloatRange.html)(from = 0.0, to = 1.0)ratio: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)): [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html)

Blend between two [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html)s using the given ratio. A blend ratio of 0.0 will result in color1, 0.5 will give an even blend, 1.0 will result in color2.

#### Parameters

androidJvm

| | |
|---|---|
| color | – the second ARGB color |
| ratio | – the blend ratio of color1 to color2 |
