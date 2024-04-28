//[tvlib](../../../index.md)/[org.mjdev.tvlib.viewmodel](../index.md)/[BaseViewModel](index.md)

# BaseViewModel

[androidJvm]\
open class [BaseViewModel](index.md) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

## Constructors

| | |
|---|---|
| [BaseViewModel](-base-view-model.md) | [androidJvm]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](index.md#264516373%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [addCloseable](index.md#264516373%2FFunctions%2F-1596939238)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [Closeable](https://developer.android.com/reference/kotlin/java/io/Closeable.html)) |
| [async](async.md) | [androidJvm]<br>fun &lt;[T](async.md)&gt; [async](async.md)(block: suspend CoroutineScope.() -&gt; [T](async.md)): [MutableLiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/MutableLiveData.html)&lt;[T](async.md)&gt; |
| [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [handleError](handle-error.md) | [androidJvm]<br>fun [handleError](handle-error.md)(block: (error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) |
| [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [postError](post-error.md) | [androidJvm]<br>fun [postError](post-error.md)(e: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) |
| [stateInViewModel](state-in-view-model.md) | [androidJvm]<br>fun &lt;[T](state-in-view-model.md)&gt; Flow&lt;[T](state-in-view-model.md)&gt;.[stateInViewModel](state-in-view-model.md)(initial: [T](state-in-view-model.md)): StateFlow&lt;[T](state-in-view-model.md)&gt;<br>fun &lt;[T](state-in-view-model.md)&gt; Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[T](state-in-view-model.md)&gt;&gt;.[stateInViewModel](state-in-view-model.md)(initial: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[T](state-in-view-model.md)&gt; = listOf()): StateFlow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[T](state-in-view-model.md)&gt;&gt;<br>fun &lt;[K](state-in-view-model.md), [V](state-in-view-model.md)&gt; Flow&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[K](state-in-view-model.md), [V](state-in-view-model.md)&gt;&gt;.[stateInViewModel](state-in-view-model.md)(initial: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[K](state-in-view-model.md), [V](state-in-view-model.md)&gt; = mapOf()): StateFlow&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[K](state-in-view-model.md), [V](state-in-view-model.md)&gt;&gt; |
| [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [error](error.md) | [androidJvm]<br>val [error](error.md): MutableStateFlow&lt;[Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)?&gt; |
