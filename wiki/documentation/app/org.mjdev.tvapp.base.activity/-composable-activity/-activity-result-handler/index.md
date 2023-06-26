//[app](../../../../index.md)/[org.mjdev.tvapp.base.activity](../../index.md)/[ComposableActivity](../index.md)/[ActivityResultHandler](index.md)

# ActivityResultHandler

inner class [ActivityResultHandler](index.md)&lt;[I](index.md)&gt;(val lifecycle: [Lifecycle](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.html), val onLaunch: (args: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[I](index.md)&gt;) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), val onActivityResult: (requestCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), resultCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), intent: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

Activity result handler.

#### Parameters

androidJvm

| |
|---|
| I |

## Constructors

| | |
|---|---|
| [ActivityResultHandler](-activity-result-handler.md) | [androidJvm]<br>constructor(lifecycle: [Lifecycle](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.html), onLaunch: (args: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[I](index.md)&gt;) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), onActivityResult: (requestCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), resultCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), intent: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Create [ActivityResultHandler](index.md) |

## Functions

| Name | Summary |
|---|---|
| [launch](launch.md) | [androidJvm]<br>fun [launch](launch.md)(vararg args: [I](index.md))<br>Launch the onLaunch handler. |
| [postResult](post-result.md) | [androidJvm]<br>fun [postResult](post-result.md)(requestCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), resultCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), intent: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)?)<br>Post result to handler. |
| [unregister](unregister.md) | [androidJvm]<br>fun [unregister](unregister.md)()<br>Function to register result handler. It is called automatically upon lifecycle state. |

## Properties

| Name | Summary |
|---|---|
| [lifecycle](lifecycle.md) | [androidJvm]<br>val [lifecycle](lifecycle.md): [Lifecycle](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.html)<br>lifecycle handler |
| [observer](observer.md) | [androidJvm]<br>val [observer](observer.md): [LifecycleEventObserver](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleEventObserver.html) |
| [onActivityResult](on-activity-result.md) | [androidJvm]<br>val [onActivityResult](on-activity-result.md): (requestCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), resultCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), intent: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>result handler |
| [onLaunch](on-launch.md) | [androidJvm]<br>val [onLaunch](on-launch.md): (args: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[I](index.md)&gt;) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>function to start activity |
