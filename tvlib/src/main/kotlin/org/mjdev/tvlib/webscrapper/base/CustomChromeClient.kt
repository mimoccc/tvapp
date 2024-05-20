/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.net.Uri
import android.os.Message
import android.view.View
import android.view.WindowManager
import android.webkit.ConsoleMessage
import android.webkit.CookieManager
import android.webkit.GeolocationPermissions
import android.webkit.JsPromptResult
import android.webkit.JsResult
import android.webkit.PermissionRequest
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebStorage
import android.webkit.WebView
import androidx.appcompat.app.AlertDialog
import timber.log.Timber

class CustomChromeClient(
    val context: Context,
    val userAgent: String = "Mozilla/5.0 (X11; Linux x86_64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36",
    private val popupEnabled: Boolean = true,
    private val onError: (Exception) -> Unit = { e -> Timber.e(e) },
    private val onIconReceived: ((Bitmap?) -> Unit)? = null,
    private val onTitleReceived: ((String?) -> Unit )?= null,
    private val onProgress: ((Int) -> Unit)? = null
) : WebChromeClient() {
    private val cookieManager: CookieManager by lazy { CookieManager.getInstance() }
    private val webViewPopUp by lazy { WebView(context) }
    private val builder: AlertDialog by lazy { AlertDialog.Builder(context).create() }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateWindow(
        view: WebView?,
        isDialog: Boolean,
        isUserGesture: Boolean,
        resultMsg: Message
    ): Boolean {
        if (popupEnabled) {
            webViewPopUp.isVerticalScrollBarEnabled = false
            webViewPopUp.isHorizontalScrollBarEnabled = false
            webViewPopUp.webChromeClient = CustomChromeClient(context)
            webViewPopUp.settings.javaScriptEnabled = true
            webViewPopUp.settings.userAgentString = userAgent
            builder.setTitle("")
            builder.setView(webViewPopUp)
            builder.setButton(DialogInterface.BUTTON_POSITIVE, "Close") { dialog, _ ->
                webViewPopUp.destroy()
                dialog.dismiss()
            }
            builder.show()
            builder.window?.clearFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                        WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
            )
            cookieManager.setAcceptCookie(true)
            cookieManager.setAcceptThirdPartyCookies(webViewPopUp, true)
            cookieManager.setAcceptThirdPartyCookies(view, true)
            val transport = resultMsg.obj as WebView.WebViewTransport
            transport.webView = webViewPopUp
            resultMsg.sendToTarget()
            return true
        } else {
            return false
        }
    }

    override fun onCloseWindow(window: WebView?) {
        if (popupEnabled) {
            try {
                webViewPopUp.destroy()
            } catch (e: Exception) {
                Timber.e(e)
            }
            try {
                builder.dismiss()
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
        Timber.e(consoleMessage?.message())
        return super.onConsoleMessage(consoleMessage)
    }

    override fun onGeolocationPermissionsHidePrompt() {
        super.onGeolocationPermissionsHidePrompt()
    }

    override fun onGeolocationPermissionsShowPrompt(
        origin: String?,
        callback: GeolocationPermissions.Callback?
    ) {
        super.onGeolocationPermissionsShowPrompt(origin, callback)
    }

    override fun onHideCustomView() {
        super.onHideCustomView()
    }

    override fun onJsAlert(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {
        onError(JSAlert(message))
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
        super.onPermissionRequest(request)
    }

    override fun onPermissionRequestCanceled(request: PermissionRequest?) {
        super.onPermissionRequestCanceled(request)
    }

    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        onProgress?.invoke(newProgress)
        super.onProgressChanged(view, newProgress)
    }

    override fun onReceivedIcon(view: WebView?, icon: Bitmap?) {
        onIconReceived?.invoke(icon)
        super.onReceivedIcon(view, icon)
    }

    override fun onReceivedTitle(view: WebView?, title: String?) {
        onTitleReceived?.invoke(title)
        super.onReceivedTitle(view, title)
    }

    override fun onReceivedTouchIconUrl(view: WebView?, url: String?, precomposed: Boolean) {
        super.onReceivedTouchIconUrl(view, url, precomposed)
    }

    override fun onRequestFocus(view: WebView?) {
        super.onRequestFocus(view)
    }

    override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
        super.onShowCustomView(view, callback)
    }

    override fun onShowFileChooser(
        webView: WebView?,
        filePathCallback: ValueCallback<Array<Uri>>?,
        fileChooserParams: FileChooserParams?
    ): Boolean {
        return super.onShowFileChooser(webView, filePathCallback, fileChooserParams)
    }

    @Deprecated("Deprecated in Java")
    @Suppress("DEPRECATION")
    override fun onConsoleMessage(message: String?, lineNumber: Int, sourceID: String?) {
        onError(JSMessage(message, lineNumber, sourceID))
        super.onConsoleMessage(message, lineNumber, sourceID)
    }

    @Deprecated("Deprecated in Java")
    @Suppress("DEPRECATION")
    override fun onExceededDatabaseQuota(
        url: String?,
        databaseIdentifier: String?,
        quota: Long,
        estimatedDatabaseSize: Long,
        totalQuota: Long,
        @Suppress("DEPRECATION") quotaUpdater: WebStorage.QuotaUpdater?
    ) {
        onError(ExceededDatabaseQuota())
        super.onExceededDatabaseQuota(
            url,
            databaseIdentifier,
            quota,
            estimatedDatabaseSize,
            totalQuota,
            quotaUpdater
        )
    }

    @Deprecated("Deprecated in Java")
    @Suppress("DEPRECATION")
    override fun onJsTimeout(): Boolean {
        onError(JSTimeoutException())
        return super.onJsTimeout()
    }

    @Deprecated("Deprecated in Java")
    @Suppress("DEPRECATION")
    override fun onShowCustomView(
        view: View?,
        requestedOrientation: Int,
        callback: CustomViewCallback?
    ) {
        super.onShowCustomView(view, requestedOrientation, callback)
    }
}