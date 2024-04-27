//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.toolkit](index.md)/[suggestContentColorFor](suggest-content-color-for.md)

# suggestContentColorFor

[androidJvm]\
fun [suggestContentColorFor](suggest-content-color-for.md)(backgroundColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html)): [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html)

Suggest Color Generates a color that can be represented on provided background [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) based on the luminance

#### Return

[Color.White](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.Companion.html#white) if luminance < 0.3 otherwise [Color.Black](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.Companion.html#black)

#### Parameters

androidJvm

| | |
|---|---|
| backgroundColor | -     provided background color |
