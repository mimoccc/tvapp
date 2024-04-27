//[app](../../../index.md)/[org.mjdev.tvapp.sync.syncers](../index.md)/[GithubMoviesSyncer](index.md)

# GithubMoviesSyncer

[androidJvm]\
class [GithubMoviesSyncer](index.md)(val adapter: [SyncAdapter](../../org.mjdev.tvapp.sync/-sync-adapter/index.md), val syncerName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = GithubMoviesSyncer::class.java.simpleName) : [Syncer](../../org.mjdev.tvapp.sync.base/-syncer/index.md)

## Constructors

| | |
|---|---|
| [GithubMoviesSyncer](-github-movies-syncer.md) | [androidJvm]<br>constructor(adapter: [SyncAdapter](../../org.mjdev.tvapp.sync/-sync-adapter/index.md), syncerName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = GithubMoviesSyncer::class.java.simpleName) |

## Functions

| Name | Summary |
|---|---|
| [doSync](do-sync.md) | [androidJvm]<br>open suspend override fun [doSync](do-sync.md)() |
| [equals](../../org.mjdev.tvapp.widget/-refresh-action/index.md#585090901%2FFunctions%2F-912451524) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvapp.widget/-refresh-action/index.md#585090901%2FFunctions%2F-912451524)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1794629105%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1794629105%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onSyncFinish](../../org.mjdev.tvapp.sync.base/-syncer/on-sync-finish.md) | [androidJvm]<br>open suspend fun [onSyncFinish](../../org.mjdev.tvapp.sync.base/-syncer/on-sync-finish.md)() |
| [onSyncStart](../../org.mjdev.tvapp.sync.base/-syncer/on-sync-start.md) | [androidJvm]<br>@[CallSuper](https://developer.android.com/reference/kotlin/androidx/annotation/CallSuper.html)<br>open suspend fun [onSyncStart](../../org.mjdev.tvapp.sync.base/-syncer/on-sync-start.md)() |
| [sync](../../org.mjdev.tvapp.sync.base/-syncer/sync.md) | [androidJvm]<br>@[CallSuper](https://developer.android.com/reference/kotlin/androidx/annotation/CallSuper.html)<br>suspend fun [sync](../../org.mjdev.tvapp.sync.base/-syncer/sync.md)(coroutineScope: CoroutineScope) |
| [toString](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1616463040%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1616463040%2FFunctions%2F-912451524)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [adapter](../../org.mjdev.tvapp.sync.base/-syncer/adapter.md) | [androidJvm]<br>val [adapter](../../org.mjdev.tvapp.sync.base/-syncer/adapter.md): [SyncAdapter](../../org.mjdev.tvapp.sync/-sync-adapter/index.md) |
| [apiService](../../org.mjdev.tvapp.sync.base/-syncer/api-service.md) | [androidJvm]<br>val [apiService](../../org.mjdev.tvapp.sync.base/-syncer/api-service.md): [ApiService](../../org.mjdev.tvapp.repository/-api-service/index.md) |
| [context](../../org.mjdev.tvapp.sync.base/-syncer/context.md) | [androidJvm]<br>val [context](../../org.mjdev.tvapp.sync.base/-syncer/context.md): [Context](https://developer.android.com/reference/kotlin/android/content/Context.html) |
| [dao](../../org.mjdev.tvapp.sync.base/-syncer/dao.md) | [androidJvm]<br>val [dao](../../org.mjdev.tvapp.sync.base/-syncer/dao.md): [DAO](../../org.mjdev.tvapp.database/-d-a-o/index.md) |
| [movieDao](../../org.mjdev.tvapp.sync.base/-syncer/movie-dao.md) | [androidJvm]<br>val [movieDao](../../org.mjdev.tvapp.sync.base/-syncer/movie-dao.md): Box&lt;[Media](../../org.mjdev.tvapp.data.local/-media/index.md)&gt; |
| [pauseState](../../org.mjdev.tvapp.sync.base/-syncer/pause-state.md) | [androidJvm]<br>val [pauseState](../../org.mjdev.tvapp.sync.base/-syncer/pause-state.md): [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; |
| [syncerName](../../org.mjdev.tvapp.sync.base/-syncer/syncer-name.md) | [androidJvm]<br>val [syncerName](../../org.mjdev.tvapp.sync.base/-syncer/syncer-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
