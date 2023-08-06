/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.web

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import org.mjdev.tvlib.annotations.TvPreview
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode

@Suppress("LocalVariableName")
@SuppressLint("SetJavaScriptEnabled")
@TvPreview
@Composable
fun WebView(
    modifier: Modifier = Modifier,
    url: String = "about:blank",
    enableJavaScript: Boolean = true
) {
    val USER_AGENT =
        "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/115.0"
    val isEdit = isEditMode()
    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                background = ColorDrawable(0)
                if (!isEdit) {
                    webChromeClient = WebChromeClient().apply {
                        with(settings) {
                            javaScriptEnabled = enableJavaScript
                            allowContentAccess = true
                            userAgentString = USER_AGENT
                        }
                    }
                    webViewClient = WebViewClient()
                }
                loadUrl(url)
            }
        }
    )
}