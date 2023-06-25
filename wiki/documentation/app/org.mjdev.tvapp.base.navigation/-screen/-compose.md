//[app](../../../index.md)/[org.mjdev.tvapp.base.navigation](../index.md)/[Screen](index.md)/[Compose](-compose.md)

# Compose

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

@[CallSuper](https://developer.android.com/reference/kotlin/androidx/annotation/CallSuper.html)

open fun [Compose](-compose.md)()

Compose function.

Helper function to preview without parameters as preview of compose needed.

#### Return

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

open fun [Compose](-compose.md)(navController: [NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html)?, backStackEntry: [NavBackStackEntry](https://developer.android.com/reference/kotlin/androidx/navigation/NavBackStackEntry.html)?, menuItems: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[MenuItem](../-menu-item/index.md)&gt;, args: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?&gt;)

Compose function for compose screen. This should be only a method overridden in super classes.

#### Parameters

androidJvm

| | |
|---|---|
| navController | Nav controller |
| backStackEntry | Back stack entry |
| menuItems | Menu items |
