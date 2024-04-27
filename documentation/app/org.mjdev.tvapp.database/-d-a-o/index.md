//[app](../../../index.md)/[org.mjdev.tvapp.database](../index.md)/[DAO](index.md)

# DAO

[androidJvm]\
class [DAO](index.md)(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))

## Constructors

| | |
|---|---|
| [DAO](-d-a-o.md) | [androidJvm]<br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../org.mjdev.tvapp.widget/-refresh-action/index.md#585090901%2FFunctions%2F-912451524) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvapp.widget/-refresh-action/index.md#585090901%2FFunctions%2F-912451524)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getCachedList](get-cached-list.md) | [androidJvm]<br>inline fun &lt;[T](get-cached-list.md)&gt; [getCachedList](get-cached-list.md)(creator: () -&gt; [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;*&gt;): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;*&gt; |
| [hashCode](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1794629105%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1794629105%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1616463040%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1616463040%2FFunctions%2F-912451524)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [allMediaItems](all-media-items.md) | [androidJvm]<br>val [allMediaItems](all-media-items.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt; |
| [cache](cache.md) | [androidJvm]<br>val [cache](cache.md): [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;[KClass](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)&lt;*&gt;, [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;*&gt;&gt; |
| [categoryDao](category-dao.md) | [androidJvm]<br>val [categoryDao](category-dao.md): Box&lt;[Category](../../org.mjdev.tvapp.data.local/-category/index.md)&gt; |
| [channelsDao](channels-dao.md) | [androidJvm]<br>val [channelsDao](channels-dao.md): Box&lt;[TVChannel](../../org.mjdev.tvapp.data.local/-t-v-channel/index.md)&gt; |
| [context](context.md) | [androidJvm]<br>val [context](context.md): [Context](https://developer.android.com/reference/kotlin/android/content/Context.html) |
| [countriesDao](countries-dao.md) | [androidJvm]<br>val [countriesDao](countries-dao.md): Box&lt;[Country](../../org.mjdev.tvapp.data.local/-country/index.md)&gt; |
| [messagesDao](messages-dao.md) | [androidJvm]<br>val [messagesDao](messages-dao.md): Box&lt;[Message](../../org.mjdev.tvapp.data.local/-message/index.md)&gt; |
| [movieDao](movie-dao.md) | [androidJvm]<br>val [movieDao](movie-dao.md): Box&lt;[Media](../../org.mjdev.tvapp.data.local/-media/index.md)&gt; |
| [parsedLinks](parsed-links.md) | [androidJvm]<br>val [parsedLinks](parsed-links.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[ParsedLink](../../org.mjdev.tvapp.data.local/-parsed-link/index.md)&gt; |
| [parsedLinksDao](parsed-links-dao.md) | [androidJvm]<br>val [parsedLinksDao](parsed-links-dao.md): Box&lt;[ParsedLink](../../org.mjdev.tvapp.data.local/-parsed-link/index.md)&gt; |
