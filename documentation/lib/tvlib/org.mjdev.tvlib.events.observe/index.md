//[tvlib](../../index.md)/[org.mjdev.tvlib.events.observe](index.md)

# Package-level declarations

## Functions

| Name | Summary |
|---|---|
| [observeEvent](observe-event.md) | [androidJvm]<br>@[MainThread](https://developer.android.com/reference/kotlin/androidx/annotation/MainThread.html)<br>inline fun &lt;[T](observe-event.md)&gt; CoroutineScope.[observeEvent](observe-event.md)(isSticky: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, noinline onReceived: ([T](observe-event.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): Job<br>@[MainThread](https://developer.android.com/reference/kotlin/androidx/annotation/MainThread.html)<br>inline fun &lt;[T](observe-event.md)&gt; [ViewModelStoreOwner](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModelStoreOwner.html).[observeEvent](observe-event.md)(coroutineScope: CoroutineScope, isSticky: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, noinline onReceived: ([T](observe-event.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): Job<br>@[MainThread](https://developer.android.com/reference/kotlin/androidx/annotation/MainThread.html)<br>inline fun &lt;[T](observe-event.md)&gt; [ComponentActivity](https://developer.android.com/reference/kotlin/androidx/activity/ComponentActivity.html).[observeEvent](observe-event.md)(dispatcher: CoroutineDispatcher = Dispatchers.Main.immediate, minActiveState: [Lifecycle.State](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.State.html) = Lifecycle.State.STARTED, isSticky: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, noinline onReceived: ([T](observe-event.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): Job<br>@[MainThread](https://developer.android.com/reference/kotlin/androidx/annotation/MainThread.html)<br>inline fun &lt;[T](observe-event.md)&gt; [LifecycleOwner](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleOwner.html).[observeEvent](observe-event.md)(dispatcher: CoroutineDispatcher = Dispatchers.Main.immediate, minActiveState: [Lifecycle.State](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.State.html) = Lifecycle.State.STARTED, isSticky: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, noinline onReceived: ([T](observe-event.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): Job |