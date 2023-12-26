//[app](../../../index.md)/[org.mjdev.tvapp.sync](../index.md)/[SyncAdapter](index.md)

# SyncAdapter

[androidJvm]\
class [SyncAdapter](index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), val apiService: [ApiService](../../org.mjdev.tvapp.repository/-api-service/index.md), val dao: [DAO](../../org.mjdev.tvapp.database/-d-a-o/index.md)) : [AbstractThreadedSyncAdapter](https://developer.android.com/reference/kotlin/android/content/AbstractThreadedSyncAdapter.html)

## Constructors

| | |
|---|---|
| [SyncAdapter](-sync-adapter.md) | [androidJvm]<br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), apiService: [ApiService](../../org.mjdev.tvapp.repository/-api-service/index.md), dao: [DAO](../../org.mjdev.tvapp.database/-d-a-o/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [getContext](index.md#-135217175%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [getContext](index.md#-135217175%2FFunctions%2F-912451524)(): [Context](https://developer.android.com/reference/kotlin/android/content/Context.html) |
| [getSyncAdapterBinder](index.md#856385466%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getSyncAdapterBinder](index.md#856385466%2FFunctions%2F-912451524)(): [IBinder](https://developer.android.com/reference/kotlin/android/os/IBinder.html) |
| [onPerformSync](on-perform-sync.md) | [androidJvm]<br>open override fun [onPerformSync](on-perform-sync.md)(account: [Account](https://developer.android.com/reference/kotlin/android/accounts/Account.html)?, extras: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)?, authority: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, provider: [ContentProviderClient](https://developer.android.com/reference/kotlin/android/content/ContentProviderClient.html)?, syncResult: [SyncResult](https://developer.android.com/reference/kotlin/android/content/SyncResult.html)?) |
| [onSecurityException](index.md#335791892%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onSecurityException](index.md#335791892%2FFunctions%2F-912451524)(p0: [Account](https://developer.android.com/reference/kotlin/android/accounts/Account.html), p1: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html), p2: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), p3: [SyncResult](https://developer.android.com/reference/kotlin/android/content/SyncResult.html)) |
| [onSyncCanceled](index.md#-1596679089%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onSyncCanceled](index.md#-1596679089%2FFunctions%2F-912451524)()<br>open fun [onSyncCanceled](index.md#462975537%2FFunctions%2F-912451524)(p0: [Thread](https://developer.android.com/reference/kotlin/java/lang/Thread.html)) |
| [onUnsyncableAccount](index.md#764819384%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onUnsyncableAccount](index.md#764819384%2FFunctions%2F-912451524)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

## Properties

| Name | Summary |
|---|---|
| [apiService](api-service.md) | [androidJvm]<br>val [apiService](api-service.md): [ApiService](../../org.mjdev.tvapp.repository/-api-service/index.md) |
| [dao](dao.md) | [androidJvm]<br>val [dao](dao.md): [DAO](../../org.mjdev.tvapp.database/-d-a-o/index.md) |
