//[app](../../index.md)/[org.mjdev.tvapp.base.navigation](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [MenuItem](-menu-item/index.md) | [androidJvm]<br>data class [MenuItem](-menu-item/index.md)(val menuText: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, val menuIcon: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, var route: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Menu item helper class. |
| [NavGraphBuilderEx](-nav-graph-builder-ex/index.md) | [androidJvm]<br>class [NavGraphBuilderEx](-nav-graph-builder-ex/index.md)(provider: [NavigatorProvider](https://developer.android.com/reference/kotlin/androidx/navigation/NavigatorProvider.html), route: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val navHostController: [NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html)) : [NavGraphBuilder](https://developer.android.com/reference/kotlin/androidx/navigation/NavGraphBuilder.html)<br>Custom nav graph builder. |
| [Screen](-screen/index.md) | [androidJvm]<br>open class [Screen](-screen/index.md)<br>Screen class. |

## Functions

| Name | Summary |
|---|---|
| [NavHostEx](-nav-host-ex.md) | [androidJvm]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>fun [NavHostEx](-nav-host-ex.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, route: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, navController: [NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html), builder: [NavGraphBuilderEx](-nav-graph-builder-ex/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Custom NavHost object to extend functionality and simplify navigation across application. |
