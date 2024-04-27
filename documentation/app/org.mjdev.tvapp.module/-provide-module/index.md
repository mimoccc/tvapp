//[app](../../../index.md)/[org.mjdev.tvapp.module](../index.md)/[ProvideModule](index.md)

# ProvideModule

[androidJvm]\
@Module

class [ProvideModule](index.md)

## Constructors

| | |
|---|---|
| [ProvideModule](-provide-module.md) | [androidJvm]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [equals](../../org.mjdev.tvapp.widget/-refresh-action/index.md#585090901%2FFunctions%2F-912451524) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvapp.widget/-refresh-action/index.md#585090901%2FFunctions%2F-912451524)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1794629105%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1794629105%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [provideAdBlockInterceptor](provide-ad-block-interceptor.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [provideAdBlockInterceptor](provide-ad-block-interceptor.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): AdBlockInterceptor |
| [provideDAO](provide-d-a-o.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [provideDAO](provide-d-a-o.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [DAO](../../org.mjdev.tvapp.database/-d-a-o/index.md) |
| [provideNetworkConnectionInterceptor](provide-network-connection-interceptor.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [provideNetworkConnectionInterceptor](provide-network-connection-interceptor.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): NetworkConnectionInterceptor |
| [providesApiService](provides-api-service.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [providesApiService](provides-api-service.md)(retrofit: Retrofit): [ApiService](../../org.mjdev.tvapp.repository/-api-service/index.md) |
| [providesAudioCursor](provides-audio-cursor.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [providesAudioCursor](provides-audio-cursor.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): AudioCursor |
| [providesMoshi](provides-moshi.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [providesMoshi](provides-moshi.md)(): Moshi |
| [providesNetworkConnectivityService](provides-network-connectivity-service.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [providesNetworkConnectivityService](provides-network-connectivity-service.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): NetworkConnectivityService |
| [providesOkHttpClient](provides-ok-http-client.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [providesOkHttpClient](provides-ok-http-client.md)(networkConnectionInterceptor: NetworkConnectionInterceptor, userAgentInterceptor: UserAgentInterceptor): OkHttpClient |
| [providesPhotoCursor](provides-photo-cursor.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [providesPhotoCursor](provides-photo-cursor.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): PhotoCursor |
| [providesRetrofit](provides-retrofit.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [providesRetrofit](provides-retrofit.md)(okHttpClient: OkHttpClient): Retrofit |
| [providesVideoCursor](provides-video-cursor.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [providesVideoCursor](provides-video-cursor.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): VideoCursor |
| [provideUserAgentInterceptor](provide-user-agent-interceptor.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [provideUserAgentInterceptor](provide-user-agent-interceptor.md)(): UserAgentInterceptor |
| [toString](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1616463040%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1616463040%2FFunctions%2F-912451524)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
