//[tvlib](../../../index.md)/[org.mjdev.tvlib.events.store](../index.md)/[ApplicationScopeViewModelProvider](index.md)

# ApplicationScopeViewModelProvider

[androidJvm]\
object [ApplicationScopeViewModelProvider](index.md) : [ViewModelStoreOwner](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModelStoreOwner.html)

## Functions

| Name | Summary |
|---|---|
| [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getApplicationScopeViewModel](get-application-scope-view-model.md) | [androidJvm]<br>fun &lt;[T](get-application-scope-view-model.md) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)&gt; [getApplicationScopeViewModel](get-application-scope-view-model.md)(modelClass: [Class](https://developer.android.com/reference/kotlin/java/lang/Class.html)&lt;[T](get-application-scope-view-model.md)&gt;): [T](get-application-scope-view-model.md) |
| [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [observeEvent](../../org.mjdev.tvlib.events.observe/observe-event.md) | [androidJvm]<br>@[MainThread](https://developer.android.com/reference/kotlin/androidx/annotation/MainThread.html)<br>inline fun &lt;[T](../../org.mjdev.tvlib.events.observe/observe-event.md)&gt; [ViewModelStoreOwner](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModelStoreOwner.html).[observeEvent](../../org.mjdev.tvlib.events.observe/observe-event.md)(coroutineScope: CoroutineScope, isSticky: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, noinline onReceived: ([T](../../org.mjdev.tvlib.events.observe/observe-event.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): Job |
| [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [viewModelStore](view-model-store.md) | [androidJvm]<br>open override val [viewModelStore](view-model-store.md): [ViewModelStore](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModelStore.html) |
