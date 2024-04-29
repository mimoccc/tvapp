//[tvlib](../../../index.md)/[org.mjdev.tvlib.webscrapper.base](../index.md)/[CustomWebViewClient](index.md)

# CustomWebViewClient

[androidJvm]\
class [CustomWebViewClient](index.md)(loadImages: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, loadScripts: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, loadMedia: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, loadCssStyles: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, loadFonts: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, adBlocker: [IAdBlocker](../../org.mjdev.tvlib.webscrapper.adblock/-i-ad-blocker/index.md) = NoAdBlock(), onError: ([Exception](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-exception/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { e -&gt;
        Timber.e(e)
    }, onPageLoadStart: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onPageLoadFinish: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null) : [WebViewClient](https://developer.android.com/reference/kotlin/android/webkit/WebViewClient.html)

## Constructors

| | |
|---|---|
| [CustomWebViewClient](-custom-web-view-client.md) | [androidJvm]<br>constructor(loadImages: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, loadScripts: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, loadMedia: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, loadCssStyles: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, loadFonts: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, adBlocker: [IAdBlocker](../../org.mjdev.tvlib.webscrapper.adblock/-i-ad-blocker/index.md) = NoAdBlock(), onError: ([Exception](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-exception/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { e -&gt;         Timber.e(e)     }, onPageLoadStart: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onPageLoadFinish: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null) |

## Types

| Name | Summary |
|---|---|
| [EmptyWebResource](-empty-web-resource/index.md) | [androidJvm]<br>class [EmptyWebResource](-empty-web-resource/index.md) : [WebResourceResponse](https://developer.android.com/reference/kotlin/android/webkit/WebResourceResponse.html) |

## Functions

| Name | Summary |
|---|---|
| [doUpdateVisitedHistory](index.md#-803139611%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [doUpdateVisitedHistory](index.md#-803139611%2FFunctions%2F-1596939238)(p0: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html), p1: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), p2: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
| [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onFormResubmission](on-form-resubmission.md) | [androidJvm]<br>open override fun [onFormResubmission](on-form-resubmission.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, dontResend: [Message](https://developer.android.com/reference/kotlin/android/os/Message.html)?, resend: [Message](https://developer.android.com/reference/kotlin/android/os/Message.html)?) |
| [onLoadResource](on-load-resource.md) | [androidJvm]<br>open override fun [onLoadResource](on-load-resource.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, url: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) |
| [onPageCommitVisible](on-page-commit-visible.md) | [androidJvm]<br>open override fun [onPageCommitVisible](on-page-commit-visible.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, url: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) |
| [onPageFinished](on-page-finished.md) | [androidJvm]<br>open override fun [onPageFinished](on-page-finished.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html), url: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [onPageStarted](on-page-started.md) | [androidJvm]<br>open override fun [onPageStarted](on-page-started.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, url: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, favicon: [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html)?) |
| [onReceivedClientCertRequest](on-received-client-cert-request.md) | [androidJvm]<br>open override fun [onReceivedClientCertRequest](on-received-client-cert-request.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, request: [ClientCertRequest](https://developer.android.com/reference/kotlin/android/webkit/ClientCertRequest.html)?) |
| [onReceivedError](on-received-error.md) | [androidJvm]<br>open override fun [onReceivedError](on-received-error.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, request: [WebResourceRequest](https://developer.android.com/reference/kotlin/android/webkit/WebResourceRequest.html)?, error: [WebResourceError](https://developer.android.com/reference/kotlin/android/webkit/WebResourceError.html)?)<br>open override fun [~~onReceivedError~~](on-received-error.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, errorCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), description: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, failingUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) |
| [onReceivedHttpAuthRequest](on-received-http-auth-request.md) | [androidJvm]<br>open override fun [onReceivedHttpAuthRequest](on-received-http-auth-request.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, handler: [HttpAuthHandler](https://developer.android.com/reference/kotlin/android/webkit/HttpAuthHandler.html)?, host: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, realm: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) |
| [onReceivedHttpError](on-received-http-error.md) | [androidJvm]<br>open override fun [onReceivedHttpError](on-received-http-error.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, request: [WebResourceRequest](https://developer.android.com/reference/kotlin/android/webkit/WebResourceRequest.html)?, errorResponse: [WebResourceResponse](https://developer.android.com/reference/kotlin/android/webkit/WebResourceResponse.html)?) |
| [onReceivedLoginRequest](on-received-login-request.md) | [androidJvm]<br>open override fun [onReceivedLoginRequest](on-received-login-request.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, realm: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, account: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, args: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) |
| [onReceivedSslError](on-received-ssl-error.md) | [androidJvm]<br>open override fun [onReceivedSslError](on-received-ssl-error.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, handler: [SslErrorHandler](https://developer.android.com/reference/kotlin/android/webkit/SslErrorHandler.html)?, error: [SslError](https://developer.android.com/reference/kotlin/android/net/http/SslError.html)?) |
| [onRenderProcessGone](on-render-process-gone.md) | [androidJvm]<br>open override fun [onRenderProcessGone](on-render-process-gone.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, detail: [RenderProcessGoneDetail](https://developer.android.com/reference/kotlin/android/webkit/RenderProcessGoneDetail.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onSafeBrowsingHit](on-safe-browsing-hit.md) | [androidJvm]<br>open override fun [onSafeBrowsingHit](on-safe-browsing-hit.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, request: [WebResourceRequest](https://developer.android.com/reference/kotlin/android/webkit/WebResourceRequest.html)?, threatType: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), callback: [SafeBrowsingResponse](https://developer.android.com/reference/kotlin/android/webkit/SafeBrowsingResponse.html)?) |
| [onScaleChanged](on-scale-changed.md) | [androidJvm]<br>open override fun [onScaleChanged](on-scale-changed.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, oldScale: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html), newScale: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)) |
| [onTooManyRedirects](on-too-many-redirects.md) | [androidJvm]<br>open override fun [~~onTooManyRedirects~~](on-too-many-redirects.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, cancelMsg: [Message](https://developer.android.com/reference/kotlin/android/os/Message.html)?, continueMsg: [Message](https://developer.android.com/reference/kotlin/android/os/Message.html)?) |
| [onUnhandledKeyEvent](on-unhandled-key-event.md) | [androidJvm]<br>open override fun [onUnhandledKeyEvent](on-unhandled-key-event.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, event: [KeyEvent](https://developer.android.com/reference/kotlin/android/view/KeyEvent.html)?) |
| [shouldInterceptRequest](index.md#856453857%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [shouldInterceptRequest](index.md#856453857%2FFunctions%2F-1596939238)(p0: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html), p1: [WebResourceRequest](https://developer.android.com/reference/kotlin/android/webkit/WebResourceRequest.html)): [WebResourceResponse](https://developer.android.com/reference/kotlin/android/webkit/WebResourceResponse.html)?<br>open override fun [~~shouldInterceptRequest~~](should-intercept-request.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html), url: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [WebResourceResponse](https://developer.android.com/reference/kotlin/android/webkit/WebResourceResponse.html)? |
| [shouldOverrideKeyEvent](index.md#1841628860%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [shouldOverrideKeyEvent](index.md#1841628860%2FFunctions%2F-1596939238)(p0: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html), p1: [KeyEvent](https://developer.android.com/reference/kotlin/android/view/KeyEvent.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [shouldOverrideUrlLoading](index.md#-1077272299%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [shouldOverrideUrlLoading](index.md#-1077272299%2FFunctions%2F-1596939238)(p0: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html), p1: [WebResourceRequest](https://developer.android.com/reference/kotlin/android/webkit/WebResourceRequest.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>open fun [~~shouldOverrideUrlLoading~~](index.md#-1319219631%2FFunctions%2F-1596939238)(p0: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html), p1: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [resources](resources.md) | [androidJvm]<br>val [resources](resources.md): [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [responses](responses.md) | [androidJvm]<br>val [responses](responses.md): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;Response&gt; |