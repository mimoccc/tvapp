//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.navigation](index.md)/[NavHostEx](-nav-host-ex.md)

# NavHostEx

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [NavHostEx](-nav-host-ex.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, route: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, startRoute: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, navController: [NavHostControllerEx](../org.mjdev.tvlib.navigation/-nav-host-controller-ex/index.md) = rememberNavControllerEx(), contentAlignment: [Alignment](https://developer.android.com/reference/kotlin/androidx/compose/ui/Alignment.html) = Alignment.Center, enterTransition: [AnimatedContentTransitionScope](https://developer.android.com/reference/kotlin/androidx/compose/animation/AnimatedContentTransitionScope.html)&lt;[NavBackStackEntry](https://developer.android.com/reference/kotlin/androidx/navigation/NavBackStackEntry.html)&gt;.() -&gt; [EnterTransition](https://developer.android.com/reference/kotlin/androidx/compose/animation/EnterTransition.html) = {
        fadeIn(animationSpec = tween(700))
    }, exitTransition: [AnimatedContentTransitionScope](https://developer.android.com/reference/kotlin/androidx/compose/animation/AnimatedContentTransitionScope.html)&lt;[NavBackStackEntry](https://developer.android.com/reference/kotlin/androidx/navigation/NavBackStackEntry.html)&gt;.() -&gt; [ExitTransition](https://developer.android.com/reference/kotlin/androidx/compose/animation/ExitTransition.html) = {
        fadeOut(animationSpec = tween(700))
    }, popEnterTransition: [AnimatedContentTransitionScope](https://developer.android.com/reference/kotlin/androidx/compose/animation/AnimatedContentTransitionScope.html)&lt;[NavBackStackEntry](https://developer.android.com/reference/kotlin/androidx/navigation/NavBackStackEntry.html)&gt;.() -&gt; [EnterTransition](https://developer.android.com/reference/kotlin/androidx/compose/animation/EnterTransition.html) = enterTransition, popExitTransition: [AnimatedContentTransitionScope](https://developer.android.com/reference/kotlin/androidx/compose/animation/AnimatedContentTransitionScope.html)&lt;[NavBackStackEntry](https://developer.android.com/reference/kotlin/androidx/navigation/NavBackStackEntry.html)&gt;.() -&gt; [ExitTransition](https://developer.android.com/reference/kotlin/androidx/compose/animation/ExitTransition.html) = exitTransition, builder: [NavGraphBuilderEx](../org.mjdev.tvlib.navigation/-nav-graph-builder-ex/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {
        screen(route = EmptyScreen())
    })
