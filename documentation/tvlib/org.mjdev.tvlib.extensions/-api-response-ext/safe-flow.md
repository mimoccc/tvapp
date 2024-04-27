//[tvlib](../../../index.md)/[org.mjdev.tvlib.extensions](../index.md)/[ApiResponseExt](index.md)/[safeFlow](safe-flow.md)

# safeFlow

[androidJvm]\
fun &lt;[T](safe-flow.md)&gt; ApiResponse&lt;[T](safe-flow.md)&gt;.[safeFlow](safe-flow.md)(error: ([Exception](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-exception/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { exception -&gt;
            Timber.e(exception)
        }, onError: (error: ApiResponse.Failure.Error) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { e -&gt;
            error(CodeException(e.message()))
        }): Flow&lt;[T](safe-flow.md)&gt;
