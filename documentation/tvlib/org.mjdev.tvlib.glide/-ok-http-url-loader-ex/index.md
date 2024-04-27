//[tvlib](../../../index.md)/[org.mjdev.tvlib.glide](../index.md)/[OkHttpUrlLoaderEx](index.md)

# OkHttpUrlLoaderEx

[androidJvm]\
class [OkHttpUrlLoaderEx](index.md)(factory: [OkHttpUrlLoaderEx.Factory](-factory/index.md)) : ModelLoader&lt;GlideUrl, [InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)&gt; , [SharedPreferences.OnSharedPreferenceChangeListener](https://developer.android.com/reference/kotlin/android/content/SharedPreferences.OnSharedPreferenceChangeListener.html)

## Constructors

| | |
|---|---|
| [OkHttpUrlLoaderEx](-ok-http-url-loader-ex.md) | [androidJvm]<br>constructor(factory: [OkHttpUrlLoaderEx.Factory](-factory/index.md)) |

## Types

| Name | Summary |
|---|---|
| [Factory](-factory/index.md) | [androidJvm]<br>class [Factory](-factory/index.md)(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) : ModelLoaderFactory&lt;GlideUrl, [InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)&gt; <br>Custom factory |

## Functions

| Name | Summary |
|---|---|
| [buildLoadData](build-load-data.md) | [androidJvm]<br>open override fun [buildLoadData](build-load-data.md)(model: GlideUrl, width: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), height: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), options: Options): ModelLoader.LoadData&lt;[InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)?&gt;<br>Prepare loading |
| [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [handles](handles.md) | [androidJvm]<br>open override fun [handles](handles.md)(url: GlideUrl): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checker function, see glide library details |
| [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onSharedPreferenceChanged](on-shared-preference-changed.md) | [androidJvm]<br>open override fun [onSharedPreferenceChanged](on-shared-preference-changed.md)(sharedPreferences: [SharedPreferences](https://developer.android.com/reference/kotlin/android/content/SharedPreferences.html)?, key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?)<br>Preferences change handler, to enable/disable debugging |
| [teardown](teardown.md) | [androidJvm]<br>fun [teardown](teardown.md)()<br>De-initialize loader |
| [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [context](context.md) | [androidJvm]<br>val [context](context.md): [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)<br>Context to be used |
