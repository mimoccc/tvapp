//[tvlib](../../../index.md)/[org.mjdev.tvlib.ui.toolkit.snackbar](../index.md)/[SnackbarHostState](index.md)/[showSnackbar](show-snackbar.md)

# showSnackbar

[androidJvm]\
suspend fun [showSnackbar](show-snackbar.md)(msg: [CharSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html), action: [CharSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html)? = null, leading: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = null, accent: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.Unspecified, withDismissAction: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, duration: [SnackbarDuration](../-snackbar-duration/index.md) = if (action == null) SnackbarDuration.Short else SnackbarDuration.Indefinite): [SnackbarResult](../-snackbar-result/index.md)
