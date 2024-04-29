//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.gallery](index.md)/[BoxWithControls](-box-with-controls.md)

# BoxWithControls

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [BoxWithControls](-box-with-controls.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, src: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = null, contentAlignment: [Alignment](https://developer.android.com/reference/kotlin/androidx/compose/ui/Alignment.html) = Alignment.BottomCenter, controlsState: [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; = remember { mutableStateOf(false) }, onTap: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { controlsState.toggle() }, controls: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)(src: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, bckColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html), controlsState: [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { _, _, _ -&gt; }, content: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)(src: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, bckColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html), controlsState: [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))