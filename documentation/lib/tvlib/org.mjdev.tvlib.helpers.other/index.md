//[tvlib](../../index.md)/[org.mjdev.tvlib.helpers.other](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [ISaver](-i-saver/index.md) | [androidJvm]<br>interface [ISaver](-i-saver/index.md)&lt;[T](-i-saver/index.md)&gt; |
| [MemorySaver](-memory-saver/index.md) | [androidJvm]<br>class [MemorySaver](-memory-saver/index.md)&lt;[T](-memory-saver/index.md)&gt; : [ISaver](-i-saver/index.md)&lt;[T](-memory-saver/index.md)&gt; |
| [Observed](-observed/index.md) | [androidJvm]<br>open class [Observed](-observed/index.md)&lt;[T](-observed/index.md)&gt;(initial: [T](-observed/index.md), saver: [ISaver](-i-saver/index.md)&lt;[T](-observed/index.md)&gt; = MemorySaver()) : [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[T](-observed/index.md)&gt; |
| [PreferenceProperty](-preference-property/index.md) | [androidJvm]<br>class [PreferenceProperty](-preference-property/index.md)&lt;[T](-preference-property/index.md)&gt;(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), repoName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;settings&quot;, name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), defaultValue: [T](-preference-property/index.md)? = null) : [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[T](-preference-property/index.md)?&gt; , [SharedPreferences.OnSharedPreferenceChangeListener](https://developer.android.com/reference/kotlin/android/content/SharedPreferences.OnSharedPreferenceChangeListener.html) |
| [Preferences](-preferences/index.md) | [androidJvm]<br>object [Preferences](-preferences/index.md) |
