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

@SuppressLint("SetJavaScriptEnabled")
@TvPreview
@Composable
fun WebView(
    modifier: Modifier = Modifier,
    url: String = "about:blank",
    enableJavaScript: Boolean = true
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                background = ColorDrawable(0)
                settings.userAgentString =
                    "Mozilla/5.0 (Linux; Android 8.0; Pixel 2 Build/OPD3.170816.012)" +
                            " AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 " +
                            "Mobile Safari/537.36"
                webChromeClient = WebChromeClient().apply {
                    with(settings) {
                        javaScriptEnabled = enableJavaScript
                        allowContentAccess = true
                    }
                }
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        }
    )
}