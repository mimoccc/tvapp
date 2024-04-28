//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.toolkit](index.md)/[hsl](hsl.md)

# hsl

[androidJvm]\
fun [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html).[hsl](hsl.md)(hue: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)? = null, saturation: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)? = null, lightness: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)? = null, alpha: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)? = null): [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html)

Return a copy of [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) from [hue](hsl.md), [saturation](hsl.md), and [lightness](hsl.md) (HSL representation).

#### Parameters

androidJvm

| | |
|---|---|
| hue | The color value in the range (0..360), where 0 is red, 120 is green, and 240 is blue; default value is null; which makes is unaltered. |
| saturation | The amount of [hue](hsl.md) represented in the color in the range (0..1), where 0 has no color and 1 is fully saturated; default value is null; which makes is unaltered. |
| lightness | A range of (0..1) where 0 is black, 0.5 is fully colored, and 1 is white; default value is null; which makes is unaltered. |
