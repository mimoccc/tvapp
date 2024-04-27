//[app](../../../index.md)/[org.mjdev.tvapp.widget](../index.md)/[MainAppWidget](index.md)

# MainAppWidget

[androidJvm]\
class [MainAppWidget](index.md) : [GlanceAppWidget](https://developer.android.com/reference/kotlin/androidx/glance/appwidget/GlanceAppWidget.html)

## Constructors

| | |
|---|---|
| [MainAppWidget](-main-app-widget.md) | [androidJvm]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [equals](../-refresh-action/index.md#585090901%2FFunctions%2F-912451524) | [androidJvm]<br>open operator fun [equals](../-refresh-action/index.md#585090901%2FFunctions%2F-912451524)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../-refresh-action/index.md#1794629105%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [hashCode](../-refresh-action/index.md#1794629105%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onDelete](index.md#1272574698%2FFunctions%2F-912451524) | [androidJvm]<br>open suspend fun [onDelete](index.md#1272574698%2FFunctions%2F-912451524)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), glanceId: [GlanceId](https://developer.android.com/reference/kotlin/androidx/glance/GlanceId.html)) |
| [provideGlance](provide-glance.md) | [androidJvm]<br>open suspend override fun [provideGlance](provide-glance.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), id: [GlanceId](https://developer.android.com/reference/kotlin/androidx/glance/GlanceId.html)) |
| [toString](../-refresh-action/index.md#1616463040%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [toString](../-refresh-action/index.md#1616463040%2FFunctions%2F-912451524)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [update](index.md#600366635%2FFunctions%2F-912451524) | [androidJvm]<br>suspend fun [update](index.md#600366635%2FFunctions%2F-912451524)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), id: [GlanceId](https://developer.android.com/reference/kotlin/androidx/glance/GlanceId.html)) |
| [WidgetContent](-widget-content.md) | [androidJvm]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>fun [WidgetContent](-widget-content.md)() |

## Properties

| Name | Summary |
|---|---|
| [sizeMode](index.md#1881163708%2FProperties%2F-912451524) | [androidJvm]<br>open val [sizeMode](index.md#1881163708%2FProperties%2F-912451524): [SizeMode](https://developer.android.com/reference/kotlin/androidx/glance/appwidget/SizeMode.html) |
| [stateDefinition](index.md#-254620330%2FProperties%2F-912451524) | [androidJvm]<br>open val [stateDefinition](index.md#-254620330%2FProperties%2F-912451524): [GlanceStateDefinition](https://developer.android.com/reference/kotlin/androidx/glance/state/GlanceStateDefinition.html)&lt;*&gt;? |
