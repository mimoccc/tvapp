//[tvlib](../../../index.md)/[org.mjdev.tvlib.extensions](../index.md)/[ComposeExt](index.md)/[rememberColorFromImage](remember-color-from-image.md)

# rememberColorFromImage

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [rememberColorFromImage](remember-color-from-image.md)(image: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, initialColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.Transparent, context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html) = LocalContext.current, onError: (error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { error -&gt; Timber.e(error) }): [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html)&lt;[Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html)&gt;
