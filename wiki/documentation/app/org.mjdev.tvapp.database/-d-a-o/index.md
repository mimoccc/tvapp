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
| [getCachedList](get-cached-list.md) | [androidJvm]<br>inline fun &lt;[T](get-cached-list.md)&gt; [getCachedList](get-cached-list.md)(creator: () -&gt; [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;*&gt;): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;*&gt; |

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
| [movieDao](movie-dao.md) | [androidJvm]<br>val [movieDao](movie-dao.md): Box&lt;[Movie](../../org.mjdev.tvapp.data.local/-movie/index.md)&gt; |
