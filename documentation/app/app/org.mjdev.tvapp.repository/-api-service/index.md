//[app](../../../index.md)/[org.mjdev.tvapp.repository](../index.md)/[ApiService](index.md)

# ApiService

[androidJvm]\
interface [ApiService](index.md)

## Functions

| Name | Summary |
|---|---|
| [categories](categories.md) | [androidJvm]<br>@GET(value = &quot;categories.json&quot;)<br>abstract suspend fun [categories](categories.md)(): ApiResponse&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Category](../../org.mjdev.tvapp.data.remote/-category/index.md)&gt;&gt; |
| [channels](channels.md) | [androidJvm]<br>@GET(value = &quot;channels.json&quot;)<br>abstract suspend fun [channels](channels.md)(): ApiResponse&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Channel](../../org.mjdev.tvapp.data.remote/-channel/index.md)&gt;&gt; |
| [countries](countries.md) | [androidJvm]<br>@GET(value = &quot;countries.json&quot;)<br>abstract suspend fun [countries](countries.md)(): ApiResponse&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Country](../../org.mjdev.tvapp.data.remote/-country/index.md)&gt;&gt; |
| [epg](epg.md) | [androidJvm]<br>@GET(value = &quot;guides.json&quot;)<br>abstract suspend fun [epg](epg.md)(): ApiResponse&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Epg](../../org.mjdev.tvapp.data.remote/-epg/index.md)&gt;&gt; |
| [equals](../../org.mjdev.tvapp.widget/-refresh-action/index.md#585090901%2FFunctions%2F-912451524) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvapp.widget/-refresh-action/index.md#585090901%2FFunctions%2F-912451524)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1794629105%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1794629105%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [languages](languages.md) | [androidJvm]<br>@GET(value = &quot;languages.json&quot;)<br>abstract suspend fun [languages](languages.md)(): ApiResponse&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Language](../../org.mjdev.tvapp.data.remote/-language/index.md)&gt;&gt; |
| [streams](streams.md) | [androidJvm]<br>@GET(value = &quot;streams.json&quot;)<br>abstract suspend fun [streams](streams.md)(): ApiResponse&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Stream](../../org.mjdev.tvapp.data.remote/-stream/index.md)&gt;&gt; |
| [toString](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1616463040%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1616463040%2FFunctions%2F-912451524)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
