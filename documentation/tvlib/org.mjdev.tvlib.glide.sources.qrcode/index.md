//[tvlib](../../index.md)/[org.mjdev.tvlib.glide.sources.qrcode](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [BarcodeDrawable](-barcode-drawable/index.md) | [androidJvm]<br>class [BarcodeDrawable](-barcode-drawable/index.md)(val text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val format: BarcodeFormat, val size: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = -1, foregroundColor: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = BLACK, val backgroundColor: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = WHITE) : [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html) |
| [BarcodeEncoder](-barcode-encoder/index.md) | [androidJvm]<br>object [BarcodeEncoder](-barcode-encoder/index.md) |
| [QRCodeLoaderFactory](-q-r-code-loader-factory/index.md) | [androidJvm]<br>class [QRCodeLoaderFactory](-q-r-code-loader-factory/index.md)(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) : ModelLoaderFactory&lt;[QRCodeRes](-q-r-code-res/index.md)?, [Drawable](https://developer.android.com/reference/kotlin/android/graphics/drawable/Drawable.html)?&gt; |
| [QRCodeRes](-q-r-code-res/index.md) | [androidJvm]<br>open class [QRCodeRes](-q-r-code-res/index.md)(val data: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
