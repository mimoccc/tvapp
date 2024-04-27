//[tvlib](../../../index.md)/[org.mjdev.tvlib.glide.sources.albumart](../index.md)/[AlbumArtLoaderFactory](index.md)

# AlbumArtLoaderFactory

[androidJvm]\
class [AlbumArtLoaderFactory](index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) : ModelLoader&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, [InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)?&gt; , ModelLoaderFactory&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, [InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)?&gt;

## Constructors

| | |
|---|---|
| [AlbumArtLoaderFactory](-album-art-loader-factory.md) | [androidJvm]<br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) |

## Types

| Name | Summary |
|---|---|
| [CoverFetcher](-cover-fetcher/index.md) | [androidJvm]<br>inner class [CoverFetcher](-cover-fetcher/index.md)(source: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) : DataFetcher&lt;[InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)?&gt; |

## Functions

| Name | Summary |
|---|---|
| [build](build.md) | [androidJvm]<br>open override fun [build](build.md)(multiFactory: MultiModelLoaderFactory): ModelLoader&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, [InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)?&gt; |
| [buildLoadData](build-load-data.md) | [androidJvm]<br>open override fun [buildLoadData](build-load-data.md)(source: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), width: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), height: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), options: Options): ModelLoader.LoadData&lt;[InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)?&gt; |
| [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [handles](handles.md) | [androidJvm]<br>open override fun [handles](handles.md)(model: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [teardown](teardown.md) | [androidJvm]<br>open override fun [teardown](teardown.md)() |
| [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
