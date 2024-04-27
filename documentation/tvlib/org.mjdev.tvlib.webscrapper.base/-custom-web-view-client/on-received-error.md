//[tvlib](../../../index.md)/[org.mjdev.tvlib.webscrapper.base](../index.md)/[CustomWebViewClient](index.md)/[onReceivedError](on-received-error.md)

# onReceivedError

[androidJvm]\
open override fun [onReceivedError](on-received-error.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, request: [WebResourceRequest](https://developer.android.com/reference/kotlin/android/webkit/WebResourceRequest.html)?, error: [WebResourceError](https://developer.android.com/reference/kotlin/android/webkit/WebResourceError.html)?)

[androidJvm]\
open override fun [~~onReceivedError~~](on-received-error.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, errorCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), description: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, failingUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?)

---

### Deprecated

Deprecated in Java

#### Replace with

```kotlin
import org.mjdev.tvlib.webscraper.base.ReceivedError

```
```kotlin
onError(ReceivedError(description))
```
---
