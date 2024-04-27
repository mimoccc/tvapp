//[app](../../../index.md)/[org.mjdev.tvapp.data.local](../index.md)/[TVChannel](index.md)

# TVChannel

[androidJvm]\
@JsonClass(generateAdapter = true)

class [TVChannel](index.md) : [Serializable](https://developer.android.com/reference/kotlin/java/io/Serializable.html), ItemWithId, ItemWithTitle&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; , ItemWithSubtitle&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; , ItemWithImage&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; , ItemWithUri&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; , ItemWithDescription&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; , ItemVideo

## Constructors

| | |
|---|---|
| [TVChannel](-t-v-channel.md) | [androidJvm]<br>constructor(stream: [Stream](../../org.mjdev.tvapp.data.remote/-stream/index.md), channel: [Channel](../../org.mjdev.tvapp.data.remote/-channel/index.md)?)constructor() |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [asMovie](-companion/as-movie.md) | [androidJvm]<br>fun [TVChannel](index.md).[asMovie](-companion/as-movie.md)(category: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Media](../-media/index.md) |
| [equals](equals.md) | [androidJvm]<br>open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](hash-code.md) | [androidJvm]<br>open override fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [altNames](alt-names.md) | [androidJvm]<br>@Json(name = &quot;alt_names&quot;)<br>var [altNames](alt-names.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;? |
| [broadcastArea](broadcast-area.md) | [androidJvm]<br>@Json(name = &quot;broadcast_area&quot;)<br>var [broadcastArea](broadcast-area.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;? |
| [categories](categories.md) | [androidJvm]<br>@Json(name = &quot;categories&quot;)<br>var [categories](categories.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;? |
| [channelId](channel-id.md) | [androidJvm]<br>@Json(name = &quot;id&quot;)<br>var [channelId](channel-id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [city](city.md) | [androidJvm]<br>@Json(name = &quot;city&quot;)<br>var [city](city.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [closed](closed.md) | [androidJvm]<br>@Json(name = &quot;closed&quot;)<br>var [closed](closed.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [country](country.md) | [androidJvm]<br>@Json(name = &quot;country&quot;)<br>var [country](country.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [description](description.md) | [androidJvm]<br>open override val [description](description.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [httpReferrer](http-referrer.md) | [androidJvm]<br>@Json(name = &quot;http_referrer&quot;)<br>var [httpReferrer](http-referrer.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [id](id.md) | [androidJvm]<br>@Json(name = &quot;id&quot;)<br>open override var [id](id.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [image](image.md) | [androidJvm]<br>open override val [image](image.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [isNsfw](is-nsfw.md) | [androidJvm]<br>@Json(name = &quot;is_nsfw&quot;)<br>var [isNsfw](is-nsfw.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)? |
| [languages](languages.md) | [androidJvm]<br>@Json(name = &quot;languages&quot;)<br>var [languages](languages.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;? |
| [launched](launched.md) | [androidJvm]<br>@Json(name = &quot;launched&quot;)<br>var [launched](launched.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [logo](logo.md) | [androidJvm]<br>@Json(name = &quot;logo&quot;)<br>var [logo](logo.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [name](name.md) | [androidJvm]<br>@Json(name = &quot;name&quot;)<br>var [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [network](network.md) | [androidJvm]<br>@Json(name = &quot;network&quot;)<br>var [network](network.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [owners](owners.md) | [androidJvm]<br>@Json(name = &quot;owners&quot;)<br>var [owners](owners.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;? |
| [replacedBy](replaced-by.md) | [androidJvm]<br>@Json(name = &quot;replaced_by&quot;)<br>var [replacedBy](replaced-by.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [streamUrl](stream-url.md) | [androidJvm]<br>@Json(name = &quot;url&quot;)<br>var [streamUrl](stream-url.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [subdivision](subdivision.md) | [androidJvm]<br>@Json(name = &quot;subdivision&quot;)<br>var [subdivision](subdivision.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [subtitle](subtitle.md) | [androidJvm]<br>open override val [subtitle](subtitle.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [title](title.md) | [androidJvm]<br>open override val [title](title.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [uri](uri.md) | [androidJvm]<br>open override val [uri](uri.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [userAgent](user-agent.md) | [androidJvm]<br>@Json(name = &quot;user_agent&quot;)<br>var [userAgent](user-agent.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [website](website.md) | [androidJvm]<br>@Json(name = &quot;website&quot;)<br>var [website](website.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
