//[tvlib](../../index.md)/[org.mjdev.tvlib.activity](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [ActivityResultHandler](-activity-result-handler/index.md) | [androidJvm]<br>class [ActivityResultHandler](-activity-result-handler/index.md)&lt;[I](-activity-result-handler/index.md)&gt;(activity: [ComposableActivity](-composable-activity/index.md), val lifecycle: [Lifecycle](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.html), val onLaunch: (args: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[I](-activity-result-handler/index.md)&gt;) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), val onActivityResult: (requestCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), resultCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), intent: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Activity result handler. |
| [ComposableActivity](-composable-activity/index.md) | [androidJvm]<br>open class [ComposableActivity](-composable-activity/index.md) : [ComponentActivity](https://developer.android.com/reference/kotlin/androidx/activity/ComponentActivity.html) |
| [TvActivity](-tv-activity/index.md) | [androidJvm]<br>open class [TvActivity](-tv-activity/index.md) : [ComposableActivity](-composable-activity/index.md), [View.OnApplyWindowInsetsListener](https://developer.android.com/reference/kotlin/android/view/View.OnApplyWindowInsetsListener.html) |
