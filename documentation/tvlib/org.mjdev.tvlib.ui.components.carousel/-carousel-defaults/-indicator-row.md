//[tvlib](../../../index.md)/[org.mjdev.tvlib.ui.components.carousel](../index.md)/[CarouselDefaults](index.md)/[IndicatorRow](-indicator-row.md)

# IndicatorRow

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [IndicatorRow](-indicator-row.md)(itemCount: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), activeItemIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, spacing: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = 8.dp, indicator: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)(isActive: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { isActive -&gt;
            val activeColor = Color.White
            val inactiveColor = activeColor.copy(alpha = 0.3f)
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(
                        color = if (isActive) activeColor else inactiveColor,
                        shape = CircleShape,
                    ),
            )
        })
