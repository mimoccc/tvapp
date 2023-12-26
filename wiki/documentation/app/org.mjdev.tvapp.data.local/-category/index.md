//[app](../../../index.md)/[org.mjdev.tvapp.data.local](../index.md)/[Category](index.md)

# Category

[androidJvm]\
@JsonClass(generateAdapter = true)

class [Category](index.md) : [Serializable](https://developer.android.com/reference/kotlin/java/io/Serializable.html), ItemWithId, ItemWithTitle&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; , ItemWithSubtitle&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; , ItemWithImage&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;

## Constructors

| | |
|---|---|
| [Category](-category.md) | [androidJvm]<br>constructor(title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))constructor() |

## Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | [androidJvm]<br>open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](hash-code.md) | [androidJvm]<br>open override fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [id](id.md) | [androidJvm]<br>@Json(name = &quot;id&quot;)<br>open override var [id](id.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [image](image.md) | [androidJvm]<br>@Json(name = &quot;image&quot;)<br>open override val [image](image.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [subtitle](subtitle.md) | [androidJvm]<br>@Json(name = &quot;subtitle&quot;)<br>open override val [subtitle](subtitle.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null |
| [title](title.md) | [androidJvm]<br>@Json(name = &quot;title&quot;)<br>open override var [title](title.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
