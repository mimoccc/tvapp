//[tvlib](../../index.md)/[org.mjdev.tvlib.glide.sources.layout](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [LayoutInflaterDrawable](-layout-inflater-drawable/index.md) | [androidJvm]<br>class [LayoutInflaterDrawable](-layout-inflater-drawable/index.md)(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), @[LayoutRes](https://developer.android.com/reference/kotlin/androidx/annotation/LayoutRes.html)val resId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)<br>Custom drawable that inflate view to itself Used for loading image error customized replacement |
| [LayoutLoaderFactory](-layout-loader-factory/index.md) | [androidJvm]<br>class [LayoutLoaderFactory](-layout-loader-factory/index.md)(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) : ModelLoaderFactory&lt;[LayoutResId](-layout-res-id/index.md)?, [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)?&gt; <br>Inflate view to a drawable thru glide library. Useful for custom drawables. Usage: GlideApp.with(context).load(LayoutResId(R.layout.layout_file)).into(target) |
| [LayoutResId](-layout-res-id/index.md) | [androidJvm]<br>class [LayoutResId](-layout-res-id/index.md)(@[LayoutRes](https://developer.android.com/reference/kotlin/androidx/annotation/LayoutRes.html)val resId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = View.NO_ID)<br>Inflate view to a drawable thru glide library. Useful for custom drawables. Usage: GlideApp.with(context).load(LayoutResId(R.layout.layout_file)).into(target) |
