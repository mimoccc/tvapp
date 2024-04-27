//[tvlib](../../../../index.md)/[org.mjdev.tvlib.glide](../../index.md)/[OkHttpUrlLoaderEx](../index.md)/[Factory](index.md)

# Factory

[androidJvm]\
class [Factory](index.md)(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) : ModelLoaderFactory&lt;GlideUrl, [InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)&gt; 

Custom factory

## Constructors

| | |
|---|---|
| [Factory](-factory.md) | [androidJvm]<br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) |

## Functions

| Name | Summary |
|---|---|
| [build](build.md) | [androidJvm]<br>@[Synchronized](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-synchronized/index.html)<br>open override fun [build](build.md)(multiFactory: MultiModelLoaderFactory): ModelLoader&lt;GlideUrl, [InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)&gt;<br>Prepare builder |
| [equals](../../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator fun [equals](../../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [hashCode](../../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [teardown](teardown.md) | [androidJvm]<br>open override fun [teardown](teardown.md)()<br>Destroy builder |
| [toString](../../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [toString](../../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [context](context.md) | [androidJvm]<br>val [context](context.md): [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)<br>Context |
| [factory](factory.md) | [androidJvm]<br>var [factory](factory.md): [OkHttpUrlLoaderEx](../index.md)?<br>OkHttpUrlLoaderEx? |
