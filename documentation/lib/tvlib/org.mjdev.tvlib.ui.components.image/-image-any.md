//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.image](index.md)/[ImageAny](-image-any.md)

# ImageAny

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [ImageAny](-image-any.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, src: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = null, contentDescription: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, alignment: [Alignment](https://developer.android.com/reference/kotlin/androidx/compose/ui/Alignment.html) = Alignment.Center, contentScale: [ContentScale](https://developer.android.com/reference/kotlin/androidx/compose/ui/layout/ContentScale.html) = ContentScale.Fit, alpha: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) = DefaultAlpha, tint: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html)? = null, colorFilter: [ColorFilter](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/ColorFilter.html)? = tint?.let { ColorFilter.tint(it) }, placeholder: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {
        Image(
            painterResource(R.drawable.broken_image),
            contentDescription,
            modifier,
            alignment,
            contentScale,
            alpha,
            colorFilter
        )
    })
