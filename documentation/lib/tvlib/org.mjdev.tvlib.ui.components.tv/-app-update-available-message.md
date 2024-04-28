//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.tv](index.md)/[AppUpdateAvailableMessage](-app-update-available-message.md)

# AppUpdateAvailableMessage

[androidJvm]\

@[Preview](https://developer.android.com/reference/kotlin/androidx/compose/ui/tooling/preview/Preview.html)

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [AppUpdateAvailableMessage](-app-update-available-message.md)(title: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = R.string.title_app_update, message: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = R.string.msg_update_available, githubUser: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;mimoccc&quot;, githubRepository: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;tvapp&quot;, backgroundColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color(0, 132, 0, 255), buttonText: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = R.string.bt_update, appUpdater: [AppUpdater](../org.mjdev.tvlib.updater/-app-updater/index.md) = rememberAppUpdater(
        githubUser = githubUser,
        githubRepository = githubRepository,
    ), isEdit: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = isEditMode(), updateAvailable: [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; = appUpdater.isUpdateAvailable.collectAsState(false), visibleState: [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; = mutableStateOf(isEdit || updateAvailable.value), onButtonClick: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { appUpdater.updateApp() })
