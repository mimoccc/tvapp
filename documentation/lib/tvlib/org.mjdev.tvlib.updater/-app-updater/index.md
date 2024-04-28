//[tvlib](../../../index.md)/[org.mjdev.tvlib.updater](../index.md)/[AppUpdater](index.md)

# AppUpdater

[androidJvm]\
class [AppUpdater](index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), githubUser: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;mimoccc&quot;, githubRepository: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;tvapp&quot;, repoName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = AppUpdater::class.simpleName ?: &quot;AppUpdater&quot;, keyName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = repoName, coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO))

## Constructors

| | |
|---|---|
| [AppUpdater](-app-updater.md) | [androidJvm]<br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), githubUser: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;mimoccc&quot;, githubRepository: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;tvapp&quot;, repoName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = AppUpdater::class.simpleName ?: &quot;AppUpdater&quot;, keyName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = repoName, coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [updateApp](update-app.md) | [androidJvm]<br>@[MainThread](https://developer.android.com/reference/kotlin/androidx/annotation/MainThread.html)<br>fun [updateApp](update-app.md)(): Job |

## Properties

| Name | Summary |
|---|---|
| [isUpdateAvailable](is-update-available.md) | [androidJvm]<br>val [isUpdateAvailable](is-update-available.md): Flow&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; |
