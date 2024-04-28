//[tvlib](../../../index.md)/[org.mjdev.tvlib.ui.components.audiopreview.dailymotion](../index.md)/[SearchResult](index.md)

# SearchResult

[androidJvm]\
@JsonClass(generateAdapter = true)

data class [SearchResult](index.md)(@Json(name = &quot;page&quot;)var page: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 0, @Json(name = &quot;limit&quot;)var limit: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 0, @Json(name = &quot;explicit&quot;)var explicit: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, @Json(name = &quot;total&quot;)var total: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 0, @Json(name = &quot;hasMore&quot;)var hasMore: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, @Json(name = &quot;list&quot;)var list: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[SearchItem](../-search-item/index.md)&gt; = emptyList())

## Constructors

| | |
|---|---|
| [SearchResult](-search-result.md) | [androidJvm]<br>constructor(@Json(name = &quot;page&quot;)page: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 0, @Json(name = &quot;limit&quot;)limit: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 0, @Json(name = &quot;explicit&quot;)explicit: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, @Json(name = &quot;total&quot;)total: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 0, @Json(name = &quot;hasMore&quot;)hasMore: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, @Json(name = &quot;list&quot;)list: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[SearchItem](../-search-item/index.md)&gt; = emptyList()) |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [component2](component2.md) | [androidJvm]<br>operator fun [component2](component2.md)(): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [component3](component3.md) | [androidJvm]<br>operator fun [component3](component3.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [component4](component4.md) | [androidJvm]<br>operator fun [component4](component4.md)(): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [component5](component5.md) | [androidJvm]<br>operator fun [component5](component5.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [component6](component6.md) | [androidJvm]<br>operator fun [component6](component6.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[SearchItem](../-search-item/index.md)&gt; |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(@Json(name = &quot;page&quot;)page: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 0, @Json(name = &quot;limit&quot;)limit: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 0, @Json(name = &quot;explicit&quot;)explicit: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, @Json(name = &quot;total&quot;)total: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 0, @Json(name = &quot;hasMore&quot;)hasMore: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, @Json(name = &quot;list&quot;)list: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[SearchItem](../-search-item/index.md)&gt; = emptyList()): [SearchResult](index.md) |
| [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator override fun [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open override fun [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238) | [androidJvm]<br>open override fun [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [explicit](explicit.md) | [androidJvm]<br>var [explicit](explicit.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hasMore](has-more.md) | [androidJvm]<br>var [hasMore](has-more.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [limit](limit.md) | [androidJvm]<br>var [limit](limit.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [list](list.md) | [androidJvm]<br>var [list](list.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[SearchItem](../-search-item/index.md)&gt; |
| [page](page.md) | [androidJvm]<br>var [page](page.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [total](total.md) | [androidJvm]<br>var [total](total.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
