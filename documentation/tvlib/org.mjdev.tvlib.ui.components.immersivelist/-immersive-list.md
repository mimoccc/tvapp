//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.immersivelist](index.md)/[ImmersiveList](-immersive-list.md)

# ImmersiveList

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [ImmersiveList](-immersive-list.md)(background: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)[ImmersiveListBackgroundScope](-immersive-list-background-scope/index.md).(index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), listHasFocus: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { _, _ -&gt; }, modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, listAlignment: [Alignment](https://developer.android.com/reference/kotlin/androidx/compose/ui/Alignment.html) = Alignment.BottomEnd, currentItemIndex: [MutableIntState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableIntState.html) = remember { mutableIntStateOf(0) }, listHasFocus: [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; = remember { mutableStateOf(false) }, list: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)[ImmersiveListScope](-immersive-list-scope/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {})
