//[app](../../../../index.md)/[org.mjdev.tvapp.base.navigation](../../index.md)/[Screen](../index.md)/[Companion](index.md)/[screen](screen.md)

# screen

[androidJvm]\
fun &lt;[T](screen.md) : [Screen](../index.md)&gt; [NavGraphBuilderEx](../../-nav-graph-builder-ex/index.md).[screen](screen.md)(route: [T](screen.md), isHomeScreen: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, isStartScreen: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, deepLinks: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[NavDeepLink](https://developer.android.com/reference/kotlin/androidx/navigation/NavDeepLink.html)&gt; = emptyList(), enterTransition: @[JvmSuppressWildcards](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-suppress-wildcards/index.html)[AnimatedContentTransitionScope](https://developer.android.com/reference/kotlin/androidx/compose/animation/AnimatedContentTransitionScope.html)&lt;[NavBackStackEntry](https://developer.android.com/reference/kotlin/androidx/navigation/NavBackStackEntry.html)&gt;.() -&gt; [EnterTransition](https://developer.android.com/reference/kotlin/androidx/compose/animation/EnterTransition.html)?? = {
                SlideInVertically
            }, exitTransition: @[JvmSuppressWildcards](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-suppress-wildcards/index.html)[AnimatedContentTransitionScope](https://developer.android.com/reference/kotlin/androidx/compose/animation/AnimatedContentTransitionScope.html)&lt;[NavBackStackEntry](https://developer.android.com/reference/kotlin/androidx/navigation/NavBackStackEntry.html)&gt;.() -&gt; [ExitTransition](https://developer.android.com/reference/kotlin/androidx/compose/animation/ExitTransition.html)?? = {
                SlideOutVertically
            }, popEnterTransition: @[JvmSuppressWildcards](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-suppress-wildcards/index.html)[AnimatedContentTransitionScope](https://developer.android.com/reference/kotlin/androidx/compose/animation/AnimatedContentTransitionScope.html)&lt;[NavBackStackEntry](https://developer.android.com/reference/kotlin/androidx/navigation/NavBackStackEntry.html)&gt;.() -&gt; [EnterTransition](https://developer.android.com/reference/kotlin/androidx/compose/animation/EnterTransition.html)?? = enterTransition, popExitTransition: @[JvmSuppressWildcards](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.jvm/-jvm-suppress-wildcards/index.html)[AnimatedContentTransitionScope](https://developer.android.com/reference/kotlin/androidx/compose/animation/AnimatedContentTransitionScope.html)&lt;[NavBackStackEntry](https://developer.android.com/reference/kotlin/androidx/navigation/NavBackStackEntry.html)&gt;.() -&gt; [ExitTransition](https://developer.android.com/reference/kotlin/androidx/compose/animation/ExitTransition.html)?? = exitTransition)

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
