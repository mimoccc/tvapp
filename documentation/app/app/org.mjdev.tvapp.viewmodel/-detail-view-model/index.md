//[app](../../../index.md)/[org.mjdev.tvapp.viewmodel](../index.md)/[DetailViewModel](index.md)

# DetailViewModel

[androidJvm]\
class [DetailViewModel](index.md)@Injectconstructor : BaseViewModel

## Constructors

| | |
|---|---|
| [DetailViewModel](-detail-view-model.md) | [androidJvm]<br>@Inject<br>constructor() |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](../-main-view-model/index.md#264516373%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](../-main-view-model/index.md#264516373%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [Closeable](https://developer.android.com/reference/kotlin/java/io/Closeable.html)) |
| [async](../-main-view-model/index.md#-200673142%2FFunctions%2F-912451524) | [androidJvm]<br>fun &lt;[T](../-main-view-model/index.md#-200673142%2FFunctions%2F-912451524)&gt; [async](../-main-view-model/index.md#-200673142%2FFunctions%2F-912451524)(block: suspend CoroutineScope.() -&gt; [T](../-main-view-model/index.md#-200673142%2FFunctions%2F-912451524)): [MutableLiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/MutableLiveData.html)&lt;[T](../-main-view-model/index.md#-200673142%2FFunctions%2F-912451524)&gt; |
| [equals](../../org.mjdev.tvapp.widget/-refresh-action/index.md#585090901%2FFunctions%2F-912451524) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvapp.widget/-refresh-action/index.md#585090901%2FFunctions%2F-912451524)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getCachedList](get-cached-list.md) | [androidJvm]<br>inline fun &lt;[T](get-cached-list.md)&gt; [getCachedList](get-cached-list.md)(creator: () -&gt; [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;*&gt;): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;*&gt; |
| [handleError](../-main-view-model/index.md#-451859271%2FFunctions%2F-912451524) | [androidJvm]<br>fun [handleError](../-main-view-model/index.md#-451859271%2FFunctions%2F-912451524)(block: (error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) |
| [hashCode](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1794629105%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1794629105%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [mediaItemsFor](media-items-for.md) | [androidJvm]<br>fun [mediaItemsFor](media-items-for.md)(data: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?&gt; |
| [postError](../-main-view-model/index.md#-1034559276%2FFunctions%2F-912451524) | [androidJvm]<br>fun [postError](../-main-view-model/index.md#-1034559276%2FFunctions%2F-912451524)(e: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
| [stateInViewModel](../-main-view-model/index.md#2111298717%2FExtensions%2F-912451524) | [androidJvm]<br>fun &lt;[T](../-main-view-model/index.md#2111298717%2FExtensions%2F-912451524)&gt; Flow&lt;[T](../-main-view-model/index.md#2111298717%2FExtensions%2F-912451524)&gt;.[stateInViewModel](../-main-view-model/index.md#2111298717%2FExtensions%2F-912451524)(initial: [T](../-main-view-model/index.md#2111298717%2FExtensions%2F-912451524)): StateFlow&lt;[T](../-main-view-model/index.md#2111298717%2FExtensions%2F-912451524)&gt;<br>fun &lt;[T](../-main-view-model/index.md#2036838961%2FExtensions%2F-912451524)&gt; Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[T](../-main-view-model/index.md#2036838961%2FExtensions%2F-912451524)&gt;&gt;.[stateInViewModel](../-main-view-model/index.md#2036838961%2FExtensions%2F-912451524)(initial: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[T](../-main-view-model/index.md#2036838961%2FExtensions%2F-912451524)&gt;): StateFlow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[T](../-main-view-model/index.md#2036838961%2FExtensions%2F-912451524)&gt;&gt;<br>fun &lt;[K](../-main-view-model/index.md#79054941%2FExtensions%2F-912451524), [V](../-main-view-model/index.md#79054941%2FExtensions%2F-912451524)&gt; Flow&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[K](../-main-view-model/index.md#79054941%2FExtensions%2F-912451524), [V](../-main-view-model/index.md#79054941%2FExtensions%2F-912451524)&gt;&gt;.[stateInViewModel](../-main-view-model/index.md#79054941%2FExtensions%2F-912451524)(initial: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[K](../-main-view-model/index.md#79054941%2FExtensions%2F-912451524), [V](../-main-view-model/index.md#79054941%2FExtensions%2F-912451524)&gt;): StateFlow&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[K](../-main-view-model/index.md#79054941%2FExtensions%2F-912451524), [V](../-main-view-model/index.md#79054941%2FExtensions%2F-912451524)&gt;&gt; |
| [toString](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1616463040%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1616463040%2FFunctions%2F-912451524)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [dao](dao.md) | [androidJvm]<br>@Inject<br>lateinit var [dao](dao.md): [DAO](../../org.mjdev.tvapp.database/-d-a-o/index.md) |
| [error](../-main-view-model/index.md#-1585900825%2FProperties%2F-912451524) | [androidJvm]<br>val [error](../-main-view-model/index.md#-1585900825%2FProperties%2F-912451524): MutableStateFlow&lt;[Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)?&gt; |
| [localAudioCursor](local-audio-cursor.md) | [androidJvm]<br>@Inject<br>lateinit var [localAudioCursor](local-audio-cursor.md): AudioCursor |
| [localPhotoCursor](local-photo-cursor.md) | [androidJvm]<br>@Inject<br>lateinit var [localPhotoCursor](local-photo-cursor.md): PhotoCursor |
| [localVideoCursor](local-video-cursor.md) | [androidJvm]<br>@Inject<br>lateinit var [localVideoCursor](local-video-cursor.md): VideoCursor |
