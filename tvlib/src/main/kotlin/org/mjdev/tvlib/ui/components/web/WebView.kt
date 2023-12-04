/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.web

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Message
import android.util.AttributeSet
import android.view.WindowManager
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebSettings.LOAD_DEFAULT
import android.webkit.WebView
import android.webkit.WebView.WebViewTransport
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import timber.log.Timber


private const val USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) " +
        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36"

@SuppressLint("SetJavaScriptEnabled")
@Previews
@Composable
fun WebView(
    modifier: Modifier = Modifier,
    url: String = "about:blank",
    enableJavaScript: Boolean = true
) {
    val isEdit = isEditMode()

    AndroidView(
        modifier = modifier,
        factory = { context ->
            CustomWebView(
                context = context,
                inEditMode = isEdit
            )
        },
        update = { view -> view.loadUrl(url) }
    )
}

@SuppressLint("SetJavaScriptEnabled", "ViewConstructor")
class CustomWebView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    val inEditMode: Boolean = false
) : WebView(context, attrs, defStyleAttr) {
    private val cookieManager: CookieManager by lazy { CookieManager.getInstance() }

    init {
        background = ColorDrawable(0)
        if (!inEditMode) {
            cookieManager.setAcceptCookie(true)
            cookieManager.setAcceptThirdPartyCookies(this, true)
            webChromeClient = CustomChromeClient(context)
            webViewClient = WebViewClient()
            settings.cacheMode = LOAD_DEFAULT
            settings.domStorageEnabled = true
            settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
            settings.useWideViewPort = true
            settings.javaScriptEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.allowContentAccess = true
            settings.allowFileAccess = true
            settings.blockNetworkImage = false
            settings.blockNetworkLoads = false
            settings.builtInZoomControls = false
            settings.cacheMode = LOAD_DEFAULT
            settings.databaseEnabled = true
            settings.displayZoomControls = false
            settings.domStorageEnabled = true
            settings.useWideViewPort = true
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                settings.isAlgorithmicDarkeningAllowed = true
            }
            settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
            settings.loadWithOverviewMode = true
            settings.userAgentString = USER_AGENT
            settings.setSupportMultipleWindows(true)
            scrollBarStyle = SCROLLBARS_INSIDE_OVERLAY
        }
    }
}

class CustomChromeClient(
    val context: Context
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
        webViewPopUp.isVerticalScrollBarEnabled = false
        webViewPopUp.isHorizontalScrollBarEnabled = false
        webViewPopUp.webChromeClient = CustomChromeClient(context)
        webViewPopUp.settings.javaScriptEnabled = true
        webViewPopUp.settings.userAgentString = USER_AGENT
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
        val transport = resultMsg.obj as WebViewTransport
        transport.webView = webViewPopUp
        resultMsg.sendToTarget()
        return true
    }

    override fun onCloseWindow(window: WebView) {
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