//[app](../../index.md)/[org.mjdev.tvapp.base.ui.components.complex](index.md)/[ScreenView](-screen-view.md)

# ScreenView

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [ScreenView](-screen-view.md)(navController: [NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html)? = null, actions: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)[RowScope](https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/RowScope.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, title: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = R.string.app_name, menuItems: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[MenuItem](../org.mjdev.tvapp.base.navigation/-menu-item/index.md)&gt; = listOf(), content: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)(state: [ScreenState](../org.mjdev.tvapp.base.state/-screen-state/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { state -&gt;
        Screen()
    })
