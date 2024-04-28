//[tvlib](../../../index.md)/[org.mjdev.tvlib.glide](../index.md)/[LoaderTarget](index.md)

# LoaderTarget

[androidJvm]\
class [LoaderTarget](index.md)(width: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = SIZE_ORIGINAL, height: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = SIZE_ORIGINAL, val onLoadStarted: (drawable: [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, val onLoadFinished: (drawable: [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null) : CustomTarget&lt;[Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)&gt; 

Custom Glide simplified load target

## Constructors

| | |
|---|---|
| [LoaderTarget](-loader-target.md) | [androidJvm]<br>constructor(onLoadStarted: (drawable: [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onLoadFinished: (drawable: [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null)constructor(width: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = SIZE_ORIGINAL, height: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = SIZE_ORIGINAL, onLoadStarted: (drawable: [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onLoadFinished: (drawable: [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getSize](index.md#-681392503%2FFunctions%2F-1596939238) | [androidJvm]<br>override fun [getSize](index.md#-681392503%2FFunctions%2F-1596939238)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: SizeReadyCallback) |
| [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onDestroy](index.md#601477902%2FFunctions%2F-1596939238) | [androidJvm]<br>open override fun [onDestroy](index.md#601477902%2FFunctions%2F-1596939238)() |
| [onLoadCleared](on-load-cleared.md) | [androidJvm]<br>open override fun [onLoadCleared](on-load-cleared.md)(placeholder: [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)?)<br>Load stopped or cleared handler |
| [onLoadFailed](on-load-failed.md) | [androidJvm]<br>open override fun [onLoadFailed](on-load-failed.md)(errorDrawable: [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)?)<br>Load failed handler |
| [onLoadStarted](on-load-started.md) | [androidJvm]<br>open override fun [onLoadStarted](on-load-started.md)(placeholder: [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)?)<br>Load start handler |
| [onResourceReady](on-resource-ready.md) | [androidJvm]<br>open override fun [onResourceReady](on-resource-ready.md)(resource: [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html), transition: Transition&lt;in [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)&gt;?)<br>Load sucessfully finished handler |
| [onStart](index.md#487324262%2FFunctions%2F-1596939238) | [androidJvm]<br>open override fun [onStart](index.md#487324262%2FFunctions%2F-1596939238)() |
| [onStop](index.md#2142417872%2FFunctions%2F-1596939238) | [androidJvm]<br>open override fun [onStop](index.md#2142417872%2FFunctions%2F-1596939238)() |
| [removeCallback](index.md#-1043233133%2FFunctions%2F-1596939238) | [androidJvm]<br>override fun [removeCallback](index.md#-1043233133%2FFunctions%2F-1596939238)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: SizeReadyCallback) |
| [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [onLoadFinished](on-load-finished.md) | [androidJvm]<br>val [onLoadFinished](on-load-finished.md): (drawable: [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null<br>Function1<@kotlin.ParameterName Drawable?, Unit>? on finish handler |
| [onLoadStarted](on-load-started.md) | [androidJvm]<br>val [onLoadStarted](on-load-started.md): (drawable: [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null<br>Function1<@kotlin.ParameterName Drawable?, Unit>? on start handler |
| [request](index.md#865001242%2FProperties%2F-1596939238) | [androidJvm]<br>@[Nullable](https://developer.android.com/reference/kotlin/androidx/annotation/Nullable.html)<br>@get:[Nullable](https://developer.android.com/reference/kotlin/androidx/annotation/Nullable.html)<br>var [request](index.md#865001242%2FProperties%2F-1596939238): Request? |
