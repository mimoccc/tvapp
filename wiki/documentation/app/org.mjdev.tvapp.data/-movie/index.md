//[app](../../../index.md)/[org.mjdev.tvapp.data](../index.md)/[Movie](index.md)

# Movie

[androidJvm]\
data class [Movie](index.md)(var id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 0, var title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, var description: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, var backgroundImageUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, var cardImageUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, var videoUri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html) = Uri.EMPTY, var studio: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;) : [Serializable](https://developer.android.com/reference/kotlin/java/io/Serializable.html), [ItemWithTitle](../../org.mjdev.tvapp.base.interfaces/-item-with-title/index.md), [ItemWithImage](../../org.mjdev.tvapp.base.interfaces/-item-with-image/index.md), [ItemWithVideoUri](../../org.mjdev.tvapp.base.interfaces/-item-with-video-uri/index.md)

## Constructors

| | |
|---|---|
| [Movie](-movie.md) | [androidJvm]<br>constructor(id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 0, title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, description: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, backgroundImageUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, cardImageUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, videoUri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html) = Uri.EMPTY, studio: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [backgroundImageUrl](background-image-url.md) | [androidJvm]<br>open override var [backgroundImageUrl](background-image-url.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [cardImageUrl](card-image-url.md) | [androidJvm]<br>var [cardImageUrl](card-image-url.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [description](description.md) | [androidJvm]<br>var [description](description.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [hasVideoUri](has-video-uri.md) | [androidJvm]<br>val [hasVideoUri](has-video-uri.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [id](id.md) | [androidJvm]<br>var [id](id.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [studio](studio.md) | [androidJvm]<br>var [studio](studio.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [title](title.md) | [androidJvm]<br>open override var [title](title.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [videoUri](video-uri.md) | [androidJvm]<br>open override var [videoUri](video-uri.md): [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html) |
