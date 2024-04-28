//[tvlib](../../../index.md)/[org.mjdev.tvlib.webscrapper.adblock](../index.md)/[AdBlock](index.md)

# AdBlock

[androidJvm]\
class [AdBlock](index.md)(inputStream: [InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html), blockMimeTypes: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false) : [IAdBlocker](../-i-ad-blocker/index.md)

## Constructors

| | |
|---|---|
| [AdBlock](-ad-block.md) | [androidJvm]<br>constructor(file: [File](https://developer.android.com/reference/kotlin/java/io/File.html))constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), fileName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))constructor(inputStream: [InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html), blockMimeTypes: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isBlocked](is-blocked.md) | [androidJvm]<br>open override fun [isBlocked](is-blocked.md)(reqUrl: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>open override fun [isBlocked](is-blocked.md)(reqUrl: [URL](https://developer.android.com/reference/kotlin/java/net/URL.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>@[Synchronized](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-synchronized/index.html)<br>open override fun [isBlocked](is-blocked.md)(reqUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
