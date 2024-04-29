//[tvlib](../../index.md)/[org.mjdev.tvlib.events.util](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [ILogger](-i-logger/index.md) | [androidJvm]<br>interface [ILogger](-i-logger/index.md) |

## Functions

| Name | Summary |
|---|---|
| [clearStickyEvent](clear-sticky-event.md) | [androidJvm]<br>inline fun &lt;[T](clear-sticky-event.md)&gt; [clearStickyEvent](clear-sticky-event.md)(event: [Class](https://developer.android.com/reference/kotlin/java/lang/Class.html)&lt;[T](clear-sticky-event.md)&gt;)<br>inline fun &lt;[T](clear-sticky-event.md)&gt; [clearStickyEvent](clear-sticky-event.md)(scope: [ViewModelStoreOwner](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModelStoreOwner.html), event: [Class](https://developer.android.com/reference/kotlin/java/lang/Class.html)&lt;[T](clear-sticky-event.md)&gt;) |
| [getEventObserverCount](get-event-observer-count.md) | [androidJvm]<br>inline fun &lt;[T](get-event-observer-count.md)&gt; [getEventObserverCount](get-event-observer-count.md)(event: [Class](https://developer.android.com/reference/kotlin/java/lang/Class.html)&lt;[T](get-event-observer-count.md)&gt;): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>inline fun &lt;[T](get-event-observer-count.md)&gt; [getEventObserverCount](get-event-observer-count.md)(scope: [ViewModelStoreOwner](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModelStoreOwner.html), event: [Class](https://developer.android.com/reference/kotlin/java/lang/Class.html)&lt;[T](get-event-observer-count.md)&gt;): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [launchWhenStateAtLeast](launch-when-state-at-least.md) | [androidJvm]<br>fun &lt;[T](launch-when-state-at-least.md)&gt; [LifecycleOwner](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleOwner.html).[launchWhenStateAtLeast](launch-when-state-at-least.md)(minState: [Lifecycle.State](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.State.html), block: suspend CoroutineScope.() -&gt; [T](launch-when-state-at-least.md)): Job |
| [removeStickyEvent](remove-sticky-event.md) | [androidJvm]<br>inline fun &lt;[T](remove-sticky-event.md)&gt; [removeStickyEvent](remove-sticky-event.md)(event: [Class](https://developer.android.com/reference/kotlin/java/lang/Class.html)&lt;[T](remove-sticky-event.md)&gt;)<br>inline fun &lt;[T](remove-sticky-event.md)&gt; [removeStickyEvent](remove-sticky-event.md)(scope: [ViewModelStoreOwner](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModelStoreOwner.html), event: [Class](https://developer.android.com/reference/kotlin/java/lang/Class.html)&lt;[T](remove-sticky-event.md)&gt;) |