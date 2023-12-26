//[app](../../../index.md)/[org.mjdev.tvapp.data.local](../index.md)/[Movie](index.md)

# Movie

[androidJvm]\
@JsonClass(generateAdapter = true)

class [Movie](index.md) : [Serializable](https://developer.android.com/reference/kotlin/java/io/Serializable.html), ItemWithId, ItemWithTitle&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; , ItemWithSubtitle&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; , ItemWithImage&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; , ItemWithUri&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; , ItemWithBackground&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; , ItemWithDescription&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;

## Constructors

| | |
|---|---|
| [Movie](-movie.md) | [androidJvm]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | [androidJvm]<br>open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](hash-code.md) | [androidJvm]<br>open override fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

## Properties

| Name | Summary |
|---|---|
| [background](background.md) | [androidJvm]<br>@Json(name = &quot;backgroundImageUrl&quot;)<br>open override var [background](background.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [category](category.md) | [androidJvm]<br>@Json(name = &quot;category&quot;)<br>var [category](category.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [country](country.md) | [androidJvm]<br>@Json(name = &quot;country&quot;)<br>var [country](country.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [description](description.md) | [androidJvm]<br>@Json(name = &quot;description&quot;)<br>open override var [description](description.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [id](id.md) | [androidJvm]<br>@Json(name = &quot;id&quot;)<br>open override var [id](id.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [image](image.md) | [androidJvm]<br>@Json(name = &quot;imageUrl&quot;)<br>open override var [image](image.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [isNsfw](is-nsfw.md) | [androidJvm]<br>@Json(name = &quot;isNsfw&quot;)<br>var [isNsfw](is-nsfw.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [studio](studio.md) | [androidJvm]<br>@Json(name = &quot;studio&quot;)<br>var [studio](studio.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [subtitle](subtitle.md) | [androidJvm]<br>open override var [subtitle](subtitle.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [title](title.md) | [androidJvm]<br>@Json(name = &quot;title&quot;)<br>open override var [title](title.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [uri](uri.md) | [androidJvm]<br>@Json(name = &quot;videoUri&quot;)<br>open override var [uri](uri.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
