//[tvlib](../../../index.md)/[org.mjdev.tvlib.ui.components.immersivelist](../index.md)/[ImmersiveListBackgroundScope](index.md)/[AnimatedContent](-animated-content.md)

# AnimatedContent

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [AnimatedContent](-animated-content.md)(targetState: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, transitionSpec: [AnimatedContentTransitionScope](https://developer.android.com/reference/kotlin/androidx/compose/animation/AnimatedContentTransitionScope.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)&gt;.() -&gt; [ContentTransform](https://developer.android.com/reference/kotlin/androidx/compose/animation/ContentTransform.html) = {
            ImmersiveListDefaults.EnterTransition.togetherWith(ImmersiveListDefaults.ExitTransition)
        }, contentAlignment: [Alignment](https://developer.android.com/reference/kotlin/androidx/compose/ui/Alignment.html) = Alignment.TopStart, label: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;AnimatedContent&quot;, content: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)[AnimatedVisibilityScope](https://developer.android.com/reference/kotlin/androidx/compose/animation/AnimatedVisibilityScope.html).(targetState: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))
