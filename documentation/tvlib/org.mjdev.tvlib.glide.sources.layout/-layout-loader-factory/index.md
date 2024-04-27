//[tvlib](../../../index.md)/[org.mjdev.tvlib.glide.sources.layout](../index.md)/[LayoutLoaderFactory](index.md)

# LayoutLoaderFactory

[androidJvm]\
class [LayoutLoaderFactory](index.md)(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) : ModelLoaderFactory&lt;[LayoutResId](../-layout-res-id/index.md)?, [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)?&gt; 

Inflate view to a drawable thru glide library. Useful for custom drawables. Usage: GlideApp.with(context).load(LayoutResId(R.layout.layout_file)).into(target)

## Constructors

| | |
|---|---|
| [LayoutLoaderFactory](-layout-loader-factory.md) | [androidJvm]<br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) |

## Functions

| Name | Summary |
|---|---|
| [build](build.md) | [androidJvm]<br>open override fun [build](build.md)(multiFactory: MultiModelLoaderFactory): ModelLoader&lt;[LayoutResId](../-layout-res-id/index.md)?, [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)?&gt;<br>Prepare loader |
| [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [teardown](teardown.md) | [androidJvm]<br>open override fun [teardown](teardown.md)()<br>Destroy loader |
| [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [context](context.md) | [androidJvm]<br>val [context](context.md): [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)<br>Context |
