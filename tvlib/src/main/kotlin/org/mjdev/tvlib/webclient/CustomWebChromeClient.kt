package org.mjdev.tvlib.webclient

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.os.Message
import android.view.View
import android.webkit.*
import timber.log.Timber

@SuppressLint("SetJavaScriptEnabled")
@Suppress("Deprecation")
class CustomWebChromeClient(private val webView: WebClient) : WebChromeClient() {
    private val settings: WebSettings get() = webView.settings
    var icon: Bitmap? = null
        internal set
    var title: String? = null
        internal set

    init {
        settings.apply {
            userAgentString = WebClient.USER_AGENT
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = false
            mediaPlaybackRequiresUserGesture = false
            allowContentAccess = false
            allowFileAccess = false
            allowFileAccessFromFileURLs = false
            allowUniversalAccessFromFileURLs = false
            databaseEnabled = false
            displayZoomControls = false
            domStorageEnabled = false
//            layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
//            loadWithOverviewMode = true
            loadsImagesAutomatically = false
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                offscreenPreRaster = true
//            }
//            useWideViewPort = true
//            setAppCacheEnabled(true)
            setSupportMultipleWindows(false)
            setSupportZoom(false)
            setGeolocationEnabled(false)
            pluginState = WebSettings.PluginState.OFF
//            setRenderPriority(WebSettings.RenderPriority.HIGH)
        }
    }

    override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
        Timber.e("Got console message: ${consoleMessage?.message()}")
        return super.onConsoleMessage(consoleMessage)
    }

    override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
    }

    override fun onHideCustomView() {
    }

    override fun onProgressChanged(view: WebView?, newProgress: Int) {
    }

    override fun onCloseWindow(window: WebView?) {
    }

    override fun onCreateWindow(
        view: WebView?,
        isDialog: Boolean,
        isUserGesture: Boolean,
        resultMsg: Message?
    ): Boolean {
        return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg)
    }

    override fun onGeolocationPermissionsHidePrompt() {
    }

    override fun onGeolocationPermissionsShowPrompt(
        origin: String?,
        callback: GeolocationPermissions.Callback?
    ) {
    }

    override fun onJsAlert(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {
        return super.onJsAlert(view, url, message, result)
    }

    override fun onJsBeforeUnload(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {
        return super.onJsBeforeUnload(view, url, message, result)
    }

    override fun onJsConfirm(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {
        return super.onJsConfirm(view, url, message, result)
    }

    override fun onJsPrompt(
        view: WebView?,
        url: String?,
        message: String?,
        defaultValue: String?,
        result: JsPromptResult?
    ): Boolean {
        return super.onJsPrompt(view, url, message, defaultValue, result)
    }

    override fun onPermissionRequest(request: PermissionRequest?) {
    }

    override fun onPermissionRequestCanceled(request: PermissionRequest?) {
    }

    override fun onReceivedIcon(view: WebView?, icon: Bitmap?) {
        this.icon = icon
    }

    override fun onReceivedTitle(view: WebView?, title: String?) {
        this.title = title
    }

    override fun onReceivedTouchIconUrl(
        view: WebView?,
        url: String?,
        precomposed: Boolean
    ) {
    }

    override fun onRequestFocus(view: WebView?) {
    }

    override fun onShowFileChooser(
        view: WebView?,
        filePathCallback: ValueCallback<Array<Uri>>?,
        fileChooserParams: FileChooserParams?
    ): Boolean {
        return super.onShowFileChooser(webView, filePathCallback, fileChooserParams)
    }

    @Deprecated("Deprecated in Java")
    override fun onConsoleMessage(message: String?, lineNumber: Int, sourceID: String?) {
    }

    @Deprecated("Deprecated in Java")
    override fun onExceededDatabaseQuota(
        url: String?,
        databaseIdentifier: String?,
        quota: Long,
        estimatedDatabaseSize: Long,
        totalQuota: Long,
        quotaUpdater: WebStorage.QuotaUpdater?
    ) {
    }

    @Deprecated("Deprecated in Java",
        ReplaceWith("super.onJsTimeout()", "android.webkit.WebChromeClient")
    )
    override fun onJsTimeout(): Boolean {
        return super.onJsTimeout()
    }

//    override fun onReachedMaxAppCacheSize(
//        requiredStorage: Long,
//        quota: Long,
//        quotaUpdater: WebStorage.QuotaUpdater?
//    ) {
//    }

    @Deprecated("Deprecated in Java")
    override fun onShowCustomView(
        view: View?,
        requestedOrientation: Int,
        callback: CustomViewCallback?
    ) {
    }
}