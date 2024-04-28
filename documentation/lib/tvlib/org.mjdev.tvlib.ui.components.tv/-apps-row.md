//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.tv](index.md)/[AppsRow](-apps-row.md)

# AppsRow

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [AppsRow](-apps-row.md)(title: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = R.string.title_apps, rowState: [TvLazyListState](https://developer.android.com/reference/kotlin/androidx/tv/foundation/lazy/list/TvLazyListState.html) = rememberTvLazyListState(), padding: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = 8.dp, backgroundColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.DarkGray.copy(alpha = 0.3f), roundCornerSize: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = 8.dp, backgroundShape: [Shape](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Shape.html) = RoundedCornerShape(roundCornerSize), startApp: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html).(app: [App](../org.mjdev.tvlib.data.local/-app/index.md)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { app -&gt;
        startActivity(app?.intent)
    }, apps: [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html)&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[App](../org.mjdev.tvlib.data.local/-app/index.md)&gt;&gt; = rememberAppsManager().collectAsState(emptyList()), cardWidth: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = computeCardWidth(3f))
