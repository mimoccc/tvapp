//[tvlib](../../../index.md)/[org.mjdev.tvlib.extensions](../index.md)/[ApiResponseExt](index.md)/[safeGet](safe-get.md)

# safeGet

[androidJvm]\
inline fun &lt;[T](safe-get.md)&gt; ApiResponse&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[T](safe-get.md)&gt;&gt;.[safeGet](safe-get.md)(crossinline error: ([Exception](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-exception/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { exception -&gt;
            Timber.e(exception)
        }): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[T](safe-get.md)&gt;
