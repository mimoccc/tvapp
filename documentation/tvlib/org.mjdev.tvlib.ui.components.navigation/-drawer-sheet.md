//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.navigation](index.md)/[DrawerSheet](-drawer-sheet.md)

# DrawerSheet

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [DrawerSheet](-drawer-sheet.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, drawerState: [DrawerState](https://developer.android.com/reference/kotlin/androidx/tv/material3/DrawerState.html) = remember { DrawerState() }, sizeAnimationFinishedListener: (initialValue: [IntSize](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/IntSize.html), targetValue: [IntSize](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/IntSize.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, content: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)([DrawerValue](https://developer.android.com/reference/kotlin/androidx/tv/material3/DrawerValue.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {
        Box(
            modifier = Modifier
                .width(200.dp)
                .background(Color.LightGray, RectangleShape)
        )
    })
