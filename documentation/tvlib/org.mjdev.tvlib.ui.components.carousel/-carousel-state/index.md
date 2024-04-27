//[tvlib](../../../index.md)/[org.mjdev.tvlib.ui.components.carousel](../index.md)/[CarouselState](index.md)

# CarouselState

[androidJvm]\
@[Stable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Stable.html)

class [CarouselState](index.md)(initialActiveItemIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0)

## Constructors

| | |
|---|---|
| [CarouselState](-carousel-state.md) | [androidJvm]<br>constructor(initialActiveItemIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isFirstItem](is-first-item.md) | [androidJvm]<br>fun [isFirstItem](is-first-item.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isLastItem](is-last-item.md) | [androidJvm]<br>fun [isLastItem](is-last-item.md)(itemCount: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [moveToNextItem](move-to-next-item.md) | [androidJvm]<br>fun [moveToNextItem](move-to-next-item.md)(itemCount: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [moveToPreviousItem](move-to-previous-item.md) | [androidJvm]<br>fun [moveToPreviousItem](move-to-previous-item.md)(itemCount: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [pauseAutoScroll](pause-auto-scroll.md) | [androidJvm]<br>fun [pauseAutoScroll](pause-auto-scroll.md)(itemIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [CarouselDefaults.ScrollPauseHandle](../-carousel-defaults/-scroll-pause-handle/index.md) |
| [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [activeItemIndex](active-item-index.md) | [androidJvm]<br>var [activeItemIndex](active-item-index.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [activePauseHandlesCount](active-pause-handles-count.md) | [androidJvm]<br>var [activePauseHandlesCount](active-pause-handles-count.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isMovingBackward](is-moving-backward.md) | [androidJvm]<br>var [isMovingBackward](is-moving-backward.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
