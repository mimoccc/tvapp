//[tvlib](../../../index.md)/[org.mjdev.tvlib.extensions](../index.md)/[GlobalExt](index.md)

# GlobalExt

[androidJvm]\
object [GlobalExt](index.md)

## Functions

| Name | Summary |
|---|---|
| [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [launch](launch.md) | [androidJvm]<br>fun [launch](launch.md)(context: [CoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/index.html) = IO, start: CoroutineStart = CoroutineStart.DEFAULT, block: suspend CoroutineScope.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): Job |
| [launchInMain](launch-in-main.md) | [androidJvm]<br>fun [launchInMain](launch-in-main.md)(block: suspend CoroutineScope.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): Job |
| [launchIO](launch-i-o.md) | [androidJvm]<br>fun [launchIO](launch-i-o.md)(block: suspend CoroutineScope.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): Job |
| [launchUI](launch-u-i.md) | [androidJvm]<br>fun [launchUI](launch-u-i.md)(block: suspend CoroutineScope.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): Job |
| [postDelayed](post-delayed.md) | [androidJvm]<br>fun &lt;[T](post-delayed.md)&gt; [T](post-delayed.md).[postDelayed](post-delayed.md)(delay: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), block: [T](post-delayed.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [toggle](toggle.md) | [androidJvm]<br>fun [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;.[toggle](toggle.md)() |
| [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [with](with.md) | [androidJvm]<br>inline fun &lt;[T](with.md)&gt; [with](with.md)(receiver: [T](with.md), block: [T](with.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [T](with.md) |
| [withScope](with-scope.md) | [androidJvm]<br>fun &lt;[T](with-scope.md)&gt; [withScope](with-scope.md)(scope: [T](with-scope.md), block: [T](with-scope.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [IO](-i-o.md) | [androidJvm]<br>val [IO](-i-o.md): CoroutineDispatcher |
| [UI](-u-i.md) | [androidJvm]<br>val [UI](-u-i.md): MainCoroutineDispatcher |
