//[app](../../../index.md)/[org.mjdev.tvapp.widget](../index.md)/[MyAppWidgetReceiver](index.md)

# MyAppWidgetReceiver

[androidJvm]\
class [MyAppWidgetReceiver](index.md) : [GlanceAppWidgetReceiver](https://developer.android.com/reference/kotlin/androidx/glance/appwidget/GlanceAppWidgetReceiver.html)

## Constructors

| | |
|---|---|
| [MyAppWidgetReceiver](-my-app-widget-receiver.md) | [androidJvm]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [abortBroadcast](index.md#-1578158536%2FFunctions%2F-912451524) | [androidJvm]<br>fun [abortBroadcast](index.md#-1578158536%2FFunctions%2F-912451524)() |
| [clearAbortBroadcast](index.md#-547655405%2FFunctions%2F-912451524) | [androidJvm]<br>fun [clearAbortBroadcast](index.md#-547655405%2FFunctions%2F-912451524)() |
| [equals](../-refresh-action/index.md#585090901%2FFunctions%2F-912451524) | [androidJvm]<br>open operator fun [equals](../-refresh-action/index.md#585090901%2FFunctions%2F-912451524)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getAbortBroadcast](index.md#1852574954%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getAbortBroadcast](index.md#1852574954%2FFunctions%2F-912451524)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getDebugUnregister](index.md#-2066178064%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getDebugUnregister](index.md#-2066178064%2FFunctions%2F-912451524)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getResultCode](index.md#-1855658543%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getResultCode](index.md#-1855658543%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getResultData](index.md#485630644%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getResultData](index.md#485630644%2FFunctions%2F-912451524)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getResultExtras](index.md#1243983328%2FFunctions%2F-912451524) | [androidJvm]<br>fun [getResultExtras](index.md#1243983328%2FFunctions%2F-912451524)(p0: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html) |
| [getSentFromPackage](index.md#289542651%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [getSentFromPackage](index.md#289542651%2FFunctions%2F-912451524)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [getSentFromUid](index.md#-726187215%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [getSentFromUid](index.md#-726187215%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [goAsync](index.md#478464125%2FFunctions%2F-912451524) | [androidJvm]<br>fun [goAsync](index.md#478464125%2FFunctions%2F-912451524)(): [BroadcastReceiver.PendingResult](https://developer.android.com/reference/kotlin/android/content/BroadcastReceiver.PendingResult.html) |
| [hashCode](../-refresh-action/index.md#1794629105%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [hashCode](../-refresh-action/index.md#1794629105%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isInitialStickyBroadcast](index.md#-448034677%2FFunctions%2F-912451524) | [androidJvm]<br>fun [isInitialStickyBroadcast](index.md#-448034677%2FFunctions%2F-912451524)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isOrderedBroadcast](index.md#1250697259%2FFunctions%2F-912451524) | [androidJvm]<br>fun [isOrderedBroadcast](index.md#1250697259%2FFunctions%2F-912451524)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onAppWidgetOptionsChanged](index.md#-976591396%2FFunctions%2F-912451524) | [androidJvm]<br>open override fun [onAppWidgetOptionsChanged](index.md#-976591396%2FFunctions%2F-912451524)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), appWidgetManager: [AppWidgetManager](https://developer.android.com/reference/kotlin/android/appwidget/AppWidgetManager.html), appWidgetId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), newOptions: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)) |
| [onDeleted](index.md#-1048953566%2FFunctions%2F-912451524) | [androidJvm]<br>open override fun [onDeleted](index.md#-1048953566%2FFunctions%2F-912451524)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), appWidgetIds: [IntArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int-array/index.html)) |
| [onDisabled](index.md#-236156441%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onDisabled](index.md#-236156441%2FFunctions%2F-912451524)(p0: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) |
| [onEnabled](index.md#1058108298%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onEnabled](index.md#1058108298%2FFunctions%2F-912451524)(p0: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) |
| [onReceive](index.md#1138408143%2FFunctions%2F-912451524) | [androidJvm]<br>open override fun [onReceive](index.md#1138408143%2FFunctions%2F-912451524)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), intent: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)) |
| [onRestored](index.md#1363375833%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onRestored](index.md#1363375833%2FFunctions%2F-912451524)(p0: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), p1: [IntArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int-array/index.html), p2: [IntArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int-array/index.html)) |
| [onUpdate](index.md#381434235%2FFunctions%2F-912451524) | [androidJvm]<br>open override fun [onUpdate](index.md#381434235%2FFunctions%2F-912451524)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), appWidgetManager: [AppWidgetManager](https://developer.android.com/reference/kotlin/android/appwidget/AppWidgetManager.html), appWidgetIds: [IntArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int-array/index.html)) |
| [peekService](index.md#-1162131393%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [peekService](index.md#-1162131393%2FFunctions%2F-912451524)(p0: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), p1: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)): [IBinder](https://developer.android.com/reference/kotlin/android/os/IBinder.html) |
| [setDebugUnregister](index.md#375803713%2FFunctions%2F-912451524) | [androidJvm]<br>fun [setDebugUnregister](index.md#375803713%2FFunctions%2F-912451524)(p0: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
| [setOrderedHint](index.md#48379132%2FFunctions%2F-912451524) | [androidJvm]<br>fun [setOrderedHint](index.md#48379132%2FFunctions%2F-912451524)(p0: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
| [setResult](index.md#455010187%2FFunctions%2F-912451524) | [androidJvm]<br>fun [setResult](index.md#455010187%2FFunctions%2F-912451524)(p0: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), p1: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), p2: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)) |
| [setResultCode](index.md#-1146739549%2FFunctions%2F-912451524) | [androidJvm]<br>fun [setResultCode](index.md#-1146739549%2FFunctions%2F-912451524)(p0: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [setResultData](index.md#44586972%2FFunctions%2F-912451524) | [androidJvm]<br>fun [setResultData](index.md#44586972%2FFunctions%2F-912451524)(p0: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [setResultExtras](index.md#1065610694%2FFunctions%2F-912451524) | [androidJvm]<br>fun [setResultExtras](index.md#1065610694%2FFunctions%2F-912451524)(p0: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)) |
| [toString](../-refresh-action/index.md#1616463040%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [toString](../-refresh-action/index.md#1616463040%2FFunctions%2F-912451524)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [coroutineContext](index.md#-2008644872%2FProperties%2F-912451524) | [androidJvm]<br>open val [coroutineContext](index.md#-2008644872%2FProperties%2F-912451524): [CoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/index.html) |
| [glanceAppWidget](glance-app-widget.md) | [androidJvm]<br>open override val [glanceAppWidget](glance-app-widget.md): [GlanceAppWidget](https://developer.android.com/reference/kotlin/androidx/glance/appwidget/GlanceAppWidget.html) |
