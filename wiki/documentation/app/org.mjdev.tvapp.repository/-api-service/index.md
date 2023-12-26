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
| [languages](languages.md) | [androidJvm]<br>@GET(value = &quot;languages.json&quot;)<br>abstract suspend fun [languages](languages.md)(): ApiResponse&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Language](../../org.mjdev.tvapp.data.remote/-language/index.md)&gt;&gt; |
| [streams](streams.md) | [androidJvm]<br>@GET(value = &quot;streams.json&quot;)<br>abstract suspend fun [streams](streams.md)(): ApiResponse&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Stream](../../org.mjdev.tvapp.data.remote/-stream/index.md)&gt;&gt; |
