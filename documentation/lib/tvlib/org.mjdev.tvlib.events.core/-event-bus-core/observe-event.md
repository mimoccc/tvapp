//[tvlib](../../../index.md)/[org.mjdev.tvlib.events.core](../index.md)/[EventBusCore](index.md)/[observeEvent](observe-event.md)

# observeEvent

[androidJvm]\
fun &lt;[T](observe-event.md) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt; [observeEvent](observe-event.md)(lifecycleOwner: [LifecycleOwner](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleOwner.html), eventName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), minState: [Lifecycle.State](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.State.html), dispatcher: CoroutineDispatcher, isSticky: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), onReceived: ([T](observe-event.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): Job
