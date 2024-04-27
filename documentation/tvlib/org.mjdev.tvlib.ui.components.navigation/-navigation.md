//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.navigation](index.md)/[Navigation](-navigation.md)

# Navigation

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [Navigation](-navigation.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, menuItems: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[MenuItem](../org.mjdev.tvlib.navigation/-menu-item/index.md)&gt; = if (isEditMode()) listOf(
        MenuItem.MENU_ITEM_EXIT,
        MenuItem.MENU_ITEM_SETTINGS,
        MenuItem.MENU_ITEM_SEARCH
    ) else listOf(), modal: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = isPortraitMode(), backgroundColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.Black.copy(alpha = 0.7f), navController: [NavHostControllerEx](../org.mjdev.tvlib.navigation/-nav-host-controller-ex/index.md) = rememberNavControllerEx(menuItems), minPortraitMenuWidth: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = 4.dp, content: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { Page() })
