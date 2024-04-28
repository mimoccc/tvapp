//[tvlib](../../index.md)/[org.mjdev.tvlib.helpers.http](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [AccessTokenInterceptor](-access-token-interceptor/index.md) | [androidJvm]<br>class [AccessTokenInterceptor](-access-token-interceptor/index.md) : Interceptor |
| [CacheInterceptor](-cache-interceptor/index.md) | [androidJvm]<br>class [CacheInterceptor](-cache-interceptor/index.md)(maxAge: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 1, timeUnit: [TimeUnit](https://developer.android.com/reference/kotlin/java/util/concurrent/TimeUnit.html) = TimeUnit.HOURS) : Interceptor |
| [CacheType](-cache-type/index.md) | [androidJvm]<br>enum [CacheType](-cache-type/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[CacheType](-cache-type/index.md)&gt; |
| [ErrorInterceptor](-error-interceptor/index.md) | [androidJvm]<br>class [ErrorInterceptor](-error-interceptor/index.md)(val fromJson: (body: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), cls: [Class](https://developer.android.com/reference/kotlin/java/lang/Class.html)&lt;[ErrorInterceptor.BaseResponse](-error-interceptor/-base-response/index.md)&gt;) -&gt; [ErrorInterceptor.BaseResponse](-error-interceptor/-base-response/index.md)?) : Interceptor |
| [ISessionRepository](-i-session-repository/index.md) | [androidJvm]<br>class [ISessionRepository](-i-session-repository/index.md) |
| [MimeTypeMapUtils](-mime-type-map-utils/index.md) | [androidJvm]<br>object [MimeTypeMapUtils](-mime-type-map-utils/index.md) |
| [NetworkConnectionInterceptor](-network-connection-interceptor/index.md) | [androidJvm]<br>class [NetworkConnectionInterceptor](-network-connection-interceptor/index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) : Interceptor |
| [SessionPreferenceManager](-session-preference-manager/index.md) | [androidJvm]<br>class [SessionPreferenceManager](-session-preference-manager/index.md) |
| [UserAgentInterceptor](-user-agent-interceptor/index.md) | [androidJvm]<br>class [UserAgentInterceptor](-user-agent-interceptor/index.md)(userAgent: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/115.0&quot;) : Interceptor |
