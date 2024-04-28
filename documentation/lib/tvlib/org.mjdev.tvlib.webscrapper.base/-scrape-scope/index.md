//[tvlib](../../../index.md)/[org.mjdev.tvlib.webscrapper.base](../index.md)/[ScrapeScope](index.md)

# ScrapeScope

[androidJvm]\
data class [ScrapeScope](index.md)(val url: [ScrapeLink](../-scrape-link/index.md)? = null, val title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, val error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)? = null, val document: [Doc](../../org.mjdev.tvlib.webscrapper.select/-doc/index.md)? = null, val videos: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[DocElement](../../org.mjdev.tvlib.webscrapper.select/-doc-element/index.md)&gt; = emptyList(), val audios: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[DocElement](../../org.mjdev.tvlib.webscrapper.select/-doc-element/index.md)&gt; = emptyList(), val anchors: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[DocElement](../../org.mjdev.tvlib.webscrapper.select/-doc-element/index.md)&gt; = emptyList())

## Constructors

| | |
|---|---|
| [ScrapeScope](-scrape-scope.md) | [androidJvm]<br>constructor(url: [ScrapeLink](../-scrape-link/index.md)? = null, title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)? = null, document: [Doc](../../org.mjdev.tvlib.webscrapper.select/-doc/index.md)? = null, videos: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[DocElement](../../org.mjdev.tvlib.webscrapper.select/-doc-element/index.md)&gt; = emptyList(), audios: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[DocElement](../../org.mjdev.tvlib.webscrapper.select/-doc-element/index.md)&gt; = emptyList(), anchors: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[DocElement](../../org.mjdev.tvlib.webscrapper.select/-doc-element/index.md)&gt; = emptyList()) |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [ScrapeLink](../-scrape-link/index.md)? |
| [component2](component2.md) | [androidJvm]<br>operator fun [component2](component2.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [component3](component3.md) | [androidJvm]<br>operator fun [component3](component3.md)(): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)? |
| [component4](component4.md) | [androidJvm]<br>operator fun [component4](component4.md)(): [Doc](../../org.mjdev.tvlib.webscrapper.select/-doc/index.md)? |
| [component5](component5.md) | [androidJvm]<br>operator fun [component5](component5.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[DocElement](../../org.mjdev.tvlib.webscrapper.select/-doc-element/index.md)&gt; |
| [component6](component6.md) | [androidJvm]<br>operator fun [component6](component6.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[DocElement](../../org.mjdev.tvlib.webscrapper.select/-doc-element/index.md)&gt; |
| [component7](component7.md) | [androidJvm]<br>operator fun [component7](component7.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[DocElement](../../org.mjdev.tvlib.webscrapper.select/-doc-element/index.md)&gt; |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(url: [ScrapeLink](../-scrape-link/index.md)? = null, title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)? = null, document: [Doc](../../org.mjdev.tvlib.webscrapper.select/-doc/index.md)? = null, videos: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[DocElement](../../org.mjdev.tvlib.webscrapper.select/-doc-element/index.md)&gt; = emptyList(), audios: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[DocElement](../../org.mjdev.tvlib.webscrapper.select/-doc-element/index.md)&gt; = emptyList(), anchors: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[DocElement](../../org.mjdev.tvlib.webscrapper.select/-doc-element/index.md)&gt; = emptyList()): [ScrapeScope](index.md) |
| [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator override fun [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open override fun [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238) | [androidJvm]<br>open override fun [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [anchors](anchors.md) | [androidJvm]<br>val [anchors](anchors.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[DocElement](../../org.mjdev.tvlib.webscrapper.select/-doc-element/index.md)&gt; |
| [audios](audios.md) | [androidJvm]<br>val [audios](audios.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[DocElement](../../org.mjdev.tvlib.webscrapper.select/-doc-element/index.md)&gt; |
| [document](document.md) | [androidJvm]<br>val [document](document.md): [Doc](../../org.mjdev.tvlib.webscrapper.select/-doc/index.md)? = null |
| [error](error.md) | [androidJvm]<br>val [error](error.md): [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)? = null |
| [title](title.md) | [androidJvm]<br>val [title](title.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [url](url.md) | [androidJvm]<br>val [url](url.md): [ScrapeLink](../-scrape-link/index.md)? = null |
| [videos](videos.md) | [androidJvm]<br>val [videos](videos.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[DocElement](../../org.mjdev.tvlib.webscrapper.select/-doc-element/index.md)&gt; |
