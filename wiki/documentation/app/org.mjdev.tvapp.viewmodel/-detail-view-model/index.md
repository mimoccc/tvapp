//[app](../../../index.md)/[org.mjdev.tvapp.viewmodel](../index.md)/[DetailViewModel](index.md)

# DetailViewModel

[androidJvm]\
class [DetailViewModel](index.md)@Injectconstructor(movieRepository: [IRepository](../../org.mjdev.tvapp.repository/-i-repository/index.md)) : [BaseViewModel](../../org.mjdev.tvapp.base.viewmodel/-base-view-model/index.md)

## Constructors

| | |
|---|---|
| [DetailViewModel](-detail-view-model.md) | [androidJvm]<br>@Inject<br>constructor(movieRepository: [IRepository](../../org.mjdev.tvapp.repository/-i-repository/index.md)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](../-main-view-model/index.md#264516373%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](../-main-view-model/index.md#264516373%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [Closeable](https://developer.android.com/reference/kotlin/java/io/Closeable.html)) |
| [detailsLoadingState](details-loading-state.md) | [androidJvm]<br>fun [detailsLoadingState](details-loading-state.md)(movieId: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?): StateFlow&lt;[DetailsLoadingState](../../org.mjdev.tvapp.state/-details-loading-state/index.md)&gt; |
| [handleError](../../org.mjdev.tvapp.base.viewmodel/-base-view-model/handle-error.md) | [androidJvm]<br>fun [handleError](../../org.mjdev.tvapp.base.viewmodel/-base-view-model/handle-error.md)(block: (error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) |
| [postError](../../org.mjdev.tvapp.base.viewmodel/-base-view-model/post-error.md) | [androidJvm]<br>fun [postError](../../org.mjdev.tvapp.base.viewmodel/-base-view-model/post-error.md)(e: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
