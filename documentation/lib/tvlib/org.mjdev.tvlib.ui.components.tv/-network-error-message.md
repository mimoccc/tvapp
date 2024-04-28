//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.tv](index.md)/[NetworkErrorMessage](-network-error-message.md)

# NetworkErrorMessage

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [NetworkErrorMessage](-network-error-message.md)(message: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = R.string.error_no_network, dismissible: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, backgroundColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.Black, networkService: [NetworkConnectivityService](../org.mjdev.tvlib.network/-network-connectivity-service/index.md) = rememberNetworkService(), networkState: [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html)&lt;[NetworkStatus](../org.mjdev.tvlib.network/-network-status/index.md)?&gt; = networkService.networkStatus.collectAsState(null), isEdit: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = isEditMode(), enter: [EnterTransition](https://developer.android.com/reference/kotlin/androidx/compose/animation/EnterTransition.html) = EnterTransition.None, exit: [ExitTransition](https://developer.android.com/reference/kotlin/androidx/compose/animation/ExitTransition.html) = ExitTransition.None)
