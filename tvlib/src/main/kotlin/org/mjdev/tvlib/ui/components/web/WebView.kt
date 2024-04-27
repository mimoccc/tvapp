/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.web

import android.annotation.SuppressLint
import android.text.Layout.Directions
import android.view.KeyEvent
import android.view.View
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import org.mjdev.tvlib.annotations.Previews
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.webscrapper.base.CustomWebView

@SuppressLint("SetJavaScriptEnabled", "ClickableViewAccessibility")
@Previews
@Composable
fun WebView(
    modifier: Modifier = Modifier,
    url: String = "about:blank",
    userAgent: String = "Mozilla/5.0 (X11; Linux x86_64) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36",
    isEdit: Boolean = isEditMode(),
    onPageLoaded: () -> Unit = {}
) {
    Box(
        modifier = modifier
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                if (isEdit) {
                    FrameLayout(context)
                } else {
                    CustomWebView(
                        context = context,
                        userAgent = userAgent,
                        onPageLoadFinish = {
                            onPageLoaded()
                        }
                    )
                }
            },
            update = { view ->
                if (view is CustomWebView) {
                    view.loadUrl(url)
                }
            }
        )
    }
}
