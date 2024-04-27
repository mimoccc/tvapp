//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.navigation](index.md)/[NavigationDrawer](-navigation-drawer.md)

# NavigationDrawer

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [NavigationDrawer](-navigation-drawer.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, visible: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, drawerState: [DrawerState](https://developer.android.com/reference/kotlin/androidx/tv/material3/DrawerState.html) = rememberDrawerState(), drawerContent: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)([DrawerValue](https://developer.android.com/reference/kotlin/androidx/tv/material3/DrawerValue.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {
        Box(
            modifier = Modifier
                .conditional(visible) {
                    width(200.dp)
                }
                .background(Color.LightGray, RectangleShape)
        )
    }, content: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {
        Box(modifier = Modifier.fillMaxSize())
    })
