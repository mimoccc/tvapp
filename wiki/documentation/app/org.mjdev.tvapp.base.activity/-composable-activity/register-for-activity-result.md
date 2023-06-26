//[app](../../../index.md)/[org.mjdev.tvapp.base.activity](../index.md)/[ComposableActivity](index.md)/[registerForActivityResult](register-for-activity-result.md)

# registerForActivityResult

[androidJvm]\
fun &lt;[T](register-for-activity-result.md)&gt; [registerForActivityResult](register-for-activity-result.md)(onLaunch: (args: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[T](register-for-activity-result.md)&gt;) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), onActivityResult: (requestCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), resultCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), intent: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [ComposableActivity.ActivityResultHandler](-activity-result-handler/index.md)&lt;[T](register-for-activity-result.md)&gt;

Register for activity result.

Custom handler registration.

#### Return

[ActivityResultHandler](-activity-result-handler/index.md) handler generated

#### Parameters

androidJvm

| | |
|---|---|
| onLaunch | On launch method, to start custom activity. |
| onActivityResult | On activity result handler. |
| T | T result type |
