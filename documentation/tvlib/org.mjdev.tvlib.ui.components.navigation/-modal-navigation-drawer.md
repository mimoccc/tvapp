//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.navigation](index.md)/[ModalNavigationDrawer](-modal-navigation-drawer.md)

# ModalNavigationDrawer

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [ModalNavigationDrawer](-modal-navigation-drawer.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, drawerState: [DrawerState](https://developer.android.com/reference/kotlin/androidx/tv/material3/DrawerState.html) = rememberDrawerState(), scrimColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.Black.copy(alpha = 0.5f), visible: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, closeDrawerWidth: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = if (visible) 200.dp else 0.dp, contentAlignment: [Alignment](https://developer.android.com/reference/kotlin/androidx/compose/ui/Alignment.html) = Alignment.TopStart, closedDrawerWidth: [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html)?&gt; = remember { mutableStateOf(null) }, localDensity: [Density](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Density.html) = LocalDensity.current, onTouchOutside: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, drawerContent: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)([DrawerValue](https://developer.android.com/reference/kotlin/androidx/tv/material3/DrawerValue.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {
        Box(
            modifier = Modifier.background(Color.LightGray, RectangleShape)
        )
    }, content: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {
        Box(
            modifier = Modifier.fillMaxSize()
        )
    })
