//[app](../../../../index.md)/[org.mjdev.tvapp.base.navigation](../../index.md)/[Screen](../index.md)/[Companion](index.md)/[screen](screen.md)

# screen

[androidJvm]\
fun &lt;[T](screen.md) : [Screen](../index.md)&gt; [NavGraphBuilderEx](../../-nav-graph-builder-ex/index.md).[screen](screen.md)(route: [T](screen.md), isHomeScreen: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, isStartScreen: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false)

Custom function to generate screen in nav graph.

#### Receiver

[NavGraphBuilderEx](../../-nav-graph-builder-ex/index.md)

#### Parameters

androidJvm

| | |
|---|---|
| route | Route route expected |
| isHomeScreen | Is home screen, indicates that this screen will be used as home screen. Should be used once, or in case that is defined multiple times, the last one is used as home screen. |
| T | T Screen object generated for route. |