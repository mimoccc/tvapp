//[app](../../index.md)/[org.mjdev.tvapp.sync](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [Authenticator](-authenticator/index.md) | [androidJvm]<br>class [Authenticator](-authenticator/index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) : [AbstractAccountAuthenticator](https://developer.android.com/reference/kotlin/android/accounts/AbstractAccountAuthenticator.html) |
| [AuthenticatorService](-authenticator-service/index.md) | [androidJvm]<br>class [AuthenticatorService](-authenticator-service/index.md) : [Service](https://developer.android.com/reference/kotlin/android/app/Service.html) |
| [DataProvider](-data-provider/index.md) | [androidJvm]<br>class [DataProvider](-data-provider/index.md) : [ContentProvider](https://developer.android.com/reference/kotlin/android/content/ContentProvider.html) |
| [SyncAdapter](-sync-adapter/index.md) | [androidJvm]<br>class [SyncAdapter](-sync-adapter/index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), val apiService: [ApiService](../org.mjdev.tvapp.repository/-api-service/index.md), val dao: [DAO](../org.mjdev.tvapp.database/-d-a-o/index.md)) : [AbstractThreadedSyncAdapter](https://developer.android.com/reference/kotlin/android/content/AbstractThreadedSyncAdapter.html) |
| [SyncItems](-sync-items/index.md) | [androidJvm]<br>class [SyncItems](-sync-items/index.md)(oldMovies: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Movie](../org.mjdev.tvapp.data.local/-movie/index.md)&gt;, newMovies: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Movie](../org.mjdev.tvapp.data.local/-movie/index.md)&gt;) |
| [SyncService](-sync-service/index.md) | [androidJvm]<br>class [SyncService](-sync-service/index.md) : [Service](https://developer.android.com/reference/kotlin/android/app/Service.html) |
