//[app](../../index.md)/[org.mjdev.tvapp.sync.base](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [Syncer](-syncer/index.md) | [androidJvm]<br>abstract class [Syncer](-syncer/index.md)(val adapter: [SyncAdapter](../org.mjdev.tvapp.sync/-sync-adapter/index.md), val syncerName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = Syncer::class.java.simpleName, val pauseState: [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; = mutableStateOf(false)) |
| [SyncItems](-sync-items/index.md) | [androidJvm]<br>class [SyncItems](-sync-items/index.md)(oldMovies: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Media](../org.mjdev.tvapp.data.local/-media/index.md)&gt;, newMovies: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Media](../org.mjdev.tvapp.data.local/-media/index.md)&gt;) |
