//[tvlib](../../../index.md)/[org.mjdev.tvlib.ui.components.carousel](../index.md)/[CarouselDefaults](index.md)

# CarouselDefaults

[androidJvm]\
object [CarouselDefaults](index.md)

## Types

| Name | Summary |
|---|---|
| [KeyEventPropagation](-key-event-propagation/index.md) | [androidJvm]<br>object [KeyEventPropagation](-key-event-propagation/index.md) |
| [NoOpScrollPauseHandle](-no-op-scroll-pause-handle/index.md) | [androidJvm]<br>object [NoOpScrollPauseHandle](-no-op-scroll-pause-handle/index.md) : [CarouselDefaults.ScrollPauseHandle](-scroll-pause-handle/index.md) |
| [ScrollPauseHandle](-scroll-pause-handle/index.md) | [androidJvm]<br>interface [ScrollPauseHandle](-scroll-pause-handle/index.md) |
| [ScrollPauseHandleImpl](-scroll-pause-handle-impl/index.md) | [androidJvm]<br>class [ScrollPauseHandleImpl](-scroll-pause-handle-impl/index.md)(carouselState: [CarouselState](../-carousel-state/index.md)) : [CarouselDefaults.ScrollPauseHandle](-scroll-pause-handle/index.md) |

## Functions

| Name | Summary |
|---|---|
| [CarouselStateUpdater](-carousel-state-updater.md) | [androidJvm]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>fun [CarouselStateUpdater](-carousel-state-updater.md)(carouselState: [CarouselState](../-carousel-state/index.md), itemCount: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [IndicatorRow](-indicator-row.md) | [androidJvm]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>fun [IndicatorRow](-indicator-row.md)(itemCount: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), activeItemIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, spacing: [Dp](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/Dp.html) = 8.dp, indicator: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)(isActive: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { isActive -&gt;             val activeColor = Color.White             val inactiveColor = activeColor.copy(alpha = 0.3f)             Box(                 modifier = Modifier                     .size(8.dp)                     .background(                         color = if (isActive) activeColor else inactiveColor,                         shape = CircleShape,                     ),             )         }) |
| [onAnimationCompletion](on-animation-completion.md) | [androidJvm]<br>suspend fun [AnimatedVisibilityScope](https://developer.android.com/reference/kotlin/androidx/compose/animation/AnimatedVisibilityScope.html).[onAnimationCompletion](on-animation-completion.md)(action: suspend () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) |
| [shouldPerformAutoScroll](should-perform-auto-scroll.md) | [androidJvm]<br>fun [shouldPerformAutoScroll](should-perform-auto-scroll.md)(focusState: [FocusState](https://developer.android.com/reference/kotlin/androidx/compose/ui/focus/FocusState.html)?, accessibilityManager: [AccessibilityManager](https://developer.android.com/reference/kotlin/android/view/accessibility/AccessibilityManager.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [contentTransform](content-transform.md) | [androidJvm]<br>@get:[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>val [contentTransform](content-transform.md): [ContentTransform](https://developer.android.com/reference/kotlin/androidx/compose/animation/ContentTransform.html) |
| [TimeToDisplayItemMillis](-time-to-display-item-millis.md) | [androidJvm]<br>const val [TimeToDisplayItemMillis](-time-to-display-item-millis.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 5000 |
