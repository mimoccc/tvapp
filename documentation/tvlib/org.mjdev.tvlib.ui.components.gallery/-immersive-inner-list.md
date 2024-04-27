//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.gallery](index.md)/[ImmersiveInnerList](-immersive-inner-list.md)

# ImmersiveInnerList

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [ImmersiveListScope](../org.mjdev.tvlib.ui.components.immersivelist/-immersive-list-scope/index.md).[ImmersiveInnerList](-immersive-inner-list.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, visible: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = ComposeExt.isEditMode(), backgroundColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.Transparent, enter: [EnterTransition](https://developer.android.com/reference/kotlin/androidx/compose/animation/EnterTransition.html) = fadeIn() + slideInVertically(initialOffsetY = { it / 2 }), exit: [ExitTransition](https://developer.android.com/reference/kotlin/androidx/compose/animation/ExitTransition.html) = fadeOut() + slideOutVertically(targetOffsetY = { it / 2 }), list: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)[ImmersiveListScope](../org.mjdev.tvlib.ui.components.immersivelist/-immersive-list-scope/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {})
