//[tvlib](../../../index.md)/[org.mjdev.tvlib.extensions](../index.md)/[ApiResponseExt](index.md)

# ApiResponseExt

[androidJvm]\
object [ApiResponseExt](index.md)

## Functions

| Name | Summary |
|---|---|
| [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [runSafe](run-safe.md) | [androidJvm]<br>suspend fun &lt;[E](run-safe.md)&gt; [runSafe](run-safe.md)(block: suspend () -&gt; [E](run-safe.md)): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[E](run-safe.md)&gt; |
| [safeFlow](safe-flow.md) | [androidJvm]<br>fun &lt;[T](safe-flow.md)&gt; ApiResponse&lt;[T](safe-flow.md)&gt;.[safeFlow](safe-flow.md)(error: ([Exception](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-exception/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { exception -&gt;             Timber.e(exception)         }, onError: (error: ApiResponse.Failure.Error) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { e -&gt;             error(CodeException(e.message()))         }): Flow&lt;[T](safe-flow.md)&gt; |
| [safeGet](safe-get.md) | [androidJvm]<br>inline fun &lt;[T](safe-get.md)&gt; ApiResponse&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[T](safe-get.md)&gt;&gt;.[safeGet](safe-get.md)(crossinline error: ([Exception](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-exception/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { exception -&gt;             Timber.e(exception)         }): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[T](safe-get.md)&gt; |
| [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
