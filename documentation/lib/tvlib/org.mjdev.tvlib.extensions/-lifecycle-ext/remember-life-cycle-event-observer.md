//[tvlib](../../../index.md)/[org.mjdev.tvlib.extensions](../index.md)/[LifecycleExt](index.md)/[rememberLifeCycleEventObserver](remember-life-cycle-event-observer.md)

# rememberLifeCycleEventObserver

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [rememberLifeCycleEventObserver](remember-life-cycle-event-observer.md)(key1: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = Unit, lifecycleOwner: [LifecycleOwner](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleOwner.html) = LocalLifecycleOwner.current, lifecycle: [Lifecycle](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.html) = lifecycleOwner.lifecycle, onEvent: (source: [LifecycleOwner](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleOwner.html), event: [Lifecycle.Event](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.Event.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))
