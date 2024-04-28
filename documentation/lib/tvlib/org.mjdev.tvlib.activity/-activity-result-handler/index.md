//[tvlib](../../../index.md)/[org.mjdev.tvlib.activity](../index.md)/[ActivityResultHandler](index.md)

# ActivityResultHandler

class [ActivityResultHandler](index.md)&lt;[I](index.md)&gt;(activity: [ComposableActivity](../-composable-activity/index.md), val lifecycle: [Lifecycle](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.html), val onLaunch: (args: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[I](index.md)&gt;) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), val onActivityResult: (requestCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), resultCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), intent: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

Activity result handler.

#### Parameters

androidJvm

| |
|---|
| I |

## Constructors

| | |
|---|---|
| [ActivityResultHandler](-activity-result-handler.md) | [androidJvm]<br>constructor(activity: [ComposableActivity](../-composable-activity/index.md), lifecycle: [Lifecycle](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.html), onLaunch: (args: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[I](index.md)&gt;) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), onActivityResult: (requestCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), resultCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), intent: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Create [ActivityResultHandler](index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [launch](launch.md) | [androidJvm]<br>fun [launch](launch.md)(vararg args: [I](index.md))<br>Launch the onLaunch handler. |
| [postResult](post-result.md) | [androidJvm]<br>fun [postResult](post-result.md)(requestCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), resultCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), intent: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)?)<br>Post result to handler. |
| [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [unregister](unregister.md) | [androidJvm]<br>fun [unregister](unregister.md)()<br>Function to register result handler. It is called automatically upon lifecycle state. |

## Properties

| Name | Summary |
|---|---|
| [lifecycle](lifecycle.md) | [androidJvm]<br>val [lifecycle](lifecycle.md): [Lifecycle](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.html)<br>lifecycle handler |
| [onActivityResult](on-activity-result.md) | [androidJvm]<br>val [onActivityResult](on-activity-result.md): (requestCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), resultCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), intent: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>result handler |
| [onLaunch](on-launch.md) | [androidJvm]<br>val [onLaunch](on-launch.md): (args: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[I](index.md)&gt;) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>function to start activity |
