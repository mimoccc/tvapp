//[tvlib](../../../index.md)/[org.mjdev.tvlib.events.core](../index.md)/[EventBusCore](index.md)

# EventBusCore

[androidJvm]\
class [EventBusCore](index.md) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

## Constructors

| | |
|---|---|
| [EventBusCore](-event-bus-core.md) | [androidJvm]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](../../org.mjdev.tvlib.viewmodel/-base-view-model/index.md#264516373%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [addCloseable](../../org.mjdev.tvlib.viewmodel/-base-view-model/index.md#264516373%2FFunctions%2F-1596939238)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [Closeable](https://developer.android.com/reference/kotlin/java/io/Closeable.html)) |
| [clearStickEvent](clear-stick-event.md) | [androidJvm]<br>fun [clearStickEvent](clear-stick-event.md)(eventName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getEventObserverCount](get-event-observer-count.md) | [androidJvm]<br>fun [getEventObserverCount](get-event-observer-count.md)(eventName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [observeEvent](observe-event.md) | [androidJvm]<br>fun &lt;[T](observe-event.md) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt; [observeEvent](observe-event.md)(lifecycleOwner: [LifecycleOwner](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleOwner.html), eventName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), minState: [Lifecycle.State](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.State.html), dispatcher: CoroutineDispatcher, isSticky: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), onReceived: ([T](observe-event.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): Job |
| [observeWithoutLifecycle](observe-without-lifecycle.md) | [androidJvm]<br>suspend fun &lt;[T](observe-without-lifecycle.md) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt; [observeWithoutLifecycle](observe-without-lifecycle.md)(eventName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), isSticky: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), onReceived: ([T](observe-without-lifecycle.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) |
| [postEvent](post-event.md) | [androidJvm]<br>fun [postEvent](post-event.md)(eventName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), value: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html), timeMillis: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)) |
| [removeStickEvent](remove-stick-event.md) | [androidJvm]<br>fun [removeStickEvent](remove-stick-event.md)(eventName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
