//[app](../../../index.md)/[org.mjdev.tvapp.data.local](../index.md)/[Message](index.md)

# Message

[androidJvm]\
@JsonClass(generateAdapter = true)

class [Message](index.md) : ItemWithTitle&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; , ItemWithMessage&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; , ItemWithId

## Constructors

| | |
|---|---|
| [Message](-message.md) | [androidJvm]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | [androidJvm]<br>open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](hash-code.md) | [androidJvm]<br>open override fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1616463040%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1616463040%2FFunctions%2F-912451524)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [deleted](deleted.md) | [androidJvm]<br>@Json(name = &quot;deleted&quot;)<br>val [deleted](deleted.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false |
| [id](id.md) | [androidJvm]<br>@Json(name = &quot;id&quot;)<br>open override var [id](id.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [message](message.md) | [androidJvm]<br>@Json(name = &quot;message&quot;)<br>open override var [message](message.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [read](read.md) | [androidJvm]<br>@Json(name = &quot;read&quot;)<br>val [read](read.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false |
| [title](title.md) | [androidJvm]<br>@Json(name = &quot;title&quot;)<br>open override var [title](title.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
