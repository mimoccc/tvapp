//[app](../../../index.md)/[org.mjdev.tvapp.base.state](../index.md)/[ScreenState](index.md)

# ScreenState

class [ScreenState](index.md)(title: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = null, error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)? = null)

Activity view state. Used to keep states and change in activity.

#### Parameters

androidJvm

| |
|---|
| title |
| error |

## Constructors

| | |
|---|---|
| [ScreenState](-screen-state.md) | [androidJvm]<br>constructor(title: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = null, error: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)? = null)<br>Create [ScreenState](index.md) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [clearError](clear-error.md) | [androidJvm]<br>fun [clearError](clear-error.md)()<br>Clear error. |
| [error](error.md) | [androidJvm]<br>fun [error](error.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>fun [error](error.md)(e: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)?)<br>Set Error. |
| [info](info.md) | [androidJvm]<br>fun [info](info.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Set info message.<br>[androidJvm]<br>fun [info](info.md)(e: [Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html))<br>Set info from throwable. |
| [setTitle](set-title.md) | [androidJvm]<br>fun [setTitle](set-title.md)(title: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?)<br>Set title. |

## Properties

| Name | Summary |
|---|---|
| [errorState](error-state.md) | [androidJvm]<br>val [errorState](error-state.md): [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)?&gt; |
| [titleState](title-state.md) | [androidJvm]<br>val [titleState](title-state.md): [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?&gt; |
