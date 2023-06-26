//[app](../../../index.md)/[org.mjdev.tvapp.viewmodel](../index.md)/[MainViewModel](index.md)

# MainViewModel

[androidJvm]\
class [MainViewModel](index.md)@Injectconstructor(movieRepository: [IRepository](../../org.mjdev.tvapp.repository/-i-repository/index.md), var networkInfo: [NetworkConnectivityService](../../org.mjdev.tvapp.base.network/-network-connectivity-service/index.md)) : [BaseViewModel](../../org.mjdev.tvapp.base.viewmodel/-base-view-model/index.md)

## Constructors

| | |
|---|---|
| [MainViewModel](-main-view-model.md) | [androidJvm]<br>@Inject<br>constructor(movieRepository: [IRepository](../../org.mjdev.tvapp.repository/-i-repository/index.md), networkInfo: [NetworkConnectivityService](../../org.mjdev.tvapp.base.network/-network-connectivity-service/index.md)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](index.md#264516373%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](index.md#264516373%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [Closeable](https://developer.android.com/reference/kotlin/java/io/Closeable.html)) |
| [handleError](../../org.mjdev.tvapp.base.viewmodel/-base-view-model/handle-error.md) | [androidJvm]<br>fun [handleError](../../org.mjdev.tvapp.base.viewmodel/-base-view-model/handle-error.md)(block: (error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) |
| [postError](../../org.mjdev.tvapp.base.viewmodel/-base-view-model/post-error.md) | [androidJvm]<br>fun [postError](../../org.mjdev.tvapp.base.viewmodel/-base-view-model/post-error.md)(e: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [categoryList](category-list.md) | [androidJvm]<br>val [categoryList](category-list.md): StateFlow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Category](../../org.mjdev.tvapp.data/-category/index.md)&gt;&gt; |
| [featuredMovieList](featured-movie-list.md) | [androidJvm]<br>val [featuredMovieList](featured-movie-list.md): StateFlow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Movie](../../org.mjdev.tvapp.data/-movie/index.md)&gt;&gt; |
| [messages](messages.md) | [androidJvm]<br>val [messages](messages.md): StateFlow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Message](../../org.mjdev.tvapp.data/-message/index.md)&gt;&gt; |
| [networkInfo](network-info.md) | [androidJvm]<br>var [networkInfo](network-info.md): [NetworkConnectivityService](../../org.mjdev.tvapp.base.network/-network-connectivity-service/index.md) |
