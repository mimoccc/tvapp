//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.card](index.md)/[CardContent](-card-content.md)

# CardContent

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [CardContent](-card-content.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, subtitleAlpha: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) = 0.6f, descriptionAlpha: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) = 0.8f, title: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {
        AutoHideEmptyText(&quot;title&quot;)
    }, subtitle: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {
        Box(Modifier.graphicsLayer {
            alpha = subtitleAlpha
        }) {
            AutoHideEmptyText(&quot;subtitle&quot;)
        }
    }, description: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {
        Box(Modifier.graphicsLayer {
            alpha = descriptionAlpha
        }) {
            AutoHideEmptyText(&quot;description&quot;)
        }
    })
