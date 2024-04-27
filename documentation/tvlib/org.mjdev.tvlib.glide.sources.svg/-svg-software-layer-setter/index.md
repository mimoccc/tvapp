//[tvlib](../../../index.md)/[org.mjdev.tvlib.glide.sources.svg](../index.md)/[SvgSoftwareLayerSetter](index.md)

# SvgSoftwareLayerSetter

[androidJvm]\
class [SvgSoftwareLayerSetter](index.md) : RequestListener&lt;[PictureDrawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/PictureDrawable.html)?&gt; 

Listener which updates the [ImageView](https://developer.android.com/reference/kotlin/android/widget/ImageView.html) to be software rendered, because [ ] /[Picture](https://developer.android.com/reference/kotlin/android/graphics/Picture.html) can't render on a hardware backed [Canvas](https://developer.android.com/reference/kotlin/android/graphics/Canvas.html).

## Constructors

| | |
|---|---|
| [SvgSoftwareLayerSetter](-svg-software-layer-setter.md) | [androidJvm]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onLoadFailed](on-load-failed.md) | [androidJvm]<br>open override fun [onLoadFailed](on-load-failed.md)(e: GlideException?, model: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, target: Target&lt;[PictureDrawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/PictureDrawable.html)?&gt;, isFirstResource: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onResourceReady](on-resource-ready.md) | [androidJvm]<br>open override fun [onResourceReady](on-resource-ready.md)(resource: [PictureDrawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/PictureDrawable.html), model: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html), target: Target&lt;[PictureDrawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/PictureDrawable.html)?&gt;?, dataSource: DataSource, isFirstResource: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
