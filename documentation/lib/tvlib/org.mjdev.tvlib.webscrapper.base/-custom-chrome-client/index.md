//[tvlib](../../../index.md)/[org.mjdev.tvlib.webscrapper.base](../index.md)/[CustomChromeClient](index.md)

# CustomChromeClient

[androidJvm]\
class [CustomChromeClient](index.md)(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), val userAgent: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;Mozilla/5.0 (X11; Linux x86_64) &quot; +
            &quot;AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36&quot;, popupEnabled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, onError: ([Exception](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-exception/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { e -&gt; Timber.e(e) }, onIconReceived: ([Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onTitleReceived: ([String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onProgress: ([Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null) : [WebChromeClient](https://developer.android.com/reference/kotlin/android/webkit/WebChromeClient.html)

## Constructors

| | |
|---|---|
| [CustomChromeClient](-custom-chrome-client.md) | [androidJvm]<br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), userAgent: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;Mozilla/5.0 (X11; Linux x86_64) &quot; +             &quot;AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36&quot;, popupEnabled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, onError: ([Exception](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-exception/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { e -&gt; Timber.e(e) }, onIconReceived: ([Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onTitleReceived: ([String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onProgress: ([Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getDefaultVideoPoster](index.md#1476212932%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [getDefaultVideoPoster](index.md#1476212932%2FFunctions%2F-1596939238)(): [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html)? |
| [getVideoLoadingProgressView](index.md#2070250264%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [getVideoLoadingProgressView](index.md#2070250264%2FFunctions%2F-1596939238)(): [View](https://developer.android.com/reference/kotlin/android/view/View.html)? |
| [getVisitedHistory](index.md#-574836608%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [getVisitedHistory](index.md#-574836608%2FFunctions%2F-1596939238)(p0: [ValueCallback](https://developer.android.com/reference/kotlin/android/webkit/ValueCallback.html)&lt;[Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;&gt;) |
| [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onCloseWindow](on-close-window.md) | [androidJvm]<br>open override fun [onCloseWindow](on-close-window.md)(window: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?) |
| [onConsoleMessage](on-console-message.md) | [androidJvm]<br>open override fun [onConsoleMessage](on-console-message.md)(consoleMessage: [ConsoleMessage](https://developer.android.com/reference/kotlin/android/webkit/ConsoleMessage.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>open override fun [onConsoleMessage](on-console-message.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, lineNumber: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), sourceID: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) |
| [onCreateWindow](on-create-window.md) | [androidJvm]<br>open override fun [onCreateWindow](on-create-window.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, isDialog: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), isUserGesture: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), resultMsg: [Message](https://developer.android.com/reference/kotlin/android/os/Message.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onExceededDatabaseQuota](on-exceeded-database-quota.md) | [androidJvm]<br>open override fun [onExceededDatabaseQuota](on-exceeded-database-quota.md)(url: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, databaseIdentifier: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, quota: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), estimatedDatabaseSize: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), totalQuota: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), quotaUpdater: [WebStorage.QuotaUpdater](https://developer.android.com/reference/kotlin/android/webkit/WebStorage.QuotaUpdater.html)?) |
| [onGeolocationPermissionsHidePrompt](on-geolocation-permissions-hide-prompt.md) | [androidJvm]<br>open override fun [onGeolocationPermissionsHidePrompt](on-geolocation-permissions-hide-prompt.md)() |
| [onGeolocationPermissionsShowPrompt](on-geolocation-permissions-show-prompt.md) | [androidJvm]<br>open override fun [onGeolocationPermissionsShowPrompt](on-geolocation-permissions-show-prompt.md)(origin: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, callback: [GeolocationPermissions.Callback](https://developer.android.com/reference/kotlin/android/webkit/GeolocationPermissions.Callback.html)?) |
| [onHideCustomView](on-hide-custom-view.md) | [androidJvm]<br>open override fun [onHideCustomView](on-hide-custom-view.md)() |
| [onJsAlert](on-js-alert.md) | [androidJvm]<br>open override fun [onJsAlert](on-js-alert.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, url: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, result: [JsResult](https://developer.android.com/reference/kotlin/android/webkit/JsResult.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onJsBeforeUnload](on-js-before-unload.md) | [androidJvm]<br>open override fun [onJsBeforeUnload](on-js-before-unload.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, url: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, result: [JsResult](https://developer.android.com/reference/kotlin/android/webkit/JsResult.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onJsConfirm](on-js-confirm.md) | [androidJvm]<br>open override fun [onJsConfirm](on-js-confirm.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, url: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, result: [JsResult](https://developer.android.com/reference/kotlin/android/webkit/JsResult.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onJsPrompt](on-js-prompt.md) | [androidJvm]<br>open override fun [onJsPrompt](on-js-prompt.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, url: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, defaultValue: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, result: [JsPromptResult](https://developer.android.com/reference/kotlin/android/webkit/JsPromptResult.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onJsTimeout](on-js-timeout.md) | [androidJvm]<br>open override fun [onJsTimeout](on-js-timeout.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onPermissionRequest](on-permission-request.md) | [androidJvm]<br>open override fun [onPermissionRequest](on-permission-request.md)(request: [PermissionRequest](https://developer.android.com/reference/kotlin/android/webkit/PermissionRequest.html)?) |
| [onPermissionRequestCanceled](on-permission-request-canceled.md) | [androidJvm]<br>open override fun [onPermissionRequestCanceled](on-permission-request-canceled.md)(request: [PermissionRequest](https://developer.android.com/reference/kotlin/android/webkit/PermissionRequest.html)?) |
| [onProgressChanged](on-progress-changed.md) | [androidJvm]<br>open override fun [onProgressChanged](on-progress-changed.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, newProgress: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [onReceivedIcon](on-received-icon.md) | [androidJvm]<br>open override fun [onReceivedIcon](on-received-icon.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, icon: [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html)?) |
| [onReceivedTitle](on-received-title.md) | [androidJvm]<br>open override fun [onReceivedTitle](on-received-title.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?) |
| [onReceivedTouchIconUrl](on-received-touch-icon-url.md) | [androidJvm]<br>open override fun [onReceivedTouchIconUrl](on-received-touch-icon-url.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, url: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, precomposed: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
| [onRequestFocus](on-request-focus.md) | [androidJvm]<br>open override fun [onRequestFocus](on-request-focus.md)(view: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?) |
| [onShowCustomView](on-show-custom-view.md) | [androidJvm]<br>open override fun [onShowCustomView](on-show-custom-view.md)(view: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?, callback: [WebChromeClient.CustomViewCallback](https://developer.android.com/reference/kotlin/android/webkit/WebChromeClient.CustomViewCallback.html)?)<br>open override fun [onShowCustomView](on-show-custom-view.md)(view: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?, requestedOrientation: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), callback: [WebChromeClient.CustomViewCallback](https://developer.android.com/reference/kotlin/android/webkit/WebChromeClient.CustomViewCallback.html)?) |
| [onShowFileChooser](on-show-file-chooser.md) | [androidJvm]<br>open override fun [onShowFileChooser](on-show-file-chooser.md)(webView: [WebView](https://developer.android.com/reference/kotlin/android/webkit/WebView.html)?, filePathCallback: [ValueCallback](https://developer.android.com/reference/kotlin/android/webkit/ValueCallback.html)&lt;[Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)&gt;&gt;?, fileChooserParams: [WebChromeClient.FileChooserParams](https://developer.android.com/reference/kotlin/android/webkit/WebChromeClient.FileChooserParams.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [context](context.md) | [androidJvm]<br>val [context](context.md): [Context](https://developer.android.com/reference/kotlin/android/content/Context.html) |
| [userAgent](user-agent.md) | [androidJvm]<br>val [userAgent](user-agent.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |