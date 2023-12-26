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
| [provideDAO](provide-d-a-o.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [provideDAO](provide-d-a-o.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [DAO](../../org.mjdev.tvapp.database/-d-a-o/index.md) |
| [providesApiService](provides-api-service.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [providesApiService](provides-api-service.md)(retrofit: Retrofit): [ApiService](../../org.mjdev.tvapp.repository/-api-service/index.md) |
| [providesAudioCursor](provides-audio-cursor.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [providesAudioCursor](provides-audio-cursor.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): AudioCursor |
| [providesCacheInterceptor](provides-cache-interceptor.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [providesCacheInterceptor](provides-cache-interceptor.md)(): CacheInterceptor |
| [providesHttpLoggingInterceptor](provides-http-logging-interceptor.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [providesHttpLoggingInterceptor](provides-http-logging-interceptor.md)(): HttpLoggingInterceptor |
| [providesMoshi](provides-moshi.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [providesMoshi](provides-moshi.md)(): Moshi |
| [providesNetworkConnectivityService](provides-network-connectivity-service.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [providesNetworkConnectivityService](provides-network-connectivity-service.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): NetworkConnectivityService |
| [providesOkHttpClient](provides-ok-http-client.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [providesOkHttpClient](provides-ok-http-client.md)(cacheInterceptor: CacheInterceptor, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient |
| [providesPhotoCursor](provides-photo-cursor.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [providesPhotoCursor](provides-photo-cursor.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): PhotoCursor |
| [providesRetrofit](provides-retrofit.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [providesRetrofit](provides-retrofit.md)(okHttpClient: OkHttpClient): Retrofit |
| [providesVideoCursor](provides-video-cursor.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [providesVideoCursor](provides-video-cursor.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): VideoCursor |
