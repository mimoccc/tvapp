//[app](../../../../index.md)/[org.mjdev.tvapp.base.navigation](../../index.md)/[Screen](../index.md)/[Companion](index.md)

# Companion

[androidJvm]\
object [Companion](index.md)

## Functions

| Name | Summary |
|---|---|
| [open](open.md) | [androidJvm]<br>inline fun &lt;[T](open.md) : [Screen](../index.md)&gt; [open](open.md)(navController: [NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html)?, vararg values: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?)<br>Open screen extension. |
| [open](open.md) | [androidJvm]<br>fun [NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html).[open](open.md)(route: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Open route. |
| [screen](screen.md) | [androidJvm]<br>fun &lt;[T](screen.md) : [Screen](../index.md)&gt; [NavGraphBuilderEx](../../-nav-graph-builder-ex/index.md).[screen](screen.md)(route: [T](screen.md), isHomeScreen: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, isStartScreen: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false)<br>Custom function to generate screen in nav graph. |

## Properties

| Name | Summary |
|---|---|
| [currentRoute](current-route.md) | [androidJvm]<br>val [NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html).[currentRoute](current-route.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>Current route nav host helper |