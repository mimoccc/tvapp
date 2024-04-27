//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.carousel](index.md)/[BigCarousel](-big-carousel.md)

# BigCarousel

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [BigCarousel](-big-carousel.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, items: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?&gt; = listOf(Unit, Unit, Unit), autoScrollDurationMillis: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 8000, carouselState: [CarouselState](-carousel-state/index.md) = remember(items) { CarouselState() }, onItemSelected: (movie: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?, fromUser: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onItemClicked: (movie: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onSwipeUp: (dragAmount: [Offset](https://developer.android.com/reference/kotlin/androidx/compose/ui/geometry/Offset.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onSwipeDown: (dragAmount: [Offset](https://developer.android.com/reference/kotlin/androidx/compose/ui/geometry/Offset.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null)
