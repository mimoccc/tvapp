//[tvlib](../../index.md)/[org.mjdev.tvlib.glide.sources.svg](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [SvgDecoder](-svg-decoder/index.md) | [androidJvm]<br>class [SvgDecoder](-svg-decoder/index.md) : ResourceDecoder&lt;[InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html), SVG&gt; <br>Decodes an SVG internal representation from an [InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html). |
| [SvgDrawableTranscoder](-svg-drawable-transcoder/index.md) | [androidJvm]<br>class [SvgDrawableTranscoder](-svg-drawable-transcoder/index.md) : ResourceTranscoder&lt;SVG?, [PictureDrawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/PictureDrawable.html)?&gt; <br>Convert the SVG's internal representation to an Android-compatible one ([Picture](https://developer.android.com/reference/kotlin/android/graphics/Picture.html)). |
| [SvgSoftwareLayerSetter](-svg-software-layer-setter/index.md) | [androidJvm]<br>class [SvgSoftwareLayerSetter](-svg-software-layer-setter/index.md) : RequestListener&lt;[PictureDrawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/PictureDrawable.html)?&gt; <br>Listener which updates the [ImageView](https://developer.android.com/reference/kotlin/android/widget/ImageView.html) to be software rendered, because [ ] /[Picture](https://developer.android.com/reference/kotlin/android/graphics/Picture.html) can't render on a hardware backed [Canvas](https://developer.android.com/reference/kotlin/android/graphics/Canvas.html). |
