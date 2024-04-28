//[app](../../../index.md)/[org.mjdev.tvapp.sync.base](../index.md)/[Syncer](index.md)

# Syncer

abstract class [Syncer](index.md)(val adapter: [SyncAdapter](../../org.mjdev.tvapp.sync/-sync-adapter/index.md), val syncerName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = Syncer::class.java.simpleName, val pauseState: [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; = mutableStateOf(false))

#### Inheritors

| |
|---|
| [GithubMoviesSyncer](../../org.mjdev.tvapp.sync.syncers/-github-movies-syncer/index.md) |

## Constructors

| | |
|---|---|
| [Syncer](-syncer.md) | [androidJvm]<br>constructor(adapter: [SyncAdapter](../../org.mjdev.tvapp.sync/-sync-adapter/index.md), syncerName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = Syncer::class.java.simpleName, pauseState: [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; = mutableStateOf(false)) |

## Functions

| Name | Summary |
|---|---|
| [doSync](do-sync.md) | [androidJvm]<br>open suspend fun [doSync](do-sync.md)() |
| [equals](../../org.mjdev.tvapp.widget/-refresh-action/index.md#585090901%2FFunctions%2F-912451524) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvapp.widget/-refresh-action/index.md#585090901%2FFunctions%2F-912451524)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1794629105%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1794629105%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onSyncFinish](on-sync-finish.md) | [androidJvm]<br>open suspend fun [onSyncFinish](on-sync-finish.md)() |
| [onSyncStart](on-sync-start.md) | [androidJvm]<br>@[CallSuper](https://developer.android.com/reference/kotlin/androidx/annotation/CallSuper.html)<br>open suspend fun [onSyncStart](on-sync-start.md)() |
| [sync](sync.md) | [androidJvm]<br>@[CallSuper](https://developer.android.com/reference/kotlin/androidx/annotation/CallSuper.html)<br>suspend fun [sync](sync.md)(coroutineScope: CoroutineScope) |
| [toString](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1616463040%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1616463040%2FFunctions%2F-912451524)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [adapter](adapter.md) | [androidJvm]<br>val [adapter](adapter.md): [SyncAdapter](../../org.mjdev.tvapp.sync/-sync-adapter/index.md) |
| [apiService](api-service.md) | [androidJvm]<br>val [apiService](api-service.md): [ApiService](../../org.mjdev.tvapp.repository/-api-service/index.md) |
| [context](context.md) | [androidJvm]<br>val [context](context.md): [Context](https://developer.android.com/reference/kotlin/android/content/Context.html) |
| [dao](dao.md) | [androidJvm]<br>val [dao](dao.md): [DAO](../../org.mjdev.tvapp.database/-d-a-o/index.md) |
| [movieDao](movie-dao.md) | [androidJvm]<br>val [movieDao](movie-dao.md): Box&lt;[Media](../../org.mjdev.tvapp.data.local/-media/index.md)&gt; |
| [pauseState](pause-state.md) | [androidJvm]<br>val [pauseState](pause-state.md): [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; |
| [syncerName](syncer-name.md) | [androidJvm]<br>val [syncerName](syncer-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
