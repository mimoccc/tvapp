//[tvlib](../../../index.md)/[org.mjdev.tvlib.helpers.other](../index.md)/[PreferenceProperty](index.md)

# PreferenceProperty

[androidJvm]\
class [PreferenceProperty](index.md)&lt;[T](index.md)&gt;(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), repoName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;settings&quot;, name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), defaultValue: [T](index.md)? = null) : [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[T](index.md)?&gt; , [SharedPreferences.OnSharedPreferenceChangeListener](https://developer.android.com/reference/kotlin/android/content/SharedPreferences.OnSharedPreferenceChangeListener.html)

## Constructors

| | |
|---|---|
| [PreferenceProperty](-preference-property.md) | [androidJvm]<br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), repoName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;settings&quot;, name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), defaultValue: [T](index.md)? = null) |

## Functions

| Name | Summary |
|---|---|
| [component1](component1.md) | [androidJvm]<br>open operator override fun [component1](component1.md)(): [T](index.md)? |
| [component2](component2.md) | [androidJvm]<br>open operator override fun [component2](component2.md)(): ([T](index.md)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onSharedPreferenceChanged](on-shared-preference-changed.md) | [androidJvm]<br>open override fun [onSharedPreferenceChanged](on-shared-preference-changed.md)(sharedPreferences: [SharedPreferences](https://developer.android.com/reference/kotlin/android/content/SharedPreferences.html)?, key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) |
| [registerForChanges](register-for-changes.md) | [androidJvm]<br>fun [registerForChanges](register-for-changes.md)() |
| [toggle](../../org.mjdev.tvlib.extensions/-global-ext/toggle.md) | [androidJvm]<br>fun [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;.[toggle](../../org.mjdev.tvlib.extensions/-global-ext/toggle.md)() |
| [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [unregister](unregister.md) | [androidJvm]<br>fun [unregister](unregister.md)() |

## Properties

| Name | Summary |
|---|---|
| [isConnected](../../org.mjdev.tvlib.network/is-connected.md) | [androidJvm]<br>val [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html)&lt;[NetworkStatus](../../org.mjdev.tvlib.network/-network-status/index.md)?&gt;?.[isConnected](../../org.mjdev.tvlib.network/is-connected.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isFocused](../../org.mjdev.tvlib.extensions/-compose-ext/is-focused.md) | [androidJvm]<br>val [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[FocusState](https://developer.android.com/reference/kotlin/androidx/compose/ui/focus/FocusState.html)&gt;.[isFocused](../../org.mjdev.tvlib.extensions/-compose-ext/is-focused.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isNotConnected](../../org.mjdev.tvlib.network/is-not-connected.md) | [androidJvm]<br>val [State](https://developer.android.com/reference/kotlin/androidx/compose/runtime/State.html)&lt;[NetworkStatus](../../org.mjdev.tvlib.network/-network-status/index.md)?&gt;?.[isNotConnected](../../org.mjdev.tvlib.network/is-not-connected.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isNotFocused](../../org.mjdev.tvlib.extensions/-compose-ext/is-not-focused.md) | [androidJvm]<br>val [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[FocusState](https://developer.android.com/reference/kotlin/androidx/compose/ui/focus/FocusState.html)&gt;.[isNotFocused](../../org.mjdev.tvlib.extensions/-compose-ext/is-not-focused.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [value](value.md) | [androidJvm]<br>open override var [value](value.md): [T](index.md)? |
