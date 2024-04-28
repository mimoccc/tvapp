//[tvlib](../../index.md)/[org.mjdev.tvlib.glide](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [ApplicationGlideApp](-application-glide-app/index.md) | [androidJvm]<br>class [ApplicationGlideApp](-application-glide-app/index.md) : AppGlideModule<br>Custom loader for images & predefined settings |
| [LoaderTarget](-loader-target/index.md) | [androidJvm]<br>class [LoaderTarget](-loader-target/index.md)(width: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = SIZE_ORIGINAL, height: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = SIZE_ORIGINAL, val onLoadStarted: (drawable: [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, val onLoadFinished: (drawable: [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null) : CustomTarget&lt;[Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)&gt; <br>Custom Glide simplified load target |
| [OkHttpUrlLoaderEx](-ok-http-url-loader-ex/index.md) | [androidJvm]<br>class [OkHttpUrlLoaderEx](-ok-http-url-loader-ex/index.md)(factory: [OkHttpUrlLoaderEx.Factory](-ok-http-url-loader-ex/-factory/index.md)) : ModelLoader&lt;GlideUrl, [InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)&gt; , [SharedPreferences.OnSharedPreferenceChangeListener](https://developer.android.com/reference/kotlin/android/content/SharedPreferences.OnSharedPreferenceChangeListener.html) |
