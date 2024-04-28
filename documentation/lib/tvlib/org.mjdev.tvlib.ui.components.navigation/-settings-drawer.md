//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.navigation](index.md)/[SettingsDrawer](-settings-drawer.md)

# SettingsDrawer

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [SettingsDrawer](-settings-drawer.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, drawerState: [DrawerState](https://developer.android.com/reference/kotlin/androidx/tv/material3/DrawerState.html) = rememberDrawerState(
        initialValue = DrawerValue.Closed
    ), onTouchOutside: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, content: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {
        Box(modifier = Modifier.fillMaxSize())
    })
