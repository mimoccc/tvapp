//[app](../../index.md)/[org.mjdev.tvapp.data.events](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [PauseEvent](-pause-event/index.md) | [androidJvm]<br>data class [PauseEvent](-pause-event/index.md)(val timeout: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 30 * 1000L) |
| [SyncEnded](-sync-ended/index.md) | [androidJvm]<br>data class [SyncEnded](-sync-ended/index.md)(val syncerName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [SyncEvent](-sync-event/index.md) | [androidJvm]<br>data class [SyncEvent](-sync-event/index.md)(val syncerName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val data: [Media](../org.mjdev.tvapp.data.local/-media/index.md)) |
| [SyncStarted](-sync-started/index.md) | [androidJvm]<br>data class [SyncStarted](-sync-started/index.md)(val syncerName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
