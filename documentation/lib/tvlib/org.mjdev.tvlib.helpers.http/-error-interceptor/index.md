//[tvlib](../../../index.md)/[org.mjdev.tvlib.helpers.http](../index.md)/[ErrorInterceptor](index.md)

# ErrorInterceptor

[androidJvm]\
class [ErrorInterceptor](index.md)(val fromJson: (body: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), cls: [Class](https://developer.android.com/reference/kotlin/java/lang/Class.html)&lt;[ErrorInterceptor.BaseResponse](-base-response/index.md)&gt;) -&gt; [ErrorInterceptor.BaseResponse](-base-response/index.md)?) : Interceptor

## Constructors

| | |
|---|---|
| [ErrorInterceptor](-error-interceptor.md) | [androidJvm]<br>constructor(fromJson: (body: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), cls: [Class](https://developer.android.com/reference/kotlin/java/lang/Class.html)&lt;[ErrorInterceptor.BaseResponse](-base-response/index.md)&gt;) -&gt; [ErrorInterceptor.BaseResponse](-base-response/index.md)?) |

## Types

| Name | Summary |
|---|---|
| [BaseResponse](-base-response/index.md) | [androidJvm]<br>interface [BaseResponse](-base-response/index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [intercept](intercept.md) | [androidJvm]<br>open override fun [intercept](intercept.md)(chain: Interceptor.Chain): Response |
| [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [fromJson](from-json.md) | [androidJvm]<br>val [fromJson](from-json.md): (body: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), cls: [Class](https://developer.android.com/reference/kotlin/java/lang/Class.html)&lt;[ErrorInterceptor.BaseResponse](-base-response/index.md)&gt;) -&gt; [ErrorInterceptor.BaseResponse](-base-response/index.md)? |
